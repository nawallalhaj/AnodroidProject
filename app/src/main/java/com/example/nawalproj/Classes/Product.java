package com.example.nawalproj.Classes;

public class Product implements SqlInterface{
    protected String pid;
    protected String prodname;
    protected String proddisc;
    protected String prodimg;
    protected int stock;
    protected double salesprice;
    protected double buyprice;


    public Product(String pid, String prodname, String proddisc, String prodimg, int stock, double salesprice, double buyprice) {
        this.pid = pid;
        this.prodname=prodname;
        this.proddisc=proddisc;
        this.prodimg=prodimg;
        this.stock=stock;
        this.salesprice=salesprice;
        this.buyprice=buyprice;
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
