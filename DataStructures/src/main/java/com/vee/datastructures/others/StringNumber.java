package com.vee.datastructures.others;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author vee
 *
 */
public class StringNumber {

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

	public boolean isNumber(String s) {
		char ch = s.charAt(0);
		if (ch != 43 && ch != 45 && ch != 46 && (ch < 48 || ch > 57)) {
			return false;
		}
		int dot = 0;
		for (int i=1; i< s.length(); i++) {
			ch = s.charAt(i);
			if (ch == 46) {
				dot++;
			}
			if (dot > 1 || (ch < 48 && ch > 57)) {
				return false;
			}
		}
		return true;
	}

	@Test
	public void testAdd() {
		assertEquals("124", add("123", "1"));
	}

	@Test
	public void testIsNumber() {
		assertEquals(true, isNumber("123"));
		assertEquals(true, isNumber("+123"));
		assertEquals(true, isNumber("-123"));
		assertEquals(true, isNumber(".123"));
		assertEquals(true, isNumber("0.123"));
		assertEquals(true, isNumber("12.3"));
		assertEquals(true, isNumber("123.0"));
		assertEquals(false, isNumber("123.0.0"));
		assertEquals(false, isNumber("a123"));
	}

}
