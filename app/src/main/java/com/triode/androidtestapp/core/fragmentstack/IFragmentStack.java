package com.triode.androidtestapp.core.fragmentstack;


import com.triode.androidtestapp.core.fragments.AtomicFragment;

/**
 * Created by triode on 8/9/16.
 */
public interface IFragmentStack<F extends AtomicFragment> {

    /**
     * Function which will be used to pop a Fragment from the fragment stack
     *
     * @return true success otherwise false
     */
    boolean pop();

    /**
     * Function which push a new Fragment to the fragment stack
     *
     * @param transaction of type FragmentTransaction
     */
    void push(FragmentTransaction transaction);

    /**
     * function which will clear the stack of fragments,
     * This also prevent the fragment animations
     *
     * @return boolean return true success else false
     */
    boolean clearFragmentStack();

    /**
     * function returns the fragment at the position provided
     * @param position  index at which the fragment to be returned
     *
     * @return {@link AtomicFragment}
     */
    F getFragmentAt(int position);
}



