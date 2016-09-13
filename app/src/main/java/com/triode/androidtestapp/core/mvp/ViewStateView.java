package com.triode.androidtestapp.core.mvp;

import android.view.View;
import android.widget.Toast;

/**
 * Created by triode on 8/9/16
 */
public abstract class ViewStateView<VS extends ViewState>
        implements IView, IViewState<VS> {

    protected View mView;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public ViewStateView() {
        super();
    }

    /**
     * Return the enclosing view
     *
     * @return return the enclosing view
     */
    @Override
    public View getView() {
        return mView;
    }

    /**
     * function which will be called after all the view creation completes
     */
    public abstract void onViewComplete();

    public void showToast(final String message) {
        Toast.makeText(mView.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
