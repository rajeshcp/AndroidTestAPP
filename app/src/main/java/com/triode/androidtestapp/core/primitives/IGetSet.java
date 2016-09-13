
package com.triode.androidtestapp.core.primitives;

/**
 * Created by Triode on 11/9/2016.
 */
public interface IGetSet {

    /**
     * Function save the value against the key provided
     * @param key of type String, The key against which the value should be saved
     * @param value of type String, The value which needs to be saved against key
     */
    boolean put(String key, String value);

    /**
     * Function save the value against the key provided
     * @param key of type String, The key against which the value should be saved
     * @param value of type float, The value which needs to be saved against key
     */
    boolean put(String key, float value);
    /**
     * Function save the value against the key provided
     * @param key of type String, The key against which the value should be saved
     * @param value of type int, The value which needs to be saved against key
     */
    boolean put(String key, int value);

    /**
     * Function save the value against the key provided
     * @param key of type String, The key against which the value should be saved
     * @param value of type boolean, The value which needs to be saved against key
     */
    boolean put(String key, boolean value);


    /**
     * Function return the value which is saved against the key
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type int
     */
    int getInteger(String key, int fallBack);

    /**
     * Function return the value which is saved against the key
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type String
     */
    String getString(String key, String fallBack);

    /**
     * Function return the value which is saved against the key
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type float
     */
    float getFloat(String key, float fallBack);

    /**
     * Function return the value which is saved against the key
     * @param key of type String
     * @param fallBack value needs to be send if the value not present
     * @return of type boolean
     */
    boolean getBoolean(String key, boolean fallBack);
}
