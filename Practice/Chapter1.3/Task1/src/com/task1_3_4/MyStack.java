package com.task1_3_4;

import java.util.ArrayList;
import java.util.List;

public class MyStack<T> {
    private List<T> stack = new ArrayList<>();
    private int size;

    private void resize(int max){
        List<T> temp = new ArrayList<>(max);
        System.arraycopy(stack, 0, temp, 0, size);
        stack = temp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void push(T item){
        if(size == stack.size()) resize(stack.size()*2);
        stack.set(size++, item);
    }

    public T pop(){
        T item = stack.get(--size);
        stack.set(size, null);
        if(size > 0 && size < stack.size()/4) {
            resize(stack.size()/2);
        }
        return item;
    }

}
