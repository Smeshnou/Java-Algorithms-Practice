package com.task5_4_16;

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
        T item = stack.get(--size);
        stack.set(size, null);
        return item;
    }

}