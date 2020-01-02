package com.selvaganesh.karadipathinterview.utils;

public class TextUtils {
    private String sample1 = "Text from TextUtils";

    public String getSample1(){
        return sample1;
    }

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static String getString(Object input) {
        return input == null ? "" : input.toString().trim().equals("") ? "" :
                input.toString().trim().equalsIgnoreCase("null") ? "" : input.toString().trim();
    }

    public static boolean isValidEmail(String valEmail){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(valEmail).matches();
    }

}
