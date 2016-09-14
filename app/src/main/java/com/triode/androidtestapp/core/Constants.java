package com.triode.androidtestapp.core;

/**
 * Created by triode on 11/9/16.
 */
public class Constants {
    public static final String _ID = "_id";
    public static final String _createdAt = "createdAt";
    public static final String _updatedAt = "updatedAt";

    public static final String DATA = "DATA";

    /**
     * Constructs a new instance of {@code Object}.
     */
    private Constants() {
        super();
    }

    /**
     * Configuration type for app
     */
    public static enum Environments {
        DEV_ENV,
        STAGE_ENV,
        PRODUCTION_ENV;
    }

    /**
     * The possible error codes to be injected to the
     * view
     */
    public static enum ErrorCodes {
        NETWORK_ERROR,
        SERVER_ERROR
    }

    public static final String ERROR = "error";
}
