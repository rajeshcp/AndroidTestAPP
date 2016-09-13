package com.triode.androidtestapp.product;

import android.support.v7.widget.Toolbar;

import com.triode.androidtestapp.R;
import com.triode.androidtestapp.core.activities.MVActivity;

/**
 * Created by triode on 13/9/16.
 */
public class HomeActivity extends MVActivity<HomeView> {

    /**
     * Extending fragment should override this to provide the
     * enclosing view class
     *
     * @return the enclosing view class
     */
    @Override
    protected Class<HomeView> getViewClass() {
        return HomeView.class;
    }

    /**
     * Function needs to override by the child classes to load
     * the contents and add the listeners
     */
    @Override
    protected void onBindView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_reorder_black_24dp);
        setSupportActionBar(toolbar);
    }
}
