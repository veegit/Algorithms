package com.vee.algorithms.problems.foobar;

import org.junit.Test;

public class GooFooCrossings {
	/*
	 * Calculate the number if times > will interact with following <
	 */
	@Test
	public void test() {
		System.out.println(numCrossings("<><--->-><-><-->-"));
	}

	public int numCrossings(String s) {
		char[] a = s.toCharArray();
		int crossings=0;
		int right=0;
		for (int i=0; i<a.length;i++) {
			if (a[i]=='>') right++;
			if (a[i]=='<') crossings+=right;
		}
		return crossings;
	}
}
