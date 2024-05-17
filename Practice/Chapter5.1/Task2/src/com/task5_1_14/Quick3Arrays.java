package com.task5_1_14;

import java.util.ArrayList;
import java.util.List;

public class Quick3Arrays {
    public static void sort(List<ArrayList<Integer>> list){
        sort(list, 0, list.size() - 1, 0);
    }

    private static void sort(List<ArrayList<Integer>> list, int begin, int end, int checkPos){
        if(begin >= end){
            return;
        }
        boolean isSorted = true;
        for(int i = begin; i < end; i++){
            if(list.get(i) != list.get(i+1)){
                isSorted = false;
            }
        }
        if(isSorted){
            return;
        }
        int newBegin = begin;
        int newEnd = end;
        int i = begin + 1;
        int key = elemAt(list.get(begin), checkPos);
        while(i <= newEnd){
            int number = elemAt(list.get(i), checkPos);
            if(key > number){
                ArrayList<Integer> temp = list.get(newBegin);
                list.set(newBegin, list.get(i));
                list.set(i, temp);
                newBegin++;
                i++;
            }
            else if(key < number){
                ArrayList<Integer> temp = list.get(newEnd);
                list.set(newEnd, list.get(i));
                list.set(i, temp);
                newEnd--;
            }
            else{
                i++;
            }
        }
        sort(list, begin, newBegin - 1, checkPos);
        sort(list, newBegin, newEnd, checkPos + 1);
        sort(list, newEnd + 1, end, checkPos);
    }

    private static int elemAt(ArrayList<Integer> arr, int pos){
        if(pos < arr.size()){
            return arr.get(pos);
        }
        else{
            return -1;
        }
    }
}
