package com.example.oop_project_phase2;

public class WeakPasswordException extends Exception{
    public String message;
    public WeakPasswordException(String message){
        this.message = message;
    }
}
