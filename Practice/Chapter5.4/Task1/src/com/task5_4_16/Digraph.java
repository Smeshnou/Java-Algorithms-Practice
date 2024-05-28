package com.task5_4_16;

import java.util.ArrayList;

public class Digraph {

    private int vertices;           
    private int edges;                 
    private ArrayList<ArrayList<Integer>> adj;    
    private ArrayList<Integer> indegree;        

    public Digraph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        indegree = new ArrayList<>(vertices);
        for (int v = 0; v < vertices; v++) {
            indegree.add(0);
        }
        adj = new ArrayList<>(vertices);
        for (int v = 0; v < vertices; v++) {
            adj.add(new ArrayList<>(vertices));
        }
    }

    public int vertices() {
        return vertices;
    }

    public int edges() {
        return edges;
    }

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        indegree.set(w, indegree.get(w)+1);
        edges++;
    }

    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }

    public int outdegree(int v) {
        return adj.get(v).size();
    }

    public int indegree(int v) {
        return indegree.get(v);
    }
}
