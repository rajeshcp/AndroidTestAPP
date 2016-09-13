package com.triode.androidtestapp.core;

import android.content.Context;
import android.support.annotation.NonNull;

import com.triode.androidtestapp.core.events.EventDispatcher;
import com.triode.androidtestapp.core.orm.CoreDBImplementation;
import com.triode.androidtestapp.core.orm.IDBChanges;

import java.lang.ref.WeakReference;

/**
 * Created by triode on 8/9/16.
 * This call will have most of the common shared instances which will be
 * globally accessed by different app components
 */
public final class APPNucleus {



    /**
     * Holds the application context, this will accessible from any where in the app
     */
    private static Context mApplicationContext;

    /**
     * Getter function for mApplicationContext
     * @return of type Context, returns the mApplicationContext instance
     */
    public static final Context getAppContext(){
        return mApplicationContext;
    }

    /**
     * Starting point of the cor-lib if this is not called this will leads to exceptions
     *
     * @param mApplicationContext not null of type Context, this will be saved in mApplicationContext
     * @param environment the mode the application to be launched, based on this value config
     *                    files under assets will be selected.
     *                    Possible values are {@link
     *                    com.triode.androidtestapp.core.Constants.Environments}
     */
    public static final void init(@NonNull final Context mApplicationContext,
                                  @NonNull final Constants.Environments environment){
        APPNucleus.mApplicationContext = mApplicationContext;
        initAppConfig(environment);
        mLocaleStore = new WeakReference(LocaleStore.obtain());
        mDispatcher  = new WeakReference(EventDispatcher.acquire());
    }



    static boolean isDebug;
    /**
     * function returns true if the app is in debug mode
     *
     * @return true if debug enabled otherwise false
     */
    public static final boolean isDebug(){
        return true;
    }


    /**
     * getter function for dispatcher
     * @return {@code mDispatcher#get}
     */
    public static EventDispatcher getmDispatcher() {
        return mDispatcher.get();
    }


    /**
     * Holds the instance of event dispatcher
     */
    private static WeakReference<EventDispatcher> mDispatcher;


    /**
     * getter function for {@code mLocaleStore}
     *
     * @return the instance of #mLocaleStore
     */
    public static LocaleStore getmLocaleStore() {
        return mLocaleStore.get();
    }

    /**
     * holds the instance of the {@link LocaleStore}
     */
    private static WeakReference<LocaleStore> mLocaleStore;


    public static CoreDBImplementation getDbHelper() {
        return dbHelper;
    }

    private static CoreDBImplementation dbHelper;
    /**
     * function initialize the DB creation
     * @param callback the callback listening for DB changes
     */
    public static final void initDB(final IDBChanges callback){
        dbHelper = CoreDBImplementation.obtain(callback);
    }


    /**
     * function initialise the loading of app config file under assets
     *
     * @param environment the mode the application to be launched, based on this value config
     *                    files under assets will be selected.
     *                    Possible values are {@link Constants.Environments}
     */
    public static final void initAppConfig(final Constants.Environments environment){
        isDebug = (environment.equals(Constants.Environments.DEV_ENV));
    }

    public static final void destroy(){
        mApplicationContext = null;
        mDispatcher = null;
        mLocaleStore = null;
        dbHelper.mDBChangeCallback = null;
        dbHelper = null;
    }
}
