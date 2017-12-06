package com.vee.algorithms.others;
import java.util.Arrays;


public class Combinatorics {
	String prev = ""; 
	
	public void combinations(String str, int r) {
		int n = str.length();
		int [] s = new int[r];
		for(int i = 0; i < r; i++) {
			s[i] = i;
		}
		display(s,str);
		final int MAX = n-1;
		
		for(int i = 1; i < C(n,r); i++) {
			int j = r-1;
			int max = MAX;
			while(s[j] == max) {
				j--; max--;
			}
			s[j] = s[j] + 1;
			/*
			 * Make sure if a prior index is updated
			 * increment the subsequent indices
			 * e.g. 4 C 2 = 0 1 2 3
			 * 0 1 => 0 2 => 0 3 => (1) 3 -> 1 (2) 
			 */
			for(int k = j+1; k < r; k++)
				s[k] = s[k-1] + 1;
			display(s,str);
		}
	}
	
	
	void display(int s[],String str) {
		String out = "";
		for (int i : s) {
			out += str.charAt(i);
		}
		System.out.println(out);
	}
	
	public static void main(String args[]) {
		char[] chars = "6789".toCharArray();
		Arrays.sort(chars);
		new Combinatorics().combinations(new String(chars), 3);
	}
	
	public static long C(int n, int r) {
		long prod = 1;
		for(int i = 0; i <r ;i++) {
			prod *= (n-i);
			prod /= (i+1);
		}
		return prod;
	}
}
