
package com.vee.algorithms.dynprog;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class WordBreak {
    public boolean wordBreak(String s, Set<String> dict) {

        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;

        for(int i = 1; i <= s.length(); i++){
            for(String str: dict){
                if(str.length() <= i){
                    if(f[i - str.length()]){
                        if(s.substring(i-str.length(), i).equals(str)){
                            f[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        // Second DP
        /*
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (f[j] && dict.contains(s.substring(j, i))) {
                    f[i] = true;
                    break;
                }
            }
        }
        */
        System.out.println(Arrays.toString(f));
        return f[s.length()];
    }

    public static void main(String args[]) {
        WordBreak w = new WordBreak();
        w.wordBreak("catsaddogs", new HashSet<>(Arrays.asList("cats", "add", "dogs")));
    }
}