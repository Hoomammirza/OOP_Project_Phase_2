package com.example.oop_project_phase2;

import com.example.oop_project_phase2.Misc.Misc;
import com.example.oop_project_phase2.UserManagement.SQLhandler;
import com.example.oop_project_phase2.UserManagement.User;
import com.example.oop_project_phase2.UserManagement.Users;
import com.example.oop_project_phase2.UserManagement.WeakPasswordException;
import com.example.oop_project_phase2.card.Card;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class signup {
    @FXML
    TextField Username;
    @FXML
    TextField Email;
    @FXML
    TextField Password;
    @FXML
    TextField Passwordc;
    @FXML
    TextField SQA;
    @FXML
    TextField Captchaanswer;
    @FXML
    Text Captcha;
    @FXML
    ChoiceBox<String> SQChoice;
    @FXML
    TextField Nickname;
    @FXML
    Text errortext;
    public int captchaint;


    public void initialize(){
        SQChoice.getItems().add(0,"1-What is your father’s name ?");
        SQChoice.getItems().add(1,"2-What is your favourite color ?");
        SQChoice.getItems().add(2,"3-What was the name of your first pet?");
        captchaint = getcaptcha();
    }

    public boolean Signup(){
        String username= Username.getText();
        String password= Password.getText();
        String passwordconfirmation= Passwordc.getText();
        String email= Email.getText();
        String nickname= Nickname.getText();
        String securityQA;
        User.securityQ securityQ = null;
        if (!username.isEmpty()&&!password.isEmpty()&&!email.isBlank()&&!nickname.isEmpty()){
            if (verifyUsername(username)) {
                if (!Users.ExistUsername(username)) {
                    try {
                        verifyPassword(password);
                        if (password.equals(passwordconfirmation)) {
                        } else {
                            errortext.setText("password doesn't match password confirmation!");
                            return false;
                        }
                    } catch (WeakPasswordException e) {
                        String s = e.message;
                        errortext.setText(s);
                        return false;
                    }
                    if (verifyEmail(email)) {
                        switch (SQChoice.getValue()) {
                            case "1-What is your father’s name ?":
                                securityQ = User.securityQ.Father;
                                break;
                            case "2-What is your favourite color ?":
                                securityQ = User.securityQ.Color;
                                break;
                            case "3-What was the name of your first pet?":
                                securityQ = User.securityQ.Pet;
                                break;
                            default:
                                errortext.setText("please select a security question");
                                return false;
                        }
                        securityQA = SQA.getText();
                        if (Captchaanswer.getText().equals(Integer.toString(captchaint))) {
                            User tempuser = new User(username, password, nickname, email, securityQ.toString(), securityQA);
                            tempuser.cards = getrandom20(SQLhandler.getallcards());
                            tempuser.isFirst = true;
                            SQLhandler.createuser(tempuser);
                            System.out.println("signup successful :D");
                            return true;
                        } else {
                            errortext.setText("captcha failed!");
                        }
                    } else {
                        errortext.setText("invalid email!");
                    }
                } else {
                    errortext.setText("a user with this username already exists!");
                }
            }else {
                errortext.setText("invalid username: username should contain only letters numbers and underscore!");
            }
        }else {
            errortext.setText("please fill all fields!");
        }
        return false;
    }
    public void RandomPassword(){
        String[] strings = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ","abcdefghijklmnopqrstuvwxyz","0123456789","!@#$%^&*"};
        Random random = new Random(System.currentTimeMillis());
        String password = "";
        int i = random.nextInt(8);
        for (int j = 0; j < 8 + i; j++) {
             password += getrandomCharachter(strings[j%4],random);
        }
        Password.setText(password);
        Passwordc.setText(password);
    }
    public void back(){
        SceneController.switchtoMenuselect();
    }
    public static char getrandomCharachter(String s,Random random){
        return s.charAt(random.nextInt(s.length()));
    }
    public static String getrandomcaptcha(){
        Random random = new Random(System.currentTimeMillis());
        String result = "";
        int i = 5 + random.nextInt(4);
        for (int j = 0; j < i; j++) {
            result += Integer.toString(random.nextInt(10));
        }
        return result;
    }

    public static ArrayList<Card> getrandom20(ArrayList<Card> start){
        ArrayList<Card> result = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        while (result.size() < 20){
            int i = random.nextInt(start.size());
            result.add(start.get(i));
            start.remove(i);
        }
        return result;
    }
    public static boolean verifyPassword(String input) throws WeakPasswordException {
        Matcher numberCeck = Misc.getMatcher(input,"[0-9]+");
        Matcher lowerCheck = Misc.getMatcher(input,"[a-z]+");
        Matcher upperCheck = Misc.getMatcher(input,"[A-Z]+");
        Matcher specialCheck = Misc.getMatcher(input,"[\\*\\!\\@\\$\\%\\^\\&\\#]+");
        Matcher lengthCeck = Misc.getMatcher(input,"(?<=^)[0-9a-zA-Z\\*\\!\\@\\$\\%\\^\\&\\#]{8,20}(?=$)");
        if (numberCeck.find()){
            if (lowerCheck.find() && upperCheck.find()){
                if (specialCheck.find()){
                    if (lengthCeck.find()){
                        return true;
                    }else {
                        throw new WeakPasswordException("Weak Password: must contain at least 8 characters!");
                    }
                }else {
                    throw new WeakPasswordException("Weak Password: must contain at least one special character!");
                }
            }else {
                throw new WeakPasswordException("Weak Password: must contain at least one lower and upper case letter!");
            }
        }else {
            throw new WeakPasswordException("Weak Password: must contain at least one number!");
        }
    }
    public static boolean verifyUsername(String usename){
        Matcher verify = Misc.getMatcher(usename,"^([a-zA-z_0-9]+)$");
        return verify.matches();
    }
    public static boolean verifyEmail(String email){
        Matcher verify = Misc.getMatcher(email,"^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        return verify.matches();
    }
    public int getcaptcha(){
        int answer;
        String string = "";
        Random random = new Random(System.currentTimeMillis());
        int j=random.nextInt(10);
        int k;
        string += Integer.toString(random.nextInt(10));
        answer = Integer.parseInt(string);
        for (int i = 0; i < 2 + j; i++) {
            k = random.nextInt(4);
            switch (k){
                case 0:
                    string += "+";
                    k = 1 + random.nextInt(9);
                    answer += k;
                    string += k;
                    break;
                case 1:
                    string += "-";
                    k = 1 + random.nextInt(9);
                    answer -= k;
                    string += k;
                    break;
                case 2:
                    string += "*";
                    k = 1 + random.nextInt(9);
                    answer *= k;
                    string += k;
                    break;
                case 3:
                    string += "/";
                    k = 1 + random.nextInt(9);
                    answer /= k;
                    string += k;
                    break;
            }
        }
        string += "=";
        Captcha.setText(string);
        return answer;
    }
}