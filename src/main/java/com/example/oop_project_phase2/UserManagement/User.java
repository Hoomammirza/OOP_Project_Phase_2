package com.example.oop_project_phase2.UserManagement;


import com.example.oop_project_phase2.card.Card;

import java.util.ArrayList;

public class User {
    public String Username;
    public String Password;
    public String Nickname;
    public String Email;
    public String SecurityQA;
    public securityQ SecurityQ;
    public int Level = 1;
    public int maxHP = 100;
    public int Coins = 100;
    public int XP = 0;
    public boolean isAdmin = false;
    public ArrayList<Card> cards;
    public  User(String Username,String Password,String Nickname,String Email,String SecurityQ,String SecurityQA,boolean isAdmin){
        this.Username = Username;
        this.Password = Password;
        this.Nickname = Nickname;
        this.Email = Email;
        this.SecurityQ = securityQ.valueOf(SecurityQ);
        this.SecurityQA = SecurityQA;
        this.isAdmin = isAdmin;
    }
    public  User(String Username,String Password,String Nickname,String Email,String SecurityQ,String SecurityQA,boolean isAdmin,int Level,int Coins,int XP){
        this.Username = Username;
        this.Password = Password;
        this.Nickname = Nickname;
        this.Email = Email;
        this.SecurityQ = securityQ.valueOf(SecurityQ);
        this.SecurityQA = SecurityQA;
        this.isAdmin = isAdmin;
        this.Level = Level;
        this.Coins = Coins;
        this.XP = XP;
    }


    //Game=====================================================================================================

    public int hitpoint;
    public Character character;
    public boolean become4CardInHand=false;
    public boolean become6CardInHand=false;
    public boolean become6CardInHandOneTime=false;
    public ArrayList<Card> hand;
    public ArrayList<ArrayList<String>> comeInHound=new ArrayList<ArrayList<String>>();
    public int gamescore;
    public boolean visibleCard=true;
    public Card[] timeline = new Card[21];
    public enum securityQ{
        Father,
        Color,
        Pet;
    }
    public static int getMaxHp(int level)
    {
        int MaxHp=(int) (150*Math.sqrt(1.1*level));
        return MaxHp;
    }
    public static int updateLevel(int level,int Xp)
    {
        while (Xp>=150*level*level)
        {
            Xp-=150*level*level;
            level++;
        }
        return level;
    }
    public static int updateXp(int level,int Xp)
    {
        while (Xp>=150*level*level)
        {
            Xp-=150*level*level;
            level++;
        }
        return Xp;
    }
    public enum Character{
        Gunner,
        Fighter,
        Dancer,
        Wizard;
    }
}
