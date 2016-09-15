package com.triode.androidtestapp.api;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.triode.androidtestapp.AppLocalStore;
import com.triode.androidtestapp.core.APPNucleus;
import com.triode.androidtestapp.core.Constants;
import com.triode.androidtestapp.core.orm.ICoreObject;

import java.util.List;

/**
 * Created by triode on 13/9/16.
 */
public class ProductDBHrlper implements ICoreObject<ProductVO> {


    public static final String NAME = "Products";
    public static final String TITLE = "title";
    public static final String IS_READ = "isRead";
    public static final String IS_FAVORITE = "isFavorite";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";


    public static final String getCreateTableQuery() {
        return "CREATE TABLE IF NOT EXISTS "
                + NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE  + " TEXT,"
                + DESCRIPTION  + " TEXT," + IMAGE  + " TEXT,"
                + IS_FAVORITE + " INTEGER," + IS_READ + " INTEGER, "
                + Constants._createdAt + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + Constants._updatedAt + " INTEGER" + ")";
    }


    /**
     * function delete all the records in the table
     *
     * @throws Exception if the deletion fails
     */
    @Override
    public void deleteAll() throws Exception {
        if(!AppLocalStore.getString(ProductLoader.PRODUCT_END_POINT, "x").equals("x")) {
            final SQLiteDatabase db = APPNucleus.getDbHelper().getWritableDatabase();
            final String sql = "delete from " + NAME;
            db.execSQL(sql);
        }
    }

    /**
     * function saves a list of passed {@code T} to the DB
     *
     * @param objects the list of items to be saved
     */
    @Override
    public void saveAll(List<ProductVO> objects) throws Exception {
        final SQLiteDatabase db = APPNucleus.getDbHelper().getWritableDatabase();
        db.beginTransaction();
        final String sql = "INSERT INTO " +
                NAME + " (title, description, image, isRead, isFavorite, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";
        SQLiteStatement statement = db.compileStatement(sql);
        for(final ProductVO productVO : objects){
            statement.clearBindings();
            statement.bindString(1, productVO.getTitle());
            statement.bindString(2, productVO.getDescription());
            statement.bindString(3, productVO.getImage());

            statement.bindLong(4, productVO.getIsRead());
            statement.bindLong(5, productVO.getIsFavorite());
            statement.bindLong(6, System.currentTimeMillis());
            statement.executeInsert();
        }
        db.setTransactionSuccessful(); // This commits the transaction if there were no exceptions
        db.endTransaction();
    }

    /**
     * function toggle the favorite
     *
     * @param vo
     */
    public static final boolean toggleFavorite(final ProductViewCompatVO vo){
        if(vo.getIsFavorite() == 1){
            vo.setIsFavorite(0);
        }else {
            vo.setIsFavorite(1);
        }
        final SQLiteDatabase db = APPNucleus.getDbHelper().getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(IS_FAVORITE, vo.getIsFavorite());
        values.put(Constants._updatedAt, System.currentTimeMillis());
        int done = db.update(NAME, values, Constants._ID + "=?", new String[]{vo.getId() + ""});
        return (done != -1);
    }


    /**
     * function set the record as read
     *
     * @param vo
     */
    public static final boolean markAsRead(final ProductViewCompatVO vo){
        vo.setIsRead(1);
        final SQLiteDatabase db = APPNucleus.getDbHelper().getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(IS_READ, vo.getIsRead());
        values.put(Constants._updatedAt, System.currentTimeMillis());
        int done = db.update(NAME, values, Constants._ID + "=?", new String[]{vo.getId() + ""});
        return (done != -1);
    }
}
