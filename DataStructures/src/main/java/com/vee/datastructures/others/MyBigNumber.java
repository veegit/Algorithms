package com.vee.datastructures.others;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author vee
 *
 */
public class MyBigNumber {

	public static void main(String[] args) {

	}

	public String add(String a, String b) {
		int i = a.length() - 1;
		int j = b.length() - 1;
		String sum = "";
		int carry = 0;
		while (i >= 0 || j >= 0) {
			int s = getNum(a, i--) + getNum(b, j--) + carry;
			carry = s / 10;
			s = s % 10;
			sum = s + sum;
		}
		return sum + "";
	}

	private int getNum(String s, int index) {
		if (index < 0) {
			return 0;
		}
		return s.charAt(index) - 48;
	}

	@Test
	public void testAdd() {
		String result = new MyBigNumber().add("123", "1");
		assertEquals(result, "124");
	}

}
