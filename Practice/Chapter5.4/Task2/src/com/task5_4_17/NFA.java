package com.task5_4_17;

import java.util.ArrayList;

public class NFA {

    private Digraph graph;
    private String regexp;
    private int length;

    public NFA(String regexp) {
        this.regexp = regexp;
        length = regexp.length();
        Stack<Integer> ops = new Stack<>();
        graph = new Digraph(length+1);
        for (int i = 0; i < length; i++) {
            int lp = i;
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|'){
                ops.push(i);
            }
            else if (regexp.charAt(i) == ')') {
                ArrayList<Integer> or = new ArrayList<>();
                or.add(ops.pop());
                for(int j = 0; regexp.charAt(or.get(j)) != '('; j++){
                    or.add(ops.pop());
                    graph.addEdge(or.get(j), i);
                }
                lp = or.getLast();
                for(int j = 0; j < or.size(); j++){
                    graph.addEdge(lp, or.get(j)+1);
                }
            }

            if (i < length-1 && regexp.charAt(i+1) == '*') {
                graph.addEdge(lp, i+1);
                graph.addEdge(i+1, lp);
            }
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')'){
                graph.addEdge(i, i+1);
            }
        }
    }
    
    public boolean recognizes(String txt) {
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        ArrayList<Integer> pc = new ArrayList<>();
        for (int v = 0; v < graph.vertices(); v++){
            if (dfs.marked(v)){ 
                pc.add(v);
            }
        }
        for (int i = 0; i < txt.length(); i++) {
            ArrayList<Integer> match = new ArrayList<>();
            for (int v : pc) {
                if (v < length){
                    if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.'){
                        match.add(v+1);
                    }
                }
            }
            dfs = new DirectedDFS(graph, match);
            pc = new ArrayList<>();
            for (int v = 0; v < graph.vertices(); v++){
                if (dfs.marked(v)){ 
                    pc.add(v);
                }
            }
        }

        for (int v : pc){
            if (v == length){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String regexp = "(.AB((C|D|E)F)*G.*)";
        String txt = "LABDFEFGUUU";
        NFA nfa = new NFA(regexp);
        System.out.println(nfa.recognizes(txt));
    }

}