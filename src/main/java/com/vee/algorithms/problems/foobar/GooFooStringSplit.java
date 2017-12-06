package com.vee.algorithms.problems.foobar;

import org.junit.Test;

public class GooFooStringSplit {
	/*
	 * Split a string with repeating string and return the number of splits
	 */
	@Test
	public void test() {
		String s = "abccbaabccba";
		s = "ababcabababcab";
		s = "abcabcabc";
		System.out.println(s);
	}

	public int numSplits(String s) {
		int d = split(s);
		return d;
	}

	private int split(String s) {
		for (int i = s.length()-1; i > 0; i--) {
			String first = s.substring(i);
			String second = s.substring(0, s.length()-i);
			if (first.equals(second) && s.length()%first.length() == 0 && String.format("%0" + s.length()/first.length() + "d", 0).replace("0",first).equals(s) ) {
				return s.length()/first.length();
			}
		}
		return 1;
	}
}
