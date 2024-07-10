package com.example.oop_project_phase2.UserManagement;

import javafx.beans.property.*;

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

    public StringProperty Guestp = new SimpleStringProperty();
    public StringProperty Hostwinp = new SimpleStringProperty();
    public StringProperty datep = new SimpleStringProperty();
    public StringProperty timep = new SimpleStringProperty();
    public IntegerProperty Guestlevelp = new SimpleIntegerProperty();
    public IntegerProperty HostCoinp = new SimpleIntegerProperty();
    public IntegerProperty HostXPp = new SimpleIntegerProperty();


    public final StringProperty GuestpProperty() {
        return Guestp;
    }
    public final String getGuestp() {
        return Guestp.get();
    }
    public final void setGuestp(String value) {
        Guestp.set(value);
    }
    public final StringProperty HostwinpProperty() {
        return Hostwinp;
    }
    public final String getHostwinp() {
        return Hostwinp.get();
    }
    public final void setHostwinp(String value) {
        Hostwinp.set(value);
    }
    public final StringProperty datepProperty() {
        return datep;
    }
    public final String getdatep() {
        return datep.get();
    }
    public final void setdatep(String value) {
        datep.set(value);
    }
    public final StringProperty timepProperty() {
        return timep;
    }
    public final String gettimep() {
        return timep.get();
    }
    public final void settimep(String value) {
        timep.set(value);
    }
    public final IntegerProperty GuestlevelpProperty() {
        return Guestlevelp;
    }
    public final Integer getGuestlevelp() {
        return Guestlevelp.get();
    }
    public final void setGuestlevelp(Integer value) {
        Guestlevelp.set(value);
    }
    public final IntegerProperty HostCoinpProperty() {
        return HostCoinp;
    }
    public final Integer getHostCoinp() {
        return HostCoinp.get();
    }
    public final void setHostCoinp(Integer value) {
        HostCoinp.set(value);
    }
    public final IntegerProperty HostXPpProperty() {
        return HostXPp;
    }
    public final Integer getHostXPp() {
        return HostXPp.get();
    }
    public final void setHostXPp(Integer value) {
        HostXPp.set(value);
    }


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
        Guestp.setValue(Guest);
        if (Hostwin) {
            Hostwinp.setValue("Won");
        } else {
            Hostwinp.setValue("Lost");
        }
        datep.setValue(date.toString());
        timep.setValue(time.toString());
        Guestlevelp.setValue(Guestlevel);
        HostCoinp.setValue(HostCoin);
        HostXPp.setValue(HostXP);
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
