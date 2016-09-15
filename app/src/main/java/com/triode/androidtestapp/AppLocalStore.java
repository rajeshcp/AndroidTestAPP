package com.triode.androidtestapp;

import android.support.annotation.StringDef;

import com.triode.androidtestapp.api.ProductLoader;
import com.triode.androidtestapp.core.APPNucleus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by triode on 9/14/16.
 */
public class AppLocalStore {

    /**
     * Function return the value which is saved against the key
     *
     * @param key      of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type boolean
     */
    public static boolean getBoolean(@PreferencesKeys String key, boolean fallBack) {
        return APPNucleus.getmLocaleStore().getBoolean(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key      of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type float
     */
    public static float getFloat(@PreferencesKeys String key, float fallBack) {
        return APPNucleus.getmLocaleStore().getFloat(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key      of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type int
     */
    public static int getInteger(@PreferencesKeys String key, int fallBack) {
        return APPNucleus.getmLocaleStore().getInteger(key, fallBack);
    }

    /**
     * Function return the value which is saved against the key
     *
     * @param key      of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type String
     */
    public static String getString(@PreferencesKeys String key, String fallBack) {
        return APPNucleus.getmLocaleStore().getString(key, fallBack);
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type boolean, The value which needs to be saved against key
     */
    public static boolean put(@PreferencesKeys String key, boolean value) {
        return APPNucleus.getmLocaleStore().put(key, value);
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type float, The value which needs to be saved against key
     */
    public static boolean put(@PreferencesKeys String key, float value) {
        return APPNucleus.getmLocaleStore().put(key, value);
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type int, The value which needs to be saved against key
     */
    public static boolean put(@PreferencesKeys String key, int value) {
        return APPNucleus.getmLocaleStore().put(key, value);
    }

    /**
     * Function save the value against the key provided
     *
     * @param key   of type String, The key against which the value should be saved
     * @param value of type String, The value which needs to be saved against key
     */
    public static boolean put(@PreferencesKeys String key, String value) {
        return APPNucleus.getmLocaleStore().put(key, value);
    }

    /**
     * @RequestType
     */
    @StringDef({ProductLoader.PRODUCT_END_POINT})
    @Retention(RetentionPolicy.SOURCE)
    @interface PreferencesKeys {
    }
}
