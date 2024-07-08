package com.example.oop_project_phase2;


import java.util.Scanner;
import java.util.regex.Matcher;

public class signin {
    static Scanner input = new Scanner(System.in);
    public static int n = 0;
    public static int run(){
        String in;
        boolean quit = false;

        Matcher login;
        Matcher ForgotPassword;
        Matcher exit;
        Matcher showcurrrentmenu;

        System.out.println("commands:\n" +
                "*  user login -u <username> -p <password>\n" +
                "*  forgot my password -u <username>\n" +
                "*  show current menu\n" +
                "*  exit");

        while (!quit){
            in = input.nextLine();
            login = Misc.getMatcher(in,"^user(\\s+)login(\\s+)-u(\\s+)(?<username>\\S+)(\\s+)-p(\\s+)(?<password>\\S+)(\\s*)$");
            ForgotPassword = Misc.getMatcher(in,"^forgot(\\s+)my(\\s+)password(\\s+)-u(\\s+)(?<username>\\S+)$");
            exit = Misc.getMatcher(in, "^exit(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(in, "^show current menu(\\s*)$");

            if (login.find()){
                if (login(login)) {
                    if (!Users.LoginUser.isAdmin)
                        return 4;
                    else
                        return 8;
                }
            } else if (ForgotPassword.find()){
                forgotpassword(ForgotPassword);
            }else if (showcurrrentmenu.find()) {
                System.out.println("signin");
            }else if (exit.find()) {
                return 0;
            }
        }
        return 0;
    }
    private static boolean login(Matcher matcher){
        try {
            Users.signin(matcher.group("username"), matcher.group("password"));
            return true;
        } catch (NoUserException e) {
            String s = e.toString();
            System.out.println("Username doesn’t exist!");
            return false;
        } catch (PasswordExeption e) {
            String s = e.toString();
            System.out.println("Password and Username don’t match!");
            return false;
        } catch (TimerException e){
            String s = e.toString();
            System.out.println("Try again in "+e.time+" seconds");
            return false;
        }
    }
    private static void forgotpassword(Matcher matcher){
        String username = matcher.group("username");
        if (Users.ExistUsername(username)){
            switch (Users.getSQ(username)){
                case Father:
                    System.out.println("What is your father’s name ?");
                    break;
                case Pet:
                    System.out.println("What is your favourite color ?");
                    break;
                case Color:
                    System.out.println("What was the name of your first pet?");
                    break;
            }
            String Answer = input.nextLine();
            if (Users.verifySQA(username,Answer)){
                System.out.println("enter you new password!");
                String password = input.nextLine();
                boolean passwordverify = false;
                while (!passwordverify){
                    try {
                        signup.verifyPassword(password);
                        passwordverify = true;
                    }catch (WeakPasswordException e){
                        System.out.println(e.message);
                        System.out.println("enter you new password!");
                        password = input.nextLine();
                        if (password.equals("exit"))
                            return;
                    }
                }
                SQLhandler.changePassword(username,password);
                System.out.println("password change successful!");
            }else {
                System.out.println("security question answer doesn't match!");
            }
        }else {
            System.out.println("this username doesn't exist!");
        }
    }
}
