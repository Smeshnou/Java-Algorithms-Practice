package com.task1_3_37;

import java.util.Scanner;
import java.util.logging.Logger;

public class Josephus {
    private static Logger logger = Logger.getLogger(Josephus.class.getName());
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
            String str = queue.dequeue() + " ";
            logger.info(str);
        }
    }
}