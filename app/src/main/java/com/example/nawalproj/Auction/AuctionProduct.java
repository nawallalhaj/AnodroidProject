package com.example.nawalproj.Auction;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_YOP;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.TABLE_AUCTIONPRODUCT;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.example.nawalproj.Classes.SqlInterface;

public class AuctionProduct implements SqlInterface {
    protected int pid;
    protected String prodType;
    protected int prodYOP;//year of production
    protected byte[] prodimg;
    protected double minprice;
    protected String prodDisc;

    public AuctionProduct(String prodType, int prodYOP, byte[] prodimg, double minprice) {
        this.prodType=prodType;
        this.prodYOP=prodYOP;
        this.prodimg=prodimg;
        this.minprice=minprice;
    }
    public AuctionProduct(int pid,String prodType, int prodYOP, byte[] prodimg, double minprice) {
        this.pid = pid;
        this.prodType=prodType;
        this.prodYOP=prodYOP;
        this.prodimg=prodimg;
        this.minprice=minprice;
    }
    public AuctionProduct(String prodType, double minprice, String prodDisc) {
        this.prodType=prodType;
        this.minprice=minprice;
        this.prodDisc=prodDisc;
    }
    public AuctionProduct(AuctionProduct p) {
        pid = p.getPid();
        prodType = p.getProdType();
        prodYOP = p.getProdYOP();
        prodDisc = p.getProdDisc();
        minprice = p.getMinprice();
        prodimg = p.getProdimg();
    }


    public AuctionProduct() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public int getProdYOP() {
        return prodYOP;
    }

    public void setProdYOP(int prodYOP) {
        this.prodYOP = prodYOP;
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
        values.put(COLUMN_AUCTIONPRODUCT_YOP,prodYOP);
        values.put(COLUMN_AUCTIONPRODUCT_DESCRIPTION, prodDisc);
        values.put(COLUMN_AUCTIONPRODUCT_MINPRICE, minprice);
        values.put(COLUMN_AUCTIONPRODUCT_IMAGE, prodimg);



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
        return 0;
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_AUCTIONPRODUCT_TYPE,
                COLUMN_AUCTIONPRODUCT_YOP,
                COLUMN_AUCTIONPRODUCT_DESCRIPTION,
                COLUMN_AUCTIONPRODUCT_IMAGE,
                COLUMN_AUCTIONPRODUCT_MINPRICE,
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
                COLUMN_AUCTIONPRODUCT_YOP,
                COLUMN_AUCTIONPRODUCT_DESCRIPTION,
                COLUMN_AUCTIONPRODUCT_IMAGE,
                COLUMN_AUCTIONPRODUCT_MINPRICE,
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

}
