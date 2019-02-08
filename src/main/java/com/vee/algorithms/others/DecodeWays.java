package com.vee.algorithms.others;
import java.util.*;

class DecodeWays {
    public int numDecodings(String s) {
        List<List<Integer>> str = new LinkedList<>();
        List<Integer> a = new LinkedList<>();
        int ch = s.charAt(0)-'0';
        if (ch == 0) {
            return 0;
        }
        a.add(ch);
        str.add(a);
        for (int i = 1; i < s.length();i++) {
            ch = s.charAt(i)-'0';
            ListIterator<List<Integer>> it = str.listIterator();
            while (it.hasNext()) {
                List<Integer> first = it.next();
                LinkedList<Integer> second = new LinkedList<>(first);
                it.remove();
                if (isValid(0,ch)) {
                    first.add(ch);
                    it.add(first);
                }
                int num = second.removeLast();
                if (isValid(num, ch)) {
                    second.add(num*10+ch);
                    it.add(second);
                }
            }
            System.out.println(str);
        }
        return str.size();
    }
    
    private boolean isValid(int a, int b) {
        if (a*10+b <= 26 && a*10+b > 0) {
            return true;
        }
        return false;
    }

    public int num(String str) {
        int []count = new int[str.length()+1];
        int last = str.charAt(0) - '0';
        if (last == 0) {
            return 0;
        }
        count[0] = 1;
        count[1] = 1;
        for (int i = 1; i < str.length(); i++) {
            int a = str.charAt(i) - '0';
            if (a != 0) {
                count[i+1] = count[i];
            }
            if (last !=0 && last*10 + a <= 26) {
                count[i+1] += count[i-1];
            }
            last = a;
        }
        return count[str.length()];
    }

    public static void main(String args[]) {
        System.out.println(new DecodeWays().numDecodings("100"));
        System.out.println(new DecodeWays().num("100"));
    }
}

/*
1223
[[1]]
[[1,2],[12]]
[[1,2,2],[1,22],[12,2]]
[[1,2,2,3],[1,2,23],[1,22,3],[12,2,3],[12,23]]

102
[[1]]


*/