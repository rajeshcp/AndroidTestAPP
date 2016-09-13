package com.triode.androidtestapp.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triode.androidtestapp.core.mvp.IPresenter;
import com.triode.androidtestapp.core.mvp.IView;


/**
 * Created by Triode on 9/12/2016.
 */
public abstract class MVPFragment<V extends IView, P extends IPresenter<V>> extends
        MVFragment<V> {



    /**
     * Holds the presenter instance
     */
    protected P mPresenter;


    /**
     * Extending fragments should override this and provide the proper presenter
     * class
     * @return the Class type of the presenter
     */
    protected abstract Class<P> getPresenterClass();




    /**
     * @see {@link AtomicFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        try{
           createAndInitializePresenter();
            onCreatePresenter();
        }catch (java.lang.InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return mView.getView();
    }


    /**
     * function create and initialize the presenter
     *
     * @throws java.lang.InstantiationException
     * @throws IllegalAccessException
     */
    protected final void createAndInitializePresenter()
            throws java.lang.InstantiationException, IllegalAccessException {
        mPresenter = getPresenterClass().newInstance();
        mPresenter.takeView(mView);
    }


    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        mPresenter.releaseView();
        super.onDestroyView();
    }

    /**
     * function called right after the creation of Presenter
     */
    protected abstract void onCreatePresenter();


    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        mPresenter.onDestroy();
        mPresenter = null;
        super.onDestroy();
    }


}
