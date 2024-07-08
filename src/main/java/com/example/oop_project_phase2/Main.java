package com.example.oop_project_phase2;

import com.example.oop_project_phase2.UserManagement.SQLhandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SQLhandler.connect();
        SceneController.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MenuSelect.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}