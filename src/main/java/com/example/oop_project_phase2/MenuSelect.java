package com.example.oop_project_phase2;


import com.example.oop_project_phase2.Misc.Misc;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MenuSelect {
    public static int run() {
        Scanner in = new Scanner(System.in);
        String input;

        Matcher signin;
        Matcher signup;
        Matcher back;
        Matcher showcurrrentmenu;
        Matcher exit;

        System.out.println("select menu: \n" +
                "*  signin\n" +
                "*  signup\n" +
                "*  exit");

        while (true) {
            input = in.nextLine();
            signin = Misc.getMatcher(input, "^signin(\\s*)$");
            signup = Misc.getMatcher(input, "^signup(\\s*)$");
            back = Misc.getMatcher(input, "^back(\\s*)$");
            exit = Misc.getMatcher(input, "^exit(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(input, "^show current menu(\\s*)$");
            if (signin.find()) {
                return 2;
            } else if (signup.find()) {
                return 3;
            } else if (showcurrrentmenu.find()) {
                System.out.println("MenuSelect");
            } else if (back.find()) {
                return 1;
            } else if (exit.find()) {
                return 0;
            }

        }
    }
}