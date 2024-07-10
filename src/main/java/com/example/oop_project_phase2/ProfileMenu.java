package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.UserManagement.Users;
import com.example.oop_project_phase2.UserManagement.WeakPasswordException;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {

//    public static int run(){
//        String in;
//        boolean quit = false;
//
//        Matcher showinformation;
//        Matcher changenickname;
//        Matcher changeusername;
//        Matcher changeemail;
//        Matcher changepassword;
//        Matcher back;
//        Matcher showcurrrentmenu;
//        Matcher exit;
//
//        System.out.println("commands:\n" +
//                "*  show information\n" +
//                "*  profile change -n <nickname>\n" +
//                "*  profile change password -o <old-password> -n <new-password>\n" +
//                "*  profile change -e <email>\n" +
//                "*  profile change -n <nickname>\n" +
//                "*  exit");
//
//        while (!quit){
//            in = input.nextLine();
//            showinformation = Misc.getMatcher(in,"^Show(\\s+)information(\\s*)$");
//            changenickname = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-n(\\s+)(?<nickname>\\S+)(\\s*)$");
//            changeusername = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-u(\\s+)(?<username>\\S+)(\\s*)$");
//            changeemail = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-e(\\s+)(?<email>\\S+)(\\s*)$");
//            changepassword = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)password(\\s+)-o(\\s+)(?<oldpassword>\\S+)(\\s+)-n(\\s+)(?<newpassword>\\S+)(\\s*)$");
//            back = Misc.getMatcher(in, "^back(\\s*)$");
//            exit = Misc.getMatcher(in, "^exit(\\s*)$");
//            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");
//
//            if (showinformation.find()){
//                showinformation();
//            } else if (changenickname.find()){
//                changeNickname(changenickname);
//            } else if (changeusername.find()){
//                changeUsername(changeusername);
//            } else if (changeemail.find()){
//                changeEmail(changeemail);
//            } else if (changepassword.find()){
//                changePassword(changepassword);
//            }else if (showcurrrentmenu.find()) {
//                System.out.println("ProfileMenu");
//            }else if (back.find()) {
//                return 4;
//            }else if (exit.find()) {
//                return 0;
//            }
//        }
//        return 0;
//    }
    @FXML
    HBox Captchabox;
    @FXML
    HBox Passwordbox1;
    @FXML
    HBox Passwordbox2;
    @FXML
    Text Captcha;
    @FXML
    Text Coins;
    @FXML
    Text error;
    @FXML
    Text Level;
    @FXML
    Text XP;
    @FXML
    TextField CaptchaA;
    @FXML
    TextField Email;
    @FXML
    TextField Nickname;
    @FXML
    TextField Npassword;
    @FXML
    TextField Opassword;
    @FXML
    TextField Username;
    @FXML
    CheckBox PasswordCheckbox;

    public int answer;
    public void initialize(){
        User user = Users.LoginUser;
        answer = getcaptcha();
        Captchabox.setDisable(true);
        Captchabox.setVisible(false);
        Passwordbox1.setDisable(true);
        Passwordbox1.setVisible(false);
        Passwordbox2.setDisable(true);
        Passwordbox2.setVisible(false);
        Coins.setText("Coins: "+Users.LoginUser.Coins+"   ");
        XP.setText("XP: "+Users.LoginUser.XP+"   ");
        Level.setText("Level: "+Users.LoginUser.Level+"   ");
        Username.setText(user.Username);
        Nickname.setText(user.Nickname);
        Email.setText(user.Email);
        Username.setText(user.Username);
    }
    public void PasswordCheck(){
        if (PasswordCheckbox.isSelected()){
            Captchabox.setDisable(false);
            Captchabox.setVisible(true);
            Passwordbox1.setDisable(false);
            Passwordbox1.setVisible(true);
            Passwordbox2.setDisable(false);
            Passwordbox2.setVisible(true);
        }else {
            Captchabox.setDisable(true);
            Captchabox.setVisible(false);
            Passwordbox1.setDisable(true);
            Passwordbox1.setVisible(false);
            Passwordbox2.setDisable(true);
            Passwordbox2.setVisible(false);
        }
    }

    private static void showinformation(){
        User user = Users.LoginUser;
        System.out.println("Username: "+ user.Username);
        System.out.println("Password: "+ user.Password);
        System.out.println("Nickname: "+ user.Nickname);
        System.out.println("Email: "+ user.Email);
        System.out.println("Username: "+ user.Username);
        System.out.println("Security Question:");
        switch (user.SecurityQ) {
            case Color:
                System.out.println("What is your father’s name ?");
                break;
            case Father:
                System.out.println("What is your favourite color ?");
                break;
            case Pet:
                System.out.println("What was the name of your first pet?");
                break;
        }
        System.out.println("Security Question Answer:");
        System.out.println(user.SecurityQA);
        System.out.println("is Admin: " + user.isAdmin);
    }
    public void Changeusername(){
        String username = Username.getText();
        if (!Users.ExistUsername(username)){
            SQLhandler.changeUsername(Users.LoginUser,username);
        }else {
            error.setText("a user with this username already exists!");
        }
    }
    public void Changeemail(){
        String Email = this.Email.getText();
        if (signup.verifyEmail(Email)){
            SQLhandler.changeEmail(Users.LoginUser,Email);
        }else {
            error.setText("email is invalid!");
        }
    }
    public void Changenickname(){
        SQLhandler.changeNickname(Users.LoginUser,Nickname.getText());
    }
    public void Changepassword()  {
        User user = Users.LoginUser;
        String oldpassword = Opassword.getText();
        String newpassword = Npassword.getText();

        if (oldpassword.equals(user.Password)) {
            if (!newpassword.equals(oldpassword)) {
                try {
                    signup.verifyPassword(newpassword);
                }catch(WeakPasswordException e){
                    String s = e.message;
                    error.setText(s);
                    return;
                }
                String captcha = signup.getrandomcaptcha();
                String captchaanswer = new Scanner(System.in).nextLine();
                if (captcha.equals(captchaanswer)) {
                    SQLhandler.changePassword(user.Username,newpassword);
                    error.setText("Password changed successfully!");
                } else {
                    error.setText("captcha failed!");
                }
            }else {
                error.setText("New password can't be the same as old password");
            }
        }else {
            error.setText("Current password is incorrect!");
        }
    }
    public void back() throws IOException {
        SceneController.switchtoMainMenu();
    }
    public int getcaptcha(){
        int answer;
        String string = "";
        Random random = new Random(System.currentTimeMillis());
        int j=random.nextInt(10);
        int k;
        string += Integer.toString(random.nextInt(10));
        answer = Integer.parseInt(string);
        for (int i = 0; i < 2 + j; i++) {
            k = random.nextInt(4);
            switch (k){
                case 0:
                    string += "+";
                    k = 1 + random.nextInt(9);
                    answer += k;
                    string += k;
                    break;
                case 1:
                    string += "-";
                    k = 1 + random.nextInt(9);
                    answer -= k;
                    string += k;
                    break;
                case 2:
                    string += "*";
                    k = 1 + random.nextInt(9);
                    answer *= k;
                    string += k;
                    break;
                case 3:
                    string += "/";
                    k = 1 + random.nextInt(9);
                    answer /= k;
                    string += k;
                    break;
            }
        }
        string += "=";
        Captcha.setText(string);
        return answer;
    }


}
