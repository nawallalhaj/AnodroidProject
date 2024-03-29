package com.example.nawalproj.DataBase;
import com.example.nawalproj.DataBase.TablesString.*;
public class QueryString {


    //region Create Tables
    public static final String SQL_CREATE_PRODUCT =
            "CREATE TABLE " + ProductTable.TABLE_PRODUCT + " (" +
                    ProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ProductTable.COLUMN_PRODUCT_TYPE + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_YOP + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_DESCRIPTION + " TEXT," +
                    ProductTable.COLUMN_PRODUCT_STOCK + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_SALEPRICE + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_BUYPRICE + " DOUBLE,"+
                    ProductTable.COLUMN_PRODUCT_KARAT + " INTEGER," +
                    ProductTable.COLUMN_PRODUCT_IMAGE + " BLOB);";
    public static final String SQL_CREATE_AUCTIONPRODUCT =
            "CREATE TABLE " + AuctionProductTable.TABLE_AUCTIONPRODUCT + " (" +
                    AuctionProductTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE + " TEXT," +
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION + " TEXT," +
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE + " DOUBLE,"+
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_STARTTIME + " INTEGER,"+
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_ENDTIME + " INTEGER,"+
                    AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE + " BLOB);";


    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + CartTable.TABLE_CART + " (" +
                    CartTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartTable.COLUMN_PRODUCT_ID + " INTEGER," +
                    CartTable.COLUMN_AMOUNT + " INTEGER," +
                    CartTable.COLUMN_USER_ID + " TEXT);";
    public static final String SQL_CREATE_FAVORITE =
            "CREATE TABLE " + FavoriteTable.TABLE_FAVORITE + " (" +
                    FavoriteTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FavoriteTable.COLUMN_PRODUCT_ID + " INTEGER," +
                    FavoriteTable.COLUMN_USER_ID + " TEXT);";

    public static final String SQL_CREATE_SALE =
            "CREATE TABLE " + SaleTable.TABLE_SALE + " (" +
                    SaleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SaleTable.COLUMN_SALE_PROD_ID + " INTEGER," +
                    SaleTable.COLUMN_SALE_USER_ID + " TEXT);";



    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PRODUCT;
    public static final String SQL_DELETE_AUCTIONPRODUCT =
            "DROP TABLE IF EXISTS " + AuctionProductTable.TABLE_AUCTIONPRODUCT;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartTable.TABLE_CART;
    public static final String SQL_DELETE_FAVORITE =
            "DROP TABLE IF EXISTS " + FavoriteTable.TABLE_FAVORITE;

    public static final String SQL_DELETE_SALE =
            "DROP TABLE IF EXISTS " + SaleTable.TABLE_SALE;

    //endregion
}

