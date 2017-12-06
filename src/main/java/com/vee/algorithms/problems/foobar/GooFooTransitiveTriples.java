package com.vee.algorithms.problems.foobar;

import java.util.Arrays;

import org.junit.Test;

public class GooFooTransitiveTriples {
	/*
	 * Return number of tripes [a,b,c] from a sorted array where b = Xa and c = Yb for any arbitary X and Y  
	 */
	@Test
	public void test() {
		int S[] = new int[] { 1,1,1,1 };
		int triples = triples(S);
		System.out.println(triples);
	}

	public int triples(int[] S) {
		int[] num = new int[S.length];
		int triples = 0;
		for (int i = 1; i < S.length; i++) {
			for (int j = 0; j <= i-1; j++) {
				if (S[i] % S[j] == 0) {
					num[i]++;
					triples += num[j];
				}
			}
		}
		System.out.println(Arrays.toString(num));
		return triples;
	}
}
