package com.example.oop_project_phase2.Game;


import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {
    @FXML
    GridPane tableGame,handhost,handguest;
    @FXML
    static ImageView [] [] cardImage=new ImageView[2][21];
    @FXML
    static ImageView [] handhostImage=new ImageView[6];
    @FXML
    static ImageView [] handGuestImage=new ImageView[6];
    @FXML
    ImageView imageHost,imageGuest;
    @FXML
    Label hostname,guestname;
    public void initialize(){

        setTableGame();
        GameController.emptyCell(Gameinit.Host,Gameinit.Guest);
        setCharacter(Gameinit.Host,Gameinit.Guest);
        GameController.run(Gameinit.Host,Gameinit.Guest);
        startHand();
    }
    static Scanner input=new Scanner(System.in);
    public static void timelineInputOutput(User Host,User Guest){
        boolean exit=false;
        String in;
        while (!exit)
        {
            System.out.println("command: \n"+
                    "show game information"+"\n"
                    +"-select card number <n> player <x>"+"\n"
                    +"-placing card number <n>"+"\n"
                    +"-placing card number <n> to copy <i>"+"\n"
                    +"-placing card number <n> in block <i>");
            in=input.nextLine();
            Matcher showInformation=Misc.getMatcher(in, "show game information");
            Matcher showCardInformation=Misc.getMatcher(in, "^select card number (?<n>\\S+) player (?<x>\\S+)");
            Matcher selectCardWithSpace=Misc.getMatcher(in, "^placing card number (?<n>\\S+) in block (?<i>\\S+)");
            Matcher selectCardWithNoSpace=Misc.getMatcher(in, "^placing card number (?<n>\\S+)");
            Matcher selectDuplicateCard=Misc.getMatcher(in, "^placing card number (?<n>\\S+) to copy (?<i>\\S+)");
            if(showInformation.find())
            {
                showGameInformation(Host,Guest);
            }
            else if(showCardInformation.find())
            {
                int n=Integer.parseInt(showCardInformation.group("n"));
                String x=showCardInformation.group("x");
                showInformationCard(Host,Guest,n-1,x);
            }
            else if(selectCardWithSpace.find())
            {
                int n=Integer.parseInt(selectCardWithSpace.group("n"));
                int i=Integer.parseInt(selectCardWithSpace.group("i"));
                if(TimelineController.setCardInGameWithSpace(Host,Guest,n-1,i-1))
                {
                    exit=true;
                }

            }
            else if(selectDuplicateCard.find())
            {
                int n=Integer.parseInt(selectDuplicateCard.group("n"));
                int i=Integer.parseInt(selectDuplicateCard.group("i"));
                if(TimelineController.SetDuplicator(Host,Guest,n-1,i-1))
                {
                    exit=true;
                }
            }
            else if(selectCardWithNoSpace.find())
            {
                int n=Integer.parseInt(selectCardWithNoSpace.group("n"));
                if(TimelineController.setCardInGameWithNoSpace(Host,Guest,n-1))
                {
                    exit=true;
                }
            }
            else
            {
                System.out.println("Incorrect command");
            }
        }
    }
    public static void showGameInformation(User host,User guest)
    {
        String a="";
        String b="";
        for (int i=0;i<host.hand.size();i++)
        {
            String c="";
            c+="name: "+host.hand.get(i).name+"  ";
            if(host.hand.get(i).feature!=null)
            {
                c+="spell card ";
            }
            else
            {
                c+="damage/healing card  ";
                c+="Duration:  "+host.hand.get(i).Duration+"  ";
                int l=host.hand.get(i).playerDamage*host.hand.get(i).Duration;
                c+="player Damage:  "+l+"  ";
                c+="attack defence point:  "+host.hand.get(i).defence_attack;
            }
            System.out.println(c);
        }
        if(host.visibleCard)
        {
            for(int i=0;i<21;i++)
            {
                if(host.timeline[i]==null)
                {
                    String c="empty";
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    a+=c;
                }
                else if(!Objects.equals(host.timeline[i].name, "empty"))
                {
                    String c=host.timeline[i].name;
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    a+=c;
                }
                else if(Objects.equals(host.timeline[i].name,"empty")&& host.timeline[i].cardReference!=null)
                {
                    String c=host.timeline[i].cardReference.name;
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    a+=c;
                }
                else
                {
                    String c="Wall";
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    a+=c;
                }
            }
        }
        int z=host.maxHP-host.hitpoint;
        System.out.println(host.Nickname+"  damage:  "+z+"  hit point:  "+host.hitpoint+"  character:  "+host.character);
        if(host.visibleCard)
        {
            for(int i=0;i<21;i++)
            {
                if(guest.timeline[i]==null)
                {
                    String c="empty";
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    b+=c;
                }
                else if(!Objects.equals(guest.timeline[i].name, "empty"))
                {
                    String c=guest.timeline[i].name;
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    b+=c;
                }
                else if(Objects.equals(guest.timeline[i].name,"empty")&& guest.timeline[i].cardReference!=null)
                {
                    String c=guest.timeline[i].cardReference.name;
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    b+=c;
                }
                else
                {
                    String c="Wall";
                    while (c.length()<17)
                    {
                        c+=" ";
                        c.length();
                    }
                    b+=c;
                }
            }
        }
        System.out.println(a);
        System.out.println(b);
        for (int i=0;i<guest.hand.size();i++)
        {
            String c="";
            c+="name: "+guest.hand.get(i).name+"  ";
            if(guest.hand.get(i).feature!=null)
            {
                c+="spell card ";
            }
            else
            {
                c+="damage/healing card  ";
                int l=guest.hand.get(i).playerDamage*guest.hand.get(i).Duration;
                c+="Duration:  "+guest.hand.get(i).Duration+"  ";
                c+="player Damage:  "+l+"  ";
                c+="attack defence point:  "+guest.hand.get(i).defence_attack;
            }
            System.out.println(c);
        }
        z=guest.maxHP-guest.hitpoint;
        System.out.println(guest.Nickname+"  damage:  "+z+"  hit point:  "+guest.hitpoint+"  character:  "+guest.character);
        System.out.println("round left:  "+GameController.round);
    }
    public static void showInformationCard(User host,User guest,int n,String name)
    {
        if(Objects.equals(host.Nickname, name))
        {
            if(host.hand.size()>n)
            {
                String c="";
                c+="name: "+host.hand.get(n).name+"  ";
                if(host.hand.get(n).feature!=null)
                {
                    c+="spell card ";
                    c+="feature:  "+host.hand.get(n).feature;
                }
                else
                {
                    c+="damage/healing card  ";
                    c+="Duration:  "+host.hand.get(n).Duration+"  ";
                    int l=host.hand.get(n).playerDamage*host.hand.get(n).Duration;
                    c+="player Damage:  "+l+"  ";
                    c+="attack defence point:  "+host.hand.get(n).defence_attack;
                }
                System.out.println(c);
            }
            else
            {
                System.out.println("out of bounds");
            }
        }
        else if(Objects.equals(guest.Nickname, name))
        {
            if(guest.hand.size()>n)
            {
                String c="";
                c+="name: "+guest.hand.get(n).name+"  ";
                if(guest.hand.get(n).feature!=null)
                {
                    c+="spell card ";
                }
                else
                {
                    int l=guest.hand.get(n).playerDamage*guest.hand.get(n).Duration;
                    c+="damage/healing card  ";
                    c+="Duration:  "+guest.hand.get(n).Duration+"  ";
                    c+="player Damage:  "+l+"  ";
                    c+="attack defence point:  "+guest.hand.get(n).defence_attack;
                }
                System.out.println(c);
            }
            else
            {
                System.out.println("out of bounds");
            }
        }
        else
        {
            System.out.println("incorrect nickname");
        }
    }
    public void setTableGame()
    {
        for (int i=0;i<2;i++)
        {
            for(int j=0;j<21;j++)
            {
                try {
                    cardImage[i][j]=new ImageView(resourceManagement.cell);
                    cardImage[i][j].setFitWidth(45);
                    cardImage[i][j].setFitHeight(78);
                    tableGame.add(cardImage[i][j],j,i);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    public void setCharacter(User Host,User Guest)
    {
        if(Host.character==User.Character.valueOf("Gunner"))
        {
            imageHost.setImage(resourceManagement.gunner);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);

        }
        else if(Host.character==User.Character.valueOf("Fighter"))
        {
            imageHost.setImage(resourceManagement.fighter);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        else if(Host.character==User.Character.valueOf("Wizard"))
        {
            imageHost.setImage(resourceManagement.wizard);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        else if(Host.character==User.Character.valueOf("Dancer"))
        {
            imageHost.setImage(resourceManagement.dancer);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        if(Guest.character==User.Character.valueOf("Gunner"))
        {
            imageGuest.setImage(resourceManagement.gunner);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Guest.character==User.Character.valueOf("Fighter"))
        {
            imageGuest.setImage(resourceManagement.fighter);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Guest.character==User.Character.valueOf("Wizard"))
        {
            imageGuest.setImage(resourceManagement.wizard);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Host.character==User.Character.valueOf("Dancer"))
        {
            imageHost.setImage(resourceManagement.dancer);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
    }
    public  static void showHand(User Host,User Guest)
    {
        for(int i=0;i<Host.hand.size();i++)
        {
            handhostImage[i].setImage(resourceManagement.getImageCard(Host.hand.get(i).name));
        }
        for (int i=Host.hand.size();i<6;i++)
        {
            handhostImage[i].setImage(resourceManagement.wall);
        }
        for(int i=0;i<Guest.hand.size();i++)
        {
            handGuestImage[i].setImage(resourceManagement.getImageCard(Guest.hand.get(i).name));
        }
        for (int i=Guest.hand.size();i<6;i++)
        {
            handGuestImage[i].setImage(resourceManagement.wall);
        }
    }
    public void startHand()
    {
        for(int i=0;i<6;i++)
        {
         handhostImage[i]=new ImageView(resourceManagement.wall);
         handhostImage[i].setFitWidth(86);
         handhostImage[i].setFitHeight(89);
         handhost.add(handhostImage[i],i,1);
        }
        for(int i=0;i<6;i++)
        {
            handGuestImage[i]=new ImageView(resourceManagement.wall);
            handGuestImage[i].setFitWidth(86);
            handGuestImage[i].setFitHeight(89);
            handguest.add(handGuestImage[i],i,1);
        }
    }
}
