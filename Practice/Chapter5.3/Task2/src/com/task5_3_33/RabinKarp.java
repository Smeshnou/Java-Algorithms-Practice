package com.task5_3_33;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RabinKarp {
    private String pat;
    private long patHash;
    private int patLength;
    private long Q;
    private int alphabetBase = 256;
    private long RM; // R^(M-1) % Q
    private Random random = new Random();

    public RabinKarp(String pat){
        this.pat = pat;
        this.patLength = pat.length();
        Q = longRandomPrime();
        RM = 1;
        for(int i = 1; i <= patLength - 1; i++) {
            RM = (alphabetBase * RM) % Q;
        }
        patHash = hash(pat, patLength);
    }

    public boolean check(String txt, int i){
        for(int j = 0; j < patLength; j++){
            if(txt.charAt(i + j) != pat.charAt(j)){
                return false;
            }
        }
        return true;
    }

    private long hash(String key, int patLength){
        long h = 0;
        for (int i = 0; i < patLength; i++) {
            h = (alphabetBase * h + key.charAt(i)) % Q;
        }
        return h;
    }

    public int search(String txt){
        int txtLength = txt.length();
        long txtHash = hash(txt, patLength);
        if(patHash == txtHash){
            return 0;
        }

        for(int i = patLength; i < txtLength; i++){
            txtHash = (txtHash + Q - RM * txt.charAt(i - patLength) % Q) % Q;
            txtHash = (txtHash * alphabetBase + txt.charAt(i)) % Q;
            if(patHash == txtHash && check(txt ,i - patLength + 1)){
                return i - patLength + 1;
            }
        }
        return txtLength;
    }

    public int longRandomPrime() {
        int count = random.nextInt(8192) + 8192;
        List<Integer> primes = new ArrayList<>();

        primes.add(2);

        for (var i = 3; primes.size() < count; i += 2) {
            if (isPrime(i, primes)) {
                primes.add(i);
            }
        }
        return primes.get(primes.size() - 1);
    } 

    private boolean isPrime(int n, List<Integer> primes) {
        for (var i = 0; i < primes.size(); i++) {
            var prime = primes.get(i);
            if (prime * prime > n) {
                return true;
            }
            if (n % prime == 0) {
                return false;
            }
        }
        return true;
    }
}
