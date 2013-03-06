package com.vee.datastructures.recursion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Permutations {
	
	public Set<String> ofPermutations;
	
	public Permutations(String str) {
		ofPermutations = new HashSet<String>();
		Permutation("",str);
	}
	
	/**
	 * 
	 * The idea is to fix each letter and get the permutation of the rest
	 * a string of 0 length will return itself OR in this case prefix+""
	 */
	private void Permutation(String prefix, String str) {
		int n = str.length();
		if(n == 0) 
			ofPermutations.add(prefix);
		else {
			for(int i =0;i<n;i++)
				Permutation(prefix+str.charAt(i),str.substring(0, i)+str.substring(i+1));
		}
	}
	
	public static void main(String args[]) {
		Permutations p = new Permutations("aaba");
		for (Iterator<String> iterator = p.ofPermutations.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}
}
