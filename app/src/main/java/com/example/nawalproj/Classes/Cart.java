package com.example.nawalproj.Classes;

import java.util.ArrayList;
import java.util.List;

public class Cart implements SqlInterface{
    private String cartid;
    private String uid;
    private String pid;
    private List<Product>products;
    public Cart(String cartid,String uid, String pid) {
        this.cartid=cartid;
        this.uid=uid;
        this.pid=pid;
        products = new ArrayList<Product>();
    }

    @Override
    public boolean Add() {
        return false;
    }

    @Override
    public boolean Delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
