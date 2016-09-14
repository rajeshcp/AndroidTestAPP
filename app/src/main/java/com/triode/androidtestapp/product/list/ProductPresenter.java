package com.triode.androidtestapp.product.list;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.api.API;
import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.CoreEvent;
import com.triode.androidtestapp.core.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by triode on 14/9/16.
 */
public class ProductPresenter<V extends ProductListView> extends BasePresenter<V> {

    public static final String PRODUCTS_FETCHED_EVENT = "products_fetched_event";

    Observable<List<ProductViewCompatVO>> observable;
    Subscription subscription;

    /**
     * function get the products from the local DB
     *
     * @param skip the number of records to be skipped
     * @param limit the max number of items to be loaded
     */
    void loadProducts(final int skip, final int limit){
        if(observable != null)
            return;
        observable = API.getProductFromDB(skip, limit);
        subscription = observable.subscribe(new Subscriber<List<ProductViewCompatVO>>() {
            @Override
            public void onCompleted() {
                unsubscribe();
                subscription = null;
                observable = null;
            }

            @Override
            public void onError(Throwable e) {
                unsubscribe();
                subscription = null;
                observable = null;
            }

            @Override
            public void onNext(List<ProductViewCompatVO> list) {
                final Bundle data = new Bundle();
                data.putParcelableArrayList(Constants.DATA,
                        (ArrayList<? extends Parcelable>) list);
                AppModule.getModule().dispatchEvent(new
                        CoreEvent(PRODUCTS_FETCHED_EVENT, data));
            }
        });
    }

    /**
     * function which will be called {@link Activity#onDestroy()} or
     * {@link Fragment#onDestroy()}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(subscription != null){
            if(!subscription.isUnsubscribed()){
                subscription.unsubscribe();
            }
            subscription = null;
        }
        observable = null;
    }
}
