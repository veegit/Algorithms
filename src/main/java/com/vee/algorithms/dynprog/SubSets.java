package com.vee.algorithms.dynprog;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class SubSets {

	public Set<List<String>> powerSetNormal(List<String> list) {
		Set<List<String>> powerset = new LinkedHashSet<List<String>>();
		powerset.add(new ArrayList<String>());
		for (String s: list) {
			Set<List<String>> subpowerset = new LinkedHashSet<List<String>>();
			for (List<String> subset : powerset) {
				List<String> newSubset = new ArrayList<String>(subset);
				newSubset.add(s);
				subpowerset.add(newSubset);
			}
			powerset.addAll(subpowerset);
		}
		return powerset;
	}

	public Set<List<String>> powerSetBinary(List<String> list) {
		Set<List<String>> powerset = new LinkedHashSet<List<String>>();
		for (int i = 0; i < (1 << list.size()); i++) {
			String s = String.format("%"+list.size()+"s", Integer.toBinaryString(i)).replace(' ', '0');
			List<String> newSubset = new ArrayList<String>();
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					newSubset.add(list.get(j));
				}
			}
			powerset.add(newSubset);
		}
		return powerset;
	}
	
	public boolean subSetSumRecursion(int[] a, int sum) {
		return false;
	}
	
	public boolean subSetSum2D(int[] a, int sum) {
		return false;
	}
	
	public List<List<Integer>> subSetSum(int[] a, int sum) {
		return new ArrayList<>();
	}

	//java -cp .:/usr/share/java/junit.jar org.junit.runner.JUnitCore [test class name]
	@Test
	public void testSubset() {
		List<String> s= new ArrayList<String>();
		int n = 10;
		for (int i = 1; i <= n; i++) {
			s.add(i+"");
		}
		long start = System.nanoTime();
		powerSetBinary(s);
		System.out.println(System.nanoTime() - start);
		start = System.nanoTime();
		Set<List<String>> powerset = powerSetNormal(s);
		System.out.println(System.nanoTime() - start);
		for (List<String> subset : powerset) {
			System.out.println(subset);
		}
	}
}
