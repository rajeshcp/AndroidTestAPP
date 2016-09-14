package com.triode.androidtestapp.api;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.triode.androidtestapp.core.utils.CoreLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by triode on 14/9/16.
 */
public final class GSONHelper {


    public static final List<ProductVO> parseProductVO(final InputStream stream)
            throws IOException {
        CoreLogger.log("Parsing Started", System.currentTimeMillis()+"");
        final Gson gson = new Gson();
        final JsonReader reader = new JsonReader(new InputStreamReader(stream));
        try {
            final List<ProductVO> productList = new ArrayList<>();
                //get the element name
            reader.beginArray();
            while (reader.hasNext()) {
                //parse every element and convert that to a country object
                productList.add((ProductVO) gson.fromJson(reader, ProductVO.class));
            }
            reader.endArray();
            CoreLogger.log("Parsing Started", System.currentTimeMillis() + "");
            reader.close();
            return productList;
        } finally {
            reader.close();
        }

    }

}
