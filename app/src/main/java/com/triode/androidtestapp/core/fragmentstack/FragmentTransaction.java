package com.triode.androidtestapp.core.fragmentstack;

import android.os.Bundle;

import com.triode.androidtestapp.core.fragments.AtomicFragment;

/**
 * Created by triode on 8/9/16.
 */
public final class FragmentTransaction {

    public static final int FRAGMENT_NO_ANIMATION = -1;

    /**
     * Holds the class for the fragment to be pushed
     */
    public Class mFragmentClass;
    /**
     * Arguments to be passed to the newly created Fragment
     */
    public Bundle mParameters;

    /**
     * Decides it is root or not
     */
    public boolean isRoot;

    /**
     * Holds the id of the FrameLayout
     */
    public int mFrameId;

    /**
     * Indicates the animation to be performed when the fragment attached to the Window
     */
    public int mInAnimation = FRAGMENT_NO_ANIMATION;

    /**
     * Indicates the animation to be performed when the fragment de-attached from the Window
     */
    public int mOutAnimation = FRAGMENT_NO_ANIMATION;

    /**
     * Function which create the Fragment and assign the arguments
     * @return AtomicFragment return the created Fragment
     */
    public AtomicFragment compile(){
        AtomicFragment fragment = null;
        try {
            fragment = (AtomicFragment) mFragmentClass.newInstance();
            fragment.setArguments(mParameters);
            return fragment;
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

}
