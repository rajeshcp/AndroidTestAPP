package com.triode.androidtestapp.core.events;

/**
 * Created by triode on 8/9/16.
 * Interface to have primitive event related dispatching methods
 */
public interface Dispatcher<D extends EventListener> {


    /**
     * Add the listener to the listener map
     *
     * @param type the type of the event against which the listener to be added
     * @param listener the listener to be invoked on event dispatch {@code EventListener}
     */
    void addEventListener(String type, D listener);

    /**
     * Remove the listener for the listener map
     *
     * @param type the type of the event against which the listener to be removed
     * @param listener the listener to be removed
     */
    void removeEventListener(String type, D listener);

    /**
     * Check the listener already registered or not
     *
     * @param type type of the event against the existence to be checked
     * @param listener the listener to be mapped
     * @return returns true if mapped else false
     */
    boolean hasEventListener(String type, D listener);

    /**
     * The method notify the proper event listeners {@link EventListener#onEvent(Event)}
     * @param event to be propagated to the listener
     */
    void dispatchEvent(Event event);

    /**
     * Function destroy the dispatcher
     */
    void dumb();

}
