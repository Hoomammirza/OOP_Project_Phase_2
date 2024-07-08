package com.example.oop_project_phase2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Misc {
    public static Matcher getMatcher(String input , String regex){ return Pattern.compile(regex).matcher(input); }
}