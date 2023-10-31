package com.example.nawalproj.Classes;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import static com.example.nawalproj.DataBase.TablesString.ProductTable.*;

public class Product implements SqlInterface{
    protected int pid;
    protected String prodType;
    protected int prodYOP;//year of production
    protected byte[] prodimg;
    protected int stock;
    protected double salesprice;
    protected double buyprice;
    protected String prodDisc;
    protected int karat;


   public Product(String prodType, int prodYOP, byte[] prodimg, int stock, double salesprice, double buyprice, String prodDisc,int karat) {
        this.prodType=prodType;
        this.prodYOP=prodYOP;
        this.prodimg=prodimg;
        this.stock=stock;
        this.salesprice=salesprice;
        this.buyprice=buyprice;
        this.prodDisc=prodDisc;
        this.karat=karat;
    }
    public Product(Product p) {
        pid = p.getPid();
        prodType = p.getProdType();
        prodYOP = p.getProdYOP();
        karat = p.getKarat();
        prodDisc = p.getProdDisc();
        stock = p.getStock();
        salesprice = p.getSalesprice();
        buyprice = p.getBuyprice();
        prodimg = p.getProdimg();
    }


    public Product() {
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getSalesprice() {
        return salesprice;
    }

    public void setSalesprice(double salesprice) {
        this.salesprice = salesprice;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public String getProdDisc() {
        return prodDisc;
    }

    public void setProdDisc(String prodDisc) {
        this.prodDisc = prodDisc;
    }

    public int getKarat() {
        return karat;
    }

    public void setKarat(int karat) {
        this.karat = karat;
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
        values.put(COLUMN_PRODUCT_TYPE, prodType);
        values.put(COLUMN_PRODUCT_YOP,prodYOP);
        values.put(COLUMN_PRODUCT_DESCRIPTION, prodDisc);
        values.put(COLUMN_PRODUCT_BUYPRICE, buyprice);
        values.put(COLUMN_PRODUCT_SALEPRICE, salesprice);
        values.put(COLUMN_PRODUCT_STOCK, stock);
        values.put(COLUMN_PRODUCT_IMAGE, prodimg);
        values.put(COLUMN_PRODUCT_KARAT, karat);



// Insert the new row, returning the primary key value of the new row
        return db.insert(TABLE_PRODUCT, null, values);

    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        String selection = BaseColumns._ID + " LIKE ?";
// Specify arguments in placeholder order.
        String[] selectionArgs = {id+""};
// Issue SQL statement.
        return db.delete(TABLE_PRODUCT, selection, selectionArgs);

    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_TYPE, prodType);
        values.put(COLUMN_PRODUCT_YOP,prodYOP);
        values.put(COLUMN_PRODUCT_DESCRIPTION, prodDisc);
        values.put(COLUMN_PRODUCT_BUYPRICE, buyprice);
        values.put(COLUMN_PRODUCT_SALEPRICE, salesprice);
        values.put(COLUMN_PRODUCT_STOCK, stock);
        values.put(COLUMN_PRODUCT_IMAGE, prodimg.toString());
        values.put(COLUMN_PRODUCT_KARAT, karat);


// Which row to update, based on the title
        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { id+"" };

        return  db.update(
                TABLE_PRODUCT,
                values,
                selection,
                selectionArgs);

    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_PRODUCT_TYPE,
                COLUMN_PRODUCT_YOP,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_PRODUCT_IMAGE,
                COLUMN_PRODUCT_STOCK,
                COLUMN_PRODUCT_SALEPRICE,
                COLUMN_PRODUCT_KARAT,
                COLUMN_PRODUCT_BUYPRICE
        };
// How you want the results sorted in the resulting Cursor
        String sortOrder =
                BaseColumns._ID + " DESC";
        Cursor c = db.query(TABLE_PRODUCT,
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
                COLUMN_PRODUCT_TYPE,
                COLUMN_PRODUCT_YOP,
                COLUMN_PRODUCT_DESCRIPTION,
                COLUMN_PRODUCT_IMAGE,
                COLUMN_PRODUCT_STOCK,
                COLUMN_PRODUCT_SALEPRICE,
                COLUMN_PRODUCT_KARAT,
                COLUMN_PRODUCT_BUYPRICE
        };
        String selection = BaseColumns._ID + " = ?";
        String[] selectionArgs = {id+""};

        Cursor c = db.query(
                TABLE_PRODUCT,   // The table to query
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
