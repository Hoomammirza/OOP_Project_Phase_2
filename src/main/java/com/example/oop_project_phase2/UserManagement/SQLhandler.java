package com.example.oop_project_phase2.UserManagement;

import com.example.oop_project_phase2.card.Card;

import java.sql.*;
import java.util.ArrayList;

public class SQLhandler {
    public static boolean isConnected = false;
    private static Connection con = null;
    public static void connect(){
        try {
            if (isConnected)
                throw new Exception("AlreadyConnected");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","honor1384");
            isConnected = true;
        } catch (Exception e){System.out.println(e);}
    }
    public static User readUser(String username,String password) throws PasswordExeption,NoUserException{
        ResultSet rs;
        PreparedStatement statement;

        try {
            if (isConnected) {
                statement = con.prepareStatement("select * from user where Username = ?;");
                statement.setString(1,username);
                rs = statement.executeQuery();
                if (rs.next()){
                    if (rs.getString("Password").equals(password)){
                        User user = new User(rs.getString("Username"),rs.getString("Password"),rs.getString("Nickname"),rs.getString("Email"),rs.getString("SecurityQ"),rs.getString("SecurityQA"),rs.getBoolean("isAdmin"),rs.getInt("Level"),rs.getInt("Coins"),rs.getInt("XP"));
                        user.cards = getUsercards(user);
                        return user;
                    }else throw new PasswordExeption();
                } else throw new NoUserException();
            }

        } catch (SQLException e){System.out.println(e);}
        return null;
    }
    public static void updateUser(User user){
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update user set Level = "+user.Level+",Coins = "+user.Coins+",XP = "+user.XP+" where Username = '"+user.Username+"';");
            }

        } catch (SQLException e){System.out.println(e);}
    }
    public static boolean Userexists(String username){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from user where Username = '" + username + "';");
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e){System.out.println(e);}
        return false;
    }
    public static String getSQ(String username){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from user where Username = '" + username + "';");
                if (rs.next()) {
                    return rs.getString("SecurityQ");
                } else {
                    throw new RuntimeException();
                }
            }
        } catch (Exception e){System.out.println(e);}
        throw new RuntimeException();
    }
    public static boolean verifySQA(String username,String Answer){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from user where Username = '" + username + "';");
                if (rs.next()) {
                    return rs.getString("SecurityQA").equals(Answer);
                } else {
                    throw new RuntimeException();
                }
            }
        } catch (Exception e){System.out.println(e);}
        throw new RuntimeException();
    }
    public static void changePassword( String username ,String password){
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update user set Password = '"+password+"' WHERE Username = '"+username+"';");

            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void createuser(User user){
        Statement statement;
        try {
            statement = con.createStatement();
            statement.execute("insert into user (Username,Password,Nickname,Email,SecurityQ,SecurityQA,isAdmin,Coins,Level,XP) VALUES ('"+user.Username+"','"+user.Password+"','"+user.Nickname+"','"+user.Email+"','"+user.SecurityQ.toString()+"','"+user.SecurityQA+"',"+0+",100,1,0);");
            for (Card card:user.cards){
                giveCard(card,user,1);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void changeUsername( User user ,String Username){
        Statement statement;
        String username = user.Username;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update user set Username = '"+Username+"' WHERE Username = '"+username+"';");
            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void changeEmail( User user ,String Email){
        Statement statement;
        String username = user.Username;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update user set Email = '"+Email+"' WHERE Username = '"+username+"';");

            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void changeNickname( User user ,String Nickname){
        Statement statement;
        String username = user.Username;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update user set Nickname = '"+Nickname+"' WHERE Username = '"+username+"';");

            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void giveCard (Card card, User user, int level) {
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.execute("insert into usercard (Username,Name,level) values ('" + user.Username + "','" + card.name + "'," + level + ");");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static boolean existsCard(String name){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from cards where Name = '" + name + "';");
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e){System.out.println(e);}
        return false;
    }
    public static Card getCard(String name){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from cards where Name = '" + name + "';");
                if (rs.next()) {
                    return new Card(rs.getString("Name"),rs.getInt("DefenceAttack"),rs.getInt("Duration"),rs.getInt("Damage"),rs.getInt("Upcost"),rs.getInt("Minlevel"), rs.getString("feature"),rs.getString("Type"),1);
                } else {
                    return null;
                }
            }
        } catch (Exception e){System.out.println(e);}
        return null;
    }
    public static Card getCard(User user, String name){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from cards where Name = '" + name + "';");
                if (rs.next()) {
                    return new Card(rs.getString("Name"),rs.getInt("DefenceAttack"),rs.getInt("Duration"),rs.getInt("Damage"),rs.getInt("Upcost"),rs.getInt("Minlevel"), rs.getString("feature"),rs.getString("Type"),getCardlevel(user, name));
                } else {
                    return null;
                }
            }
        } catch (Exception e){System.out.println(e);}
        return null;
    }
    public static boolean hasCard(User user,  String name){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Name = '" + name + "' and Username = '"+user.Username+"';");
                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e){System.out.println(e);}
        return false;
    }
    public static int getCardlevel(User user,  String name){
        ResultSet rs;
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Name = '" + name + "' and Username = '"+user.Username+"';");
                if (rs.next()) {
                    return rs.getInt("level");
                }
            }
        } catch (Exception e){System.out.println(e);}
        return 0;
    }
    public static ArrayList<Card> getallcards(){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from cards ;");
                while (rs.next()) {
                    cards.add(new Card(rs.getString("Name"),rs.getInt("DefenceAttack"),rs.getInt("Duration"),rs.getInt("Damage"),rs.getInt("Upcost"),rs.getInt("Minlevel"), rs.getString("feature"),rs.getString("Type"),1));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }
    public static ArrayList<Card> getUsercards(User user){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Username = '"+user.Username+"';");
                while (rs.next()) {
                    cards.add(getCard(user,rs.getString("Name")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }public static ArrayList<Card> getUsercardsupgradable(User user){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Username = '"+user.Username+"';");
                while (rs.next()) {
                    Card card = getCard(user,rs.getString("Name"));
                    if (card.upgradeLevel <= user.Level && card.feature == null)
                        cards.add(card);
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }
    public static ArrayList<Card> getUsercardsspecial(User user){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Username = '"+user.Username+"';");
                while (rs.next()) {
                    Card card = getCard(user,rs.getString("Name"));
                    if (card.feature != null)
                        cards.add(getCard(user,rs.getString("Name")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }
    public static ArrayList<Card> getUsercardsnormal(User user){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from usercard where Username = '"+user.Username+"';");
                while (rs.next()) {
                    Card card = getCard(user,rs.getString("Name"));
                    if (card.feature == null)
                        cards.add(getCard(user,rs.getString("Name")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }
    public static ArrayList<Card> getCardsnormal(){
        ResultSet rs;
        Statement statement;
        ArrayList<Card> cards = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from cards where feature is null ;");
                while (rs.next()) {
                    cards.add(getCard(rs.getString("Name")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return cards;
    }
    public static int updateCard(User user,  String name, int level){
        Statement statement;
        try {
            if (isConnected) {
                statement = con.createStatement();
                statement.executeUpdate("update usercard set level = "+ (level)+" where Name = '" + name + "' and Username = '"+user.Username+"';");
            }
        } catch (Exception e){System.out.println(e);}
        return 0;
    }
    public static ArrayList<Historyitem> getHistory(User user, String sort){
        ResultSet rs;
        Statement statement;

        ArrayList<Historyitem> items = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from history where Host = '"+user.Username+"' ORDER BY "+sort+";");

                while (rs.next()) {
                    items.add(new Historyitem(rs.getString("Host"),rs.getString("Guest"),rs.getBoolean("Hostwin"),rs.getBoolean("Guestwin"),rs.getDate("Date"),rs.getTime("Time"),rs.getInt("Hostlevel"),rs.getInt("Guestlevel"),rs.getInt("Hostcoin"),rs.getInt("HostXP"),rs.getInt("GuestCoin"),rs.getInt("GuestXP")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return items;
    }
    public static void setnewHistory(String Host,String Guest,boolean Hostwin,boolean Guestwin ,int Hostlevel,int Guestlevel,int HostCoin,int HostXP,int GuestCoin,int GuestXP){
        try {
            if (isConnected) {
                PreparedStatement statement = con.prepareStatement("insert into history (Host,Guest,Hostwin,Guestwin,Date,Time,Hostlevel,Guestlevel,Hostcoin,HostXP,GuestCoin,GuestXP) values (?,?,?,?,?,?,?,?,?,?,?,?);");
                statement.setString(1,Host);
                statement.setString(2,Guest);
                statement.setBoolean(3,Hostwin);
                statement.setBoolean(4,Guestwin);
                statement.setDate(5,new Date(new java.util.Date().getTime()));
                statement.setTime(6,new Time(new java.util.Date().getTime()));
                statement.setInt(7,Hostlevel);
                statement.setInt(8,Guestlevel);
                statement.setInt(9,HostCoin);
                statement.setInt(10,HostXP);
                statement.setInt(11,GuestCoin);
                statement.setInt(12,GuestXP);
                statement.execute();
            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void addCard(String name,int defenceattack,int duration,int playerdamage,int upgradelevel,int upgradecost){
        try {
            if (isConnected) {
                PreparedStatement statement = con.prepareStatement("insert into cards (Name,DefenceAttack,Damage,Duration,Upcost,Minlevel) values (?,?,?,?,?,?);");
                statement.setString(1,name);
                statement.setInt(2,defenceattack);
                statement.setInt(3,playerdamage);
                statement.setInt(4,duration);
                statement.setInt(5,upgradecost);
                statement.setInt(6,upgradelevel);
                statement.execute();
            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void edditcard(String name,int defenceattack,int duration,int playerdamage,int upgradelevel,int upgradecost,String originalname){
        try {
            if (isConnected) {
                PreparedStatement statement = con.prepareStatement("delete from cards where Name = ?;");
                statement.setString(1,originalname);
                statement.execute();
                addCard(name, defenceattack, duration, playerdamage, upgradelevel, upgradecost);
            }
        } catch (Exception e){System.out.println(e);}
    }
    public static void removeCard(String originalname){
        try {
            if (isConnected) {
                PreparedStatement statement = con.prepareStatement("delete from cards where Name = ?;");
                statement.setString(1,originalname);
                statement.execute();
            }
        } catch (Exception e){System.out.println(e);}
    }
    public static ArrayList<User> getallusers(){
        ResultSet rs;
        Statement statement;

        ArrayList<User> items = new ArrayList<>();
        try {
            if (isConnected) {
                statement = con.createStatement();
                rs = statement.executeQuery("select * from user where isAdmin = 0 order by Username asc");
                while (rs.next()) {
                    items.add(new User(rs.getString("Username"),rs.getString("Password"),rs.getString("Nickname"),rs.getString("Email"),rs.getString("SecurityQ"),rs.getString("SecurityQA"),rs.getBoolean("isAdmin"),rs.getInt("Level"),rs.getInt("Coins"),rs.getInt("XP")));
                }
            }
        } catch (Exception e){System.out.println(e);}
        return items;

    }
}
