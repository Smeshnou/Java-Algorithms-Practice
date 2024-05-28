package com.task5_2_19;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class TrieST<V> {
    private static int sizeOfAlphbet = 10;
    private Node root;

    private class Node {
        private V val;
        private int size;
        private List<Node> next = new ArrayList<>(10);
        Node(){
            for (int i = 0; i < 10; i++){
                next.add(null);
            }
        }
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
        return get(node.get().next.get(c - 48), key, d+1);
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
        Optional<Node> optRoot = Optional.ofNullable(root);
        if(optRoot.isPresent()){
            return root.size;
        }
        return 0;
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
            x.next.set(c - 48, delete(x.next.get(c - 48), key, d+1, delKey));
        }
        if(delKey.booleanValue()){
            x.size--;
        }

        Optional<V> optVal = Optional.ofNullable(x.val);
        if (optVal.isPresent()){
            return x;
        } 
        for (int c = 0; c < sizeOfAlphbet; c++){
            Optional<Node> optNode = Optional.ofNullable(x.next.get(c));
            if (optNode.isPresent()){
                return x;
            }
        }
        return null;
    } 

    public Queue<String> keys() {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, Queue<String> results) {
        Optional<Node> node = Optional.ofNullable(x);
        if (node.isEmpty()){
            return;
        }
        Optional<V> optVal = Optional.ofNullable(x.val);
        if (optVal.isPresent()) {
            results.enqueue(prefix.toString());
        }
        for (char c = 0; c < sizeOfAlphbet; c++) {
            prefix.append((char)(c+48));
            collect(x.next.get(c), prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }



    private static Random random = new Random();
    public static void main(String[] args){
        TrieST<Integer> tree = new TrieST<>();
        int N = 10;
        while(tree.size() != N){
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < 9; i++){
                str.append(random.nextInt(10));
            }
            tree.put(str.insert(0, "375").toString(), tree.size());
        }
        Queue<String> phoneNumbers = tree.keys();
        int countPhNum = phoneNumbers.size();
        for (int i = 0; i < countPhNum; i++) {
            System.out.println(phoneNumbers.dequeue());
        }
    }
}