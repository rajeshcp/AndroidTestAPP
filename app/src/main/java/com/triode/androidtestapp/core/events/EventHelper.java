package com.triode.androidtestapp.core.events;

/**
 * Created by triode on 14/9/16.
 */
public interface EventHelper {

    void addViewEvents(final EventListener listener);
    void removeViewEvents(final EventListener listener);
    void addGlobalEvents(final EventListener listener);
    void removeGlobalEvents(final EventListener listener);
}
