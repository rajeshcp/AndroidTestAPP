package com.triode.androidtestapp.product.splash;

import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.events.CoreEvent;

/**
 * Created by triode on 14/9/16.
 */
public class ErrorEvent extends CoreEvent {

    Constants.ErrorCodes errorCode;
    /**
     * Constructs a new instance of {@code Object}.
     *
     * @param type   the type of th event
     */
    public ErrorEvent(String type, Constants.ErrorCodes code) {
        super(type, null);
        errorCode = code;
    }

    public Constants.ErrorCodes getErrorCode() {
        return errorCode;
    }
}
