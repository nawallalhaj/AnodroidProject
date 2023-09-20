package com.example.nawalproj.Classes;

import java.util.ArrayList;
import java.util.List;

public class Cart implements SqlInterface{
    private String cartid;
    private String uid;
    private List<Product>products;
    public Cart(String cartid,String uid) {
        this.cartid=cartid;
        this.uid=uid;
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
