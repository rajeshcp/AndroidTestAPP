package com.triode.androidtestapp.product.list;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.triode.androidtestapp.AppModule;
import com.triode.androidtestapp.R;
import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.adapters.RecyclerAdapter;
import com.triode.androidtestapp.core.events.CoreEvent;

/**
 * Created by triode on 14/9/16.
 */
public class ProductsAdapter  extends RecyclerAdapter.BaseRecycleViewAdapter<ProductViewCompatVO,
        ProductsAdapter.ViewHolder> {

    static final class ViewHolder extends RecyclerAdapter.ViewHolder{
        AppCompatTextView title, description, share, favorite;
        AppCompatImageView imageView;
        /**
         * Constructor function
         *
         * @param itemView the enclosing view
         */
        public ViewHolder(View itemView) {
            super(itemView);
            title = (AppCompatTextView)itemView.findViewById(R.id.product_title);
            description = (AppCompatTextView)itemView.findViewById(R.id.product_description);
            share = (AppCompatTextView)itemView.findViewById(R.id.share_button);
            favorite = (AppCompatTextView)itemView.findViewById(R.id.favorite_button);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.product_image);
        }
    }

    LayoutInflater inflater;
    public ProductsAdapter(final LayoutInflater inflater){
        super();
        this.inflater = inflater;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inflater = null;
    }

    /**
     * @see #createView(ViewGroup, int)
     */
    @Override
    protected ViewHolder createView(ViewGroup pareGroup, int viewType) {
        final ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.
                product_list_item_layout, pareGroup,false));
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchInteractionEvent(FAVORITE_EVENT, holder.position);
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchInteractionEvent(SHARE_EVENT, holder.position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchInteractionEvent(ITEM_CLICK_EVENT, holder.position);
            }
        });
        return holder;
    }

    /**
     * function notifies about the interaction
     * @param name
     * @param position
     */
    void dispatchInteractionEvent(final String name,
                                  final int position){
        final Bundle data = new Bundle();
        data.putInt(Constants.DATA, position);
        AppModule.getModule().dispatchEvent(new CoreEvent(name, data));
    }


    /**
     * @see #updateView(RecyclerAdapter.ViewHolder, Object)
     */
    @Override
    protected void updateView(ViewHolder viewHolder, ProductViewCompatVO item) {
        viewHolder.title.setText(item.getTitle());
        viewHolder.description.setText(item.getDescription());
        viewHolder.title.setTypeface(null, item.getTitleTypeFace());
        viewHolder.favorite.setCompoundDrawablesWithIntrinsicBounds(item.getFavoriteColor(),
                0, 0, 0);
        Picasso.with(viewHolder.itemView.getContext()).
                load(item.getImage()).into(viewHolder.imageView);
        if(viewHolder.position == getItemCount() -1){
            AppModule.getModule().dispatchEvent(new CoreEvent(LAST_ITEM_REACHED, null));
        }
    }

    public static final String SHARE_EVENT      = "SHARE_EVENT";
    public static final String FAVORITE_EVENT   = "FAVORITE_EVENT";
    public static final String ITEM_CLICK_EVENT = "ITEM_CLICK_EVENT";
    public static final String LAST_ITEM_REACHED = "LAST_ITEM_REACHED";

}
