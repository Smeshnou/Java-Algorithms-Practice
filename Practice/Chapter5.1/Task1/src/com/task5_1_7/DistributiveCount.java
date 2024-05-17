package com.task5_1_7;

import java.util.ArrayList;

public class DistributiveCount {

    public static <V> void queueDistributiveCount(ArrayList<MyObject<V>> arr, int maxKey){
        ArrayList<Queue<MyObject<V>>> queues = new ArrayList<>();

        for(int i = 0; i < maxKey + 1; i++){
            queues.add(new Queue<>());
        }
        
        for(int i = 0; i < arr.size(); i++){
            queues.get(arr.get(i).key()).enqueue(arr.get(i));
        }
        
        int index = 0;
        for(int i = 0; i < maxKey + 1; i++){
            while(!queues.get(i).isEmpty()){
                arr.set(index, queues.get(i).dequeue());
                index++;
            }
        }
    }

    public static void main(String arg[]){
        ArrayList<MyObject<String>> arr = new ArrayList<>();
        arr.add(new MyObject<String>(0, "a"));
        arr.add(new MyObject<String>(1, "b"));
        arr.add(new MyObject<String>(2, "c"));
        arr.add(new MyObject<String>(3, "d"));
        arr.add(new MyObject<String>(4, "e"));
        arr.add(new MyObject<String>(0, "f"));
        queueDistributiveCount(arr, 4);
        for(int i = 0; i < 6; i++){
            System.out.println(arr.get(i).value);
        }
    }
}
