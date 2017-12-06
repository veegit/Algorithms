package com.vee.algorithms.problems.cake;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class CakeRepeatingIntegers {
	/**
	 * 
	 * We have a list of integers, where:
    The integers are in the range 0..n
    The list has a length of n+1
It follows that our list has at least one integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than twice.
Write a function which finds any integer that appears more than once in our list
	 */
	@Test
	public void test() {
		System.out.println(findRepeating(new int[]{1,1,2,1,3,1,1,2,1}));
	}
	
	public List<Integer> findRepeating(int[] a) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< a.length; i++) {
			while (a[a[i]] != a[i]) { 
				swap(a, i, a[i]);
			}
		}
		for (int i=0; i< a.length; i++) {
			if (a[i] != i) { 
				list.add(i);
			}
		}
		return list.stream().map(i -> a[i]).collect(Collectors.toList());
	}
	
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
