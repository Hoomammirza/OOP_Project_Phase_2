package com.example.oop_project_phase2.Game;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.NoUserException;
import com.example.oop_project_phase2.UserManagement.PasswordExeption;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import com.example.oop_project_phase2.UserManagement.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Scanner;
import java.util.regex.Matcher;

public class guestUser {
    static boolean login=false;
    static boolean setCoin=false;
    @FXML
    Label coinLabel;
    @FXML
    Label coinException,loginException;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    TextField coin;
    @FXML
    Button wager;
    private  boolean login(String username,String password){
        try {
            User user = SQLhandler.readUser(username,password);
            if (user.Username.equals(Gameinit.Host.Username)){
                loginException.setText("you can not play against yourself!");
                return false;
            }
            Gameinit.Guest=user;
            return true;
        } catch (NoUserException e) {
            loginException.setText("Username doesn't exist!");
            return false;
        } catch (PasswordExeption e) {
            String s = e.toString();
            loginException.setText("Password and Username don’t match!");
            return false;
        }
    }
    public void login()
    {
        String username=this.username.getText();
        String password=this.password.getText();
        if(login(username,password))
        {
            login=true;
            this.username.setEditable(false);
            this.password.setEditable(false);
            loginException.setText(null);
            if(!(Gameinit.wager))
            {
                coin.setDisable(false);
                coin.setVisible(true);
                coinLabel.setDisable(false);
                coin.setVisible(true);
                wager.setDisable(false);
                wager.setVisible(true);
            }
        }
    }
    public  void selectwager(){
        try {
            int wager=Integer.parseInt(coin.getText());
            if (wager <= Gameinit.Host.Coins && Gameinit.Guest.Coins>= wager){
                Gameinit.wagerint = wager;
                coinException.setText(null);
                setCoin=true;
            }
            else {
                coinException.setText("One of the player doesnt have enough coin!");
                setCoin=false;

            }
        }
        catch (Exception e) {
            coinException.setText("please set the Number");
        }
        setCoin=false;

    }
    public void initialize()
    {
        if(!(login && Gameinit.wager))
        {
            coin.setDisable(true);
            coin.setVisible(false);
            coinLabel.setDisable(true);
            coin.setVisible(false);
            wager.setDisable(true);
            wager.setVisible(false);
        }
    }
    public void startGame()
    {
        if(setCoin && login)
        {
            login=false;
            setCoin=false;
        }
    }
}
