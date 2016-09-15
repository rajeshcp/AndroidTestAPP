package com.triode.androidtestapp.product.list;

import android.os.Bundle;

import com.triode.androidtestapp.R;
import com.triode.androidtestapp.api.ProductDBHrlper;
import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.CoreEvent;
import com.triode.androidtestapp.core.events.Event;
import com.triode.androidtestapp.core.events.EventListener;
import com.triode.androidtestapp.core.fragments.FragmentViewStateFragment;
import com.triode.androidtestapp.core.fragmentstack.FragmentTransaction;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.product.Utils;
import com.triode.androidtestapp.product.details.ProductDetailsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by triode on 14/9/16.
 */
public class ProductListFragment extends FragmentViewStateFragment<ViewState, ProductListView,
        ProductPresenter<ProductListView>, ProductListFragmentState> implements EventListener{
    EventHelper eventHelper = new EventHelper();

    private ProductListFragmentState fragmentState;
    /**
     * function needs to be override to save the fragment state
     *
     * @param state
     */
    @Override
    protected void restoreFragmentState(ProductListFragmentState state) {
        fragmentState = state;
    }

    /**
     * function needs to be override to save the fragment state
     */
    @Override
    protected ProductListFragmentState saveFragmentState() {
        return fragmentState;
    }

    /**
     * @see #getPresenterClass()
     */
    @Override
    protected Class getPresenterClass() {
        return ProductPresenter.class;
    }

    /**
     * function called right after the creation of Presenter
     */
    @Override
    protected void onCreatePresenter() {
        eventHelper.addGlobalEvents(this);
        if(fragmentState == null){
            fragmentState = new ProductListFragmentState(new ArrayList<ProductViewCompatVO>());
            mPresenter.loadProducts(fragmentState.getProductList().size(), ITEMS_PER_PAGE);
        }
        createAdapter();
        setAdapter();
    }

    ProductsAdapter mAdapter;
    void createAdapter(){
        mAdapter = new ProductsAdapter(getActivity().getLayoutInflater());
    }

    void setAdapter(){
        mAdapter.setmDataprovider(fragmentState.getProductList());
        mView.recyclerView.setAdapter(mAdapter);
    }

    /**
     * @see #onDestroy()
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        eventHelper.removeGlobalEvents(this);
    }

    /**
     * @see #getViewClass()
     */
    @Override
    protected Class<ProductListView> getViewClass() {
        return ProductListView.class;
    }

    /**
     * @see #onBackPressed()
     */
    @Override
    public boolean onBackpressed() {
        return false;
    }

    /**
     * @see #onBindView()
     */
    @Override
    protected void onBindView() {
        getAtomicActivity().getSupportActionBar().show();
        eventHelper.addViewEvents(this);
    }

    /**
     * @see #restoreViewState(ViewState)
     */
    @Override
    public void restoreViewState(ViewState viewState) {
        super.restoreViewState(viewState);
        setAdapter();
        eventHelper.addViewEvents(this);
    }

    /**
     * @see #onDestroyView()
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        eventHelper.removeViewEvents(this);
    }

    static final int ITEMS_PER_PAGE = 50;
    private void handleProductLoad(final List<ProductViewCompatVO> list){
        if(list == null || list.isEmpty()){
            fragmentState.setPageLoadComplete(true);
        }else {
            fragmentState.getProductList().addAll(list);
            mAdapter.notifyDataSetChanged();
            if(list.size() < ITEMS_PER_PAGE){
                fragmentState.setPageLoadComplete(true);
            }
        }
    }

    /**
     * @see EventListener#onEvent(Event)
     */
    @Override
    public void onEvent(Event event) {
        if(event.getType().equals(ProductPresenter.PRODUCTS_FETCHED_EVENT)){
            mView.hideProgress();
            final List<ProductViewCompatVO> list = ((CoreEvent)event).getmExtra().
                    getParcelableArrayList(Constants.DATA);
            handleProductLoad(list);
        }else if(event.getType().equals(ProductsAdapter.LAST_ITEM_REACHED)){
            if(!fragmentState.isPageLoadComplete()) {
                mPresenter.loadProducts(fragmentState.getProductList().size(),
                        ITEMS_PER_PAGE);
                mView.showProgress();
            }
        }else if(event.getType().equals(ProductsAdapter.FAVORITE_EVENT)){
            handleFavoriteClick(((CoreEvent)event).getmExtra().getInt(Constants.DATA));
        }else if(event.getType().equals(ProductsAdapter.SHARE_EVENT)){
            shareApplication(((CoreEvent)event).getmExtra().getInt(Constants.DATA));
        }else {
            showProductDetails(((CoreEvent)event).getmExtra().getInt(Constants.DATA));
        }
    }

    /**
     * function show the product details page
     *
     * @param position the selected item position
     */
    void showProductDetails(final int position){
        final ProductViewCompatVO vo = mAdapter.get(position);
        final Bundle data = new Bundle();
        data.putParcelable(Constants.DATA, vo);
        final FragmentTransaction transaction = new FragmentTransaction();
        transaction.mFrameId = R.id.container_view;
        transaction.mParameters = data;
        transaction.mInAnimation = android.support.design.R.anim.abc_slide_in_bottom;
        transaction.mOutAnimation = android.support.design.R.anim.abc_fade_out;
        transaction.mFragmentClass = ProductDetailsFragment.class;
        getAtomicActivity().getmFragmentManager().push(transaction);
    }

    /**
     * function toggle the favorite status of the record
     *
     * @param position item position
     */
    void handleFavoriteClick(final int position){
        final ProductViewCompatVO vo = mAdapter.get(position);
        if(ProductDBHrlper.toggleFavorite(vo)){
            mAdapter.notifyItemChanged(position);
        }
    }

    /**
     * function initiate the share intent to share the
     * app
     */
    void shareApplication(int position) {
        final ProductViewCompatVO vo = mAdapter.get(position);
        Utils.shareProduct(vo, this);
    }
}
