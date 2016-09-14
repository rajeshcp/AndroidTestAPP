package com.triode.androidtestapp.product.list;

import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.core.events.EventListener;

/**
 * Created by triode on 14/9/16.
 */
public class EventHelper implements com.triode.androidtestapp.core.events.EventHelper {

    @Override
    public void addGlobalEvents(EventListener listener) {
        AppModule.getModule().addEventListener(ProductPresenter.PRODUCTS_FETCHED_EVENT,
                listener);
    }

    @Override
    public void addViewEvents(EventListener listener) {
        AppModule.getModule().addEventListener(ProductsAdapter.LAST_ITEM_REACHED,
                listener);
        AppModule.getModule().addEventListener(ProductsAdapter.FAVORITE_EVENT,
                listener);
        AppModule.getModule().addEventListener(ProductsAdapter.SHARE_EVENT,
                listener);
        AppModule.getModule().addEventListener(ProductsAdapter.ITEM_CLICK_EVENT,
                listener);
    }


    @Override
    public void removeGlobalEvents(EventListener listener) {
        AppModule.getModule().removeEventListener(ProductPresenter.PRODUCTS_FETCHED_EVENT,
                listener);
    }

    @Override
    public void removeViewEvents(EventListener listener) {
        AppModule.getModule().removeEventListener(ProductsAdapter.LAST_ITEM_REACHED,
                listener);
        AppModule.getModule().removeEventListener(ProductsAdapter.FAVORITE_EVENT,
                listener);
        AppModule.getModule().removeEventListener(ProductsAdapter.SHARE_EVENT,
                listener);
        AppModule.getModule().removeEventListener(ProductsAdapter.ITEM_CLICK_EVENT,
                listener);
    }
}
