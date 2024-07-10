package com.example.oop_project_phase2;


import com.example.oop_project_phase2.UserManagement.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Scanner;

public class signin {
    static Scanner input = new Scanner(System.in);
    public static int n = 0;

    @FXML
    Text errortext;
    @FXML
    Text SQ;
    @FXML
    TextField Username;
    @FXML
    TextField Password;
    @FXML
    HBox Usernamebox;
    @FXML
    HBox Passwordbox;
    @FXML
    HBox Controlbox;
    @FXML
    CheckBox isNewUser;
    @FXML
    HBox errorbox;
    @FXML
    HBox Textbox;
    @FXML
    HBox SQselect;
    @FXML
    HBox SQAbox;
    @FXML
    HBox NewPasswordbox;
    @FXML
    HBox Controlbox1;
    @FXML
    TextField NewPassword;
    @FXML
    TextField SQA;
    @FXML
    Button ChangePasswordButton;
    public AnimationTimer animationTimer;

//    public static int run(){
//        String in;
//        boolean quit = false;
//
//        Matcher login;
//        Matcher ForgotPassword;
//        Matcher exit;
//        Matcher showcurrrentmenu;
//
//        System.out.println("commands:\n" +
//                "*  user login -u <username> -p <password>\n" +
//                "*  forgot my password -u <username>\n" +
//                "*  show current menu\n" +
//                "*  exit");
//
//        while (!quit){
//            in = input.nextLine();
//            login = Misc.getMatcher(in,"^user(\\s+)login(\\s+)-u(\\s+)(?<username>\\S+)(\\s+)-p(\\s+)(?<password>\\S+)(\\s*)$");
//            ForgotPassword = Misc.getMatcher(in,"^forgot(\\s+)my(\\s+)password(\\s+)-u(\\s+)(?<username>\\S+)$");
//            exit = Misc.getMatcher(in, "^exit(\\s*)$");
//            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
//
//            if (login.find()){
//                if (login(login)) {
//                    if (!Users.LoginUser.isAdmin)
//                        return 4;
//                    else
//                        return 8;
//                }
//            } else if (ForgotPassword.find()){
//                forgotpassword(ForgotPassword);
//            }else if (showcurrrentmenu.find()) {
//                System.out.println("signin");
//            }else if (exit.find()) {
//                return 0;
//            }
//        }
//        return 0;
//    }

    public void initialize(){
        animationTimer = new AnimationTimer() {
            long last = 0;
            int tick = 0;
            @Override
            public void handle(long l) {
                if (last == 0){
                    last = l;
                }
                if (tick == 0){
                    tick = 10;
                }
                if (last + 1000000000 < l) {
                    tick--;
                    if (tick == 0){
                        errortext.setText("");
                        animationTimer.stop();
                    }else {
                        errortext.setText("Please wait:"+tick+"seconds!");
                    }
                    last = l;
                }
            }
        };
        errorbox.toBack();
        Controlbox.toBack();
        Passwordbox.toBack();
        Usernamebox.toBack();
        NewPasswordbox.setDisable(true);
        NewPasswordbox.setVisible(false);
        Textbox.setDisable(true);
        Textbox.setVisible(false);
        SQselect.setDisable(true);
        SQselect.setVisible(false);
        SQAbox.setDisable(true);
        SQAbox.setVisible(false);
        NewPasswordbox.setDisable(true);
        NewPasswordbox.setVisible(false);
        Controlbox1.setDisable(true);
        Controlbox1.setVisible(false);

    }

    public void signaction() throws IOException {
        try {
            Users.signin(Username.getText(), Password.getText());
            SceneController.switchtoMainMenu();
        } catch (NoUserException e) {
            String s = e.toString();
            errortext.setText("Username doesn’t exist!");
        } catch (PasswordExeption e) {
            String s = e.toString();
            animationTimer.start();
            errortext.setText("Password and Username don’t match!");
        } catch (TimerException e){
            String s = e.toString();
            animationTimer.start();
        }
    }
    public void ForgetPassword(){
        String username = Username.getText();
        if (Users.ExistUsername(username)){
            switch (Users.getSQ(username)){
                case Father:
                    SQ.setText("What is your father’s name ?");
                    break;
                case Pet:
                    SQ.setText("What is your favourite color ?");
                    break;
                case Color:
                    SQ.setText("What was the name of your first pet?");
                    break;
            }
            String Answer = input.nextLine();
            if (Users.verifySQA(username,Answer)){
                String password = NewPassword.getText();
                try {
                    signup.verifyPassword(password);
                }catch (WeakPasswordException e) {
                    errortext.setText(e.message);
                    return;
                }
                SQLhandler.changePassword(username,password);
                ChangePasswordButton.setText("Success");
            }else {
                errortext.setText("security question answer doesn't match!");
            }
        }else {
            errortext.setText("this username doesn't exist!");
        }
    }
    public void back(){
        SceneController.switchtoMenuselect();
    }
    public void ForgotPasswordCheck(){
        if (isNewUser.isSelected()){
            errorbox.toFront();
            NewPasswordbox.setDisable(false);
            NewPasswordbox.setVisible(true);
            Textbox.setDisable(false);
            Textbox.setVisible(true);
            SQselect.setDisable(false);
            SQselect.setVisible(true);
            SQAbox.setDisable(false);
            SQAbox.setVisible(true);
            NewPasswordbox.setDisable(false);
            NewPasswordbox.setVisible(true);
            Controlbox1.setDisable(false);
            Controlbox1.setVisible(true);
            SQupdate();
        }else {
            errorbox.toBack();
            Controlbox.toBack();
            Passwordbox.toBack();
            Usernamebox.toBack();
            NewPasswordbox.setDisable(true);
            NewPasswordbox.setVisible(false);
            Textbox.setDisable(true);
            Textbox.setVisible(false);
            SQselect.setDisable(true);
            SQselect.setVisible(false);
            SQAbox.setDisable(true);
            SQAbox.setVisible(false);
            NewPasswordbox.setDisable(true);
            NewPasswordbox.setVisible(false);
            Controlbox1.setDisable(true);
            Controlbox1.setVisible(false);
        }
    }
    public void SQupdate(){
        if (SQLhandler.Userexists(Username.getText())){
            switch (Users.getSQ(Username.getText())){
                case Father:
                    SQ.setText("What is your father’s name ?");
                    break;
                case Pet:
                    SQ.setText("What is your favourite color ?");
                    break;
                case Color:
                    SQ.setText("What was the name of your first pet?");
                    break;
            }
        }else {
            errortext.setText("Please enter a valid username");
        }
    }
}
