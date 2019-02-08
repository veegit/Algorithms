package com.vee.algorithms.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
1 2 3
4 5 6
7 8 9
x 0 x

All 10 digit numbers, knights path are the next number
*/

class PhoneNumber {
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    static {
        map.put(1, Arrays.asList(6,8));
        map.put(2, Arrays.asList(7,9));
        map.put(3, Arrays.asList(4,8));
        map.put(4, Arrays.asList(0,3,9));
        map.put(5, Arrays.asList());
        map.put(6, Arrays.asList(0,1,7));
        map.put(7, Arrays.asList(2,6));
        map.put(8, Arrays.asList(1,3));
        map.put(9, Arrays.asList(2,4));
        map.put(0, Arrays.asList(6,8));
    }

    public static void main(String []args) {
        System.out.println(new PhoneNumber().comb(20));
    }

    public List<String> comb(int n) {
        long a = (long) Math.pow(10,9) + 7;
        List<String> comb = IntStream.range(0, 10).boxed().map(String::valueOf).collect(Collectors.toList());
        for (int i = 1; i < n; i++) {
            List<String> newComb = new java.util.LinkedList<>();
            for (String s : comb) {
                List<Integer> next = map.get(s.charAt(s.length()-1)-'0');
                for (int x : next) {
                    newComb.add(s + x);
                }
            }
            comb = newComb;
        }
        return comb;
    }

}

