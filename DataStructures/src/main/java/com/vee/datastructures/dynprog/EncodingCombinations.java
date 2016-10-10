package com.vee.datastructures.dynprog;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * 123 -> abc, lc, aw
 *
 */
public class EncodingCombinations {


	void show(int n) {
		String nstr = n + "";
		List<String> combinations = new ArrayList<String>();
		int i = 0;
		int num = nstr.charAt(i) - 48;
		combinations.add(toChar(num));
		for (i = 1; i < nstr.length(); i++) {
			num = nstr.charAt(i) - 48;
			List<String> temp = new ArrayList<String>();
			for (String s : combinations) {
				int code = toInt(s.charAt(s.length()-1));
				int newcode = code * 10 + num;
				if (newcode < 27) {
					temp.add(s.substring(0, s.length()-1) + toChar(newcode));
				}
				temp.add(s + toChar(num));
			}
			n = n / 10;
			combinations = temp;
		}
		for (String s : combinations) {
			System.out.println(s);
		}
	}

	int toInt(char ch) {
		return ch - 96;
	}

	String toChar(int i) {
		return (char) (i + 96) + "";
	}

	@Test
	public void testShow() {
		show(123);
		show(616);
	}
}
