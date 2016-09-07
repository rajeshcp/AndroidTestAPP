package com.triode.androidtestapp.core.events;

/**
 * Created by triode on 8/9/16.
 * Common interface used to enable the event primitives
 */
public interface Event {


    /**
     * Function return the type of event
     * @return of type {@link String}
     */
    String getType();

    /**
     * To get the creator or source of the event
     * @return of type {@link Object}
     */
    Object getSource();


    /**
     * To set the source of the event propagation
     * @param object the object from which the event is triggered
     */
    void setSource(Object object);

    /**
     * function check event dispatched with bundle or not
     *
     * @return true if exist else flase
     */
    boolean hasExtra();

}
