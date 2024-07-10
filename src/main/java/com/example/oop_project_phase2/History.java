package com.example.oop_project_phase2;


import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.Historyitem;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.UserManagement.Users;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class History {
    public static User user;
    public static int page = 1;
    private static Scanner input = new Scanner(System.in);
    private static ObservableList<Historyitem> historyitems;
    @FXML
    TableView<Historyitem> table;
    @FXML
    TableColumn<Historyitem,String> Date;
    @FXML
    TableColumn<Historyitem,String> Time;
    @FXML
    TableColumn<Historyitem,String> Win;
    @FXML
    TableColumn<Historyitem,String> OpponentName;
    @FXML
    TableColumn<Historyitem,Integer> OpponentLevel;
    @FXML
    TableColumn<Historyitem,Integer> Coins;
    @FXML
    TableColumn<Historyitem,Integer> XP;
    public void initialize(){
        user = Users.LoginUser;
        Matcher sort;
        sort = Misc.getMatcher("sort Date DESC", "^sort(\\s+)(?<sorttype>Date|OpponentsName|Win|Oppenentslevel)(\\s+)(?<sortorder>ASC|DESC)");
        sort.find();
        getHistory(sort);
        table.setItems(historyitems);
        Date.setCellValueFactory(new PropertyValueFactory<>("datep"));
        Time.setCellValueFactory(new PropertyValueFactory<>("timep"));
        Win.setCellValueFactory(new PropertyValueFactory<>("Hostwinp"));
        OpponentName.setCellValueFactory(new PropertyValueFactory<>("Guestp"));
        OpponentLevel.setCellValueFactory(new PropertyValueFactory<>("Guestlevelp"));
        Coins.setCellValueFactory(new PropertyValueFactory<>("HostCoinp"));
        XP.setCellValueFactory(new PropertyValueFactory<>("HostXPp"));
    }
//    public static int run(){
//        page = 1;
//        user= Users.LoginUser;
//        String in;
//        boolean quit = false;
//
//        Matcher next;
//        Matcher previous;
//        Matcher pagenumber;
//        Matcher sort;
//        Matcher back;
//        Matcher showMenu;
//        Matcher exit;
//
//        System.out.println("commands:\n" +
//                "* start game\n"+
//                "* show my cards:\n" +
//                "* sort <sorttyoe>(Date|OpponentsName|Win|Oppenentslevel) <sortorder>(ASC|DESC)\n" +
//                "* show current menu\n" +
//                "* profile menu\n"+
//                "* back\n" +
//                "* exit");
//        sort = Misc.getMatcher("sort Date DESC", "^sort(\\s+)(?<sorttype>Date|OpponentsName|Win|Oppenentslevel)(\\s+)(?<sortorder>ASC|DESC)");
//        sort.find();
//        getHistory(sort);
//
//        while (!quit){
//            in = input.nextLine();
//            next = Misc.getMatcher(in,"^next$");
//            previous = Misc.getMatcher(in,"^previous$");
//            pagenumber = Misc.getMatcher(in, "^page(\\s+)(?<number>\\d+)(\\s*)$");
//            sort = Misc.getMatcher(in, "^sort(\\s+)(?<sorttype>Date|OpponentsName|Win|Oppenentslevel)(\\s+)(?<sortorder>ASC|DESC)");
//            showMenu=Misc.getMatcher(in,"show current menu");
//            back=Misc.getMatcher(in,"^back(\\s*)$");
//            exit=Misc.getMatcher(in,"^exit(\\s*)$");
//            if (exit.find()){
//                return 0;
//            } else if (back.find()) {
//                return 4;
//            } else if(showMenu.find()) {
//                System.out.println("Game History");
//            }else if (pagenumber.find()){
//                showHistory(historyitems,Integer.parseInt(pagenumber.group("number")));
//            }else if (sort.find()){
//                getHistory(sort);
//            }else if (next.find()){
//                showHistory(historyitems,page+1);
//            }else if (previous.find()){
//                showHistory(historyitems,page-1);
//            }
//        }
//        return 0;
//    }
    public static void getHistory(Matcher matcher){
            String sortorder = matcher.group("sortorder");
            String sorttype = matcher.group("sorttype");
            if (sorttype.contains("Win")){
                sorttype = "HostWin";
            }else if (sorttype.contains("OpponentsName")){
                sorttype = "Guest";
            }else if (sorttype.contains("Opponentslevel")){
                sorttype = "Guestlevel";
            }
            historyitems = FXCollections.observableArrayList(SQLhandler.getHistory(user,sorttype+" "+sortorder));
            //showHistory(historyitems,1);
            page = 1;
    }
    public static boolean showHistory(ArrayList<Historyitem> historyitems,int n){
        if (historyitems.isEmpty()){
            System.out.println("no history!");
            return false;
        }
        if (n < 0){
            System.out.println("page doesn't exist");
            return false;
        }
        if ((int)(historyitems.size() - 1)/10+1 < n ){
            System.out.println("page doesn't exist");
            return false;
        }
        System.out.println("Index   Date    Time    Wi   Opponent  Opponent level  CoinsWon  XP gained");
        for (int i = (n-1)*10 ; i < n*10 && i< historyitems.size(); i++) {
            System.out.println("-"+(i+1)+"\t"+historyitems.get(i).date.toString()+"\t"+historyitems.get(i).time.toString()+"\t"+Boolean.toString(historyitems.get(i).Hostwin)+"\t"+historyitems.get(i).Guest+"\t"+historyitems.get(i).Guestlevel+"\t"+historyitems.get(i).HostCoin+"\t"+historyitems.get(i).HostXP);
        }
        System.out.println("Page "+n+"/"+((historyitems.size() - 1)/10+1));
        page = n;
        return true;
    }
    public void back() throws IOException {
        SceneController.switchtoMainMenu();
    }

}
