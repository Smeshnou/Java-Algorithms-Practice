package com.task3_2_6;

import java.util.logging.Logger;

public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;

    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;
        private int height;
        public Node(K key, V val, int size, int height){
            this.key = key;
            this.val = val;
            this.size = size;
            this.height = height;
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if(node == null) {
            return 0;
        }
        else{
            return node.size;
        }
    }

    public int heightField(){
        return heightField(root);
    }

    private int heightField(Node node){
        if(node == null) {
            return 0;
        }
        else{
            return node.height;
        }
    }

    public V get(K key){
        Node node = root;
        if(node == null){
            return null;
        }
        while(key != node.key){
            if(key.compareTo(node.key) > 0){
                node = node.right;
            }
            else if(key.compareTo(node.key) < 0){
                node = node.left;
            }
            
            if(node == null){
                return null;
            }
        }
        return node.val;
    }

    public void put(K key, V val){
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val){
        if(node == null){ 
            return new Node(key, val, 1, 1);
        }
        int compare = key.compareTo(node.key);
        if(compare < 0){
            node.left = put(node.left, key, val);
        }
        else if(compare > 0){
            node.right = put(node.right, key, val);
        }
        else{
            node.val = val;
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightField(node.left), heightField(node.right)) + 1;
        return node;
    }

    public K min(){
        if(root == null){
            return null;
        }
        return min(root).key;
    }

    private Node min(Node node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    public K max(){
        if(root == null){
            return null;
        }
        return max(root).key;
    }

    private Node max(Node node){
        if(node == null){
            return null;
        }
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    public K floor(K key){
        Node node = floor(root, key);
        if(node == null){
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, K key){
        if(node == null){
            return null;
        }
        int compare = key.compareTo(node.key);
        if(compare == 0){
            return node;
        }
        else if(compare < 0){
            return floor(node.left, key);
        }
        Node temp = floor(node.right, key);
        if(temp != null){
            return temp;
        }
        else{
            return node;
        }
    }

    public K ceiling(K key){
        Node node = ceiling(root, key);
        if(node == null){
            return null;
        }
        return node.key;
    }

    private Node ceiling(Node node, K key){
        if(node == null){
            return null;
        }
        int compare = key.compareTo(node.key);
        if(compare == 0){
            return node;
        }
        else if(compare > 0){
            return ceiling(node.right, key);
        }
        Node temp = ceiling(node.left, key);
        if(temp != null){
            return temp;
        }
        else{
            return node;
        }
    }

    public K select(int rank){
        Node node = root;
        if(node == null){
            return null;
        }
        int leftSize = size(node.left);
        while(leftSize != rank){
            if(leftSize < rank){
                node = node.right;
                rank = rank - leftSize - 1;
            }
            else{
                node = node.left;
            }
            leftSize = size(node.left);
        }
        return node.key;
    }

    public int rank(K key){
        Node node = root;
        if(node == null){
            return 0;
        }
        int compare = key.compareTo(node.key);
        int rank = 0;
        while(compare != 0){
            if(compare < 0){
                node = node.left;
            }
            else if(compare > 0){
                rank += size(node.left) + 1;
                node = node.right;
            }
            if(node == null){
                return 0;
            }
        }
        return rank + size(node.left);
    }

    public int height(){
        return height(root);
    }

    private int height(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node node){
        if(node.left == null){
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightField(node.left), heightField(node.right)) + 1;
        return node;
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    private Node deleteMax(Node node){
        if(node.right == null){
            return node.left;
        }
        node.right = deleteMin(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightField(node.left), heightField(node.right)) + 1;
        return node;
    }

    public void delete(K key){
        root = delete(root, key);
    }

    private Node delete(Node node, K key){
        if(node.right == null){
            return null;
        }
        int compare = key.compareTo(node.key);
        if(compare < 0){
            node.left = delete(node.left, key);
        }
        else if(compare > 0){
            node.right = delete(node.right, key);
        }
        else{
            if(node.right == null){
                return node.left;   
            }
            else if(node.left == null){
                return node.right;
            }
            Node temp = node;
            node = min(temp.right);
            if(node == null){
                return null;
            }
            node.right = deleteMin(node.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        node.height = Math.max(heightField(node.left), heightField(node.right)) + 1;
        return node;
    }

    private static Logger logger = Logger.getLogger(BinarySearchTree.class.getName());
    public static void main(String[] args){
        BinarySearchTree<String, Integer> tree = new BinarySearchTree<>();
        tree.put("C", 3);
        tree.put("D", 4);
        tree.put("B", 2);
        tree.put("A", 1);
        Integer h = tree.heightField();
        logger.info(h.toString());
        h = tree.height();
        logger.info(h.toString());
    }   
}
