package com.triode.androidtestapp.product.details;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.R;
import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.events.CoreEvent;
import com.triode.androidtestapp.core.mvp.IView;
import com.triode.androidtestapp.product.list.ProductsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by triode on 15/9/16.
 */
public class ProductDetailsView implements IView {

    @BindView(R.id.product_image)
    AppCompatImageView imageView;

    @BindView(R.id.product_title)
    AppCompatTextView title;

    @BindView(R.id.product_description)
    AppCompatTextView description;

    @BindView(R.id.favorite_button)
    AppCompatTextView favoriteButton;

    @OnClick(R.id.favorite_button)
    void onFavoriteClick(){
        AppModule.getModule().dispatchEvent(new CoreEvent(ProductsAdapter.FAVORITE_EVENT, null));
    }

    @OnClick(R.id.share_button)
    void onShareClick(){
        AppModule.getModule().dispatchEvent(new CoreEvent(ProductsAdapter.SHARE_EVENT, null));
    }
    Unbinder unbinder;
    View mView;
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
     * Create the view from the id provided
     *
     * @param inflater inflater using which the view shold be inflated
     * @param parent   to which the view to be attached
     */
    @Override
    public void init(LayoutInflater inflater, ViewGroup parent) {
        mView = inflater.inflate(R.layout.product_details_layout, parent, false);
        unbinder = ButterKnife.bind(this, mView);
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
     * function update the product details
     *
     * @param item the product details object
     */
    void updateView(final ProductViewCompatVO item){
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        title.setTypeface(null, item.getTitleTypeFace());
        updateFavorite(item.getFavoriteColor());
        Picasso.with(mView.getContext()).
                load(item.getImage()).into(imageView);
    }

    /**
     * function update the favorite drawable
     *
     * @param drawable
     */
    void updateFavorite(int drawable){
        favoriteButton.setCompoundDrawablesWithIntrinsicBounds(drawable,
                0, 0, 0);
    }

    /**
     * function called on destroy of the view
     */
    @Override
    public void onDestroy() {
        mView = null;
        unbinder.unbind();
    }
}
