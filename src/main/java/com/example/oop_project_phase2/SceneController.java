package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Game.Game;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    public static void switchtoSignin() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Signin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoSignup() throws IOException {
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Signup.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoMainMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoSecondUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("secondUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 577, 500);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoGameinit() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game init.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 653, 624);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoGame() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 938, 536);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoHistory() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("History.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoProfile() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Profile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
    public static void switchtoSettings() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Chert-O-Pert!");
        stage.setScene(scene);
        stage.show();
    }
}
