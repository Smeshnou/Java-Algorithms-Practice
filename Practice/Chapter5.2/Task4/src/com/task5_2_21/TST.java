package com.task5_2_21;

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
        return root.size;
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
        else{
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
            x.val   = val;
        }
        return x;
    }

    public Queue<String> keys() {
        Queue<String> queue = new Queue<>();
        collect(root, new StringBuilder(), queue);
        return queue;
    }

    public Queue<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<>();
        Optional<Node<V>> node = Optional.ofNullable(get(root, prefix, 0));
        if (node.isEmpty()) {
            return queue;
        }
        Optional<V> optVal = Optional.ofNullable(node.get().val);
        if (optVal.isPresent()) {
            queue.enqueue(prefix);
        }
        collect(node.get().mid, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<V> x, StringBuilder prefix, Queue<String> queue) {
        Optional<Node<V>> node = Optional.ofNullable(x);
        if (node.isEmpty()) {
            return;
        }
        collect(x.left,  prefix, queue);
        Optional<V> optVal = Optional.ofNullable(x.val);
        if (optVal.isPresent()) {
            queue.enqueue(prefix.toString() + x.c);
        }
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public Queue<String> searchSubStr(String str){
        Queue<String> keys = keys();
        Queue<String> result = new Queue<>();
        while(keys.size() > 0){
            String verb = keys.dequeue();
            String cloneVerb = verb;
            TST<Integer> subTree = new TST<>();
            int length = verb.length();
            for(int i = 0; i < length; i++){
                subTree.put(cloneVerb, i);
                cloneVerb = cloneVerb.substring(1);
            }
            Queue<String> withPrefixes = subTree.keysWithPrefix(str);
            if(!withPrefixes.isEmpty()){
                result.enqueue(verb);
            }
        }
        return result;
    }

    public static void main(String[] args){
        TST<Integer> tree = new TST<>();
        tree.put("shop",0);
        tree.put("shopping", 1);
        tree.put("show", 2);
        tree.put("null", 3);
        Queue<String> queue = tree.searchSubStr("sho");
        while(queue.size() > 0){
            System.out.println(queue.dequeue());
        }
    }
}
