package com.triode.androidtestapp.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;

import com.triode.androidtestapp.core.mvp.IView;

/**
 * Created by triode on 13/9/16.
 */
public abstract class MVActivity<V extends IView> extends AtomicActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /**
     * Holds the view instance
     */
    protected V mView;


    /**
     * Function needs to override by the child classes to load
     * the contents and add the listeners
     *
     */
    protected abstract void onBindView();



    /**
     * Extending fragment should override this to provide the
     * enclosing view class
     *
     * @return the enclosing view class
     */
    protected abstract Class<V> getViewClass();

    /**
     * function create and initialize the enclosing view
     *
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    protected final void initializeView() throws InstantiationException, IllegalAccessException{
        mView = getViewClass().newInstance();
        mView.init(getLayoutInflater(), null);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            initializeView();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        setContentView(mView.getView());
        onBindView();
    }

    /**
     * Called when the main window associated with the activity has been
     * attached to the window manager.
     */
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    /**
     * function called when activity detached from window manager
     */
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mView.onDestroy();
        mView = null;
    }


    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        if(!mView.onBackPressed()) {
            super.onBackPressed();
        }
    }

}
