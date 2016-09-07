package com.triode.androidtestapp.core.events;

import android.support.annotation.NonNull;

import com.triode.androidtestapp.core.utils.CoreLogger;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by triode on 8/9/16.
 * The class responsible for dispatching the event to the
 * observers
 */
public class EventDispatcher implements Dispatcher<EventListener> {


    private static final String TAG = EventDispatcher.class.getSimpleName();
    /**
     * Singleton instance Global to the app
     */
    private static EventDispatcher mInstance;
    /**
     * The map in which all the listeners will be saved
     */
    private HashMap<String, CopyOnWriteArrayList<EventListener>> mListenersMap;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public EventDispatcher() {
        super();
        mListenersMap = new HashMap<>();
    }

    /**
     * getter method for mInstance
     *
     * @return Constructs a new instance of {@code EventDispatcher}
     */
    public static EventDispatcher acquire() {
        mInstance = (mInstance == null) ? new EventDispatcher() :
                mInstance;
        return mInstance;
    }

    /**
     * Add the listener to the listener map
     *
     * @param type     the type of the event against which the listener to be added
     * @param listener the listener to be invoked on event dispatch {@code EventListener}
     */
    @Override
    public void addEventListener(String type, EventListener listener) {
        CoreLogger.log("Started Listening For " + type, listener.getClass().getName());
        synchronized (mListenersMap) {
            CopyOnWriteArrayList<EventListener> listenerList = mListenersMap.get(type);
            if (listenerList == null) {
                listenerList = new CopyOnWriteArrayList<>();
                mListenersMap.put(type, listenerList);
            }
            listenerList.add(listener);
        }
    }

    /**
     * Remove the listener for the listener map
     *
     * @param type     the type of the event against which the listener to be removed
     * @param listener the listener to be removed
     */
    @Override
    public void removeEventListener(@NonNull String type, @NonNull EventListener listener) {
        CoreLogger.log("About to remove " + type + " for", listener.getClass().getName());
        synchronized (mListenersMap) {
            final CopyOnWriteArrayList<EventListener> listeners = mListenersMap.get(type);
            if (listeners == null) {
                CoreLogger.log("Invalid call to remove " + type + " for", listener.getClass().getName());
                return;
            }
            listeners.remove(listener);
            CoreLogger.log("Stopped listening for " + type, listener.getClass().getName());
            if (listeners.size() == 0) {
                mListenersMap.remove(type);
            }
        }
    }

    /**
     * Check the listener already registered or not
     *
     * @param type     type of the event against the existence to be checked
     * @param listener the listener to be mapped
     * @return returns true if mapped else false
     */
    @Override
    public boolean hasEventListener(String type, @NonNull EventListener listener) {
        synchronized (mListenersMap) {
            final CopyOnWriteArrayList<EventListener> listeners = mListenersMap.get(type);
            if (listeners == null)
                return false;
            return listeners.contains(listener);
        }
    }

    /**
     * The method notify the proper event listeners {@link EventListener#onEvent(Event)}
     *
     * @param event to be propagated to the listener
     */
    @Override
    public void dispatchEvent(@NonNull Event event) {
        if (event == null) {
            CoreLogger.i(TAG, "Event object can not be null");
            return;
        }
        final String type = event.getType();
        event.setSource(this);
        final CopyOnWriteArrayList<EventListener> listeners;
        synchronized (mListenersMap) {
            listeners = mListenersMap.get(type);
        }
        if (listeners == null)
            return;
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }

    /**
     * Function destroy the dispatcher
     */
    @Override
    public void dumb() {
        synchronized (mListenersMap) {
            mListenersMap.clear();
        }
    }
}
