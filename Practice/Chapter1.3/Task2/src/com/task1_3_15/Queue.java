package com.task1_3_15;

import java.util.Random;
import java.util.Scanner;

public class Queue<T> {
    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[1];
    int size = 0;

    @SuppressWarnings("unchecked")
    private void resize(int max){
        T[] temp = (T[]) new Object[max];
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
        if(size == array.length) resize(array.length*2);
        array[size++] = item;
    }

    @SuppressWarnings("unchecked")
    public T dequeue(){
        T item = array[0];
        T[] temp = (T[]) new Object[--size];
        System.arraycopy(array, 1, temp, 0, size);
        if(size > 0 && size == array.length/4) resize(array.length/2);
        array = temp;
        return item;
    }

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
        System.out.println("Count of strings: " + (k + randomValue) + ". k-th string from end: "+ queue.dequeue());
    }
    
}
