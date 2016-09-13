package com.triode.androidtestapp.core.orm;



/**
 * Created by Triode on 5/12/2016.
 */
public class ORMModule {
    /**
     * function creates the query and return it
     *
     * @param className the class name
     * @return the created instance of {@code Query}
     */
    public Query getQuery(final String className){
        return new Query(className);
    }

}
