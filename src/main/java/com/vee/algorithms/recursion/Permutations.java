package com.vee.algorithms.recursion;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Permutations {
	
	public Set<String> ofPermutations;
	
	public Permutations(String str) {
		ofPermutations = new TreeSet<String>();
		Permutation("",str);
	}
	
	/**
	 * 
	 * The idea is to fix each letter and get the permutation of the rest
	 * A string of 0 length will return itself OR in this case fixed+""
	 */
	private void Permutation(String fixed, String str) {
		int n = str.length();
		if(n == 0) 
			ofPermutations.add(fixed);
		else {
			for(int i =0;i<n;i++)
				Permutation(fixed+str.charAt(i),
						str.substring(0, i) + str.substring(i+1));
		}
	}
	
	public static void main(String args[]) {
		Permutations p = new Permutations("1234");
		for (Iterator<String> iterator = p.ofPermutations.iterator(); iterator.hasNext();) {
			System.out.println(iterator.next());
		}
	}


}
