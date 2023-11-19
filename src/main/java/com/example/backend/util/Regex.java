package com.example.backend.util;

public class Regex {
    public static final String EMAIL_REGEX = "^[\\w-_\\.]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";
}
