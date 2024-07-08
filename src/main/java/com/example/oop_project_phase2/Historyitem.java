package com.example.oop_project_phase2;

import java.sql.Date;
import java.sql.Time;

public class Historyitem {
    public String Host;
    public String Guest;
    public boolean Hostwin;
    public boolean Guestwin;
    public Date date;
    public Time time;
    public int Hostlevel;
    public int Guestlevel;
    public int HostCoin;
    public int HostXP;
    public int GuestCoin;
    public int GuestXP;

    public Historyitem(String Host,String Guest,boolean Hostwin,boolean Guestwin,Date date,Time time,int Hostlevel,int Guestlevel,int HostCoin,int HostXP,int GuestCoin,int GuestXP) {
        this.Host = Host;
        this.Guest = Guest;
        this.date = date;
        this.time = time;
        this.Hostlevel = Hostlevel;
        this.Guestlevel = Guestlevel;
        this.Hostwin = Hostwin;
        this.Guestwin = Guestwin;
        this.HostCoin = HostCoin;
        this.HostXP = HostXP;
        this.GuestCoin = GuestCoin;
        this.GuestXP = GuestXP;
    }

    public String print() {
        String result = "";
        if (Users.LoginUser.Username.equals(Host)){
            result += date.toString() + "\t";
            result += time.toString() + "\t";
            result += Hostwin + "\t";
            result += Guest + "\t" + Guestlevel +"\t";
            result += HostCoin + "\t" + HostXP + "\t";
        }else {
            result += date.toString() + "\t";
            result += time.toString() + "\t";
            result += Guestwin + "\t";
            result += Host + "\t" + Hostlevel +"\t";
            result += GuestCoin + "\t" + GuestXP + "\t";
        }
        return result;
    }
}
