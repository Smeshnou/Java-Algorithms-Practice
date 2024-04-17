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

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        return this.compareTo((Task)obj) == 0;
    }

    @Override
    public int hashCode() {
        int result = (start == null ? 0 : start.hashCode());
        result = 31 * result + (end == null ? 0 : end.hashCode());
        return result;
    }  

    public Time end(){
        return end;
    }

    public Time start(){
        return start;
    }

}
