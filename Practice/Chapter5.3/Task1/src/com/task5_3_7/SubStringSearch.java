package com.task5_3_7;

import java.util.ArrayList;
import java.util.List;

public class SubStringSearch {

    public static int search(String pat, String txt){
        int patLength = pat.length();
        int txtLength = txt.length();

        for(int i = 0; i <= txtLength - patLength; i++){
            int j;
            for(j = 0; j < patLength; j++){
                if(txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if(j == patLength){
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> searchAll(String pat, String txt){
        int patLength = pat.length();
        int txtLength = txt.length();
        List<Integer> all = new ArrayList<>();

        for(int i = 0; i <= txtLength - patLength; i++){
            int j;
            for(j = 0; j < patLength; j++){
                if(txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if(j == patLength){
                all.add(i);
            }
        }
        return all;
    }

    public static int count(String pat, String txt){
        int patLength = pat.length();
        int txtLength = txt.length();
        int count = 0;

        for(int i = 0; i <= txtLength - patLength; i++){
            int j;
            for(j = 0; j < patLength; j++){
                if(txt.charAt(i + j) != pat.charAt(j)){
                    break;
                }
            }
            if(j == patLength){
                count++;
            }
        }
        return count;
    }
}
