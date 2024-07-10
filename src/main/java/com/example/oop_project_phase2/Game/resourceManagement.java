package com.example.oop_project_phase2.Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.beans.EventHandler;
import java.io.File;
import java.net.MalformedURLException;


public class resourceManagement {
    public static Image fighter;
    public static Image wizard;
    public static Image dancer;
    public static Image gunner;
    public static Image cell;
    public static double musicvolume = 0.5;
    public static Media RangoMusic;
    public static MediaPlayer MusicPlayer;
    public static void load() throws MalformedURLException {
        RangoMusic = new Media(new File("src/main/resources/com/example/oop_project_phase2/Y2meta.app - Walk Don't Rango (128 kbps).mp3").toURI().toURL().toString());
        MusicPlayer = new MediaPlayer(RangoMusic);
        MusicPlayer.setVolume(musicvolume);
        MusicPlayer.setCycleCount(Integer.MAX_VALUE);
        MusicPlayer.play();
    }
    static {
        try {
            cell=new Image(new File("C:\\Users\\lenovo\\IdeaProjects\\OOP_Project_Phase_2\\src\\main\\resources\\com\\example\\oop_project_phase2\\CSS\\image\\cell.png").toURI().toURL().toString());
            fighter=new Image(new File("C:\\Users\\lenovo\\IdeaProjects\\OOP_Project_Phase_2\\src\\main\\resources\\com\\example\\oop_project_phase2\\CSS\\image\\fighter.png").toURI().toURL().toString());
            gunner=new Image(new File("C:\\Users\\lenovo\\IdeaProjects\\OOP_Project_Phase_2\\src\\main\\resources\\com\\example\\oop_project_phase2\\CSS\\image\\gunner.png").toURI().toURL().toString());
            dancer=new Image(new File("C:\\Users\\lenovo\\IdeaProjects\\OOP_Project_Phase_2\\src\\main\\resources\\com\\example\\oop_project_phase2\\CSS\\image\\dancer.png").toURI().toURL().toString());
            wizard=new Image(new File("C:\\Users\\lenovo\\IdeaProjects\\OOP_Project_Phase_2\\src\\main\\resources\\com\\example\\oop_project_phase2\\CSS\\image\\wizard.png").toURI().toURL().toString());
        }
        catch (Exception e)
        {

        }
    }

}
