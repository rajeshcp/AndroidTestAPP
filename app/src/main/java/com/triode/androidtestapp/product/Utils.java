package com.triode.androidtestapp.product;

import android.content.Intent;

import com.triode.androidtestapp.api.ProductViewCompatVO;
import com.triode.androidtestapp.core.fragments.AtomicFragment;

/**
 * Created by triode on 15/9/16.
 */
public final class Utils {
    /**
     * function initiate the share intent with the
     * product content
     */
    public static final void shareProduct(final ProductViewCompatVO vo,
                                          final AtomicFragment fragment){
        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,  vo.getTitle() + "\n" +
                vo.getDescription() + "\n" +
                vo.getImage());
        sendIntent.setType("text/plain");
        fragment.startActivity(sendIntent);
    }
}
