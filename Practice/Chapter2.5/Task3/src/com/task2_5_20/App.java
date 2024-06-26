package com.task2_5_20;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        //count of tasks
        int n = scan.nextInt();
        scan.nextLine();
        String input;
        for(int i = 0; i < n; ++i){
            input = scan.nextLine();
            list.add(new Task(input.split(" ")[0], input.split(" ")[1]));
        }
        scan.close();

        list.sort(null);

        long maxDownTime = list.get(1).getStart().getTime() - list.get(0).getEnd().getTime();
        Time startWork = list.get(0).getStart();
        Time endWork = list.get(0).getEnd();
        long maxWorkInterval = 0;
        long interval;
        for(int i = 1; i < list.size() - 1; ++i){
            interval = list.get(i + 1).getStart().getTime() - list.get(i).getEnd().getTime();
            if(interval > maxDownTime){
                maxDownTime = interval;
                if(endWork.getTime() - startWork.getTime() > maxWorkInterval){
                    maxWorkInterval = endWork.getTime() - startWork.getTime();
                }
            }
            if(interval > 0){
                startWork = list.get(i + 1).getStart();
                endWork = list.get(i + 1).getEnd();
            }
            else{
                endWork = list.get(i + 1).getEnd();
            }
        }
        if(endWork.getTime() - startWork.getTime() > maxWorkInterval){
            maxWorkInterval = endWork.getTime() - startWork.getTime();
        }
        String str = "Max work time interval: " + maxWorkInterval / 1000 + " seconds\n" + "Max downtime interval: " + maxDownTime / 1000 + " seconds";
        logger.info(str);
    }
}
