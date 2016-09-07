package com.triode.androidtestapp.core.events;

import android.os.Bundle;


/**
 * Created by triode on 8/9/16.
 * Class act as the bas class for all the vents in the
 * System
 */
public class CoreEvent implements Event {


    /**
     * getter function for the {@code mExtra}
     *
     * @return the {@code mExtra}
     */
    public Bundle getmExtra() {
        return mExtra;
    }

    /**
     * function check event dispatched with bundle or not
     *
     * @return true if exist else flase
     */
    @Override
    public boolean hasExtra() {
        return mExtra != null;
    }

    /**
     * Holds the extra values about the event
     */
    protected final Bundle mExtra;

    /**
     * Constructs a new instance of {@code Object}.
     *
     * @param type the type of th event
     */
    public CoreEvent(final String type, final Bundle mExtra) {
        super();
        mType = type;
        this.mExtra = mExtra;
    }

    /**
     * Holds the type of the event
     */
    private final String mType;

    /**
     * Event propagation source
     */
    private Object mSource;

    /**
     * Function return the type of event
     *
     * @return of type {@link String}
     */
    @Override
    public String getType() {
        return mType;
    }

    /**
     * To get the creator or source of the event
     *
     * @return of type {@link Object}
     */
    @Override
    public Object getSource() {
        return mSource;
    }

    /**
     * To set the source of the event propagation
     *
     * @param object the object from which the event is triggered
     */
    @Override
    public void setSource(Object object) {
        this.mSource = object;
    }
}
