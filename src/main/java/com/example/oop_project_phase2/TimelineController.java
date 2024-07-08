package com.example.oop_project_phase2;


import java.util.Objects;
import java.util.Random;

public  class TimelineController {
    public static void doCard(User Host,User Guest,String feauture)
    {
        if(CardsController.doActionNow(feauture))
        {
            CardsController.feature(feauture,Host,Guest,GameController.round);
        }
    }
    public static void doCard(User Host,User Guest,int i)
    {
        if(CardsController.doActionNow(Host.timeline[i].feature))
        {
           CardsController.featureShield(Host.timeline[i].feature,Guest,i);
        }
    }
    public static void doCard(User Host,User Guest,int n,String feature)
    {
        if(CardsController.doActionNow(feature))
        {
            CardsController.featureDuplicator(Host,feature,n);
        }
    }
    public static void reduceHitpoint(User Host,User Guest,int i)
    {
        if(Guest.timeline[i]!=null)
        {
            Host.hitpoint-=Guest.timeline[i].playerDamage;
        }
        if(Host.timeline[i]!=null)
        {
            Guest.hitpoint-=Host.timeline[i].playerDamage;
        }
    }
    public static boolean setCardInGameWithSpace(User host,User Guest,int n,int i)
    {
            if(host.hand.size()>n)
            {
                if(host.hand.get(n).Duration!=0) {
                    if (i + host.hand.get(n).Duration >= 21) {
                        System.out.println("out of bounds!");
                    } else {
                        boolean empty = true;
                        for (int j = i; j < i + host.hand.get(n).Duration && empty; j++) {
                            if (host.timeline[j] != null) {
                                empty = false;
                            }
                        }
                        if (empty) {
                            host.timeline[i] = host.hand.get(n);
                            host.timeline[i].upgradeCharacter(host);
                            for (int j = i + 1; j < i + host.hand.get(n).Duration && empty; j++) {
                                host.timeline[j] = new Card(host.timeline[i]);
                            }
                            doCard(host, Guest, i);
                            cardVsCard(host, Guest, i);
                            host.hand.remove(n);
                            return true;
                        } else {
                            System.out.println("this cell is full!");
                        }

                    }
                }
                else
                {
                    System.out.println("this card has no Duration");
                }
            }
            else
            {
                System.out.println("out of hand bounds");
            }
        return false;
    }
    public static boolean setCardInGameWithNoSpace(User host,User Guest,int n)
    {
        if(host.hand.size()>n)
        {
            if(host.hand.get(n).Duration==0)
            {
                doCard(host,Guest,host.hand.get(n).feature);
                host.hand.remove(n);
                return true;
            }
            else
            {
                System.out.println("this Card have Duration");
            }
        }
        else
        {
            System.out.println("out of hand bounds");
        }
        return false;
    }
    public static boolean SetDuplicator(User host,User Guest,int n,int i)
    {
        if(host.hand.size()>n)
        {
            if(host.hand.get(n).Duration==0)
            {
                if(Objects.equals(host.hand.get(n).feature, "duplicator"))
                {
                    if(host.hand.size()>i)
                    {
                        doCard(host,Guest,i,host.hand.get(n).feature);
                        host.hand.remove(n);
                        return true;
                    }
                    else
                    {
                        System.out.println("out of bounds");
                    }
                }
                else
                {
                    System.out.println("this card is not duplicator");
                }
            }
            else
            {
                System.out.println("this Card have Duration");
            }
        }
        else
        {
            System.out.println("out of hand bounds");
        }
        return false;
    }
    public static void cardVsCard(User host,User Guest,int i)
    {
        if(Guest.timeline[i]!=null)
        {
            if(Guest.timeline[i].Duration>1 && host.timeline[i].Duration>= Guest.timeline[i].Duration && !Objects.equals(Guest.timeline[i].name, "empty"))
            {
                if(host.timeline[i].defence_attack>Guest.timeline[i].defence_attack)
                {
                    for (int j=0;j<host.timeline[i].Duration;j++)
                    {
                        if(Guest.timeline[i+j]!=null)
                        {
                            Guest.timeline[i+j].playerDamage=0;
                        }
                    }
                    getBonos(host);
                }
                else if(host.timeline[i].defence_attack==Guest.timeline[i].defence_attack)
                {
                    for (int j=0;j<host.timeline[i].Duration;j++) {
                        if (Guest.timeline[i + j] != null) {
                            host.timeline[i+j].playerDamage=0;
                            Guest.timeline[i+j].playerDamage=0;
                        }
                    }
                }
                else if(Guest.timeline[i].defence_attack>host.timeline[i].defence_attack)
                {
                    for (int j=0;j<host.timeline[i].Duration;j++) {
                        if (Guest.timeline[i + j] != null) {
                            host.timeline[i+j].playerDamage=0;
                        }
                    }
                }
            }
            else
            {
                for (int j=0;j<host.timeline[i].Duration;j++)
                {
                    if(Guest.timeline[i+j]!=null)
                    {
                        if(host.timeline[i+j].defence_attack>Guest.timeline[i+j].defence_attack)
                        {
                            Guest.timeline[i+j].playerDamage=0;
                        }
                        else if(Guest.timeline[i+j].defence_attack>host.timeline[i+j].defence_attack)
                        {
                            host.timeline[i+j].playerDamage=0;
                        }
                        else
                        {
                            host.timeline[i+j].playerDamage=0;
                            Guest.timeline[i+j].playerDamage=0;
                        }
                    }
                }
            }
        }
    }
    public static void getBonos(User host)
    {
        Random random=new Random();
        int a=random.nextInt(10);
        if(a==1 || a==2)
        {
            host.XP+=10;
        }
        else if(a==3||a==4||a==5||a==6)
        {
            host.Coins+=5;
        }
        else
        {
            if(host.hand.size()<6)
            {
                GameController.getNewCardInHand(host);
            }
        }
    }
}
