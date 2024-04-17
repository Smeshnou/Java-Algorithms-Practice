package com.task2_5_2;

import java.util.List;
import java.util.ArrayList;

public class MergeStringsSort {
    private static List<String> temp;

    MergeStringsSort(){}

    private static boolean less(String str1, String str2){
        if(str1.length() != str2.length()){
            return str1.length() < str2.length();
        }
        else{
            for(int i = 0; i < Math.min(str1.length(), str2.length()); ++i){
                if(str1.charAt(i) != str2.charAt(i)){
                    return str1.charAt(i) < str2.charAt(i);
                }
            }
            return false;
        }
    }

    private static void marge(List<String> list, int begin, int mid, int end){
        int i = begin;
        int j = mid + 1;
        for(int k = begin; k <= end; ++k){
            temp.add(k, list.get(k));
        }
        for(int k = begin; k <= end; ++k){
            if(i > mid) {
                list.set(k, temp.get(j++));
            }
            else if(j > end){
                list.set(k, temp.get(i++));
            }
            else if(less(temp.get(j), temp.get(i))){
                list.set(k, temp.get(j++));
            }
            else{
                list.set(k, temp.get(i++));
            }
        }
    }

    public static void sort(List<String> list){
        int size = list.size();
        temp = new ArrayList<>(size);
        sort(list, 0, list.size()-1); 
    } 

    private static void sort(List<String> list, int begin, int end){
        if(end <= begin) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        sort(list, begin, mid);
        sort(list, mid + 1, end);
        marge(list, begin, mid, end);
    }
}