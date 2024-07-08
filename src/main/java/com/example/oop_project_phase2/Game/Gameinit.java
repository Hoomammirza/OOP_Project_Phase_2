package com.example.oop_project_phase2.Game;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Gameinit {
    public static boolean wager;
    public static int wagerint;
    public static User Host,Guest;
    private static Scanner input = new Scanner(System.in);
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
}
//diasble
//invisible