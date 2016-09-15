package com.triode.androidtestapp.api;

import com.triode.androidtestapp.AppLocalStore;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.business.BusinessImplementation;
import com.triode.androidtestapp.core.business.RequestPayload;
import com.triode.androidtestapp.core.orm.Exceptions;
import com.triode.androidtestapp.core.orm.Query;
import com.triode.androidtestapp.core.utils.CoreLogger;

import java.util.List;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by triode on 12/9/16.
 */
public class ProductLoader {

    public static final String PRODUCT_END_POINT = "https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json";

    /**
     * function creates an observable which fetch the product from
     * server if the local cache is expired
     *
     * @return Observable<Boolean> which emits a boolean value
     */
    public static final Observable<Boolean> updateProductList(){
        return Observable.create(
                new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> sub) {
                        try {
                            sub.onNext(updateProductsIfRequired());
                            sub.onCompleted();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).retryWhen(new RetryWhenObservable(5))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * function check the last updated time of the request
     * if it crosses 24 hours or no cache exist it get a fresh
     * copy from server
     *
     * @return true if success full
     */
    private static boolean updateProductsIfRequired() throws Exception{
        if(isCacheExpired()){
            final Response response = BusinessImplementation.
                    acquire().getResponse(getRequestPayload());
            boolean result = false;
            if (response.code() == 200) {
                final List<ProductVO> productVOList =
                        GSONHelper.parseProductVO(response.body().byteStream());
                result = true;
                final ProductDBHrlper productDBHelper = new ProductDBHrlper();
                productDBHelper.deleteAll();
                productDBHelper.saveAll(productVOList);
                productVOList.clear();
                AppLocalStore.put(PRODUCT_END_POINT, System.currentTimeMillis() + "");
            } else {
                final String serverResponse = response.body().toString();
                CoreLogger.log("Server ", serverResponse);
            }
            response.body().close();
            return result;
        }else {
            return true;
        }
    }

    /**
     * function check the token is expired or not
     *
     * @return boolean true if expired otherwise false
     */
    public static final boolean isCacheExpired() {
        try {
            final long lastUpdated = Long.parseLong(AppLocalStore.getString
                    (PRODUCT_END_POINT, "x"));
            return (System.currentTimeMillis() - lastUpdated) > (24 * 60 * 60 * 1000);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * function return the payload for the request
     *
     * @return the created instance of payload
     */
    private static RequestPayload getRequestPayload(){
        final RequestPayload payload = new RequestPayload(PRODUCT_END_POINT);
        return payload;
    }


    /**
     * function returns an {@code Observable<List<ProductViewCompatVO>>} which emits
     * list of {@link ProductViewCompatVO} which is obtained from the db
     *
     * @return the created Observable
     */
    public static Observable<List<ProductViewCompatVO>> getProductFromDB(final int skip,
                                                                         final int itemPerPage){
        return Observable.create(
                new Observable.OnSubscribe<List<ProductViewCompatVO>>() {
                    @Override
                    public void call(Subscriber<? super List<ProductViewCompatVO>> sub) {
                        try {
                            sub.onNext(getProducts(skip, itemPerPage));
                            sub.onCompleted();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    /**
     * fetch the product from DB
     */
    public static List<ProductViewCompatVO> getProducts(final int skip, final int itemsCount)
            throws Exceptions.QueryException{
        final Query query = new Query(ProductDBHrlper.NAME);
        query.setMselect(new String[]{ProductDBHrlper.IMAGE, ProductDBHrlper.DESCRIPTION,
                ProductDBHrlper.IS_READ, ProductDBHrlper.IS_FAVORITE,
                ProductDBHrlper.TITLE
        });
        query.whereNotEquals(ProductDBHrlper.TITLE, "");
        query.setLimit(skip, itemsCount, false);
        final Object result = query.query(ProductViewCompatVO.class);
        return (List<ProductViewCompatVO>) result;
    }


}
