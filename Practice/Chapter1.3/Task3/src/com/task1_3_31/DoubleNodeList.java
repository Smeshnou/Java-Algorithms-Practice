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

    public T first(){
        T item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public T last(){
        T item = last.item;
        last = last.previous;
        size--;
        return item;
    }

    public void insertBeforeNode(int index, T item){
        if(index < size && index > -1){
            DoubleNode indexNode = first; 
            for(int i = 0; i < index; i++){
                indexNode = indexNode.next;
            }
            if(index == 0){
                insertBeginning(item);
            }
            else{
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            indexNode.previous.next = newNode;
            newNode.previous = indexNode.previous;
            newNode.next = indexNode;
            indexNode.previous = newNode;
            size++;
            }
        }
        else{
            System.out.println("Error, incorrect index");
        }
    }

    public void insertAfterNode(int index, T item){
        if(index < size && index > -1){
            DoubleNode indexNode = first; 
            for(int i = 0; i < index; i++){
                indexNode = indexNode.next;
            }
            if(index == size - 1){
                insertEnd(item);
            }
            else{
            DoubleNode newNode = new DoubleNode();
            newNode.item = item;
            indexNode.next.previous = newNode;
            newNode.next = indexNode.next;
            newNode.previous = indexNode;
            indexNode.next = newNode;
            size++;
            }
        }
        else{
            System.out.println("Exeption");
        }
    }

    public T node(int index){
        if(index == 0){
            return first();
        }
        if(index == size - 1){
            return last();
        }
        else{
            T item = last.item;
            DoubleNode indexNode = first; 
            for(int i = 0; i < index; i++){
                indexNode = indexNode.next;
            }
            indexNode.next = indexNode.previous;
            indexNode.previous = indexNode.next;
            size--;
            return item;
        }
    }

    public void show(){
        for(DoubleNode curentNode = first; curentNode != null; curentNode = curentNode.next){
            System.out.println(curentNode.item);
        }
    }
}
