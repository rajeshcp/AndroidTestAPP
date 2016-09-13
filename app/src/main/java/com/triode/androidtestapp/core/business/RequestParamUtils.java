package com.triode.androidtestapp.core.business;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Triode on 12/9/16.
 */
public class RequestParamUtils {

    /**
     * Content type
     */
    public static final MediaType JSON = MediaType.parse("application/json");

    /**
     * function append the url params to the root
     *
     * @param payload the payload Object
     * @return the formatted url
     */
    public static String appendGetParams(@NonNull final RequestPayload payload) {
        final Bundle data = payload.getValue(RequestPayload.REQUEST_PARAMS);
        if (data == null)
            return payload.getmAPIEndPoint();
        final String params = serialize(data);
        return params.length() > 0 ? payload.getmAPIEndPoint() + "?" + params :
                payload.getmAPIEndPoint();
    }


    /**
     * function serialize the contents in the bundle
     *
     * @param data the data against the serializing to be done
     * @return the name value payer string separated by &
     */
    public final static String serialize(final Bundle data){
        String params = "";
        for (final String key : data.keySet()) {
            params += ((params.length() > 0) ? "&" : "") + key + "=" + data.getString(key);
        }
        return params;
    }


    /**
     * function create the {@code Headers} form the payload
     *
     * @param payload the payload against which the headers to be formed
     * @return {@;code Headers} the created header
     */
    public static final
    @Nullable
    Headers formHeaders(@NonNull final RequestPayload payload) {
        final Bundle data = payload.getValue(RequestPayload.REQUEST_HEADERS);
        final Headers.Builder build = new Headers.Builder();
        if (data != null) {
            for (final String key : data.keySet()) {
                build.add(key, data.getString(key));
            }
        }
        return build.build();
    }

    /**
     * function returns the request body
     * @param payload the payload against the body to be created
     * @return the created payload
     */
    public static final
    @NonNull
    RequestBody getRequestBody(@NonNull final RequestPayload payload) throws JSONException {
        RequestBody body = getFormRequestBody(payload);
        if (body == null) {
            body = RequestBody.create(JSON, getJSONBody(payload));
        }
        return body;
    }

    /**
     * function parse the payload body to {@code JSONObject} String
     *
     * @param payload the payload against the json to be created
     * @return the parse JSON String
     */
    private static final
    @Nullable
    String getJSONBody(@NonNull final RequestPayload payload) throws JSONException {
        final Bundle data = payload.getValue(RequestPayload.REQUEST_BODY);
        if (data == null)
            return null;
        if (data.containsKey(RequestPayload.DEFAULT))
            return data.getString(RequestPayload.DEFAULT);

        final JSONObject object = new JSONObject();
        for (final String key : data.keySet()) {
            try {
                final String value = data.getString(key);
                if (value.indexOf('[') != -1) {
                    object.put(key, new JSONArray(value));
                } else {
                    object.put(key, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object.toString();
    }

    /**
     * function which will return the create the RequestBody from the
     * form params appended
     *
     * @param payload the payload against the body to be obtained
     * @return the created RequestBody object or null
     */
    private static
    @Nullable
    RequestBody getFormRequestBody(@NonNull final RequestPayload payload) {
        final Bundle data = payload.getValue(RequestPayload.FORM_DATA);
        if (data == null)
            return null;
        final FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (final String key : data.keySet()) {
            formBodyBuilder.add(key, data.getString(key));
        }
        return formBodyBuilder.build();
    }

}
