package com.example.nawalproj.Classes;

import java.sql.Date;
import java.sql.Time;

public class AuctionParticipants extends Auction{
    private String aucPid;
    private String uid;
    private double paidPrice;

    public AuctionParticipants(String aucid, String pid, Time runningTime, Date startingDay, Time startingHour, String aucPid, String uid, double paidPrice) {
        super(aucid, pid, runningTime, startingDay, startingHour);
        this.aucPid=aucPid;
        this.uid=uid;
        this.paidPrice=paidPrice;
    }
}
