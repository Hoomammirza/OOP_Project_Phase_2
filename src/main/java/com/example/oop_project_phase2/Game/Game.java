package com.example.oop_project_phase2.Game;


import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.*;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {
    public static boolean wager;
    public static int wagerint;
    public static User Host,Guest;
    private static Scanner input = new Scanner(System.in);
    public static int run() {
        initGuest();
        if (wager)
            pickwager();
        GameController.run(Host,Guest);
        return 4;
    }
    public static void pickwager(){
        System.out.println("Please select you wager Player 1:");
        boolean quit = false;
        Matcher selectwager;
        Matcher showcurrrentmenu;

        System.out.println("commands:\n" +
                "*  select wager <wager>\n"
                + "*  show current menu ");

        while (!quit) {
            String in = input.nextLine();
            selectwager = Misc.getMatcher(in, "^select(\\s+)wager(\\s+)(?<wager>\\d+)(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
            if (selectwager.find()) {
                if (selectwager(selectwager))
                    quit = true;
            } else if (showcurrrentmenu.find()) {
                System.out.println("Game menu: Character Select");
            }
        }
    }
    public static boolean selectwager(Matcher matcher){
        int wager = Integer.parseInt(matcher.group("wager"));
        if (wager <= Host.Coins && Guest.Coins>= wager){
            wagerint = wager;
            return true;
        }else {
            System.out.println("wager too high!");
            return false;
        }
    }

    public static void initGuest() {
        Host = Users.LoginUser;
        boolean quit = false;
        Matcher login;
        Matcher showcurrrentmenu;

        System.out.println("commands:\n" +
                "*  user login -u <username> -p <password>\n"
                + "*  show current menu ");

        while (!quit) {
            String in = input.nextLine();
            login = Misc.getMatcher(in, "^user(\\s+)login(\\s+)-u(\\s+)(?<username>\\S+)(\\s+)-p(\\s+)(?<password>\\S+)(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
            if (login.find()) {
                if (login(login.group("username"), login.group("password")))
                    quit = true;
            } else if (showcurrrentmenu.find()) {
                System.out.println("Game menu: login");
            }
        }
        //=================================================================================================================
        System.out.println("Please select you character Player 1:\n" +
                "Gunner , Fighter , Dancer , Wizard ");
        quit = false;
        Matcher selectCharacter;

        System.out.println("commands:\n" +
                "*  select <character>\n"
                + "*  show current menu ");

        while (!quit) {
            String in = input.nextLine();
            selectCharacter = Misc.getMatcher(in, "^select(\\s+)(?<character>\\S+)(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
            if (selectCharacter.find()) {
                if (SelectCharacter(Host,selectCharacter))
                    quit = true;
            } else if (showcurrrentmenu.find()) {
                System.out.println("Game menu: Character Select");
            }
        }
        //=================================================================================================================
        System.out.println("Please select you character Player 2:\n" +
                "Gunner , Fighter , Dancer , Wizard ");
        quit = false;

        System.out.println("commands:\n" +
                "*  select <character>\n"
                + "*  show current menu ");

        while (!quit) {
            String in = input.nextLine();
            selectCharacter = Misc.getMatcher(in, "^select(\\s+)(?<character>\\S+)(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
            if (selectCharacter.find()) {
                if (SelectCharacter(Guest,selectCharacter))
                    quit = true;
            } else if (showcurrrentmenu.find()) {
                System.out.println("Game menu: Character Select");
            }
        }

    }
    private static boolean login(String username,String password){
        try {
            User user = SQLhandler.readUser(username,password);
            if (user.Username.equals(Host.Username)){
                System.out.println("you can not play against yourself!");
                return false;
            }
            Guest=user;
            return true;
        } catch (NoUserException e) {
            String s = e.toString();
            System.out.println("Username doesn't exist!");
            return false;
        } catch (PasswordExeption e) {
            String s = e.toString();
            System.out.println("Password and Username don’t match!");
            return false;
        }
    }
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
    public static boolean SelectCharacter(User user,Matcher matcher){
        String character = matcher.group("character");
        switch (character){
            case "Gunner":
                user.character = User.Character.valueOf(character);
                break;
            case "Fighter":
                user.character = User.Character.valueOf(character);
                break;
            case "Dancer":
                user.character = User.Character.valueOf(character);
                break;
            case "Wizard":
                user.character = User.Character.valueOf(character);
                break;
            case "Default":
                System.out.println("invalid character name!");
                return false;
        }
        return true;
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
}
//150*sqrt(x)
//150*xp^2