package com.triode.androidtestapp.core.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.triode.androidtestapp.core.activities.AtomicActivity;


/**
 * Created by triode on 8/9/16.
 */
public class AtomicFragment extends Fragment {

    /**
     * function returns the activity associated with the Fragment
     *
     * @return AppCompatActivity instance
     */
    protected AppCompatActivity getAtomicActivity(){
        return ((AtomicActivity)getActivity());
    }

}
