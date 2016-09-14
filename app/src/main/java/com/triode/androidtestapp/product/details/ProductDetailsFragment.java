package com.triode.androidtestapp.product.details;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.api.ProductDBHrlper;
import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.Event;
import com.triode.androidtestapp.core.events.EventListener;
import com.triode.androidtestapp.core.fragments.MVFragment;
import com.triode.androidtestapp.product.Utils;
import com.triode.androidtestapp.product.list.ProductsAdapter;

/**
 * Created by triode on 15/9/16.
 */
public class ProductDetailsFragment extends MVFragment<ProductDetailsView>
implements EventListener{

    /**
     * Extending fragment should override this to provide the
     * enclosing view class
     *
     * @return the enclosing view class
     */
    @Override
    protected Class<ProductDetailsView> getViewClass() {
        return ProductDetailsView.class;
    }

    ProductViewCompatVO product;
    /**
     * Function needs to override by the child classes to load
     * the contents and add the listeners
     */
    @Override
    protected void onBindView() {
        product = getArguments().getParcelable(Constants.DATA);
        if(product.getIsRead() == 0){
            ProductDBHrlper.markAsRead(product);
        }
        mView.updateView(product);
        AppModule.getModule().addEventListener(ProductsAdapter.FAVORITE_EVENT, this);
        AppModule.getModule().addEventListener(ProductsAdapter.SHARE_EVENT, this);
    }

    /**
     * @see #onDestroyView()
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppModule.getModule().removeEventListener(ProductsAdapter.FAVORITE_EVENT, this);
        AppModule.getModule().removeEventListener(ProductsAdapter.SHARE_EVENT, this);
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
     * @see EventListener#onEvent(Event)
     */
    @Override
    public void onEvent(Event event) {
        if(event.getType().equals(ProductsAdapter.FAVORITE_EVENT)){
            if(ProductDBHrlper.toggleFavorite(product)){
                mView.updateFavorite(product.getFavoriteColor());
            }
        }else {
            Utils.shareProduct(product, this);
        }
    }
}
