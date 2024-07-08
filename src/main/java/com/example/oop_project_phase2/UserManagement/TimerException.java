package com.example.oop_project_phase2.UserManagement;

public class TimerException extends Exception{
    public int time;
    public TimerException(int time){
        this.time = time;
    }
}
