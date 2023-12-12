package com.example.nawalproj.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import static com.example.nawalproj.DataBase.TablesString.FavoriteTable.COLUMN_PRODUCT_ID;
import static com.example.nawalproj.DataBase.TablesString.FavoriteTable.COLUMN_USER_ID;
import static com.example.nawalproj.DataBase.TablesString.FavoriteTable.TABLE_FAVORITE;


public class Favorite implements SqlInterface{
    private int favoriteid;
    private String uid;
    private int pid;
    public Favorite(String uid, int pid) {
        this.uid=uid;
        this.pid=pid;
    }
    @Override
    public long Add(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, pid);
        values.put(COLUMN_USER_ID,uid);
// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_FAVORITE, null, values);
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_FAVORITE, selection, selectionArgs);
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        return 0;
    }
    public Cursor SelectById(SQLiteDatabase db,String uid,int pid) {
        String[] projection = {
                BaseColumns._ID,
        };
        String selection = COLUMN_USER_ID + " = ? AND "+COLUMN_PRODUCT_ID+" = ?";
        String[] selectionArgs = {uid,pid+""};

        Cursor c = db.query(
                TABLE_FAVORITE,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null);
        return c;
    }
    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_PRODUCT_ID,
                COLUMN_USER_ID,
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_FAVORITE,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }

    public int getFavid() {
        return favoriteid;
    }

    public void setFavid(int favid) {
        this.favoriteid = favid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
