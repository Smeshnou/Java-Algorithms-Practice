package com.task5_2_14;

import java.util.Optional;

public class TST<V> {
    private Node<V> root;

    private static class Node<V> {
        private char c;      
        private int size = 0;
        private Node<V> left;
        private Node<V> mid;
        private Node<V> right;
        private V val;
    }
    
    public int size() {
        Optional<Node<V>> optRoot = Optional.ofNullable(root);
        if(optRoot.isPresent()){
            return root.size;
        }
        return 0;
    }
    
    public boolean contains(String key) {
        return get(key) != null;
    }
    
    public V get(String key) {
        Optional<Node<V>> node = Optional.ofNullable(get(root, key, 0));
        if (node.isEmpty()) {
            return null;
        }
        return node.get().val;
    }
    
    private Node<V> get(Node<V> x, String key, int d) {
        Optional<Node<V>> node = Optional.ofNullable(x);
        if (node.isEmpty()) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c){
            return get(x.left,  key, d);
        }
        else if (c > x.c) {
            return get(x.right, key, d);
        }
        else if (d < key.length() - 1) {
            return get(x.mid,   key, d+1);
        }
        else {
            return x;
        }
    }
    
    public void put(String key, V val) {
        Optional<V> optVal = Optional.ofNullable(val);
        if(!contains(key) && optVal.isEmpty()){
            return;
        }
        root = put(root, key, val, 0);
    }

    private Node<V> put(Node<V> x, String key, V val, int d) {
        char c = key.charAt(d);
        Optional<Node<V>> node = Optional.ofNullable(x);
        if (node.isEmpty()) {
            x = new Node<>();
            x.c = c;
        }
        Optional<V> optVal = Optional.ofNullable(val);
        if(optVal.isPresent() && !contains(key)){
            x.size++;
        }
        else if(optVal.isEmpty()){
            x.size--;
        }
        if (c < x.c) {
            x.left  = put(x.left,  key, val, d);
        }
        else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        }
        else if (d < key.length() - 1) {
            x.mid = put(x.mid,   key, val, d+1);
        }
        else {
            x.val = val;
        }
        return x;
    }

    public Queue<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    private void collect(Node<V> x, StringBuilder prefix, Queue<String> queue) {
        Optional<Node<V>> node = Optional.ofNullable(x);
        if (node.isEmpty()){
            return;
        } 
        collect(x.left,  prefix, queue);
        Optional<V> optVal = Optional.ofNullable(x.val);
        if (optVal.isPresent()){
            queue.enqueue(prefix.toString() + x.c);
        } 
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public static void main(String[] args){
        TST<Integer> tree = new TST<>();
        
        String inputStr = "cgcgggcgcg";
        int L = 3;

        if(L <= inputStr.length()){
            for (int i = 0; i + L < inputStr.length(); i++) {
                tree.put(inputStr.substring(i, i + L), tree.size());
            }
            Queue<String> subStr = tree.keys();
            int countSubStr = subStr.size();
            for (int i = 0; i < countSubStr; i++) {
                System.out.println(subStr.dequeue());
            }
        }
    }
}