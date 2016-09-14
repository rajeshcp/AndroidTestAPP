package com.triode.androidtestapp.product.list;

import android.os.Parcel;

import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.fragments.FragmentState;

import java.util.List;

/**
 * Created by triode on 14/9/16.
 */
public class ProductListFragmentState extends FragmentState {

    public static final Creator<ProductListFragmentState> CREATOR = new Creator<ProductListFragmentState>() {
        @Override
        public ProductListFragmentState createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public ProductListFragmentState[] newArray(int i) {
            return new ProductListFragmentState[0];
        }
    };

    private List<ProductViewCompatVO> productList;
    private boolean pageLoadComplete;

    public List<ProductViewCompatVO> getProductList() {
        return productList;
    }



    /**
     * Constructs a new instance of {@code Object}.
     *
     * @param source
     */
    public ProductListFragmentState(Parcel source) {
        super(source);
        source.readList(productList, ProductViewCompatVO.class.getClassLoader());
        pageLoadComplete = source.readInt() == 1;
    }

    /**
     * Constructs a new instance of {@code Object}.
     */
    public ProductListFragmentState(final List<ProductViewCompatVO> list) {
        super();
        productList = list;
    }

    public boolean isPageLoadComplete() {
        return pageLoadComplete;
    }

    public void setPageLoadComplete(boolean pageLoadComplete) {
        this.pageLoadComplete = pageLoadComplete;
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
        super.writeToParcel(dest, flags);
        dest.writeList(productList);
        dest.writeInt(pageLoadComplete ? 1 : 0);
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
        return super.describeContents();
    }
}
