//package com.example.oop_project_phase2.Game;
//
//
//import com.example.oop_project_phase2.Misc.Misc;
//import com.example.oop_project_phase2.UserManagement.*;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.GridPane;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//
//public class Game {
//    public static int temp;
//    public static ImageView tempi;
//    @FXML
//    GridPane tableGame,handhost,handguest;
//    @FXML
//    static ImageView [] [] cardImage=new ImageView[2][21];
//    @FXML
//    static ImageView [] handhostImage=new ImageView[6];
//    @FXML
//    static Label [] handhostInformation=new Label[6];
//    @FXML
//    static ImageView [] handGuestImage=new ImageView[6];
//    @FXML
//    static Label [] handGuestInformation=new Label[6];
//    @FXML
//    ImageView imageHost,imageGuest;
//    @FXML
//    Label hostname,guestname;
//    @FXML
//    AnchorPane Root;
//    @FXML
//    Label hitpointGuest,hitpointHost;
//    public void initialize(){
//        setTableGame();
//        GameController.emptyCell(Gameinit.Host,Gameinit.Guest);
//        setCharacter(Gameinit.Host,Gameinit.Guest);
//        startHand();
//        GameController.init(Gameinit.Host,Gameinit.Guest);
////        changeGraphicElement(Gameinit.Host,Gameinit.Guest);
//    }
//    public void setTableGame()
//    {
//        for (int i=0;i<2;i++)
//        {
//            for(int j=0;j<21;j++)
//            {
//                try {
//                    cardImage[i][j]=new ImageView(resourceManagement.cell);
//                    cardImage[i][j].setFitWidth(45);
//                    cardImage[i][j].setFitHeight(78);
//                    tableGame.add(cardImage[i][j],j,i);
//                }
//                catch (Exception e)
//                {
//                    System.out.println(e);
//                }
//            }
//        }
//    }
//    public void setCharacter(User Host,User Guest)
//    {
//        if(Host.character==User.Character.valueOf("Gunner"))
//        {
//            imageHost.setImage(resourceManagement.gunner);
//            imageHost.setFitHeight(150);
//            imageHost.setFitWidth(200);
//
//        }
//        else if(Host.character==User.Character.valueOf("Fighter"))
//        {
//            imageHost.setImage(resourceManagement.fighter);
//            imageHost.setFitHeight(150);
//            imageHost.setFitWidth(200);
//        }
//        else if(Host.character==User.Character.valueOf("Wizard"))
//        {
//            imageHost.setImage(resourceManagement.wizard);
//            imageHost.setFitHeight(150);
//            imageHost.setFitWidth(200);
//        }
//        else if(Host.character==User.Character.valueOf("Dancer"))
//        {
//            imageHost.setImage(resourceManagement.dancer);
//            imageHost.setFitHeight(150);
//            imageHost.setFitWidth(200);
//        }
//        if(Guest.character==User.Character.valueOf("Gunner"))
//        {
//            imageGuest.setImage(resourceManagement.gunner);
//            imageGuest.setFitHeight(150);
//            imageGuest.setFitWidth(200);
//        }
//        else if(Guest.character==User.Character.valueOf("Fighter"))
//        {
//            imageGuest.setImage(resourceManagement.fighter);
//            imageGuest.setFitHeight(150);
//            imageGuest.setFitWidth(200);
//        }
//        else if(Guest.character==User.Character.valueOf("Wizard"))
//        {
//            imageGuest.setImage(resourceManagement.wizard);
//            imageGuest.setFitHeight(150);
//            imageGuest.setFitWidth(200);
//        }
//        else if(Host.character==User.Character.valueOf("Dancer"))
//        {
//            imageHost.setImage(resourceManagement.dancer);
//            imageHost.setFitHeight(150);
//            imageHost.setFitWidth(200);
//        }
//    }
//    public  static void showHand(User Host,User Guest)
//    {
//        for(int i=0;i<Host.hand.size();i++)
//        {
//            handhostImage[i].setImage(resourceManagement.getImageCard(Host.hand.get(i).name));
//            handhostInformation[i].setText("AD:"+Host.hand.get(i).defence_attack+"/PD:"+Host.hand.get(i).playerDamage+"/D:"+Host.hand.get(i).Duration);
//        }
//        for (int i=Host.hand.size();i<6;i++)
//        {
//            handhostImage[i].setImage(resourceManagement.wall);
//            handhostInformation[i].setText("");
//        }
//        for(int i=0;i<Guest.hand.size();i++)
//        {
//            handGuestImage[i].setImage(resourceManagement.getImageCard(Guest.hand.get(i).name));
//            handGuestInformation[i].setText("AD:"+Guest.hand.get(i).defence_attack+"/PD"+Guest.hand.get(i).playerDamage+"/D"+Guest.hand.get(i).Duration);
//        }
//        for (int i=Guest.hand.size();i<6;i++)
//        {
//            handGuestImage[i].setImage(resourceManagement.wall);
//            handGuestInformation[i].setText("");
//        }
//    }
//    public void startHand()
//    {
//        for(int i=0;i<6;i++)
//        {
//            handhostInformation[i]=new Label();
//         handhostImage[i]=new ImageView(resourceManagement.wall);
//         handhostImage[i].setFitWidth(86);
//         handhostImage[i].setFitHeight(89);
//         handhostImage[i].setOnMouseDragged(new EventHandler<MouseEvent>() {
//             @Override
//             public void handle(MouseEvent mouseEvent) {
//                 ImageView imageView =  (ImageView) mouseEvent.getSource();
//                 imageView.setLayoutX(0);
//                 imageView.setLayoutY(0);
//                 imageView.setX(mouseEvent.getSceneX());
//                 imageView.setY(mouseEvent.getSceneY());
//             }
//         });
//
//         handhostImage[i].setOnMousePressed(new EventHandler<MouseEvent>() {
//             @Override
//             public void handle(MouseEvent mouseEvent) {
//                 ImageView imageView = (ImageView) mouseEvent.getSource();
//                 tempi = imageView;
//                 System.out.println("hello");
//                 temp = GridPane.getColumnIndex(tempi);
//                 handhost.getChildren().remove(tempi);
//                 Root.getChildren().add(tempi);
//             }
//         });
//         handhostImage[i].setOnMouseReleased(new EventHandler<MouseEvent>() {
//             @Override
//             public void handle(MouseEvent mouseEvent) {
//                 Root.getChildren().removeAll(tempi);
//                 handhost.add(tempi,temp,1);
//                 if (mouseEvent.getSceneY() > 281.5 && mouseEvent.getSceneY() < 357){
//                     int n = (int)((int)(mouseEvent.getSceneX()-8)/43.905);{
//                         if (GameController.host2.hand.get(temp).feature == null){
//                             if(!TimelineController.setCardInGameWithSpace(GameController.host2,GameController.quest2,temp,n))
//                                 return;
//                         }else if (GameController.host2.hand.get(temp).feature.equals("duplicator")){
//                             if(!TimelineController.SetDuplicator(GameController.host2,GameController.quest2,temp,n))
//                                 return;
//                         }else {
//                             if(!TimelineController.setCardInGameWithNoSpace(GameController.host2,GameController.quest2,temp))
//                                 return;
//                         }
//                         Game.showHand(GameController.host2,GameController.quest2);
//                         setTimLineImage(GameController.host2,GameController.quest2);
//                         GameController.endlittleround();
//                         if ((GameController.whoStart && GameController.host2 == GameController.host1)||(!GameController.whoStart && GameController.host2 == GameController.quest1)){
//                             GameController.freshhand();
//                             Game.showHand(GameController.host2,GameController.quest2);
//                         }
//                         if (GameController.round == 0){
//                             GameController.end4round();
//                         }
//                         if (GameController.finish){
//                             GameController.endGAME();
//                         }
//                     }
//                 }
//             }
//         });
//         handhost.add(handhostImage[i],i,1);
//         handhost.add(handhostInformation[i],i,0);
//        }
//        for(int i=0;i<6;i++)
//        {
//            handGuestInformation[i]=new Label();
//            handGuestImage[i]=new ImageView(resourceManagement.wall);
//            handGuestImage[i].setFitWidth(86);
//            handGuestImage[i].setFitHeight(89);
//            handguest.add(handGuestImage[i],i,1);
//            handguest.add(handGuestInformation[i],i,0);
//        }
//    }
////    public void changeGraphicElement(User Host,User Guest)
////    {
////        setCharacter(Host,Guest);
////        showHand(Host,Guest);
////        guestname.setText(Guest.Nickname);
////        hostname.setText(Host.Nickname);
////        hitpointGuest.setText(String.valueOf(Guest.hitpoint));
////        hitpointHost.setText(String.valueOf(Host.hitpoint));
////    }
//    public void setTimLineImage(User host,User guest)
//    {
//        for(int i=0;i<21;i++)
//        {
//            if(host.timeline[i]!=null)
//            {
//                if(!Objects.equals(host.timeline[i].name, "empty"))
//                {
//                    cardImage[1][i].setImage(resourceManagement.getImageCard(host.timeline[i].name));
//                }
//                else if(host.timeline[i].cardReference!=null)
//                {
//                    cardImage[1][i].setImage(resourceManagement.getImageCard(host.timeline[i].cardReference.name));
//                }
//            }
//        }
//        for (int i=0;i<21;i++)
//        {
//            if(guest.timeline[i]!=null)
//            {
//                if(!Objects.equals(guest.timeline[i].name, "empty"))
//                {
//                    cardImage[0][i].setImage(resourceManagement.getImageCard(guest.timeline[i].name));
//                }
//                else if(guest.timeline[i].cardReference!=null)
//                {
//                    cardImage[0][i].setImage(resourceManagement.getImageCard(guest.timeline[i].cardReference.name));
//                }
//            }
//        }
//    }
//}
////(8+43.905*n,8+43.905*(n+1)),(206,281.5)
////(8+43.905*n,8+43.905*(n+1)),(281.5,357)
////395,400
////554,119
package com.example.oop_project_phase2.Game;


import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.SceneController;
import com.example.oop_project_phase2.UserManagement.*;
import com.example.oop_project_phase2.card.Card;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Game {
    public static Scene scene;
    public static int temp;
    public static ImageView tempi;
    @FXML
    GridPane tableGame,handhost,handguest;
    @FXML
    static ImageView [] [] cardImage=new ImageView[2][21];
    @FXML
    static ImageView [] handhostImage=new ImageView[6];
    @FXML
    static Label [] handhostInformation=new Label[6];
    @FXML
    static ImageView [] handGuestImage=new ImageView[6];
    @FXML
    static Label [] handGuestInformation=new Label[6];
    @FXML
     ImageView imageHost;
    @FXML
     ImageView imageGuest;
    @FXML
     Label hostname;
    @FXML
     Label guestname,round;
    @FXML
    AnchorPane Root;
    @FXML
     Label hitpointGuest;
    @FXML
     Label hitpointHost,endGame;
    public void initialize(){
        setTableGame();
        setCharacter(Gameinit.Host,Gameinit.Guest);
        startHand();
        GameController.init(Gameinit.Host,Gameinit.Guest);
        changeGraphic(GameController.host2,GameController.quest2);
//        changeGraphicElement(Gameinit.Host,Gameinit.Guest);
    }
    public void setTableGame()
    {
        for (int i=0;i<2;i++)
        {
            for(int j=0;j<21;j++)
            {
                try {
                    cardImage[i][j]=new ImageView(resourceManagement.cell);
                    cardImage[i][j].setFitWidth(45);
                    cardImage[i][j].setFitHeight(78);
                    tableGame.add(cardImage[i][j],j,i);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }
    }
    public  void setCharacter(User Host, User Guest)
    {
        if(Host.character==User.Character.valueOf("Gunner"))
        {
            imageHost.setImage(resourceManagement.gunner);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);

        }
        else if(Host.character==User.Character.valueOf("Fighter"))
        {
            imageHost.setImage(resourceManagement.fighter);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        else if(Host.character==User.Character.valueOf("Wizard"))
        {
            imageHost.setImage(resourceManagement.wizard);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        else if(Host.character==User.Character.valueOf("Dancer"))
        {
            imageHost.setImage(resourceManagement.dancer);
            imageHost.setFitHeight(150);
            imageHost.setFitWidth(200);
        }
        if(Guest.character==User.Character.valueOf("Gunner"))
        {
            imageGuest.setImage(resourceManagement.gunner);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Guest.character==User.Character.valueOf("Fighter"))
        {
            imageGuest.setImage(resourceManagement.fighter);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Guest.character==User.Character.valueOf("Wizard"))
        {
            imageGuest.setImage(resourceManagement.wizard);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
        else if(Guest.character==User.Character.valueOf("Dancer"))
        {
            imageGuest.setImage(resourceManagement.dancer);
            imageGuest.setFitHeight(150);
            imageGuest.setFitWidth(200);
        }
    }
    public  static void showHand(User Host,User Guest)
    {
        for(int i=0;i<Host.hand.size();i++)
        {
            handhostImage[i].setImage(resourceManagement.getImageCard(Host.hand.get(i).name));
            if(Host.hand.get(i).feature==null)
            {
                int a=Host.hand.get(i).playerDamage*Host.hand.get(i).Duration;
                handhostInformation[i].setText("AD:"+Host.hand.get(i).defence_attack+"/PD:"+a+"/D:"+Host.hand.get(i).Duration);
            }
            else
            {
                handhostInformation[i].setText("f: "+Host.hand.get(i).feature);
            }
        }
        for (int i=Host.hand.size();i<6;i++)
        {
            handhostImage[i].setImage(resourceManagement.wall);
            handhostInformation[i].setText("");
        }
        for(int i=0;i<Guest.hand.size();i++)
        {
            handGuestImage[i].setImage(resourceManagement.getImageCard(Guest.hand.get(i).name));
            if(Guest.hand.get(i).feature==null)
            {
                int a=Guest.hand.get(i).playerDamage*Guest.hand.get(i).Duration;
                handGuestInformation[i].setText("AD:"+Guest.hand.get(i).defence_attack+"/PD"+a+"/D"+Guest.hand.get(i).Duration);
            }
        }
        for (int i=Guest.hand.size();i<6;i++)
        {
            handGuestImage[i].setImage(resourceManagement.wall);
            handGuestInformation[i].setText("");
        }
    }
    public void startHand()
    {
        for(int i=0;i<6;i++)
        {
            handhostInformation[i]=new Label();
            handhostImage[i]=new ImageView(resourceManagement.wall);
            handhostImage[i].setFitWidth(86);
            handhostImage[i].setFitHeight(89);
            handhostImage[i].setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ImageView imageView =  (ImageView) mouseEvent.getSource();
                    imageView.setLayoutX(0);
                    imageView.setLayoutY(0);
                    imageView.setX(mouseEvent.getSceneX());
                    imageView.setY(mouseEvent.getSceneY());
                }
            });

            handhostImage[i].setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ImageView imageView = (ImageView) mouseEvent.getSource();
                    tempi = imageView;
                    System.out.println("hello");
                    temp = GridPane.getColumnIndex(tempi);
                    handhost.getChildren().remove(tempi);
                    Root.getChildren().add(tempi);
                }
            });
            handhostImage[i].setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    Root.getChildren().removeAll(tempi);
                    handhost.add(tempi,temp,1);
                    if (mouseEvent.getSceneY() > 281.5 && mouseEvent.getSceneY() < 357){
                        int n = (int)((int)(mouseEvent.getSceneX()-8)/43.905);{
                            if (GameController.host2.hand.get(temp).feature == null){
                                if(!TimelineController.setCardInGameWithSpace(GameController.host2,GameController.quest2,temp,n))
                                    return;
                            }else if (GameController.host2.hand.get(temp).feature.equals("duplicator")){
                                if(!TimelineController.SetDuplicator(GameController.host2,GameController.quest2,temp,n))
                                    return;
                            }else {
                                if(!TimelineController.setCardInGameWithNoSpace(GameController.host2,GameController.quest2,temp))
                                    return;
                            }
                            changeGraphic(GameController.host2,GameController.host1);
                            round.setText("round:"+GameController.round);
                            Game.showHand(GameController.host2,GameController.quest2);
                            setTimLineImage(GameController.host2,GameController.quest2);
                            GameController.endlittleround();
                            changeGraphic(GameController.host2,GameController.quest2);
                            if ((GameController.whoStart && GameController.host2 == GameController.host1)||(!GameController.whoStart && GameController.host2 == GameController.quest1)){
                                GameController.freshhand();
                                Game.showHand(GameController.host2,GameController.quest2);
                                setTimLineImage(GameController.host2,GameController.quest2);
                                changeGraphic(GameController.host2,GameController.quest2);
                            }
                            round.setText("round:"+GameController.round);
                            if (GameController.round == 0){
                                GameController.end4round(hitpointHost,hitpointGuest);
                                if(!GameController.finish)
                                {
                                    GameController.host2.comeInHound = new ArrayList<ArrayList<String>>();
                                    GameController.quest2.comeInHound = new ArrayList<ArrayList<String>>();
                                    GameController.host2.hand = GameController.get5CardHand(GameController.host2);
                                    GameController.quest2.hand = GameController.get5CardHand(GameController.quest2);
                                    GameController.host2.timeline=new Card[21];
                                    GameController.quest2.timeline=new Card[21];
                                    setTimLineImage(GameController.host2,GameController.quest2);
                                    GameController.emptyCell(GameController.host2,GameController.quest2);
                                    showHand(GameController.host2,GameController.quest2);
                                }
                            }
                            if (GameController.finish){
                                GameController.endGAME();
                                setEndGame(GameController.host2,GameController.quest2);
                                scene.addEventFilter(KeyEvent.KEY_PRESSED,(KeyEvent e) ->{
                                    if(e.getCode()== KeyCode.ENTER)
                                    {
                                        try {
                                            SceneController.switchtoMainMenu();
                                        }
                                        catch (Exception exception)
                                        {

                                        }
                                    }
                                });

                            }
                            round.setText("round:"+GameController.round);
                        }
                    }
                }
            });
            handhost.add(handhostImage[i],i,1);
            handhost.add(handhostInformation[i],i,0);
        }
        for(int i=0;i<6;i++)
        {
            handGuestInformation[i]=new Label();
            handGuestImage[i]=new ImageView(resourceManagement.wall);
            handGuestImage[i].setFitWidth(86);
            handGuestImage[i].setFitHeight(89);
            handguest.add(handGuestImage[i],i,1);
            handguest.add(handGuestInformation[i],i,0);
        }
    }
    //    public void changeGraphicElement(User Host,User Guest)
//    {
//        setCharacter(Host,Guest);
//        showHand(Host,Guest);
//        guestname.setText(Guest.Nickname);
//        hostname.setText(Host.Nickname);
//        hitpointGuest.setText(String.valueOf(Guest.hitpoint));
//        hitpointHost.setText(String.valueOf(Host.hitpoint));
//    }
    public static void setTimLineImage(User host,User guest)
    {
        for(int i=0;i<21;i++)
        {
            if(host.timeline[i]!=null)
            {
                if(!Objects.equals(host.timeline[i].name, "empty"))
                {
                    cardImage[1][i].setImage(resourceManagement.getImageCard(host.timeline[i].name));
                }
                else if(host.timeline[i].cardReference!=null)
                {
                    cardImage[1][i].setImage(resourceManagement.getImageCard(host.timeline[i].cardReference.name));
                }
                else
                {
                    cardImage[1][i].setImage(resourceManagement.wall);
                }
            }
            else
            {
                cardImage[1][i].setImage(resourceManagement.cell);
            }
        }
        for (int i=0;i<21;i++)
        {
            if(guest.timeline[i]!=null)
            {
                if(!Objects.equals(guest.timeline[i].name, "empty"))
                {
                    cardImage[0][i].setImage(resourceManagement.getImageCard(guest.timeline[i].name));
                }
                else if(guest.timeline[i].cardReference!=null)
                {
                    cardImage[0][i].setImage(resourceManagement.getImageCard(guest.timeline[i].cardReference.name));
                }
                else
                {
                    cardImage[0][i].setImage(resourceManagement.wall);
                }
            }
            else
            {
                cardImage[0][i].setImage(resourceManagement.cell);
            }
        }
    }
    public  void changeGraphic(User host,User guest)
    {
        hostname.setText(host.Nickname);
        hitpointHost.setText(String.valueOf(host.hitpoint));
        guestname.setText(guest.Nickname);
        hitpointGuest.setText(String.valueOf(guest.hitpoint));
        setCharacter(host,guest);
    }
    public void setEndGame(User host,User guest)
    {
        if(host.hitpoint>0)
        {
            if(Gameinit.wager)
            {
                endGame.setText(endGame.getText()+"user "+host.Nickname+" win!!");
                endGame.setText(endGame.getText()+"user "+host.Nickname+" get "+Gameinit.wagerint+" coin");
                endGame.setText(endGame.getText()+"user "+guest.Nickname+" lose!!");
                int a=-Gameinit.wagerint;
                endGame.setText(endGame.getText()+"user "+guest.Nickname+"get "+a+" coin");
                host.Coins+=Gameinit.wagerint;
                guest.Coins-=Gameinit.wagerint;
            }
            else
            {
                endGame.setText(endGame.getText()+"user "+host.Nickname+" win!!");
                endGame.setText(endGame.getText()+"user"+host.Nickname+" get 50 coin");
                host.Coins+=50;
                endGame.setText(endGame.getText()+"user"+host.Nickname+" experience increase 60 ");
                host.XP+=60;
                endGame.setText(endGame.getText()+"user "+guest.Nickname+" lose!!");
                endGame.setText(endGame.getText()+"user"+guest.Nickname+" experience increase 5 ");
                guest.XP+=5;
            }
        }
        else if(guest.hitpoint>0)
        {
            if(Gameinit.wager)
            {
                endGame.setText(endGame.getText()+"user "+guest.Nickname+" win!!");
                endGame.setText(endGame.getText()+"user "+guest.Nickname+" get "+Gameinit.wagerint+" coin");
                endGame.setText(endGame.getText()+"user "+host.Nickname+" lose!!");
                int a=-Gameinit.wagerint;
                endGame.setText(endGame.getText()+"user "+host.Nickname+"get "+a+" coin");
                guest.Coins+=Gameinit.wagerint;
                host.Coins-=Gameinit.wagerint;
            }
            else
            {
                endGame.setText(endGame.getText()+"user "+guest.Nickname+" win!!");
                endGame.setText(endGame.getText()+"user"+guest.Nickname+" get 50 coin");
                guest.Coins+=50;
                endGame.setText(endGame.getText()+"user"+guest.Nickname+" experience increase 60 ");
                guest.XP+=60;
                endGame.setText(endGame.getText()+"user "+host.Nickname+" lose!!");
                endGame.setText(endGame.getText()+"user"+host.Nickname+" experience increase 5 ");
                host.XP+=5;
            }
        }
    }
    public void back()
    {
        try {
            SceneController.switchtoMainMenu();
        }
        catch (Exception e)
        {

        }
    }
}
//(8+43.905*n,8+43.905*(n+1)),(206,281.5)
//(8+43.905*n,8+43.905*(n+1)),(281.5,357)
//395,400
//554,119