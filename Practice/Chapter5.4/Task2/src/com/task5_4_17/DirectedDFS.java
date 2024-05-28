package com.task5_4_17;

import java.util.ArrayList;

public class DirectedDFS {

    private ArrayList<Boolean> marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new ArrayList<>(G.vertices());
        for(int i = 0; i < G.vertices(); i++){
            marked.add(false);
        }
        dfs(G, s);
    }

    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new ArrayList<>(G.vertices());
        for(int i = 0; i < G.vertices(); i++){
            marked.add(false);
        }
        for (int s : sources) {
            if (!marked.get(s)) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked.set(v, true);
        for (int w : G.adj(v)) {
            if (!marked.get(w)) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked.get(v);
    }
}