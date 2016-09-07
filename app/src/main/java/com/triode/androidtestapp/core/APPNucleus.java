package com.triode.androidtestapp.core;

/**
 * Created by triode on 8/9/16.
 * This call will have most of the common shared instances which will be
 * globally accessed by different app components
 */
public final class APPNucleus {

    /**
     * function returns true if the app is in debug mode
     *
     * @return true if debug enabled otherwise false
     */
    public static final boolean isDebug(){
        return true;
    }
}
