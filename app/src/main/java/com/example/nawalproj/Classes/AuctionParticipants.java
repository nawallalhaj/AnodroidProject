package com.example.nawalproj.Classes;

import java.sql.Date;
import java.sql.Time;

public class AuctionParticipants {
    private int aucPid;
    private String uid;
    private double paidPrice;

    public AuctionParticipants(int aucid, String uid, double paidPrice) {
        this.aucPid=aucPid;
        this.uid=uid;
        this.paidPrice=paidPrice;
    }

    public int getAucPid() {
        return aucPid;
    }

    public void setAucPid(int aucPid) {
        this.aucPid = aucPid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(double paidPrice) {
        this.paidPrice = paidPrice;
    }
}
