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

    public  void initGuest() {
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
    public void initialize()
    {

    }
}