package com.vee.intw.linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.event.ListSelectionEvent;

import org.testng.collections.Lists;

public class WordDistance {

	public static void main(String[] args) {
		
	}
	
	public int distance(String words[], String w1, String w2) {
		Map<String, List<Integer>> map = new HashMap<>();
		for (int i=0; i<words.length; i++) {
			List<Integer> list;
			if (map.containsKey(words[i])) {
				list = map.get(words[i]); 
			} else {
				list = new ArrayList<>();
				map.put(words[i], list);
			}
			list.add(i);
		}
		List<Integer> w1Positions = map.get(w1);
		List<Integer> w2Positions = map.get(w2);
		new Random().nextI
	}
	
	private int binarySearch(int s, int[] a, int low, int high, int min) {
		int mid = (high - low)/2;
		if (a[mid]-s > a[min]) {
			return min;
		} else {
			
		}
	}

}
