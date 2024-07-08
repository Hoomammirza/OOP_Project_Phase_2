package com.example.oop_project_phase2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class AdminMenu {
    public static Scanner input = new Scanner(System.in);
    private static ArrayList<Card> cards;
    public static int run() {
        cards = SQLhandler.getCardsnormal();
        String in;
        boolean quit = false;

        Matcher addCard;
        Matcher showallcards;
        Matcher editCard;
        Matcher removecard;
        Matcher showallplayers;
        Matcher back;
        Matcher showcurrrentmenu;
        Matcher exit;

        System.out.println("commands:\n" +
                "*  add card -n <name> -da <defence/attack> -d <duration> -pd <playerdamage> -u <upgrade level> -uc <upgradecost>\n" +
                "*  edit card -nu <cardnumber> -n <name> -da <defence/attack> -d <duration> -pd <playerdamage> -u <upgrade level> -uc <upgradecost>\n" +
                "*  remove card <number>\n" +
                "*  show all cards\n" +
                "*  show all players\n" +
                "*  exit");

        while (!quit) {
            in = input.nextLine();
            addCard = Misc.getMatcher(in, "^add(\\s+)card(\\s+)-n(\\s+)(?<name>.+)(\\s+)-da(\\s+)(?<defenceattack>\\S+)(\\s+)-d(\\s+)(?<duration>\\S+)(\\s+)-pd(\\s+)(?<playerdamage>\\S+)(\\s+)-u(\\s+)(?<upgradelevel>\\S+)(\\s+)-uc(\\s+)(?<upgradecost>\\S+)(\\s*)$");
            showallcards = Misc.getMatcher(in, "^show all cards$");
            editCard = Misc.getMatcher(in, "^edit(\\s+)card(\\s+)-nu(\\s+)(?<cardnumber>\\S+)(\\s+)-n(\\s+)(?<name>.+)(\\s+)-da(\\s+)(?<defenceattack>\\S+)(\\s+)-d(\\s+)(?<duration>\\S+)(\\s+)-pd(\\s+)(?<playerdamage>\\S+)(\\s+)-u(\\s+)(?<upgradelevel>\\S+)(\\s+)-uc(\\s+)(?<upgradecost>\\S+)(\\s*)$");
            removecard = Misc.getMatcher(in, "^remove(\\s+)card(\\s+)(?<cardnumber>\\d+)(\\s*)$");
            showallplayers = Misc.getMatcher(in, "^show all players$");
            back = Misc.getMatcher(in, "^back(\\s*)$");
            exit = Misc.getMatcher(in, "^exit(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");

            if (addCard.find()) {
                addCard(addCard);
            } else if (showallcards.find()) {
                showallcards();
            } else if (editCard.find()) {
                editcard(editCard);
            } else if (removecard.find()) {
                removecard(removecard);
            } else if (showallplayers.find()) {
                showallusers();
            } else if (showcurrrentmenu.find()) {
                System.out.println("ProfileMenu");
            } else if (back.find()) {
                return 1;
            } else if (exit.find()) {
                return 0;
            }else {
                System.out.println("invalid command");
            }
        }
        return 0;
    }
    public static void addCard(Matcher matcher){
        String name = matcher.group("name" );
        String defenceattack = matcher.group("defenceattack" );
        String duration = matcher.group("duration" );
        String playerdamage = matcher.group("playerdamage" );
        String upgradelevel = matcher.group("upgradelevel" );
        String upgradecost = matcher.group("upgradecost" );
        int defenceattackn;
        int durationn;
        int playerdamagen;
        int upgradeleveln;
        int upgradecostn;
        try {
            defenceattackn = Integer.parseInt(defenceattack);
            durationn = Integer.parseInt(duration);
            playerdamagen = Integer.parseInt(playerdamage);
            upgradeleveln = Integer.parseInt(upgradelevel);
            upgradecostn = Integer.parseInt(upgradecost);
        }catch (Exception e){
            System.out.println("some fields have invalid input");
            return;
        }
        if (!SQLhandler.existsCard(name)){
            if (defenceattackn >= 10 && defenceattackn <=100){
                if (1 <= durationn && 5>= durationn){
                    if (playerdamagen <= 50 && playerdamagen >= 10){
                        if (upgradeleveln>=1){
                            SQLhandler.addCard(name,defenceattackn,durationn,playerdamagen,upgradeleveln,upgradecostn);
                            cards = SQLhandler.getCardsnormal();
                            System.out.println("card added successfully !");
                        }else {
                            System.out.println("invalid upgrade level");
                        }
                    }else {
                        System.out.println("invalid player damage");
                    }
                }else {
                    System.out.println("invalid duration");
                }
            }else {
                System.out.println("invalid defence attack");
            }
        }else {
            System.out.println("this card already exists!");
        }
    }
    public static void showallcards(){
        System.out.println("index   name   attack/defence   duration   damage   upgrade cost   minimum level");
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            System.out.println("-"+(i+1)+"   "+card.name+"   "+card.defence_attack+"   "+card.Duration+"   "+card.playerDamage+"   "+card.upgradeCost+"   "+card.upgradeLevel);
        }
    }
    public static void editcard(Matcher matcher){
        String originalnumber = matcher.group("cardnumber");
        String name = matcher.group("name" );
        String defenceattack = matcher.group("defenceattack" );
        String duration = matcher.group("duration" );
        String playerdamage = matcher.group("playerdamage" );
        String upgradelevel = matcher.group("upgradelevel" );
        String upgradecost = matcher.group("upgradecost" );
        int defenceattackn;
        int durationn;
        int playerdamagen;
        int upgradeleveln;
        int upgradecostn;
        int cardnumber;
        try {
            defenceattackn = Integer.parseInt(defenceattack);
            durationn = Integer.parseInt(duration);
            playerdamagen = Integer.parseInt(playerdamage);
            upgradeleveln = Integer.parseInt(upgradelevel);
            upgradecostn = Integer.parseInt(upgradecost);
            cardnumber = Integer.parseInt(originalnumber);

        }catch (Exception e){
            System.out.println("some fields have invalid input");
            return;
        }
        if (!(cardnumber>=1 &&cardnumber<=cards.size())){
            System.out.println("invalid card number!");
            return;
        }
        if (!SQLhandler.existsCard(name)){
            if (defenceattackn >= 10 && defenceattackn <=100){
                if (1 <= durationn && 5>= durationn){
                    if (playerdamagen <= 50 && playerdamagen >= 10){
                        if (upgradeleveln>=1){
                            System.out.println("you are about to edit this card are you sure(y|n)?");
                            String in = new Scanner(System.in).nextLine();
                            if (in.equals("y")) {
                                SQLhandler.edditcard(name, defenceattackn, durationn, playerdamagen, upgradeleveln, upgradecostn,cards.get(cardnumber-1).name);
                                cards = SQLhandler.getCardsnormal();
                                System.out.println("card edited successfully !");
                            }else {
                                System.out.println("edit cancelled");
                            }
                        }else {
                            System.out.println("invalid upgrade level");
                        }
                    }else {
                        System.out.println("invalid player damage");
                    }
                }else {
                    System.out.println("invalid duration");
                }
            }else {
                System.out.println("invalid defence attack");
            }
        }else {
            System.out.println("this card already exists!");
        }
    }
    public static void removecard(Matcher matcher){
        String originalnumber = matcher.group("cardnumber");
        int cardnumber;
        try {
            cardnumber = Integer.parseInt(originalnumber);
        }catch (Exception e){
            System.out.println("some fields have invalid input");
            return;
        }
        SQLhandler.removeCard(cards.get(cardnumber-1).name);
        System.out.println("card removed successfully!");
    }
    private static void showallusers(){
        System.out.println("index   name   level   coins");
        ArrayList<User> users = SQLhandler.getallusers();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.println("-"+(i+1)+"   "+user.Username+"   "+user.Level+"   "+user.Coins);
        }
    }
}
