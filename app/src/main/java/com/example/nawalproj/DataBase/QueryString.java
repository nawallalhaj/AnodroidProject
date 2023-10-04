package com.example.nawalproj.DataBase;
import com.example.nawalproj.Classes.AuctionParticipants;
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


    public static final String SQL_CREATE_CART =
            "CREATE TABLE " + CartTable.TABLE_CART + " (" +
                    CartTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    CartTable.COLUMN_PRODUCT_ID + " INTEGER," +
                    CartTable.COLUMN_USER_ID + " TEXT);";

    public static final String SQL_CREATE_SALE =
            "CREATE TABLE " + SaleTable.TABLE_SALE + " (" +
                    SaleTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    SaleTable.COLUMN_SALE_PROD_ID + " INTEGER," +
                    SaleTable.COLUMN_SALE_USER_ID + " TEXT);";
    public static final String SQL_CREATE_AUCTION =
            "CREATE TABLE " + AuctionTable.TABLE_AUCTION + " (" +
                    AuctionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AuctionTable.COLUMN_AUCTION_PROD_ID + " INTEGER," +
                    AuctionTable.COLUMN_AUCTION_MINIMALPRICE + " DOUBLE," +
                    AuctionTable.COLUMN_AUCTION_STARTINGHOUR + " TIME," +
                    AuctionTable.COLUMN_AUCTION_STARTINGDAY + " DATE," +
                    AuctionTable.COLUMN_AUCTION_RUNNIGTIME + " TIME);";
    public static final String SQL_CREATE_AUCTIONP =
            "CREATE TABLE " + AuctionParticipantsTable.TABLE_AUCTIONP + " (" +
                    AuctionParticipantsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    AuctionParticipantsTable.COLUMN_AUCTIONP_PAIDPRICE + " DOUBLE," +
                    AuctionParticipantsTable.COLUMN_AUCTIONP_USER_ID + " TEXT);";



    //endregions

    //region Delete Tables

    public static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductTable.TABLE_PRODUCT;

    public static final String SQL_DELETE_CART =
            "DROP TABLE IF EXISTS " + CartTable.TABLE_CART;

    public static final String SQL_DELETE_SALE =
            "DROP TABLE IF EXISTS " + SaleTable.TABLE_SALE;

    //endregion
}

