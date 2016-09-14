package com.triode.androidtestapp.product.splash;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.R;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.Event;
import com.triode.androidtestapp.core.events.EventListener;
import com.triode.androidtestapp.core.fragments.ViewStateFragment;
import com.triode.androidtestapp.core.fragmentstack.FragmentTransaction;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.product.list.ProductListFragment;

/**
 * Created by triode on 13/9/16.
 */
public class SplashFragment<V extends SplashView, P extends SplashPresenter<V>> extends
        ViewStateFragment<V, P, ViewState> implements EventListener{

    /**
     * function called right after the creation of Presenter
     */
    @Override
    protected void onCreatePresenter() {
        AppModule.getModule().addEventListener(SplashPresenter.PRODUCT_UPDATE_ERROR, this);
        AppModule.getModule().addEventListener(SplashPresenter.PRODUCT_UPDATE_COMPLETE, this);
        if(mView.mViewState.getViewState() == ViewState.VIEW_STATE_LOADING){
            mView.showLoading();
            mPresenter.initFetch();
        }
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        AppModule.getModule().removeEventListener(SplashPresenter.PRODUCT_UPDATE_ERROR, this);
        AppModule.getModule().removeEventListener(SplashPresenter.PRODUCT_UPDATE_COMPLETE, this);
    }

    /**
     * Extending fragment should override this to provide the
     * enclosing view class
     *
     * @return the enclosing view class
     */
    @Override
    protected Class getViewClass() {
        return SplashView.class;
    }

    /**
     * this allow the fragment to listen for the device back
     * press
     *
     * @return true if handled else false
     */
    @Override
    public boolean onBackpressed() {
        return false;
    }

    /**
     * Function needs to override by the child classes to load
     * the contents and add the listeners
     */
    @Override
    protected void onBindView() {
        AppModule.getModule().addEventListener(SplashView.RETRY_CLICK_EVENT, this);
    }

    /**
     * @see #onDestroyView()
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppModule.getModule().removeEventListener(SplashView.RETRY_CLICK_EVENT, this);
    }

    /**
     * function restore the state of the view
     *
     * @param viewState the view state saved
     */
    @Override
    public void restoreViewState(ViewState viewState) {
        super.restoreViewState(viewState);
        AppModule.getModule().addEventListener(SplashView.RETRY_CLICK_EVENT, this);
    }

    /**
     * Extending fragments should override this and provide the proper presenter
     * class
     *
     * @return the Class type of the presenter
     */
    @Override
    protected Class getPresenterClass() {
        return SplashPresenter.class;
    }

    /**
     * @see EventListener#onEvent(Event)
     */
    @Override
    public void onEvent(Event event) {
        if(event.getType().equals(SplashView.RETRY_CLICK_EVENT)){
            mView.showLoading();
            mPresenter.initFetch();
        }else if(event.getType().equals(SplashPresenter.PRODUCT_UPDATE_COMPLETE)){
            loadProductsListFragment();
        }else {
            handleProductLoadError(((ErrorEvent)event));
        }
    }

    /**
     * function launch the product list fragment
     */
    void loadProductsListFragment(){
        if(mView == null){
            getSavedViewState().setViewState(ViewState.VIEW_STATE_COMPLETE);
        }
        final FragmentTransaction transaction = new FragmentTransaction();
        transaction.isRoot = true;
        transaction.mFrameId = R.id.container_view;
        transaction.mFragmentClass = ProductListFragment.class;
        getAtomicActivity().getmFragmentManager().push(transaction);
    }

    /**
     * function handles the load error event
     *
     * @param event event delegated
     */
    void handleProductLoadError(ErrorEvent event){
        if(mView == null){
            getSavedViewState().setViewState(event.errorCode ==
                    Constants.ErrorCodes.NETWORK_ERROR ? ViewState.VIEW_STATE_NETWORK_ERROR :
            ViewState.VIEW_STATE_SERVER_ERROR);
        }else {
            mView.showError(event.getErrorCode());
        }
    }
}
