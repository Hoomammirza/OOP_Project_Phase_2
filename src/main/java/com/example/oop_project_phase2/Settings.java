package com.example.oop_project_phase2;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import com.example.oop_project_phase2.Game.resourceManagement;

import java.io.IOException;

public class Settings {
    @FXML
    ChoiceBox<String> Musicchoicebox;
    @FXML
    ChoiceBox<String> Themechoicebox;
    @FXML
    Slider volumeslider;
    public void drag(){
        resourceManagement.setMusicvolume(volumeslider.getValue());
    }
    public void initialize(){
        volumeslider.setValue(resourceManagement.musicvolume);
        Musicchoicebox.getItems().add(0,"Rango");
        Musicchoicebox.getItems().add(1,"Pirates");
        Musicchoicebox.setValue(resourceManagement.currentmusic.toString());
    }
    public void Changemusic(){
        resourceManagement.setMusic(Musicchoicebox.getValue());
    }
    public void back() throws IOException {
        SceneController.switchtoMainMenu();
    }

}
