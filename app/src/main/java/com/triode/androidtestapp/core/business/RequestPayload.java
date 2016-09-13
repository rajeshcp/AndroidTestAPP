package com.triode.androidtestapp.core.business;

import android.os.Bundle;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import okhttp3.CacheControl;


/**
 * Created by Triode on 12/9/16.
 */
public class RequestPayload {

    public static final String DEFAULT = "DEFAULT";
    public static final String REQUEST_BODY = "body";
    public static final String REQUEST_HEADERS = "headers";
    public static final String REQUEST_PARAMS = "params";
    public static final String FORM_DATA = "form_data";
    /**
     * Holds the API end point
     */
    private final String mAPIEndPoint;
    /**
     * Bundle where all the request details will be saved
     */
    private final Bundle mDataHolder;
    /**
     * To set the cache control
     */
    CacheControl mCacheControl;
    /**
     * Constructs a new instance of {@code Object}.
     *
     * @param url the url to the service
     */
    public RequestPayload(final String url) {
        super();
        mDataHolder = new Bundle();
        this.mAPIEndPoint = url;
    }

    /**
     * getter function for mCacheControl
     *
     * @return the mCacheControl object
     */
    public CacheControl getCacheControl() {
        return mCacheControl;
    }

    /**
     * setter function for mCacheControl
     *
     * @param cache the cache needs to be set
     */
    public void setCacheControl(final CacheControl cache) {
        mCacheControl = cache;
    }

    /**
     * getter function for mAPIEndPoint
     *
     * @return the API set
     */
    public String getmAPIEndPoint() {
        return mAPIEndPoint;
    }

    /**
     * function which will add the value to the payload
     *
     * @param key   the key against the value to be added
     * @param value the value to be added
     * @param type  the type possible values are {@see ValueType}
     */
    public void addValue(final String key, final String value, @ParamType final String type) {
        getBundle(type).putString(key, value);
    }

    /**
     * function which will return the bundle
     * to save the pair based on type
     *
     * @param type the type of bundle to be returned
     * @return the Bundle obtained
     */
    private Bundle getBundle(@ParamType final String type) {
        Bundle data = getValue(type);
        if (data == null) {
            data = new Bundle();
            mDataHolder.putBundle(type, data);
        }
        return data;
    }

    /**
     * function returns the the respective value against the key from the
     * mDataHolder
     *
     * @param type the type of value to be fetched
     * @return the Bundle
     */
    public Bundle getValue(@ParamType final String type) {
        return mDataHolder.getBundle(type);
    }

    /**
     * @ViewState
     */
    @StringDef({DEFAULT, REQUEST_BODY, REQUEST_HEADERS, REQUEST_PARAMS, FORM_DATA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ParamType {
    }
}
