package com.triode.androidtestapp.api;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.orm.IDBModel;

/**
 * Created by triode on 13/9/16.
 */
public class ProductVO implements Parcelable, IDBModel{

    public static final Creator<ProductVO> CREATOR = new Creator<ProductVO>() {
        @Override
        public ProductVO createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public ProductVO[] newArray(int i) {
            return new ProductVO[0];
        }
    };

    public ProductVO() {
        super();
    }

    String image, description, title;
    private int id, isFavorite, isRead;

    public ProductVO(String title, String description, String image) {
        super();
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public ProductVO(final Parcel in) {
        super();
        id = in.readInt();
        isFavorite = in.readInt();
        isRead = in.readInt();

        image = in.readString();
        description = in.readString();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(isFavorite);
        parcel.writeInt(isRead);

        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeString(title);
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
        id = cursor.getInt(cursor.getColumnIndex(Constants._ID));
        isFavorite = cursor.getInt(cursor.getColumnIndex(ProductDBHrlper.IS_FAVORITE));
        isRead = cursor.getInt(cursor.getColumnIndex(ProductDBHrlper.IS_READ));

        title = cursor.getString(cursor.getColumnIndex(ProductDBHrlper.TITLE));
        description = cursor.getString(cursor.getColumnIndex(ProductDBHrlper.DESCRIPTION));
        image = cursor.getString(cursor.getColumnIndex(ProductDBHrlper.IMAGE));

        title = title.substring(0, 1).toUpperCase() + title.substring(1);
        description = description.substring(0, 1).toUpperCase() +
                description.substring(1);
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public int getIsRead() {
        return isRead;
    }

    public String getTitle() {
        return title;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
