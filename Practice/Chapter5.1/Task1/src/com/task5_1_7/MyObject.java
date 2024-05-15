package com.task5_1_7;


public class MyObject<V> {
    int key;
    V value;

    public MyObject(int key, V value){
        this.key = key;
        this.value = value;
    }

    public int key(){
        return key;
    }

    public V val(){
        return value;
    }

}