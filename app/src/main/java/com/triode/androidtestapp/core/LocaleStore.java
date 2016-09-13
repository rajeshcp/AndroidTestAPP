package com.triode.androidtestapp.core;

import android.content.Context;
import android.content.SharedPreferences;

import com.triode.androidtestapp.core.primitives.IGetSet;


/**
 * Created by Triode on 8/5/2016.
 *
 * The class responsible for saving contents to the {@link SharedPreferences}
 */
public class LocaleStore implements IGetSet {


    /**
     * Holds the singleton instance of the class
     */
    private static LocaleStore mInstance;

    /**
     * Getter function for mInstance
     *
     * @return mInstance of type HLPreferenceUtils
     */
    public static LocaleStore obtain(){
        mInstance = (mInstance == null) ? new LocaleStore() : mInstance;
        return mInstance;
    }

    /**
     * return the Editor object against which editing will be done
     * @return SharedPreferences.Editor
     */
    public static SharedPreferences.Editor obtainEditor(){
        final Context context = APPNucleus.getAppContext();
        return  context.
                getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).edit();
    }

    /**
     * Return the preferences with name
     * @return of type SharedPreferences
     */
    public static SharedPreferences obtainPreferences(){
        final Context context = APPNucleus.getAppContext();
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }




    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type String, The value which needs to be saved against key
     */
    @Override
    public boolean put(String key, String value) {
        return obtainEditor().putString(key, value).commit();
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type float, The value which needs to be saved against key
     */
    @Override
    public boolean put(String key, float value) {
        return obtainEditor().putFloat(key, value).commit();
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type int, The value which needs to be saved against key
     */
    @Override
    public boolean put(String key, int value) {
        return obtainEditor().putInt(key, value).commit();
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type boolean, The value which needs to be saved against key
     */
    @Override
    public boolean put(String key, boolean value) {
        return obtainEditor().putBoolean(key, value).commit();
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type int
     */
    @Override
    public int getInteger(String key, int fallBack) {
        return obtainPreferences().getInt(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type String
     */
    @Override
    public String getString(String key, String fallBack) {
        return obtainPreferences().getString(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type float
     */
    @Override
    public float getFloat(String key, float fallBack) {
        return obtainPreferences().getFloat(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type boolean
     */
    @Override
    public boolean getBoolean(String key, boolean fallBack) {
        return obtainPreferences().getBoolean(key, fallBack);
    }
}
