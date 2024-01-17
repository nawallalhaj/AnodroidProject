package com.example.nawalproj.Auction;

import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_DESCRIPTION;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_IMAGE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_MINPRICE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.COLUMN_AUCTIONPRODUCT_TYPE;
import static com.example.nawalproj.DataBase.TablesString.AuctionProductTable.TABLE_AUCTIONPRODUCT;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class AuctionBidders {
    protected int pid;
    protected String bidderName;
    protected String prodType;

    protected byte[] prodimg;
    protected double price;

    public AuctionBidders(String prodType, String bidderName, byte[] prodimg, double price) {
        this.bidderName=bidderName;
        this.prodType = prodType;
        this.prodimg = prodimg;
        this.price = price;
    }

    public AuctionBidders(int pid, String prodType, String bidderName, byte[] prodimg, double price) {
        this.bidderName=bidderName;
        this.pid = pid;
        this.prodType = prodType;
        this.prodimg = prodimg;
        this.price = price;
    }

    public AuctionBidders(String prodType, double price) {
        this.prodType = prodType;
        this.price = price;
    }

    public AuctionBidders(AuctionBidders p) {
        pid = p.getPid();
        bidderName = p.getBidderName();
        prodType = p.getProdType();
        price = p.getPrice();
        prodimg = p.getProdimg();
    }


    public AuctionBidders() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public byte[] getProdimg() {
        return prodimg;
    }

    public void setProdimg(byte[] prodimg) {
        this.prodimg = prodimg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Cursor Select(SQLiteDatabase db) {
        String[] projection = {
                BaseColumns._ID,
                COLUMN_AUCTIONPRODUCT_TYPE,
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
}