package com.example.oop_project_phase2.Game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class resourceManagement {
    public static Image fighter;
    public static Image wizard;
    public static Image dancer;
    public static Image gunner;
    public static Image cell;
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
