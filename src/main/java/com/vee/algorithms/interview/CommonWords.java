package com.vee.algorithms.interview;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

/**
 * Print most common words in a sentence or a group of sentences.
 * It should be case sensitive. If they are more than 1 then print all of them
 *
 *
 */
//interview = twitter
public class CommonWords {

	public String[] common(String s) {
		String a[] = s.split("\\W+");
		Map<String, Integer> occurences = new LinkedHashMap<String, Integer>();
		for (String str: a) {
			Integer val = occurences.get(str);
			if (val == null) {
				occurences.put(str, 1);
			} else {
				occurences.put(str, val+1);
			}
		}

		int max = -1;
		List<String> list = new ArrayList<String>();
		for (Entry<String, Integer> e : occurences.entrySet()) {
			if (max < e.getValue()) {
				list.clear();
				max = e.getValue();
				list.add(e.getKey());
			} else if (max == e.getValue()) {
				list.add(e.getKey());
			}
		}

		return list.toArray(new String[0]);
	}

	@Test
	public void testSingleWord() {
		String s = "Print most common words in a sentence or a group of sentences.";
		String a[] = new CommonWords().common(s);
		assertEquals(a.length, 1);
		assertEquals(a[0], "a");
	}

	@Test
	public void testMultiWord() {
		String s = "Buffalo buffalo Buffalo buffalo buffalo buffalo Buffalo Buffalo";
		String a[] = new CommonWords().common(s);
		assertEquals(a.length, 2);
		assertEquals(a[0], "Buffalo");
		assertEquals(a[1], "buffalo");
	}

}
