package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.UserManagement.Users;
import com.example.oop_project_phase2.UserManagement.WeakPasswordException;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private static Scanner input = new Scanner(System.in);
    public static int run(){
        String in;
        boolean quit = false;

        Matcher showinformation;
        Matcher changenickname;
        Matcher changeusername;
        Matcher changeemail;
        Matcher changepassword;
        Matcher back;
        Matcher showcurrrentmenu;
        Matcher exit;

        System.out.println("commands:\n" +
                "*  show information\n" +
                "*  profile change -n <nickname>\n" +
                "*  profile change password -o <old-password> -n <new-password>\n" +
                "*  profile change -e <email>\n" +
                "*  profile change -n <nickname>\n" +
                "*  exit");

        while (!quit){
            in = input.nextLine();
            showinformation = Misc.getMatcher(in,"^Show(\\s+)information(\\s*)$");
            changenickname = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-n(\\s+)(?<nickname>\\S+)(\\s*)$");
            changeusername = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-u(\\s+)(?<username>\\S+)(\\s*)$");
            changeemail = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)-e(\\s+)(?<email>\\S+)(\\s*)$");
            changepassword = Misc.getMatcher(in,"^profile(\\s+)change(\\s+)password(\\s+)-o(\\s+)(?<oldpassword>\\S+)(\\s+)-n(\\s+)(?<newpassword>\\S+)(\\s*)$");
            back = Misc.getMatcher(in, "^back(\\s*)$");
            exit = Misc.getMatcher(in, "^exit(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");

            if (showinformation.find()){
                showinformation();
            } else if (changenickname.find()){
                changeNickname(changenickname);
            } else if (changeusername.find()){
                changeUsername(changeusername);
            } else if (changeemail.find()){
                changeEmail(changeemail);
            } else if (changepassword.find()){
                changePassword(changepassword);
            }else if (showcurrrentmenu.find()) {
                System.out.println("ProfileMenu");
            }else if (back.find()) {
                return 4;
            }else if (exit.find()) {
                return 0;
            }
        }
        return 0;
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
    private static void changeUsername(Matcher matcher){
        String username = matcher.group("username");
        if (!Users.ExistUsername(username)){
            SQLhandler.changeUsername(Users.LoginUser,username);
        }else {
            System.out.println("a user with this username already exists!");
        }
    }
    private static void changeEmail(Matcher matcher){
        String Email = matcher.group("email");
        if (signup.verifyEmail(Email)){
            SQLhandler.changeEmail(Users.LoginUser,Email);
        }else {
            System.out.println("email is invalid!");
        }
    }
    private static void changeNickname(Matcher matcher){
        String Nickname = matcher.group("nickname");
        SQLhandler.changeNickname(Users.LoginUser,Nickname);
    }
    private static void changePassword(Matcher matcher)  {
        User user = Users.LoginUser;
        String oldpassword = matcher.group("oldpassword");
        String newpassword = matcher.group("newpassword");

        if (oldpassword.equals(user.Password)) {
            if (!newpassword.equals(oldpassword)) {
                try {
                    signup.verifyPassword(newpassword);
                }catch(WeakPasswordException e){
                    String s = e.message;
                    System.out.println(s);
                    return;
                }
                String captcha = signup.getrandomcaptcha();
                String captchaanswer = new Scanner(System.in).nextLine();
                if (captcha.equals(captchaanswer)) {
                    SQLhandler.changePassword(user.Username,newpassword);
                    System.out.println("Password changed successfully!");
                } else {
                    System.out.println("captcha failed!");
                }
            }else {
                System.out.println("New password can't be the same as old password");
            }
        }else {
            System.out.println("Current password is incorrect!");
        }
    }

}
