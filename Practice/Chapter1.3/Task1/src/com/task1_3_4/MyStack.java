package com.task1_3_4;

public class MyStack<T> {
    @SuppressWarnings("unchecked")
    private T[] stack = (T[])new Object[1];
    private int size;

    private void resize(int max){
        @SuppressWarnings("unchecked")
        T[] temp = (T[])new Object[max];
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
        if(size == stack.length) resize(stack.length*2);
        stack[size++] = item;
    }

    public T pop(){
        T item = stack[--size];
        stack[size] = null;
        if(size > 0 && size < stack.length/4) {
            resize(stack.length/2);
        }
        return item;
    }

}
