package com.triode.androidtestapp.core.orm;

import java.util.List;

/**
 * Created by Triode on 6/15/2016.
 */
public interface ICoreObject<T> {
    /**
     * function saves a list of passed {@code T} to the DB
     *
     * @param objects the list of items to be saved
     */
    void saveAll(final List<T> objects) throws Exception;


    /**
     * function delete all the records in the table
     * @throws Exception if the deletion fails
     */
    void deleteAll() throws Exception;
}
