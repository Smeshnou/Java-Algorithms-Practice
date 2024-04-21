package com.task2_5_14;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

public class App {
    private static Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        //count of strings

        int n = scan.nextInt();
        scan.nextLine();
        ArrayList<Domain> domains = new ArrayList<>(n);
        for(int i = 0; i < n; ++i){
            String str = scan.nextLine();
            domains.add(new Domain(str));
        }
        scan.close();

        domains.sort(null);

        String str;
        for(int i = 0; i < domains.size(); ++i){
            str = domains.get(i).toString();
            logger.info(str);
        }
    }
}
