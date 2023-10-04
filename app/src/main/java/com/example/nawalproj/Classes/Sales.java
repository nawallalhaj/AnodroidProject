package com.example.nawalproj.Classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Sales implements SqlInterface{
    private String salesid;
    private int pid;

    public Sales(String salesid,int pid) {
        this.salesid = salesid;
        this.pid=pid;
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

    public String getSalesid() {
        return salesid;
    }

    public void setSalesid(String salesid) {
        this.salesid = salesid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
