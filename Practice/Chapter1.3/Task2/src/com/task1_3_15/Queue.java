package com.task1_3_15;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private List<T> array = new ArrayList<>();
    int size = 0;

    private void resize(int max){
        List<T> temp = new ArrayList<>(max);
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }

    public void enqueue(T item){
        if(size == array.size()) resize(array.size()*2);
        array.set(size++, item);
    }

    public T dequeue(){
        T item = array.get(0);
        List<T> temp = new ArrayList<>(--size);
        System.arraycopy(array, 1, temp, 0, size);
        if(size > 0 && size == array.size()/4) resize(array.size()/2);
        array = temp;
        return item;
    }

    private static Logger logger = Logger.getLogger(Queue.class.getName());

    public static void main(String[] args){
        Queue<String> queue = new Queue<>();
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        scan.close();
        //simulate standart input
        Random r = new Random();
        int randomValue = r.nextInt(100);
        for (int i = 0; i < k + randomValue; i++){
            queue.enqueue("string " + i);
        }
        int size = queue.size();
        for (int i = 0; i < size - k; i++){
            queue.dequeue();
        } 
        String str = "Count of strings: " + (k + randomValue) + ". k-th string from end: "+ queue.dequeue();
        logger.info(str);
    }
    
}
