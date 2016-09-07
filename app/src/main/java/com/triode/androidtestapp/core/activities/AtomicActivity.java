package com.triode.androidtestapp.core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.triode.androidtestapp.core.fragmentstack.FragmentManager;

/**
 * Created by triode on 8/9/16.
 */
public class AtomicActivity extends AppCompatActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = new FragmentManager(getSupportFragmentManager());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * getter function for mFragmentManager
     *
     * @return FragmentManager instance associated with the AtomicActivity
     */
    protected final FragmentManager getmFragmentManager(){
        return mFragmentManager;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFragmentManager.dumb();
        mFragmentManager = null;
    }
}
