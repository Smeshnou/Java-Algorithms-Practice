package com.task1;

public class MyStack<T> {
    private T[] stack;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyStack(){
        stack = (T[]) new Object[1];
    }
    @SuppressWarnings("unchecked")
    private void resize(int max){
        T[] temp = (T[]) new Object[max];
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
        if(size == stack.length){
            resize(2*stack.length);
        }
        stack[size++] = item;
    }

    public T pop(){
        T item = stack[--size];
        stack[size] = null;
        if(size > 0 && size <= stack.length/4){
            resize(stack.length/2);
        }
        return item;
    }
}