package com.task5_1_16;

public class StrQueue {
    int size = 0;
    Node first;
    Node end;

    private class Node{
        String val;
        Node next;
        public Node(String val){
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

    public void enqueue(String item){
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

    public String dequeue(){
        if(size > 0){
            size--;
            String item = first.val;
            first = first.next;
            return item;
        }
        else{
            return null;
        }
    }

    private String get(int pos){
        if(pos < size){
            Node node = first;
            for(int i = 0; i < pos; i++){
                node = node.next;
            }
            return node.val;
        }
        else{
            return null;
        }
    }

    private void set(int pos, String str){
        if(pos < size){
            Node node = first;
            for(int i = 0; i < pos; i++){
                node = node.next;
            }
            node.val = str;
        }
    }

    private int charAt(String str, int pos){
        if(pos < str.length()){
            return str.charAt(pos);
        }
        else{
            return -1;
        }
    }

    public void sort(){
        sort(0, size - 1, 0);
    }
    
    private void sort(int begin, int end, int pos){
        if(begin >= end){
            return;
        }

        int newBegin = begin;
        int newEnd = end;
        int i = begin + 1;
        int key = charAt(this.get(begin), pos);

        while(i <= newEnd){
            int c = charAt(this.get(i), pos);
            if(key > c){
                String temp = this.get(newBegin);
                this.set(newBegin, this.get(i));
                this.set(i, temp);
                newBegin++;
                i++;
            }
            else if(key < c){
                String temp = this.get(newEnd);
                this.set(newEnd, this.get(i));
                this.set(i, temp);
                newEnd--;
            }
            else{
                i++;
            }
        }
        
        sort(begin, newBegin - 1, pos);
        if(key >= 0){
            sort(newBegin, newEnd, pos + 1);
        }
        sort(newEnd + 1, end, pos);
    }
}
