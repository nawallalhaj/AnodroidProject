package com.example.nawalproj.Classes;

import java.sql.Date;
import java.sql.Time;

public class Auction {
    protected String aucid;

    protected int pid;
    protected Time runningTime;
    private double minmalPrice;
    protected Date startingDay;
    protected Time startingHour;


    public Auction(String aucid, int pid, Time runningTime, Date startingDay, Time startingHour,double minmalPrice) {
        this.minmalPrice=minmalPrice;
        this.aucid = aucid;
        this.pid=pid;
        this.runningTime=runningTime;
        this.startingDay=startingDay;
        this.startingHour=startingHour;
    }

    public String getAucid() {
        return aucid;
    }

    public void setAucid(String aucid) {
        this.aucid = aucid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Time getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Time runningTime) {
        this.runningTime = runningTime;
    }

    public double getMinmalPrice() {
        return minmalPrice;
    }

    public void setMinmalPrice(double minmalPrice) {
        this.minmalPrice = minmalPrice;
    }

    public Date getStartingDay() {
        return startingDay;
    }

    public void setStartingDay(Date startingDay) {
        this.startingDay = startingDay;
    }

    public Time getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(Time startingHour) {
        this.startingHour = startingHour;
    }
}
