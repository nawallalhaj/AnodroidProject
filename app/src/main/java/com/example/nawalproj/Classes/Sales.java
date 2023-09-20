package com.example.nawalproj.Classes;

import java.util.ArrayList;
import java.util.List;

public class Sales implements SqlInterface{
    private String salesid;
    private List<Product> salesprod;
    private List<User> salesuser;
    private double salesprice;
    private double buyprices;

    public Sales(String salesid,double salesprice,double buyprices) {
        this.salesid = salesid;
        salesprod = new ArrayList<Product>();
        salesuser = new ArrayList<User>();
        this.salesuser=salesuser;
        this.salesprice=salesprice;
        this.buyprices=buyprices;
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
