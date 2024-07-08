package com.example.oop_project_phase2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class signup {
    signup(){
        initAsciiArt();
    }
    public static ArrayList<ArrayList<String>> ascii_art;
    public static void initAsciiArt() {
        ascii_art=new ArrayList<>();
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(0).add(" .d8888b. ");
        ascii_art.get(0).add("d88P  Y88b");
        ascii_art.get(0).add("888    888");
        ascii_art.get(0).add("888    888");
        ascii_art.get(0).add("888    888");
        ascii_art.get(0).add("888    888");
        ascii_art.get(0).add("Y88b  d88P");
        ascii_art.get(0).add("  Y8888P  ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(1).add(" d888  ");
        ascii_art.get(1).add("d8888  ");
        ascii_art.get(1).add("  888  ");
        ascii_art.get(1).add("  888  ");
        ascii_art.get(1).add("  888  ");
        ascii_art.get(1).add("  888  ");
        ascii_art.get(1).add("  888  ");
        ascii_art.get(1).add("8888888");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(2).add(" .d8888b. ");
        ascii_art.get(2).add("d88P  Y88b");
        ascii_art.get(2).add("       888");
        ascii_art.get(2).add("     .d88P");
        ascii_art.get(2).add(" .od888P\" ");
        ascii_art.get(2).add("d88P\"     ");
        ascii_art.get(2).add("888\"      ");
        ascii_art.get(2).add("888888888 ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(3).add(" .d8888b. ");
        ascii_art.get(3).add("d88P  Y88b");
        ascii_art.get(3).add("     .d88P");
        ascii_art.get(3).add("    8888\" ");
        ascii_art.get(3).add("     \"Y8b.");
        ascii_art.get(3).add("888    888");
        ascii_art.get(3).add("Y88b  d88P");
        ascii_art.get(3).add(" \"Y8888P\" ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(4).add("    d8888 ");
        ascii_art.get(4).add("   d8P888 ");
        ascii_art.get(4).add("  d8P 888 ");
        ascii_art.get(4).add(" d8P  888 ");
        ascii_art.get(4).add("d88   888 ");
        ascii_art.get(4).add("8888888888");
        ascii_art.get(4).add("      888 ");
        ascii_art.get(4).add("      888 ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(5).add("888888888 ");
        ascii_art.get(5).add("888       ");
        ascii_art.get(5).add("888       ");
        ascii_art.get(5).add("8888888b. ");
        ascii_art.get(5).add("     \"Y88b");
        ascii_art.get(5).add("       888");
        ascii_art.get(5).add("Y88b  d88P");
        ascii_art.get(5).add(" \"Y8888P\" ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(6).add(" .d8888b. ");
        ascii_art.get(6).add("d88P  Y88b");
        ascii_art.get(6).add("888       ");
        ascii_art.get(6).add("888d888b. ");
        ascii_art.get(6).add("888P \"Y88b");
        ascii_art.get(6).add("888    888");
        ascii_art.get(6).add("Y88b  d88P");
        ascii_art.get(6).add(" \"Y8888P\" ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(7).add("8888888888");
        ascii_art.get(7).add("      d88P");
        ascii_art.get(7).add("     d88P ");
        ascii_art.get(7).add("    d88P  ");
        ascii_art.get(7).add(" 88888888 ");
        ascii_art.get(7).add("  d88P    ");
        ascii_art.get(7).add(" d88P     ");
        ascii_art.get(7).add("d88P      ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(8).add(" .d8888b. ");
        ascii_art.get(8).add("d88P  Y88b");
        ascii_art.get(8).add("Y88b. d88P");
        ascii_art.get(8).add(" \"Y88888\" ");
        ascii_art.get(8).add(".d8P\"\"Y8b.");
        ascii_art.get(8).add("888    888");
        ascii_art.get(8).add("Y88b  d88P");
        ascii_art.get(8).add(" \"Y8888P\" ");
        ascii_art.add(new ArrayList<String>());
        ascii_art.get(9).add(" .d8888b. ");
        ascii_art.get(9).add("d88P  Y88b");
        ascii_art.get(9).add("888    888");
        ascii_art.get(9).add("Y88b. d888");
        ascii_art.get(9).add(" \"Y888P888");
        ascii_art.get(9).add("       888");
        ascii_art.get(9).add("Y88b  d88P");
        ascii_art.get(9).add(" \"Y8888P\" ");
    }
    public static ArrayList<String> showRandomCaptcha(String random) {
        initAsciiArt();
        ArrayList<String>answer=new ArrayList<String>();
        for(int i=0;i<8;i++)
        {
            String a="";
            for(int j=0;j<random.length();j++)
            {
                String b= String.valueOf(random.charAt(j));
                a+=ascii_art.get(Integer.parseInt(b)).get(i);
                a+="  ";
            }
            answer.add(a);
        }
        return answer;
    }
    public static int run()
    {
        initAsciiArt();

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean quit=false;

        Matcher exit;
        Matcher showcurrrentmenu;
        Matcher signup;
        Matcher back;
        System.out.println("commands:\n" +
                "*  user create -u <username> -p <password> <password confirmation> –email <email> -n <nickname>\n" +
                "*  show current menu\n" +
                "*  exit");


        while (!quit)
        {
            input=scanner.nextLine();
            exit = Misc.getMatcher(input, "^exit(\\s*)$");
            back = Misc.getMatcher(input, "^back(\\s*)$");
            showcurrrentmenu = Misc.getMatcher(input, "^show current menu(\\s*)$");
            signup = Misc.getMatcher(input, "^user(\\s+)create(\\s+)-u(\\s+)(?<username>\\S*)(\\s+)-p(\\s+)(?<password>\\S*)(\\s+)(?<passwordconfirmation>\\S*)(\\s)-email(\\s+)(?<email>\\S*)(\\s+)-n(\\s+)(?<nickname>\\S*)(\\s*)$");

            if (exit.find()){
                return 0;
            }else if (showcurrrentmenu.find()){
                System.out.println("Registermenu");
            }else if (signup.find()){
                if(signup(signup))
                {
                    return 1;
                }
            }else if (back.find()){
                return 1;
            }
        }
        return 0;
    }
    public static boolean signup(Matcher matcher){
        String username=matcher.group("username");
        String password=matcher.group("password");
        String passwordconfirmation=matcher.group("passwordconfirmation");
        String email=matcher.group("email");
        String nickname=matcher.group("nickname");
        String securityQA;
        String securityQs;
        String securityQAc;
        User.securityQ securityQ = null;
        if (!username.isEmpty()&&!password.isEmpty()&&!email.isBlank()&&!nickname.isEmpty()){
            if (verifyUsername(username)) {
                if (!Users.ExistUsername(username)) {
                    if (!password.equals("random")) {
                        try {
                            verifyPassword(password);
                            if (password.equals(passwordconfirmation)) {
                            } else {
                                System.out.println("password doesn't match password confirmation!");
                                return false;
                            }
                        } catch (WeakPasswordException e) {
                            String s = e.message;
                            System.out.println(s);
                            return false;
                        }
                    }else {
                        password = generateRandomPassword();
                        System.out.println("your password is: " + password);
                        passwordconfirmation = new Scanner(System.in).nextLine();
                        if (!passwordconfirmation.equals(password)){
                            System.out.println("Password confirmation doesn't match!");
                            return false;
                        }
                    }
                    if (verifyEmail(email)) {
                        System.out.println("User created successfully. Please choose a security question :\n" +
                                "• 1-What is your father’s name ?\n" +
                                "• 2-What is your favourite color ?\n" +
                                "• 3-What was the name of your first pet?\n" +
                                "answer using this format:" +
                                "question pick -q <question-number> -a <answer> -c <answer- confirm>");
                        String input = new Scanner(System.in).nextLine();
                        Matcher matcher1 = Misc.getMatcher(input, "^question(\\s+)pick(\\s+)-q(\\s+)(?<questionnumber>\\S+)(\\s+)-a(\\s+)(?<answer>\\S+)(\\s+)-c(\\s+)(?<answerconfirm>\\S+)$");
                        if (matcher1.find()) {
                            securityQs = matcher1.group("questionnumber");
                            if (securityQs.matches("\\d")) {
                                switch (Integer.parseInt(securityQs)) {
                                    case 1:
                                        securityQ = User.securityQ.Father;
                                        break;
                                    case 2:
                                        securityQ = User.securityQ.Color;
                                        break;
                                    case 3:
                                        securityQ = User.securityQ.Pet;
                                        break;
                                    default:
                                        System.out.println("invalid security question number!");
                                        return false;
                                }
                                securityQA = matcher1.group("answer");
                                securityQAc = matcher1.group("answerconfirm");
                                if (securityQA.equals(securityQAc)) {
                                    String captcha = getrandomcaptcha();
                                    ArrayList<String> captchashow = showRandomCaptcha(captcha);
                                    for (int i = 0; i < captcha.length(); i++) {
                                        Random random = new Random(System.currentTimeMillis());
                                        for (int j = 0; j < 40; j++) {
                                            int k = random.nextInt(captcha.length()*(12) - 4);
                                            int m = random.nextInt(8);
                                            captchashow.set(m,captchashow.get(m).substring(0,k)+'A'+captchashow.get(m).substring(k+1));
                                        }
                                    }
                                    System.out.println("please write the numbers you see on screen:");
                                    for (int j = 0; j < 8; j++) {
                                        System.out.println(captchashow.get(j));
                                    }
                                    String captchaanswer = new Scanner(System.in).nextLine();
                                    if (captcha.equals(captchaanswer)) {
                                        User tempuser = new User(username, password, nickname, email, securityQ.toString(), securityQA, false);
                                        tempuser.cards = getrandom20(SQLhandler.getallcards());
                                        SQLhandler.createuser(tempuser);
                                        System.out.println("signup successful :D");
                                        return true;
                                    }else {
                                        System.out.println("captcha failed!");
                                    }
                                } else {
                                    System.out.println("answer confirmation does not match!");
                                }
                            } else {
                                System.out.println("invalid security question number!");
                                return false;
                            }
                        } else {
                            System.out.println("invalid command!");
                        }
                    } else {
                        System.out.println("invalid email!");
                    }

                } else {
                    System.out.println("a user with this username already exists!");
                }
            }else {
                System.out.println("invalid username: username should contain only letters numbers and underscore!");
            }
        }else {
            System.out.println("please fill all fields!");
        }
        return false;
    }
    public static String generateRandomPassword(){
        String[] strings = new String[]{"ABCDEFGHIJKLMNOPQRSTUVWXYZ","abcdefghijklmnopqrstuvwxyz","0123456789","!@#$%^&*"};
        Random random = new Random(System.currentTimeMillis());
        String password = "";
        int i = random.nextInt(8);
        for (int j = 0; j < 8 + i; j++) {
             password += getrandomCharachter(strings[j%4],random);
        }
        return password;
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
}