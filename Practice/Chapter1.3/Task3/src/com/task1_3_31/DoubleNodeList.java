package com.task1_3_31;

public class DoubleNodeList<T> {
    private DoubleNode first = null;
    private DoubleNode last = null;

    private int size = 0;
    
    private class DoubleNode{
        private T item;
        private DoubleNode next = null;
        private DoubleNode previous = null;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void insertBeginning(T item){
        DoubleNode oldFirst = first;
        first = new DoubleNode();
        first.item = item;
        first.next = oldFirst;
        oldFirst.previous = first;
        size++;
        if(size == 1){
            last = first;
        }
    }

    public void insertEnd(T item){
        DoubleNode oldLast = last;
        last = new DoubleNode();
        last.item = item;
        last.previous = oldLast;
        oldLast.next = last;
        size++;
        if(size == 1){
            first = last;
        }
    }

    public void deleteFirst(){
        first = first.next;
        size--;
    }

    public void deleteLast(){
        last = last.previous;
        size--;
    }

    public void insertBeforeNode(int index, T item){
        if(index < size && index > -1){
            DoubleNode x = first; 
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            if(index == 0){
                insertBeginning(item);
            }
            else{
            DoubleNode node = new DoubleNode();
            node.item = item;
            x.previous.next = node;
            node.previous = x.previous;
            node.next = x;
            x.previous = node;
            size++;
            }
        }
        else{
            System.out.println("Error, incorrect index");
        }
    }

    public void insertAfterNode(int index, T item){
        if(index < size && index > -1){
            DoubleNode x = first; 
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            if(index == size - 1){
                insertEnd(item);
            }
            else{
            DoubleNode node = new DoubleNode();
            node.item = item;
            x.next.previous = node;
            node.next = x.next;
            node.previous = x;
            x.next = node;
            size++;
            }
        }
        else{
            System.out.println("Exeption");
        }
    }

    public void deleteNode(int index){
        if(index < size && index > -1){
            DoubleNode x = first; 
            for(int i = 0; i < index; i++){
                x = x.next;
            }
            if(index == 0){
                deleteFirst();
            }
            if(index == size - 1){
                deleteLast();
            }
            else{
            x.next = x.previous;
            x.previous = x.next;
            size--;
            }
        }
        else{
            System.out.println("Exeption");
        }
    }

    public void show(){
        for(DoubleNode x = first; x != null; x = x.next){
            System.out.println(x.item);
        }
    }
}
