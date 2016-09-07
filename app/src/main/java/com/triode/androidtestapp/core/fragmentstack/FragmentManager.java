package com.triode.androidtestapp.core.fragmentstack;

import android.support.v4.app.Fragment;

import com.triode.androidtestapp.core.fragments.AtomicFragment;

/**
 * Created by triode on 8/9/16.
 */
public final class FragmentManager implements IFragmentStack<AtomicFragment> {

    android.support.v4.app.FragmentManager fragmentManager;

    /**
     *
     * @param fragmentManager
     */
    public FragmentManager(android.support.v4.app.FragmentManager fragmentManager) {
        super();
        this.fragmentManager = fragmentManager;
    }

    /**
     * function which will clear the stack of fragments,
     * This also prevent the fragment animations
     *
     * @return boolean return true success else false
     */
    @Override
    public boolean clearFragmentStack() {
        fragmentManager.popBackStack(null, android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
        return true;
    }

    /**
     * function returns the fragment at the position provided
     *
     * @param position index at which the fragment to be returned
     * @return {@link AtomicFragment}
     */
    @Override
    public AtomicFragment getFragmentAt(int position) {
        android.support.v4.app.FragmentManager.BackStackEntry backEntry = fragmentManager.
                getBackStackEntryAt(position);
        String str=backEntry.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(str);
        return (AtomicFragment) fragment;
    }

    /**
     * Function which will be used to pop a Fragment from the fragment stack
     *
     * @return true success otherwise false
     */
    @Override
    public boolean pop() {
        return fragmentManager.popBackStackImmediate();
    }

    /**
     * Function which push a new Fragment to the fragment stack
     *
     * @param transaction of type FragmentTransaction
     */
    @Override
    public void push(FragmentTransaction transaction) {
        final android.support.v4.app.FragmentTransaction ft =
                fragmentManager.beginTransaction();
        if(transaction.mInAnimation == FragmentTransaction.FRAGMENT_NO_ANIMATION ||
                transaction.mOutAnimation == FragmentTransaction.FRAGMENT_NO_ANIMATION) {
            ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }else{
            ft.setCustomAnimations(transaction.mInAnimation, transaction.mOutAnimation);
        }
        String tag = "";
        if(transaction.isRoot){
            clearFragmentStack();
            tag = "0";
        }else {
            tag = fragmentManager.getBackStackEntryCount() + "";
        }
        Fragment fragment = transaction.compile();
        ft.replace(transaction.mFrameId, fragment, tag);
        ft.addToBackStack(tag);
        ft.commit();
    }

    /**
     * function clear the fragmentManager instance
     */
    public void dumb(){
        fragmentManager = null;
    }
}
