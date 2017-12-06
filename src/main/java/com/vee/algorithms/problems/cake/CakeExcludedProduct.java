package com.vee.algorithms.problems.cake;

import org.junit.Test;

public class CakeExcludedProduct {
	/**
	You have a list of integers, and for each index you want to find the product of every integer except the integer at that index.
	Write a function get_products_of_all_ints_except_at_index() that takes a list of integers and returns a list of the products.
	For example, given:
	  [1, 7, 3, 4]
	your function would return:
	  [84, 12, 28, 21]
	by calculating:
	  [7*3*4, 1*3*4, 1*7*4, 1*7*3]
	Do not use division in your solution.**/ 
	@Test
	public void test() {
		System.out.println(excludedProduct(new int[]{1, 7, 3, 4}));
	}
	
	public int[] excludedProduct(int a[]) {
		int []b = new int[a.length];
		int []op1 = new int[a.length];
		int []op2 = new int[a.length];
		op1[0] = 1;
		op2[a.length-1] = 1;
		for (int i = 1; i < a.length; i++) {
			op1[i] = op1[i-1] * a[i-1];
		}
		for (int i = a.length-2; i >= 0; i--) {
			op2[i] = op2[i+1] * a[i+1];
		}
		for (int i=0; i < a.length; i++) {
			b[i] = op1[i] * op2[i];
		}
		return b;
	}
}
