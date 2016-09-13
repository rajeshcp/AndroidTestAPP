package com.triode.androidtestapp.core.mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by triode on 8/9/16
 */
public interface IView {

    /**
     * Create the view from the id provided
     * @param inflater inflater using which the view shold be inflated
     * @param parent to which the view to be attached
     */
    void init(LayoutInflater inflater, ViewGroup parent);

    /**
     * Return the enclosing view
     * @return return the enclosing view
     */
    View getView();

    /**
     * To handle the back press
     * @return false if not handled true if handled
     */
    boolean onBackPressed();

    /**
     * function called on destroy of the view
     */
    void onDestroy();

}
