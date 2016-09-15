package com.triode.androidtestapp.product.list;

import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.triode.androidtestapp.R;
import com.triode.androidtestapp.core.mvp.ViewState;
import com.triode.androidtestapp.core.mvp.ViewStateView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by triode on 14/9/16.
 */
public class ProductListView extends ViewStateView<ViewState> {

    @BindView(R.id.product_list)
    RecyclerView recyclerView;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private View view;

    Unbinder unbinder;

    /**
     * Return the enclosing view
     *
     * @return return the enclosing view
     */
    @Override
    public View getView() {
        return view;
    }

    /**
     * Create the view from the id provided
     *
     * @param inflater inflater using which the view shold be inflated
     * @param parent   to which the view to be attached
     */
    @Override
    public void init(LayoutInflater inflater, ViewGroup parent) {
        view = inflater.inflate(R.layout.products_list_layout, parent, false);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager llm;
        if(inflater.getContext().getResources().
                getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            llm = new LinearLayoutManager(recyclerView.getContext());
        }else {
            llm = new GridLayoutManager(recyclerView.getContext(), 2);
        }
        llm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
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
        recyclerView = null;
    }

    ViewState viewState;
    /**
     * function which will be called after all the view creation completes
     */
    @Override
    public void onViewComplete() {
        if(viewState == null){
            viewState = new ViewState();
        }
    }

    /**
     * function restore the state of the view
     *
     * @param viewState the view state saved
     */
    @Override
    public void restoreViewState(ViewState viewState) {
        this.viewState = viewState;
    }

    /**
     * function save the sate of the view and return it
     *
     * @return the {@code ViewState} of the enclosing view of the
     * fragment
     */
    @Override
    public ViewState saveViewState() {
        return viewState;
    }

    public void showProgress(){
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress(){
        progressBar.setVisibility(View.GONE);
    }
}
