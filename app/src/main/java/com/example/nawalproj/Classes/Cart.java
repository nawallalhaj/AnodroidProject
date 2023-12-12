package com.example.nawalproj.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import static com.example.nawalproj.DataBase.TablesString.CartTable.*;


public class Cart implements SqlInterface{

    private int cartid;
    private String uid;
    private int pid;
    private int amount;

    public Cart(String uid, int pid,int amount) {

        this.uid=uid;
        this.pid=pid;
        this.amount = amount;
    }

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public long Add(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, pid);
        values.put(COLUMN_USER_ID,uid);
        values.put(COLUMN_AMOUNT, amount);
// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_CART, null, values);
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_CART, selection, selectionArgs);
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_ID, pid);
        values.put(COLUMN_USER_ID,uid);
        values.put(COLUMN_AMOUNT, amount);
// Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { id+"" };

        return  db.update(
                TABLE_CART,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_PRODUCT_ID,
                COLUMN_USER_ID,
                COLUMN_AMOUNT
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_CART,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }
}
