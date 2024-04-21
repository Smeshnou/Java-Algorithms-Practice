package com.task2_5_20;

import java.sql.Time;

public class Task implements Comparable<Task>{
    private Time start;
    private Time end;
    
    public Task(String start, String end){
        this.start = Time.valueOf(start);
        this.end = Time.valueOf(end);
    }

    @Override
    public int compareTo(Task that){
        return this.start.compareTo(that.start);
    }
    
    public Time getEnd(){
        return end;
    }

    public Time getStart(){
        return start;
    }
}