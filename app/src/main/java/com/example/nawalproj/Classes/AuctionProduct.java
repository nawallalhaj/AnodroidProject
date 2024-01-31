package com.example.nawalproj.Classes;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_ENDTIME;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_STARTTIME;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.TABLE_AUCTIONPRODUCT;
import static com.example.nawalproj.DataBase.TablesString.ProductTable.TABLE_PRODUCT;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class AuctionProduct implements SqlInterface {
    protected int aid;
    protected String prodType;
    protected byte[] prodimg;
    protected double minprice;
    protected String prodDisc;

    protected long starttime,endtime;
    public AuctionProduct(String prodType, byte[] prodimg, double minprice, String prodDisc,long starttime,long endtime) {
        this.prodType=prodType;
        this.prodimg=prodimg;
        this.minprice=minprice;
        this.prodDisc=prodDisc;
        this.starttime = starttime;
        this.endtime = endtime;
    }
    public AuctionProduct(int aid,String prodType, byte[] prodimg, double minprice) {
        this.aid = aid;
        this.prodType=prodType;
        this.prodimg=prodimg;
        this.minprice=minprice;
    }
    public AuctionProduct(AuctionProduct p) {
        aid = p.getAid();
        prodType = p.getProdType();
        prodDisc = p.getProdDisc();
        minprice = p.getMinprice();
        prodimg = p.getProdimg();
    }


    public AuctionProduct() {
    }


    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public double getMinprice() {
        return minprice;
    }

    public void setMinprice(double minprice) {
        this.minprice = minprice;
    }

    public String getProdDisc() {
        return prodDisc;
    }

    public void setProdDisc(String prodDisc) {
        this.prodDisc = prodDisc;
    }
    public byte[] getProdimg() {
        return prodimg;
    }

    public void setProdimg(byte[] prodimg) {
        this.prodimg = prodimg;
    }


    //region Add,Delete,Update,Select Sql
    @Override
    public long Add(SQLiteDatabase db) {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_AUCTIONPRODUCT_TYPE, prodType);
        values.put(COLUMN_AUCTIONPRODUCT_DESCRIPTION, prodDisc);
        values.put(COLUMN_AUCTIONPRODUCT_MINPRICE, minprice);
        values.put(COLUMN_AUCTIONPRODUCT_IMAGE, prodimg);
        values.put(COLUMN_AUCTIONPRODUCT_STARTTIME, starttime);
        values.put(COLUMN_AUCTIONPRODUCT_ENDTIME, endtime);



// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_AUCTIONPRODUCT, null, values);

    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_AUCTIONPRODUCT, selection, selectionArgs);

    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_AUCTIONPRODUCT_MINPRICE, minprice);
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = {id+""};

        return  db.update(
                TABLE_AUCTIONPRODUCT,
                values,
                selection,
                selectionArgs);
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_AUCTIONPRODUCT_TYPE,
                COLUMN_AUCTIONPRODUCT_DESCRIPTION,
                COLUMN_AUCTIONPRODUCT_IMAGE,
                COLUMN_AUCTIONPRODUCT_MINPRICE,
                COLUMN_AUCTIONPRODUCT_STARTTIME,
                COLUMN_AUCTIONPRODUCT_ENDTIME,
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_AUCTIONPRODUCT,
                projection,
                null,
                null,
                null,
                null,
                sortOrder);
        return c;
    }

    public Cursor SelectById(SQLiteDatabase db,int id) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_AUCTIONPRODUCT_TYPE,
                COLUMN_AUCTIONPRODUCT_DESCRIPTION,
                COLUMN_AUCTIONPRODUCT_IMAGE,
                COLUMN_AUCTIONPRODUCT_MINPRICE,
                COLUMN_AUCTIONPRODUCT_STARTTIME,
                COLUMN_AUCTIONPRODUCT_ENDTIME,
        };
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = {id+""};

        Cursor c = db.query(
                TABLE_AUCTIONPRODUCT,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null  );
        return c;
    }
    @Override
    public String toString(){
        return prodType;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }
    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long currtime) {
        this.starttime = starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long longtime) {
        this.endtime = longtime;
    }


}
