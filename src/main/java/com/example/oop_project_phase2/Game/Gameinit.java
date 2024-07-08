package com.example.oop_project_phase2.Game;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Scanner;
import java.util.regex.Matcher;

public class Gameinit {

    public static boolean wager;
    public static int wagerint;
    public static User Host,Guest;
    private static Scanner input = new Scanner(System.in);

    public  void initGuest() {
        Host = Users.LoginUser;
        boolean quit = false;
        Matcher showcurrrentmenu;

        System.out.println("commands:\n" +
                "*  user login -u <username> -p <password>\n"
                + "*  show current menu ");
        //=================================================================================================================
        System.out.println("Please select you character Player 1:\n" +
                "Gunner , Fighter , Dancer , Wizard ");
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