package com.triode.androidtestapp.core.mvp;

import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;


/**
 * Created by triode on 8/9/16
 */
public class BasePresenter<V extends IView> implements IPresenter<V> {


    /**
     * Holds the view reference
     */
    protected WeakReference<V> mViewRef;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public BasePresenter() {
        super();
    }

    /**
     * function to be called to set the view
     *
     * @param view the view associated with the presenter
     */
    @Override
    public void takeView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    /**
     * function release the view from the presenter there by blocking
     * all updates to the view. Possible call from
     * {@link android.app.Activity#onDetachedFromWindow()} or
     * {@link android.support.v4.app.Fragment#onDestroyView()}
     */
    @Override
    public void releaseView() {
        if(mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    /**
     * function which will be called {@link android.app.Activity#onDestroy()} or
     * {@link android.support.v4.app.Fragment#onDestroy()}
     */
    @Override
    public void onDestroy() {

    }

    /**
     * function returns the enclosing {@code V} instance
     *
     * @return {@code V} instance
     */
    @UiThread
    public V getView(){
        return mViewRef == null ? null : mViewRef.get();
    }

    /**
     * function returns if the presenter has an active view
     * Highly sensitive method when it comes to updating the view
     *
     * @return true if the {@code mViewRef} is not NULL otherwise flase
     */
    @UiThread
    public boolean isReleased(){
        return (mViewRef == null || mViewRef.get() == null);
    }
}
