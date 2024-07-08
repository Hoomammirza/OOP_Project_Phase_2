package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    public static Stage stage;
    public static void switchtoMenuselect() {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuSelect.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        }catch (Exception e){
            System.out.println(e);
        }
        stage.setTitle("Chert-O-Pert");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoSignin() {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuSelect.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        }catch (Exception e){
            System.out.println(e);
        }
        stage.setTitle("Chert-O-Pert");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoSignup() {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuSelect.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        }catch (Exception e){
            System.out.println(e);
        }
        stage.setTitle("Chert-O-Pert");
        stage.setScene(scene);
        stage.show();
    }
}
