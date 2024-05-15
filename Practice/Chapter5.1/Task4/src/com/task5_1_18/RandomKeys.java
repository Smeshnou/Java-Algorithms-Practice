package com.task5_1_18;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomKeys {
    private static Random rand = new Random();

    public static List<String> randomDecimalKeys(int N, int W){
        ArrayList<String> list = new ArrayList<>(N);
        for(int i = 0; i < N; i++){
            int number = rand.nextInt(9) + 1;
            for(int j = 1; j < W; j++){
                number = number * 10 + rand.nextInt(10);
            }
            list.set(i, String.valueOf(number));
        }
        return list;
    }
}
