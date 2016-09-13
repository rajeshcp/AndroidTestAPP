package com.triode.androidtestapp.core.business;



import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Triode on 12/9/16.
 */
public class BusinessImplementation implements IBusiness {


    private static BusinessImplementation mInstance;
    final OkHttpClient mClient;

    /**
     * Constructs a new instance of {@code Object}.
     */
    private BusinessImplementation() {
        super();
        mClient = new OkHttpClient.Builder()
                .build();

    }

    /**
     * getter function for mInstance
     *
     * @return {@code BusinessImplementation}
     */
    public static BusinessImplementation acquire() {
        mInstance = (mInstance == null) ? new BusinessImplementation() : mInstance;
        return mInstance;
    }


    /**
     * function which will return the {@<code>Response</code>} which is obtained
     * from the request
     *
     * @param payload the payload for the request
     * @return the Response object
     */
    @Override
    public Response getResponse(RequestPayload payload) throws IOException {
        final Request request = new Request.Builder()
                .url(RequestParamUtils.appendGetParams(payload))
                .headers(RequestParamUtils.formHeaders(payload))
                .build();
        final Response response = mClient.newCall(request).execute();
        return response;
    }
}
