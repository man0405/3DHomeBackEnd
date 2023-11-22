package com.example.backend.util;

import java.util.regex.Pattern;

public class Regex {
    private static final String EMAIL_REGEX = "^[\\w-_\\.]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";


    public static Boolean CheckMail(String mail){
       return(Pattern.matches(EMAIL_REGEX,mail));
    }

    public static Boolean CheckPassword(String password){
        return(Pattern.matches(PASSWORD_REGEX,password));

    }
}
