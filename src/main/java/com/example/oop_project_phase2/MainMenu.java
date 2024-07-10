package com.example.oop_project_phase2;


import com.example.oop_project_phase2.Game.Game;
import com.example.oop_project_phase2.Game.Gameinit;
import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.UserManagement.Users;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.regex.Matcher;

public class MainMenu {
    public static User user;
    private static Scanner input = new Scanner(System.in);

    @FXML
    Text Level;
    @FXML
    Text Coins;
    @FXML
    Text XP;

    public void initialize(){
        user = Users.LoginUser;
        Coins.setText("Coins : " + user.Coins);
        Level.setText("Coins : " + user.Level);
        XP.setText("Coins : " + user.XP);
    }

//    public static int run(){
//        user= Users.LoginUser;
//        String in;
//        boolean quit = false;
//
//        Matcher startGame;
//        Matcher startGamewager;
//        Matcher showCard;
//        Matcher HistoryGame;
//        Matcher ProfileMenu;
//        Matcher exitAccount;
//        Matcher shopMenu;
//        Matcher showcurrentMenu;
//
//        System.out.println("commands:\n" +
//                "* start game normal\n"+
//                "* start game wager\n"+
//                "* show my cards:\n" +
//                "* show history game\n" +
//                "* show current menu\n" +
//                "* shop menu\n"+
//                "* profile menu\n"+
//                "* exit account");
//
//        while (!quit){
//            in = input.nextLine();
//            startGame = Misc.getMatcher(in,"start game normal");
//            startGamewager = Misc.getMatcher(in,"start game wager");
//            showCard = Misc.getMatcher(in,"show my cards:");
//            HistoryGame = Misc.getMatcher(in, "show history game");
//            ProfileMenu = Misc.getMatcher(in, "profile menu");
//            showcurrentMenu=Misc.getMatcher(in,"show current menu");
//            shopMenu=Misc.getMatcher(in,"shop menu");
//            exitAccount=Misc.getMatcher(in,"exit account");
//
//            if (startGame.find()){
//                Gameinit.wager = false;
//                return 6;
//            }if (startGamewager.find()){
//                Gameinit.wager = true;
//                return 6;
//            }else if (showCard.find()){
//                printCard();
//            }else if (exitAccount.find()) {
//                return 1;
//            }else if(showcurrentMenu.find()) {
//                System.out.println("Main Menu");
//            }else if(ProfileMenu.find()) {
//                return 5;
//            }else if (HistoryGame.find()){
//                return 7;
//            }else if (shopMenu.find()){
//                return 9;
//            }
//        }
//        return 0;
//    }
//    public static void printCard()
//    {
//        for (int i=0;i<Users.LoginUser.cards.size();i++)
//        {
//            int a=Users.LoginUser.cards.get(i).playerDamage*Users.LoginUser.cards.get(i).Duration;
//            System.out.println("name:  "+Users.LoginUser.cards.get(i).name+"\n"
//                    +"duration: "+Users.LoginUser.cards.get(i).Duration+"\n"+
//                    "card attack/defences:  "+Users.LoginUser.cards.get(i).defence_attack+"\n"+
//                    "player damage:  "+a+"\n"+
//                    "level:  "+Users.LoginUser.cards.get(i).level);
//        }
//    }
    public void Profile(){

    }
    public void Shop(){

    }
    public void History() throws IOException {
        SceneController.switchtoHistory();
    }
    public void SGno() throws IOException {
        Gameinit.wager = false;
        SceneController.switchtoSecondUser();
    }
    public void SGwa(){
        Gameinit.wager = true;
    }
    public void logout(){
        SceneController.switchtoMenuselect();
    }
}
