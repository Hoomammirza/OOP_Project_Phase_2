package com.example.oop_project_phase2;

public class Users {
    public static User LoginUser = null;
    public static boolean ExistUsername(String username)
    {
        return SQLhandler.Userexists(username);
    }
    private static Timer FailLoginTimer = new Timer(0);
    private static int NumberOfTries = 0;
    public static User signin (String Username,String Password) throws NoUserException, PasswordExeption, TimerException {
        if (FailLoginTimer.running()){
            throw new TimerException(( int)FailLoginTimer.getTimeRemaining() / 1000);
        }
        try {
            User user = SQLhandler.readUser(Username,Password);
            NumberOfTries = 0;
            LoginUser = user;
            return user;
        }catch (NoUserException | PasswordExeption e){
            NumberOfTries++;
            FailLoginTimer = new Timer(5000 * NumberOfTries);
            throw e;
        }
    }
    public static User.securityQ getSQ(String username){
        return User.securityQ.valueOf(SQLhandler.getSQ(username));
    }
    public static boolean verifySQA(String username , String SQA){
        return SQLhandler.verifySQA(username,SQA);
    }
    public void changePassword() {

    }

    public static boolean CorrectUserName(String s)
    {
        if(s.matches("[a-zA-Z0-9_\\S]+"))
        {
            return true;
        }
        return false;
    }
    public static boolean correctPassword(String s)
    {
        String SpecialChars1="0123456789";
        boolean correct1=false;
        for(char character :SpecialChars1.toCharArray())
        {
            if(s.contains(String.valueOf(character)))
            {
                correct1=true;
                break;
            }
        }
        String SpecialChars2 = "";
        for(int i=97;i<123;i++)
        {
            SpecialChars2+=(char)i;
        }
        boolean correct2=false;
        for(char character :SpecialChars2.toCharArray())
        {
            if(s.contains(String.valueOf(character)))
            {
                correct2=true;
                break;
            }
        }
        String SpecialChars3 = "";
        for(int i=65;i<91;i++)
        {
            SpecialChars3+=(char)i;
        }
        boolean correct3=false;
        for(char character :SpecialChars3.toCharArray())
        {
            if(s.contains(String.valueOf(character)))
            {
                correct3=true;
                break;
            }
        }

        if(correct1 && correct2 && correct3 && (s.length()>=8))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean correctEmail(String email)
    {
        if(email.contains("@") && email.endsWith(".com"))
            return true;
        return false;
    }
}
