package com.triode.androidtestapp.core.mvp;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by triode on 8/9/16
 */
public class ViewState implements Parcelable {


    public static final int VIEW_STATE_LOADING = 1;
    public static final int VIEW_STATE_COMPLETE = 2;
    public static final int VIEW_STATE_NETWORK_ERROR = 3;
    public static final int VIEW_STATE_SERVER_ERROR = 4;
    public static final int VIEW_STATE_EMPTY_RESULT = 5;

    @IntDef({VIEW_STATE_COMPLETE, VIEW_STATE_LOADING,
            VIEW_STATE_EMPTY_RESULT, VIEW_STATE_NETWORK_ERROR,
            VIEW_STATE_SERVER_ERROR
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewStateType {
    }


    private int viewState = VIEW_STATE_LOADING;

    public void setViewState(@ViewStateType int viewState) {
        this.viewState = viewState;
    }

    public int getViewState() {
        return viewState;
    }

    /**
     * Constructs a new instance of {@code Object}.
     */
    public ViewState() {
        super();
    }

    /**
     *
     */
    public static final Parcelable.Creator<ViewState> CREATOR
            = new Parcelable.Creator<ViewState>() {
        /**
         *
         * @param in
         * @return
         */
        public ViewState createFromParcel(Parcel in) {
            return new ViewState(in);
        }

        /**
         * @param size
         * @return
         */
        public ViewState[] newArray(int size) {
            return new ViewState[size];
        }
    };


    /**
     * function which recreate the object from the Parcel
     * @param in of type Parcel
     */
    public ViewState(Parcel in) {
        viewState = in.readInt();
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(viewState);
    }
}
