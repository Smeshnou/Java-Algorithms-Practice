package com.task1;

import java.util.logging.Logger;
import java.util.Scanner;

public class App {
        static Logger logger = Logger.getLogger(App.class.getName());
    public static void main(String[] args){
        String str;
        Scanner scan = new Scanner(System.in);
        str = scan.nextLine();
        scan.close();
        Parentheses par = new Parentheses();
        if(par.correctSetting(str)){
            logger.info("true");
        }
        else{
            logger.info("false");
        }
    }
}