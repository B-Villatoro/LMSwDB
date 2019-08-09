package com.smoothstack.lms.service;

public class Env {

    public static String user(){
        return System.getenv("SUSER");
    }

    public static String p(){
        return System.getenv("SP");
    }

    public static String db(){
        return System.getenv("DB");
    }
    public static String port(){
        return System.getenv("DBP");
    }
}
