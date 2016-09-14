package com.triode.androidtestapp.api;

import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Parcel;

import com.triode.androidtestapp.R;

/**
 * Created by triode on 14/9/16.
 */
public class ProductViewCompatVO extends ProductVO {

    public static final Creator<ProductViewCompatVO> CREATOR = new Creator<ProductViewCompatVO>() {
        @Override
        public ProductViewCompatVO createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public ProductViewCompatVO[] newArray(int i) {
            return new ProductViewCompatVO[0];
        }
    };

    public ProductViewCompatVO(String title, String description, String image) {
        super(title, description, image);
    }

    public ProductViewCompatVO(Parcel in) {
        super(in);
        updateViewCompatValues();
    }

    public ProductViewCompatVO() {
        super();
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    /**
     * function which parse the corresponding raw which
     * points to
     *
     * @param cursor the cursor obtained
     * @return the {@code java.lang.Object} itself
     */
    @Override
    public void parseCursor(Cursor cursor) {
        super.parseCursor(cursor);
        updateViewCompatValues();
    }

    int titleTypeFace = Typeface.BOLD;
    int favoriteColor;

    @Override
    public void setIsFavorite(int isFavorite) {
        super.setIsFavorite(isFavorite);
        favoriteColor = (getIsFavorite() == 1) ? R.drawable.ic_star_blue :
                R.drawable.ic_star_black_24dp;
    }

    @Override
    public void setIsRead(int isRead) {
        super.setIsRead(isRead);
        titleTypeFace = (getIsRead() == 1) ? Typeface.NORMAL : Typeface.BOLD;
    }

    /**
     * function update the view compat values
     */
    private void updateViewCompatValues(){
        titleTypeFace = (getIsRead() == 1) ? Typeface.NORMAL : Typeface.BOLD;
        favoriteColor = (getIsFavorite() == 1) ? R.drawable.ic_star_blue :
                R.drawable.ic_star_black_24dp;
    }

    public int getFavoriteColor() {
        return favoriteColor;
    }

    public int getTitleTypeFace() {
        return titleTypeFace;
    }
}
