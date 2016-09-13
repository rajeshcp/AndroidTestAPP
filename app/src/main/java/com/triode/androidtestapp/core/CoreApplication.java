package com.triode.androidtestapp.core;

import android.app.Application;

import com.triode.androidtestapp.core.events.Dispatcher;
import com.triode.androidtestapp.core.events.Event;
import com.triode.androidtestapp.core.events.EventListener;

/**
 * Created by triode on 12/9/16.
 */
public abstract class CoreApplication extends Application implements Dispatcher<EventListener>{

    /**
     * Add the listener to the listener map
     *
     * @param type     the type of the event against which the listener to be added
     * @param listener the listener to be invoked on event dispatch {@code EventListener}
     */
    @Override
    public void addEventListener(String type, EventListener listener) {
        APPNucleus.getmDispatcher().addEventListener(type, listener);
    }

    /**
     * Remove the listener for the listener map
     *
     * @param type     the type of the event against which the listener to be removed
     * @param listener the listener to be removed
     */
    @Override
    public void removeEventListener(String type, EventListener listener) {
        APPNucleus.getmDispatcher().removeEventListener(type, listener);
    }

    /**
     * Check the listener already registered or not
     *
     * @param type     type of the event against the existence to be checked
     * @param listener the listener to be mapped
     * @return returns true if mapped else false
     */
    @Override
    public boolean hasEventListener(String type, EventListener listener) {
        return APPNucleus.getmDispatcher().hasEventListener(type, listener);
    }

    /**
     * The method notify the proper event listeners {@link EventListener#onEvent(Event)}
     *
     * @param event to be propagated to the listener
     */
    @Override
    public void dispatchEvent(Event event) {
        APPNucleus.getmDispatcher().dispatchEvent(event);
    }

    /**
     * Function destroy the dispatcher
     */
    @Override
    public void dumb() {
        APPNucleus.getmDispatcher().dumb();
    }
}
