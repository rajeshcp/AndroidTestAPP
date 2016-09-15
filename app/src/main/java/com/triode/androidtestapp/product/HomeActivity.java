package com.triode.androidtestapp.product;

import android.support.v7.widget.Toolbar;

import com.triode.androidtestapp.R;
import com.triode.androidtestapp.core.activities.MVActivity;
import com.triode.androidtestapp.core.fragmentstack.FragmentTransaction;
import com.triode.androidtestapp.product.splash.SplashFragment;

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
        //toolbar.setLogo(R.drawable.ic_reorder_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(!isRestored){
            getSupportActionBar().hide();
            showSpalshScreen();
        }
    }

    /**
     * function show the splash screen
     */
    private void showSpalshScreen(){
        final FragmentTransaction transaction = new FragmentTransaction();
        transaction.isRoot = true;
        transaction.mInAnimation = android.support.design.R.anim.abc_grow_fade_in_from_bottom;
        transaction.mOutAnimation = android.support.design.R.anim.abc_slide_out_top;
        transaction.mFrameId = R.id.container_view;
        transaction.mFragmentClass = SplashFragment.class;
        getmFragmentManager().push(transaction);
    }
}
