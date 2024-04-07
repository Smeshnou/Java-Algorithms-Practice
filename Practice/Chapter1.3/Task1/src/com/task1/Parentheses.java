package com.task1;

public class Parentheses {
    private MyStack<Character> openBrackets = new MyStack<>();
    public boolean correctSetting(String str){
        for (int i = 0; i < str.length(); i++){
            if((str.charAt(i) == ')' && openBrackets.pop() != '(') || (str.charAt(i) == ']' && openBrackets.pop() != '[') || (str.charAt(i) == '}' && openBrackets.pop() != '{')){
                return false;
            }
            if(str.charAt(i) == '(' || str.charAt(i) == '[' || str.charAt(i) == '{'){
                openBrackets.push(str.charAt(i));
            }
        }   
        return true;
    }
}