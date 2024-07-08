package com.example.oop_project_phase2;


import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    public static Scanner input = new Scanner(System.in);
    public static User user;
    public static ArrayList<Card> buycards;
    public static ArrayList<Card> upcards;
    public static int run() {
        user = Users.LoginUser;
        String in;
        boolean quit = false;

        Matcher shownewcards;
        Matcher buycard;
        Matcher upgradeCard;
        Matcher showupcards;
        Matcher back;
        Matcher showcurrrentmenu;
        Matcher exit;

        refreshbuycard();
        refreshupcards();

        System.out.println("commands:\n" +
                "*  show upgradable cards\n" +
                "*  show new cards\n" +
                "*  buy card <number>\n" +
                "*  upgrade card <number>\n" +
                "*  show all players\n" +
                "*  exit");

        while (!quit) {
            in = input.nextLine();
            shownewcards = Misc.getMatcher(in, "^show new cards$");
            buycard = Misc.getMatcher(in, "^buy(\\s+)card(\\s+)(?<number>\\d+)$");
            upgradeCard = Misc.getMatcher(in, "^upgrade(\\s+)card(\\s+)(?<number>\\d+)$");
            showupcards = Misc.getMatcher(in, "^show upgradable cards");
            back = Misc.getMatcher(in, "^back(\\s*)$");
            exit = Misc.getMatcher(in, "^exit(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");

            if (shownewcards.find()) {
                shownewcards();
            } else if (buycard.find()) {
                buycard(buycard);
            } else if (upgradeCard.find()) {
                upgradecard(upgradeCard);
            } else if (showupcards.find()) {
                showupcards();
            } else if (showcurrrentmenu.find()) {
                System.out.println("Shop Menu");
            } else if (back.find()) {
                return 4;
            } else if (exit.find()) {
                return 0;
            }else {
                System.out.println("invalid command");
            }
        }
        return 0;
    }
    public static void refreshbuycard(){
        buycards = new ArrayList<>();
        for (Card card:SQLhandler.getallcards()){
            boolean temp = false;
            for (Card usercard: SQLhandler.getUsercards(user)){
                if ( usercard.name.equals(card.name)){
                    temp = true;
                    break;
                }
            }
            if (!temp)
                buycards.add(card);
        }
    }
    public static void shownewcards(){
        refreshbuycard();
        System.out.println("you have "+user.Coins+" Coins!");
        System.out.println("index   name   attack/defence   duration   damage   base upgrade cost   minimum level   cost");
        for (int i = 0; i < buycards.size(); i++) {
            Card card = buycards.get(i);
            System.out.println("-"+(i+1)+"   "+card.name+"   "+card.defence_attack+"   "+card.Duration+"   "+card.playerDamage+"   "+card.upgradeCost+"   "+card.upgradeLevel+"   "+calculatecost(card));
        }
    }

    public static int calculatecost(Card card){
        int cost = 0;
        cost += card.defence_attack/2;
        cost += card.Duration*3;
        cost += card.playerDamage;
        if (card.feature != null){
            cost = 200;
        }
        return cost;
    }
    public static int calculateupcost(Card card){
        return (int)(card.upgradeCost*Math.pow(1.25,SQLhandler.getCardlevel(user,card.name)));
    }
    public static void buycard(Matcher matcher){
        int number = Integer.parseInt(matcher.group("number"));
        if (calculatecost(buycards.get(number-1))<=user.Coins){
            user.Coins -= calculatecost(buycards.get(number-1));
            SQLhandler.giveCard(buycards.get(number-1),user,1);
            buycards.remove(number-1);
            refreshbuycard();
            SQLhandler.updateUser(user);
            System.out.println("card bought successfully!");
        }else {
            System.out.println("you dont have enough coins!");
        }
    }
    public static void refreshupcards(){
        upcards = SQLhandler.getUsercardsupgradable(user);
    }
    public static void showupcards(){
        refreshupcards();
        System.out.println("you have "+user.Coins+" Coins!");
        System.out.println("index   name   attack/defence   duration   damage   minimum level   card level   current upgrade cost");
        for (int i = 0; i < upcards.size(); i++) {
            Card card = upcards.get(i);
            System.out.println("-"+(i+1)+"   "+card.name+"   "+card.defence_attack+"   "+card.Duration+"   "+card.playerDamage+"   "+card.upgradeLevel+"   "+SQLhandler.getCardlevel(user,card.name)+"   "+(calculateupcost(card)));
        }
    }
    public static void upgradecard(Matcher matcher){
        int number = Integer.parseInt(matcher.group("number"));
        if (calculateupcost(upcards.get(number-1))<=user.Coins){
            user.Coins -= calculateupcost(upcards.get(number-1));
            SQLhandler.updateCard(user,upcards.get(number-1).name,SQLhandler.getCardlevel(user,upcards.get(number-1).name)+1);
            upcards.remove(number-1);
            refreshbuycard();
            SQLhandler.updateUser(user);
            System.out.println("card upgraded successfully!");
        }else {
            System.out.println("you dont have enough coins!");
        }
    }

}
