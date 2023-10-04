package com.example.nawalproj.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cart implements SqlInterface{
    private String cartid;
    private String uid;
    private int pid;
    private List<Product>products;
    public Cart(String cartid,String uid, int pid) {
        this.cartid=cartid;
        this.uid=uid;
        this.pid=pid;
        products = new ArrayList<Product>();
    }

    public String getCartid() {
        return cartid;
    }

    public void setCartid(String cartid) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public long Add(SQLiteDatabase db) {
        return 0;
    }

    @Override
    public int Delete(SQLiteDatabase db, int id) {
        return 0;
    }

    @Override
    public int Update(SQLiteDatabase db, int id) {
        return 0;
    }

    @Override
    public Cursor Select(SQLiteDatabase db) {
        return null;
    }
}
