package com.example.oop_project_phase2;

public class Timer {
    long targettime;
    public Timer(long duration){
        targettime = System.currentTimeMillis() + duration;
    }
    public boolean running(){
        return System.currentTimeMillis() < targettime;
    }
    public long getTimeRemaining(){
        return targettime - System.currentTimeMillis();
    }
}
