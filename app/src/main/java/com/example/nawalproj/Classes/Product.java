package com.example.nawalproj.Classes;

public class Product implements SqlInterface{
    protected String pid;
    protected String prodType;
    protected String prodYOP;
    protected String prodimg;
    protected int stock;
    protected double salesprice;
    protected double buyprice;
    protected String prodDisc;
    protected int karat;


    public Product(String pid, String prodType, String prodYOP, String prodimg, int stock, double salesprice, double buyprice, String prodDisc,int karat) {
        this.pid = pid;
        this.prodType=prodType;
        this.prodYOP=prodYOP;
        this.prodimg=prodimg;
        this.stock=stock;
        this.salesprice=salesprice;
        this.buyprice=buyprice;
        this.prodDisc=prodDisc;
        this.karat=karat;
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
