package com.triode.androidtestapp.core.events;

/**
 * Created by triode on 8/9/16.
 */
public interface EventListener {

    /**
     * Delegate method which will be called against respective events
     * @param event the event which is dispatched by the {@link Dispatcher}
     */
    void onEvent(Event event);

}
