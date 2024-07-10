package com.example.oop_project_phase2.Game;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.SceneController;
import com.example.oop_project_phase2.UserManagement.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Gameinit {
    @FXML
    Label characterExceotion;
    @FXML
    Label user;
    @FXML
    Button button;
    @FXML
    ImageView gunner;
    @FXML
    ImageView fighter;
    @FXML
    ImageView dancer;
    @FXML
    ImageView wizard;
    static boolean character1=false;
    static boolean character2=false,second=false;
    public static boolean wager;
    public static int wagerint;
    public static User Host=Users.LoginUser;
    public static User Guest;

    public void gunner()
    {
        if(!character1)
        {
            Host.character = User.Character.valueOf("Gunner");
            character1=true;
        }
        else if(!character2)
        {
            Guest.character = User.Character.valueOf("Gunner");
            character2=true;
        }
    }
    public void fighter()
    {
        if(!character1)
        {
            Host.character = User.Character.valueOf("Fighter");
            character1=true;
        }
        else if(!character2)
        {
            Guest.character = User.Character.valueOf("Fighter");
            character2=true;
        }
    }
    public void dancer()
    {
        if(!character1)
        {
            Host.character = User.Character.valueOf("Dancer");
            character1=true;
        }
        else if(!character2)
        {
            Guest.character = User.Character.valueOf("Dancer");
            character2=true;
        }
    }
    public void wizard()
    {
        if(!character1)
        {
            Host.character = User.Character.valueOf("Wizard");
            character1=true;
        }
        else if(!character2)
        {
            Guest.character = User.Character.valueOf("Wizard");
            character2=true;
        }
    }
    public void initialize()
    {
        user.setText("user "+Host.Nickname+" please choose your character");
        button.setText("select");

    }
    public void change() throws IOException {
        if(character2 && character1)
        {
            character1=false;
            character2=false;
            second=false;
            characterExceotion.setText("");
            SceneController.switchtoGame();
        }
        else if(character1 && second)
        {
            characterExceotion.setText("first choose your character");
        }
        else if(character1)
        {
            user.setText("user "+Guest.Nickname+" please choose your character");
            button.setText("start game");
            second=true;
            characterExceotion.setText("");
        }
        else
        {
            characterExceotion.setText("first choose your character");
        }
    }
    public void back()
    {
        try {
            SceneController.switchtoSecondUser();
        }
        catch (Exception e)
        {

        }
    }
}