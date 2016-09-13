package com.triode.androidtestapp.core.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.triode.androidtestapp.core.mvp.IPresenter;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.core.mvp.ViewStateView;

/**
 * Created by triode on 12/9/16.
 */
public abstract class FragmentViewStateFragment<VS extends ViewState, V extends ViewStateView<VS>,
        P extends IPresenter<V>, FS extends FragmentState> extends ViewStateFragment<V, P, VS>{

    /**
     * @see {@link ViewStateFragment#onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        restoreFromFragmentState(savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * function try to restore the saved fragment state
     *
     * @param savedInstanceState the saved instance state
     * @return
     */
    private boolean restoreFromFragmentState(@Nullable Bundle savedInstanceState){
        if(savedInstanceState != null && savedInstanceState.containsKey(FRAGMENT_STATE)){
            final FS mFragmentState = savedInstanceState.getParcelable(FRAGMENT_STATE);
            restoreFragmentState(mFragmentState);
            return true;
        }
        return false;
    }

    /**
     * to save the fragment state
     *
     * @param outState
     */
    @Override
    public final void onSaveInstanceState(Bundle outState) {
        final FS state = saveFragmentState();
        if(state != null)
            outState.putParcelable(FRAGMENT_STATE, state);
        super.onSaveInstanceState(outState);
    }

    /**
     * function needs to be override to save the fragment state
     *
     */
    protected abstract FS saveFragmentState();

    /**
     * function needs to be override to save the fragment state
     *
     */
    protected abstract void restoreFragmentState(FS state);


}
