package com.example.oop_project_phase2;

import java.io.IOException;

public class MenuSelect {
    public void doSignin() throws IOException {
        SceneController.switchtoSignin();
    }
    public void doSignup() throws IOException {
        SceneController.switchtoSignup();
    }
}