package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Game.Game;

public class SceneController {
    public static void run(){
        int n = 1;
        boolean quit = false;
        while (!quit){
            switch (n){
                case 0:
                    return;
                case 1:
                    n = MenuSelect.run();
                    break;
                case 2:
                    n = signin.run();
                    break;
                case 3:
                    n = signup.run();
                    break;
                case 4:
                    n=MainMenu.run();
                    break;
                case 5:
                    n=ProfileMenu.run();
                    break;
                case 6:
                    n= Game.run();
                    break;
                case 7:
                    n=History.run();
                    break;
                case 8:
                    n=AdminMenu.run();
                    break;
                case 9:
                    n=ShopMenu.run();
            }
        }
    }
}
