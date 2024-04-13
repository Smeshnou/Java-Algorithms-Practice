package com.task1_3_4;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Logger;

public class Parentheses {
    private MyStack<Character> openBrackets = new MyStack<>();

    private boolean brackets(char bracket1, char bracket2){
        return (bracket1 == '(' && bracket2 == ')') || (bracket1 == '[' && bracket2 == ']') || (bracket1 == '{' && bracket2 == '}');
    }

    public boolean correctSetting(String str){
        HashMap<Character, Boolean> map = new HashMap<>();
        map.put('(', true);
        map.put('[', true);
        map.put('{', true);
        map.put(')', false);
        map.put(']', false);
        map.put('}', false);
        for (int i = 0; i < str.length(); i++){
            if(Boolean.TRUE.equals(!map.get(str.charAt(i))) && (openBrackets.isEmpty() || brackets(openBrackets.pop(), str.charAt(i)))){
                return false;
            }
            else if(Boolean.TRUE.equals(map.get(str.charAt(i)))){
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