package com.smoothstack.lms.myutil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdValidate {

    public static boolean isValid(String checkId) {
        if(checkId.equals("")){
            return false;
        }
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(checkId);
        return !m.find();
    }

    public static int parser(String checkId) {
        return Integer.parseInt(checkId);
    }


}

