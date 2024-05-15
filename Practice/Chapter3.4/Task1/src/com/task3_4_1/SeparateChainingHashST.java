package com.task3_4_1;

import java.util.ArrayList;

public class SeparateChainingHashST<K, V> {
    private int size = 0;
    private ArrayList<SequentialSearchST<K, V>> list;

    public SeparateChainingHashST(){
        this(997);
    }

    public SeparateChainingHashST(int size){
        this.size = size;
        list = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            list.add(new SequentialSearchST<>());
        }
    }

    private int hash(K key){
        return (11 * (key.hashCode() - 64)) % size;
    }

    public V get(K key){
        return list.get(hash(key)).get(key);
    }

    public void put(K key, V val){
        list.get(hash(key)).put(key, val);
    }

    public static void main(String[] args){
        SeparateChainingHashST<Character, Integer> hashST = new SeparateChainingHashST<>(5);
        hashST.put('E', 1);
        hashST.put('A', 2);
        hashST.put('S', 3);
        hashST.put('Y', 4);
        hashST.put('Q', 5);
        hashST.put('U', 6);
        hashST.put('T', 7);
        hashST.put('I', 8);
        hashST.put('O', 9);
        hashST.put('N', 10);
    }
}
