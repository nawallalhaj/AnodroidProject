package com.example.nawalproj.Classes;

import java.sql.Date;
import java.sql.Time;

public class Auction {
    protected String aucid;

    protected String pid;
    protected Time runningTime;
    protected Date startingDay;
    protected Time startingHour;


    public Auction(String aucid, String pid, Time runningTime, Date startingDay, Time startingHour) {
        this.aucid = aucid;
        this.pid=pid;
        this.runningTime=runningTime;
        this.startingDay=startingDay;
        this.startingHour=startingHour;
    }
}
