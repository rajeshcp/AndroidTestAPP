package com.triode.androidtestapp.product.splash;

import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.R;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.CoreEvent;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.core.mvp.ViewStateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by triode on 13/9/16.
 */
public class SplashView extends ViewStateView<ViewState> {

    public static final String RETRY_CLICK_EVENT = "RETRY_CLICK_EVENT";

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.progress_text)
    AppCompatTextView mLoadingTextView;

    @BindView(R.id.retry_button)
    AppCompatTextView mRetryButton;

    Unbinder unbinder;

    @OnClick(R.id.retry_button)
    void onRetryClick(){
        AppModule.getModule().dispatchEvent(new CoreEvent(RETRY_CLICK_EVENT, null));
    }

    /**
     * Create the view from the id provided
     *
     * @param inflater inflater using which the view shold be inflated
     * @param parent   to which the view to be attached
     */
    @Override
    public void init(LayoutInflater inflater, ViewGroup parent) {
        mView = inflater.inflate(R.layout.splash_fragment_layout, parent, false);
        unbinder = ButterKnife.bind(this, mView);
    }

    /**
     * function which will be called after all the view creation completes
     */
    @Override
    public void onViewComplete() {
        if(mViewState == null){
            mViewState = new ViewState();
        }
    }

    /**
     * To handle the back press
     *
     * @return false if not handled true if handled
     */
    @Override
    public boolean onBackPressed() {
        return false;
    }

    /**
     * function called on destroy of the view
     */
    @Override
    public void onDestroy() {
        unbinder.unbind();
        unbinder = null;
        mView = null;
    }

    /**
     * function restore the state of the view
     *
     * @param viewState the view state saved
     */
    @Override
    public void restoreViewState(ViewState viewState) {
        mViewState = viewState;
        restoreView();
    }

    /**
     * function save the sate of the view and return it
     *
     * @return the {@code ViewState} of the enclosing view of the
     * fragment
     */
    @Override
    public ViewState saveViewState() {
        return mViewState;
    }

    ViewState mViewState;
    /**
     * function restore the view
     */
    void restoreView() {
        switch (mViewState.getViewState()) {
            case ViewState.VIEW_STATE_LOADING: {
                showLoading();
                break;
            }
            case ViewState.VIEW_STATE_NETWORK_ERROR: {
                showError(Constants.ErrorCodes.NETWORK_ERROR);
                break;
            }
            case ViewState.VIEW_STATE_SERVER_ERROR: {
                showError(Constants.ErrorCodes.SERVER_ERROR);
                break;
            }
            case ViewState.VIEW_STATE_COMPLETE:{
                mView.setVisibility(View.GONE);
                break;
            }
        }
    }


    /**
     * Function to the loading view
     */
    public void showLoading() {
        mView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        mLoadingTextView.setText("Fetching the product list....");
        mRetryButton.setVisibility(View.GONE);
        mViewState.setViewState(ViewState.VIEW_STATE_LOADING);
    }

    /**
     * function hide the loading screen
     */
    public void hideLoading() {
        mView.setVisibility(View.GONE);
    }

    /**
     * functions shows the error if the loading fails
     *
     * @param error
     */
    public void showError(Constants.ErrorCodes error) {
        if (error == Constants.ErrorCodes.NETWORK_ERROR) {
            mViewState.setViewState(ViewState.VIEW_STATE_NETWORK_ERROR);
            mLoadingTextView.setText("Make sure you are connected to the internet");
            mRetryButton.setVisibility(View.VISIBLE);
        } else if (Constants.ErrorCodes.SERVER_ERROR == error) {
            mViewState.setViewState(ViewState.VIEW_STATE_SERVER_ERROR);
            mLoadingTextView.setText("Unable to fetch the result, please try again ");
            mRetryButton.setVisibility(View.VISIBLE);
        }
        mView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
}
