package com.task2_5_14;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Domain implements Comparable<Domain> {
    private List<String> levels;

    public Domain(String levels){
        this.levels = new ArrayList<>();
        this.levels.addAll(Arrays.asList(levels.split("\\.")));
    }
    
    public String toString(){
        if(levels.isEmpty()){
            return "";
        }
        else{
            StringBuilder bld = new StringBuilder();
            bld.insert(0, levels.get(0));
            for(int i = 1; i < levels.size(); ++i){
                bld.insert(0, levels.get(i) + ".");
            }
            return bld.toString();
        }
    }

    @Override
    public int compareTo(Domain that){
        for(int i = 0; i < Math.min(this.levels.size(), that.levels.size()); ++i){
            int check = this.levels.get(this.levels.size() - i - 1).compareTo(that.levels.get(that.levels.size() - i - 1));
            if(check != 0){
                return check;
            }
        }
        return this.levels.size() - that.levels.size();
    }

}