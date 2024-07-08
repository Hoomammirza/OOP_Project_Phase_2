package com.example.oop_project_phase2.Game;

public class ExceptionGame extends Exception{
    public String message;
    public ExceptionGame(String message){
        this.message = message;
    }

}