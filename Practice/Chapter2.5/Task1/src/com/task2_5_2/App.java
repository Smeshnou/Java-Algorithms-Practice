package com.task2_5_2;

import java.util.Scanner;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());

    private static void findCompositeWords(List<String> list, int index, List<String> compositeWords){
        for(int j = index; j < list.size(); ++j){
            int includeIndex = list.get(j).indexOf(list.get(index));
            if(includeIndex != -1){
                String word;
                if(includeIndex == 0){
                    word = list.get(j).substring(list.get(index).length(), list.get(j).length());
                }
                else{
                    word = list.get(j).substring(0, includeIndex-1);
                }
                if(list.contains(word) && !compositeWords.contains(list.get(j))){
                    compositeWords.add(list.get(j));
                }
            }
        }
    }

    private static List<String> compositeWords(List<String> list){
        List<String> compositeWords = new ArrayList<>();
        for(int i = 0; i < list.size(); ++i){
            findCompositeWords(list, i, compositeWords);
        }
        return compositeWords;
    }
    
    public static void main(String[] args){
        List<String> words = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        String strOfWords = scan.nextLine();
        scan.close();
        words.addAll(Arrays.asList(strOfWords.split(" ")));
        MergeStringsSort.sort(words);
        List<String> compositeWords = new ArrayList<>(compositeWords(words));
        
        for(int i = 0; i < compositeWords.size(); ++i){
            String str = compositeWords.get(i);
            logger.info(str);
        }
    }
}
