package com.task5_4_17;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {
    private List<T> stack = new ArrayList<>();
    private int size = 0;

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void push(T item){
        size++;
        stack.add(item);
    }

    public T pop(){
        return stack.get(--size);
    }

}