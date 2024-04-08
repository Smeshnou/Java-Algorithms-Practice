package com.task1_3_37;

import java.util.Scanner;

public class Josephus {
    public static void main(String[] args){
        Queue<Integer> queue = new Queue<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        scan.close();
        for(int i = 0; i < n; i++){
            queue.enqueue(i);
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m - 1; j++){
                queue.enqueue(queue.dequeue());
            }
            System.out.print(queue.dequeue() + " ");
        }
    }
}