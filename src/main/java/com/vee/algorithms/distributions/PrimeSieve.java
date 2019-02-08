package com.vee.algorithms.distributions;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

class PrimeSieve {
    public static void main(String args[]) {
        new PrimeSieve().printPrimes(100);
    }

    public void printPrimes(int n) {
        BitSet bs = new BitSet(n+1);
        bs.set(0, n+1);
        bs.clear(0);
        bs.clear(1);
        for (int i = 2; i < Math.sqrt(n+1); i = bs.nextSetBit(i+1)) {
            for (int j = i+i; j < n+1; j = j +i) {
                bs.clear(j);
            }
        }
        System.out.println(bs);
        List<Integer> l = Arrays.asList(1,2,3,100);
        String s = String.format("%2s",l.stream().mapToInt(i -> i).sum()).replace(' ', '0');
        System.out.println(s);

    }
}