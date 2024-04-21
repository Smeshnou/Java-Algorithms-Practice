package com.task2_5_21;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Vector implements Comparable<Vector>{
    List<Integer> vect;

    public Vector(List<Integer> vect){
        vect.sort(null);
        this.vect = vect;
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < vect.size(); ++i){
            str.append(vect.get(i) + " ");
        }
        return str.toString();
    }

    @Override
    public int compareTo(Vector that) {
        if(this.vect.size() != that.vect.size()){
            return this.vect.size() - that.vect.size();
        }

        for(int i = 0; i < this.vect.size(); ++i){
            if(!Objects.equals(this.vect.get(i), that.vect.get(i))){
                return this.vect.get(i) - that.vect.get(i);
            }
        }
        return 0;
    }

    private static Logger logger = Logger.getLogger(Vector.class.getName());

    public static void main(String[] args) {
        List<Integer> vect1 = new ArrayList<>(List.of(5,7,4,3,6,1,2,3));
        List<Integer> vect2 = new ArrayList<>(List.of(2,3,3,7,1,5,4,8));
        List<Integer> vect3 = new ArrayList<>(List.of(1,2,4,7,5,3,2,1));

        List<Vector> vectors = new ArrayList<>(List.of(new Vector(vect1), new Vector(vect2), new Vector(vect3)));

        vectors.sort(null);

        logger.info("Sorted vectors");
        for(int i = 0; i < vectors.size(); ++i){
            String str = "Vector " + i + ": " + vectors.get(i).toString();
            logger.info(str);
        }
    }
}