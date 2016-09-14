package com.triode.androidtestapp.product.splash;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.api.API;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.CoreEvent;
import com.triode.androidtestapp.core.mvp.BasePresenter;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by triode on 13/9/16.
 */
public class SplashPresenter<V extends SplashView> extends BasePresenter<V>{

    private Observable<Boolean> mProductUpdateObservable;
    private Subscription mProductSubscription;

    public static final String PRODUCT_UPDATE_COMPLETE = "PRODUCT_UPDATE_COMPLETE";
    public static final String PRODUCT_UPDATE_ERROR = "PRODUCT_UPDATE_ERROR";


    /**
     * Constructs a new instance of {@code Object}.
     */
    public SplashPresenter() {
        super();
        mProductUpdateObservable = API.updateProductList();
    }

    /**
     *
     */
    public void initFetch(){
        mProductSubscription = mProductUpdateObservable.subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                unsubscribe();
            }

            @Override
            public void onError(Throwable e) {
                unsubscribe();
                if(e.getCause() instanceof UnknownHostException || e.getCause() instanceof
                        SocketTimeoutException){
                    dispatchError(Constants.ErrorCodes.NETWORK_ERROR);
                }else {
                    dispatchError(Constants.ErrorCodes.SERVER_ERROR);
                }
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if(aBoolean) {
                    dispatchLoadEvent();
                }else {
                    dispatchError(Constants.ErrorCodes.SERVER_ERROR);
                }
            }
        });
    }

    /**
     * function dispatch the error
     */
    void dispatchError(Constants.ErrorCodes code){
        final ErrorEvent errorEvent = new ErrorEvent(PRODUCT_UPDATE_ERROR, code);
        AppModule.getModule().dispatchEvent(errorEvent);
    }

    /**
     * function notify the load status
     */
    void dispatchLoadEvent(){
        final CoreEvent event = new CoreEvent(PRODUCT_UPDATE_COMPLETE, null);
        AppModule.getModule().dispatchEvent(event);
    }

    /**
     * function which will be called {@link Activity#onDestroy()} or
     * {@link Fragment#onDestroy()}
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mProductSubscription != null){
            if(!mProductSubscription.isUnsubscribed()){
                mProductSubscription.unsubscribe();
            }
            mProductSubscription = null;
        }
        mProductUpdateObservable = null;
    }
}
