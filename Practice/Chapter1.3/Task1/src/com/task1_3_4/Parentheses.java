package com.task1_3_4;

import java.util.Scanner;
import java.util.logging.Logger;

public class Parentheses {
    private MyStack<Character> openBrackets = new MyStack<>();
    public boolean correctSetting(String str){
        for (int i = 0; i < str.length(); i++){
            if((str.charAt(i) == ')' && (openBrackets.isEmpty() || openBrackets.pop() != '(')) || (str.charAt(i) == ']' && (openBrackets.isEmpty() || openBrackets.pop() != '[')) || (str.charAt(i) == '}' && (openBrackets.isEmpty() || openBrackets.pop() != '{'))){
                return false;
            }
            if(str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{'){
                openBrackets.push(str.charAt(i));
            }
        }   
        return true;
    }

    private static Logger logger = Logger.getLogger(Parentheses.class.getName());
    public static void main(String[] args) {
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