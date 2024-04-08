package com.task1_3_37;

public class Queue<T> {
    Node first = null;
    Node last = null;
    int size;
    private class Node{
        T item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return size;
    }
    
    public void enqueue(T item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }
        else{
            oldLast.next = last;
        }
        size++;
    }

    public T dequeue(){
        T item = first.item;
        first = first.next; 
        if(isEmpty()){
            last = null;
        }
        size--;
        return item;
    } 
}