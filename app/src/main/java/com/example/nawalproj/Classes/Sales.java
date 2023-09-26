package com.example.nawalproj.Classes;

import java.util.ArrayList;
import java.util.List;

public class Sales implements SqlInterface{
    private String salesid;
    private String pid;

    public Sales(String salesid,String pid) {
        this.salesid = salesid;
       this.pid=pid;
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
