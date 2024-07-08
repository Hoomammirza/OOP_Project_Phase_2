package com.example.oop_project_phase2.UserManagement;

public class PasswordExeption extends Exception {
    public PasswordExeption(){};
    public PasswordExeption(String message){
        super(message);
    }
}
