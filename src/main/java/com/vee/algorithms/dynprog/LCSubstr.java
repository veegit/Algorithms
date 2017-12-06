package com.vee.algorithms.dynprog;

import java.util.HashMap;
import java.util.Map;

/**
 * Find longest common substring between 2 strings
 * vee
 *
 */
public class LCSubstr {

	public String LCS(String a, String b,int i, int j) {
		if(i == -1 || j == -1) {
			return "";
		}
		if(a.charAt(i) == b.charAt(j)) {
			return LCS(a,b,i-1,j-1)+ a.charAt(i);
		} else
		{
			String f = LCS(a,b,i-1,j);
			String s = LCS(a,b,i,j-1);
			if(f.length() > s.length()) {
				return f;
			} else {
				return s;
			}
		}
	}

	public static void main(String[] args) {
		//new LCSubstr().LCS("abcd", "bc",3,1);
		new LCSubstr().longestK("aadaaabccbc", 2);
	}
	
	/**
	 * Longest substring (consecutive string) were number of unique chars is of size K
	 * @param a
	 * @param k
	 */
	public String longestK(String a, int k) {
		Map<Character, Integer> map = new HashMap<>();
		int max_s = 0, max_e = 0, curr_s = 0, curr_e = 0;
		int COUNT = 0;
		for (int i = 0; i < a.length(); i++) {
			char ch = a.charAt(i);
			int maxSize = max_e - max_s;
			curr_e = i;
			if (map.containsKey(ch)) {
				map.computeIfPresent(ch, (key,v) -> v+1);
				COUNT++;
			} else {
				while (map.size() >= k) {
					char first = a.charAt(curr_s);
					map.computeIfPresent(first, (key, v) -> v-1);
					if (map.get(first) == 0) {
						map.remove(first);
					}
					curr_s++;
					COUNT++;
				}
				map.put(ch, 1);
			}
			if ((curr_e - curr_s) > maxSize) {
				max_s = curr_s;
				max_e = curr_e;
			}
			System.out.println(ch + "->" + map);
		}
		System.out.println(a.substring(max_s, max_e+1));
		System.out.println(COUNT);
		return a.substring(max_s, max_e+1);
	}
}
