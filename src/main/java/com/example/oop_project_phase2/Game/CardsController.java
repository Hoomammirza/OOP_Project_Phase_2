package com.example.oop_project_phase2.Game;


import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.card.Card;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class CardsController {
    public static void feature(String feature, User host, User guest, int round)
    {
        if(Objects.equals(feature,"healing"))
        {
            host.hitpoint+=20;
        }
        else if(Objects.equals(feature,"randomBuff"))
        {
            Random random=new Random();
            int a=random.nextInt(21);
            boolean empty=false;
            for (int i=0;i<21;i++)
            {
                if(host.timeline[i]!=null )
                {
                    if(!Objects.equals(host.timeline[i].name, "empty"))
                    {
                        empty=true;
                    }
                }
            }
            while (empty)
            {
                if(host.timeline[a]!=null)
                {
                    if(!Objects.equals(host.timeline[a].name, "empty"))
                    {
                        for(int j=0;j<host.timeline[a].Duration;j++)
                        {
                            host.timeline[a+j].defence_attack=(int) (host.timeline[a+j].defence_attack*1.2);
                            empty=false;
                        }
                    }
                }
                if(empty)
                {
                    a=random.nextInt(21);
                }
            }
        }
        else if(Objects.equals(feature,"changeHole"))
        {
            Random random=new Random();
            int a=random.nextInt(2);
            if(a==0)
            {
                Boolean empty=false;
                a=random.nextInt(21);
                for (int j=0;j<21 && !empty;j++)
                {
                    if(host.timeline[j]!=null)
                    {
                        if(Objects.equals(host.timeline[j].name, "empty") && host.timeline[j].cardReference==null)
                        {
                            host.timeline[j]=null;
                            empty=true;
                        }
                    }
                }
                empty=false;
                while (!empty)
                {
                    if(host.timeline[a]==null)
                    {
                        host.timeline[a]=new Card(null);
                        empty=true;
                    }
                    a=random.nextInt(21);
                }
            }
            else if(a==1)
            {
                Boolean empty=false;
                a=random.nextInt(21);
                for (int j=0;j<21 && !empty;j++)
                {
                    if(guest.timeline[j]!=null)
                    {
                        if(Objects.equals(guest.timeline[j].name, "empty") && guest.timeline[j].cardReference==null)
                        {
                            guest.timeline[j]=null;
                            empty=true;
                        }
                    }
                }
                empty=false;
                while (!empty)
                {
                    if(guest.timeline[a]==null)
                    {
                        guest.timeline[a]=new Card(null);
                        empty=true;
                    }
                    a=random.nextInt(21);
                }
            }
        }
        else if(Objects.equals(feature, "pairHole"))
        {
            Boolean empty=false;
            for (int j=0;j<21 && !empty;j++)
            {
                if(host.timeline[j]!=null)
                {
                    if(Objects.equals(host.timeline[j].name, "empty") && host.timeline[j].cardReference==null)
                    {
                        host.timeline[j]=null;
                        empty=true;
                    }
                }
            }
        }
        else if(Objects.equals(feature,"reduceRound"))
        {
            GameController.round--;
        }
        else if(Objects.equals(feature,"removeCardFromHand"))
        {
            Random random=new Random();
            int a=random.nextInt(guest.hand.size());
            Card card=guest.hand.get(a);
            guest.hand.remove(a);
            guest.become4CardInHand=true;
            if(host.hand.size()<6)
            {
                host.hand.add(card);
                guest.become6CardInHandOneTime=true;
            }

        }
        else if(Objects.equals(feature,"reducePowerOpponentCard"))
        {
            Random random=new Random();
            int a=random.nextInt(21);
            Boolean empty=true;
            int z=0;
            for (int i=0;i<21;i++)
            {
                if(guest.timeline[i]!=null)
                {
                    if(!Objects.equals(guest.timeline[i].name,"empty"))
                    {
                        z++;
                    }
                }
            }
            if(z>0)
            {
                while (empty)
                {
                    if(guest.timeline[a]!=null)
                    {
                        if(!Objects.equals(guest.timeline[a].name, "empty"))
                        {
                            for(int j=0;j<guest.timeline[a].Duration;j++)
                            {
                                guest.timeline[a+j].playerDamage=(int) (guest.timeline[a+j].playerDamage*0.6);
                                empty=false;
                            }
                        }
                    }
                    if(empty)
                    {
                        a=random.nextInt(21);
                    }
                }
                empty=true;
                int b=random.nextInt(21);
                if(z>1)
                {
                    while (empty)
                    {
                        if(a!=b)
                        {
                            if(guest.timeline[b]!=null)
                            {
                                if(!Objects.equals(guest.timeline[b].name, "empty"))
                                {
                                    for (int j=0;j<guest.timeline[b].Duration;j++)
                                    {
                                        guest.timeline[b+j].defence_attack=(int) (guest.timeline[b+j].defence_attack*0.6);
                                        empty=false;
                                    }
                                }
                            }
                        }
                        if(empty)
                        {
                            b=random.nextInt(21);
                        }
                    }
                }
            }
        }
        else if(Objects.equals(feature, "hider"))
        {
            guest.visibleCard=false;
            ArrayList<ArrayList<Integer>> duration=new ArrayList<ArrayList<Integer>>();
            duration.add(new ArrayList<Integer>());
            duration.add(new ArrayList<Integer>());
            duration.add(new ArrayList<Integer>());
            duration.add(new ArrayList<Integer>());
            duration.add(new ArrayList<Integer>());
            for(int j=0;j<21;j++)
            {
                if(guest.timeline[j]!=null)
                {
                    if(!Objects.equals(guest.timeline[j].name, "empty"))
                    {
                        duration.get(guest.timeline[j].Duration).add(j);
                    }
                }
            }
            Random random=new Random();
            int a=random.nextInt(5);
            boolean change=true;
            for (int i=0;i<duration.size();i++)
            {
                if(duration.get(i).size()>=2)
                {
                    change=false;
                }
            }
            while (!change)
            {
                if(duration.get(a).size()>=2)
                {
                    int b=random.nextInt(duration.get(a).size());
                    int c=random.nextInt(duration.get(a).size());
                    while (b==c)
                    {
                        c=random.nextInt(duration.get(a).size());
                    }
                    Card card1=guest.timeline[duration.get(a).get(b)];
                    Card card2=guest.timeline[duration.get(a).get(c)];
                    for (int j=0;j<card1.Duration;j++)
                    {
                        guest.timeline[duration.get(a).get(b)+j]=null;
                    }
                    for (int j=0;j<card2.Duration;j++)
                    {
                        guest.timeline[duration.get(a).get(c)+j]=null;
                    }
                    guest.timeline[duration.get(a).get(c)]=card1;
                    for(int j=1;j<card1.Duration;j++)
                    {
                        guest.timeline[duration.get(a).get(c)+j]=new Card(card1);
                    }
                    guest.timeline[duration.get(a).get(b)]=card2;
                    for(int j=1;j<card1.Duration;j++)
                    {
                        guest.timeline[duration.get(a).get(b)+j]=new Card(card2);
                    }
                    change=true;
                }
                else
                {
                    a=random.nextInt(5);
                }
            }
        }
    }
    public static void featureShield(String feature,User guest,int i)
    {
        if(Objects.equals(feature, "shield"))
        {
            if(guest.timeline[i]!=null)
            {
                guest.timeline[i].playerDamage=0;
            }
        }
    }
    public static void featureDuplicator(User host,String feature,int n)
    {
        if(Objects.equals(feature, "duplicator"))
        {
            if(host.hand.size()<6)
            {
                host.become6CardInHand=true;
                host.hand.add(host.hand.get(n));
            }
        }
    }
    public static boolean doActionNow(String name)
    {
        if(Objects.equals(name,"healing")||Objects.equals(name,"changeHole") || Objects.equals(name,"pairHole") ||Objects.equals(name,"removeCardFromHand")|| Objects.equals(name,"reduceRound")||Objects.equals(name,"reducePowerOpponentCard")||Objects.equals(name,"duplicator")||Objects.equals(name,"hider")||Objects.equals(name,"randomBuff")||Objects.equals(name,"shield"))
        {
            return true;
        }
        return false;
    }
}
