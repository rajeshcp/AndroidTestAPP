package com.triode.androidtestapp;

import com.triode.androidtestapp.core.APPNucleus;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.CoreApplication;

/**
 * Created by triode on 14/9/16.
 */
public class AppModule extends CoreApplication {

    DatabaseObserver mObserver = new DatabaseObserver();


    private static AppModule mModule;
    public AppModule() {
        super();
        mModule = this;
    }

    public static AppModule getModule() {
        return mModule;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        APPNucleus.init(getApplicationContext(), Constants.Environments.DEV_ENV);
        APPNucleus.initDB(mObserver);
    }
}
