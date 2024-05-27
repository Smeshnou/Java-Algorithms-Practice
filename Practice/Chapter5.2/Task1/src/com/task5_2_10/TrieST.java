package com.task5_2_10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrieST<V> {
    private static int sizeOfAlphbet = 256;
    private Node root;

    private class Node {
        private V val;
        private int size;
        private List<Node> next = new ArrayList<>(sizeOfAlphbet);
    }

    public V get(String key) {
        Optional<Node> node = Optional.ofNullable(get(root, key, 0));
        if (node.isEmpty()){
            return null;
        } 
        return node.get().val;
    }
    
    public boolean contains(String key) {
        return get(key) != null;
    }

    private Node get(Node x, String key, int d) {
        Optional<Node> node = Optional.ofNullable(x);
        if (node.isEmpty()) {
            return null;
        }
        if (d == key.length()){
           return x;
        }
        char c = key.charAt(d);
        return get(node.get().next.get(c), key, d+1);
    }
    
    public void put(String key, V val) {
        Optional<V> optVal = Optional.ofNullable(val);
        if (optVal.isEmpty()) {
            delete(key);
        }
        else {
            boolean[] newKey = {false};
            root = put(root, key, val, 0, newKey);
        }
    }

    private Node put(Node x, String key, V val, int d, boolean[] newKey) {
        Optional<Node> node = Optional.ofNullable(x);
        if (node.isEmpty()){
            x = new Node();
        } 
        if (d == key.length()) {
            Optional<V> optVal = Optional.ofNullable(x.val);
            if (optVal.isEmpty()) {
                newKey[0] = true;
                x.size++;
            }
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next.set(c - 48, put(x.next.get(c - 48), key, val, d+1, newKey));
        if(newKey[0]){
            x.size++;
        }
        return x;
    }
    
    public int size() {
        return root.size;
    }
    
    public boolean isEmpty() {
        return size() == 0;
    }

    public void delete(String key) {
        Boolean delKey = false;
        root = delete(root, key, 0, delKey);
    }

    private Node delete(Node x, String key, int d, Boolean delKey) {
        Optional<Node> node = Optional.ofNullable(x);
        if (node.isEmpty()){
            x = new Node();
        }  
        if (d == key.length()) {
            Optional<V> optVal = Optional.ofNullable(x.val);
            if (optVal.isPresent()) {
                delKey = true;
            } 
            x.val = null;
        }
        else {
            char c = key.charAt(d);
            x.next.set(c, delete(x.next.get(c), key, d+1, delKey));
        }
        if(delKey.booleanValue()){
            x.size--;
        }

        Optional<V> optVal = Optional.ofNullable(x.val);
        if (optVal.isPresent()){
            return x;
        } 
        for (int c = 0; c < sizeOfAlphbet; c++){
            if (x.next.get(c) != null){
                return x;
            }
        }
        return null;
    } 
}
