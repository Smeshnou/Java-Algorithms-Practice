package com.task5_2_21;

public class Queue<T> {
    int size = 0;
    Node first;
    Node end;

    private class Node{
        T val;
        Node next;
        public Node(T val){
            this.val = val;
            next = null;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    public int size(){
        return size;
    }

    public void enqueue(T item){
        if(size == 0){
            first = new Node(item);
            end = first;
        }
        else{
            end.next = new Node(item);
            end = end.next;
        }
        size++;
    }

    public T dequeue(){
        if(size > 0){
            size--;
            T item = first.val;
            first = first.next;
            return item;
        }
        else{
            return null;
        }
    }
}
