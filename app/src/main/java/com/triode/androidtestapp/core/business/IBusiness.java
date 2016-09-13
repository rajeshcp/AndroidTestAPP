package com.triode.androidtestapp.core.business;

import android.support.annotation.WorkerThread;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by triode on 12/9/16.
 */
public interface IBusiness {

    /**
     * function which will return the {@<code>Response</code>} which is obtained
     * from the request
     * @param payload the payload for the request
     * @return the Response object
     */
    @WorkerThread
    Response getResponse(final RequestPayload payload) throws IOException;
}
