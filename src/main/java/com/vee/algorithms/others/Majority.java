package com.vee.algorithms.others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/* 
 * Majority of an array is a number occuring more than 50% of the size of array
 */
public class Majority {

	public static void main(String[] args) {
		int a[] = new int[] { 2, 6, 4, 1, 8, 7, 4, 5, 7, 2, 8, 4, 9, 5, 8, 9 };
		int major = new Majority().majority(a);
		System.out.println(Math.random() + " " + major);
		Set<Integer> s = new Majority().majorityFrac(a, 6);
		for (int i : s) {
			System.out.print(i + " ");
		}
	}

	int majority(int a[]) {
		int count = 0, major = 0;
		for (int i = 0; i < a.length; i++) {
			if (count == 0) {
				major = a[i];
			}
			if (a[i] == major) {
				count++;
			} else {
				count--;
			}
		}
		return major;
	}

	Set<Integer> majorityFrac(int a[], int k) {
		HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		for (int i = 0; i < a.length; i++) {
			if (count.get(a[i]) != null) {
				count.put(a[i], count.get(a[i]) + 1);
			} else if (count.size() < k - 1) {
				count.put(a[i], 1);
			} else {
				Iterator<Integer> it = count.keySet().iterator();
				while (it.hasNext()) {
					int j = it.next();
					count.put(j, count.get(j) - 1);
					if (count.get(j) == 0) {
						it.remove();
					}
				}
			}
		}
		Set<Integer> s = count.keySet();
		Arrays.sort(a);
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
		return s;
	}
}
