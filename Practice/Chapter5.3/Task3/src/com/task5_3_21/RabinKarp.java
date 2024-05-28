package com.task5_3_21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RabinKarp {
    private String pat;
    private long patHash1;
    private long patHash2;
    private int patLength;
    private long Q;
    private int alphabetBase = 256;
    private long RM1; // R^(M-1) % Q for the first half
    private long RM2; // R^(M-1) % Q for the second half
    private int mid;
    private Random random = new Random();

    public RabinKarp(String pat){
        this.patLength = pat.length();
        this.pat = pat;
        mid = patLength / 2;
        Q = longRandomPrime();
        RM1 = 1;
        for (int i = 1; i < mid ; i++) {
            RM1 = (alphabetBase * RM1) % Q;
        }
        RM2 = 1;
        for (int i = mid + 2; i < patLength; i++) {
            RM2 = (alphabetBase * RM2) % Q;
        }
        patHash1 = hash(pat, 0, mid);
        patHash2 = hash(pat, mid + 1, patLength);
    }

    public boolean check(String txt, int i){
        for(int j = 0; j < patLength; j++){
            if(txt.charAt(i + j) != pat.charAt(j) && j != mid){
                return false;
            }
        }
        return true;
    }

    private long hash(String key, int begin, int end){
        long h = 0;
        for (int i = begin; i < end; i++) {
            h = (alphabetBase * h + key.charAt(i)) % Q;
        }
        return h;
    }

    public int search(String txt){
        int txtLength = txt.length();
        long txtHash1 = hash(txt, 0, mid);
        long txtHash2 = hash(txt, mid + 1, patLength);

        if(patHash1 == txtHash1 && patHash2 == txtHash2){
            return 0;
        }

        for(int i = patLength; i < txtLength; i++){
            txtHash1 = (txtHash1 + Q - RM1 * txt.charAt(i - patLength) % Q) % Q;
            txtHash1 = (txtHash1 * alphabetBase + txt.charAt(i - mid - 1)) % Q;

            txtHash2 = (txtHash2 + Q - RM2 * txt.charAt(i - mid) % Q) % Q;
            txtHash2 = (txtHash2 * alphabetBase + txt.charAt(i)) % Q;
            if(patHash1 == txtHash1 && patHash2 == txtHash2 && check(txt ,i - patLength + 1)){
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
