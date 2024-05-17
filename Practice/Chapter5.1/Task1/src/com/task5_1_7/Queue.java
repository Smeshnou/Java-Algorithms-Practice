package com.task5_1_7;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    int size = 0;
    Node first;
    Node end;

    private class Node{
        T val;
        Node next;
        public Node(T val){
            this.val = val;
            next = null;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }

    public void enqueue(T item){
        if(size == 0){
            first = new Node(item);
            end = first;
        }
        else{
            end.next = new Node(item);
            end = end.next;
        }
        size++;
    }

    public T dequeue(){
        if(size > 0){
            size--;
            T item = first.val;
            first = first.next;
            return item;
        }
        else{
            return null;
        }
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
