package com.task3_3_39;

public class RedBlackBST<K extends Comparable<K>, V> {
    private Node root;
    Node lastAccessedNode = null;    
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        private K key;
        private V val;
        private Node left;
        private Node right;
        private int size;
        private boolean color;
        public Node(K key, V val, int size, boolean color){
            this.key = key;
            this.val = val;
            this.size = size;
            this.color = color;
        }
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node node) {
        if (node.left == null){
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
             node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }     
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public boolean isEmpty(){
        return isEmpty(root);
    }

    public boolean isEmpty(Node node){
        return node == null;
    }

    private boolean isRed(Node node){
        if(node == null){
            return false;
        }
        else{
            return node.color == RED;
        }
    }

    private Node rotateLeft(Node oldRoot){
        Node newRoot = oldRoot.right;
        oldRoot.right = newRoot.left;
        newRoot.left = oldRoot;
        newRoot.color = oldRoot.color;
        oldRoot.color = RED;
        newRoot.size = oldRoot.size;
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right);
        return newRoot;
    } 

    private Node rotateRight(Node oldRoot){
        Node newRoot = oldRoot.left;
        oldRoot.left = newRoot.right;
        newRoot.right = oldRoot;
        newRoot.color = oldRoot.color;
        oldRoot.color = RED;
        newRoot.size = oldRoot.size;
        oldRoot.size = 1 + size(oldRoot.left) + size(oldRoot.right);
        return newRoot;
    }

    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
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

    public V get(K key){
        if(lastAccessedNode != null && lastAccessedNode.key == key){
            return lastAccessedNode.val;
        }
        else{
            Node node = get(root, key);
            if(node != null){
                return node.val;
            }
            else{
                return null;
            }
        }
    }

    private Node get(Node node, K key){
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
        lastAccessedNode = node;
        return node;
    }

    public void put(K key, V val){
        if(lastAccessedNode != null && lastAccessedNode.key == key){
            lastAccessedNode.val = val;
        }
        else{
            root = put(root, key, val);
            root.color = BLACK;
        }
    }

    private Node put(Node node, K key, V val){
        if(node == null){ 
            lastAccessedNode = new Node(key, val, 1, RED); 
            return lastAccessedNode;
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

        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        node.size = size(node.left) + size(node.right) + 1;
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
        lastAccessedNode = node;
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
        lastAccessedNode = node;
        return node;
    }
}