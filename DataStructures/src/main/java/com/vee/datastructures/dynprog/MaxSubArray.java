package com.vee.datastructures.dynprog;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxSubArray {

	/*
	 * maximum subarray problem is the task of finding the contiguous subarray
	 * within a one-dimensional array of numbers (containing at least one
	 * positive number) which has the largest sum. For example, for the sequence
	 * of values -2,1,-3,4,-1,2,1,-5,4;
	 * the contiguous subarray with the largest sum is
	 * 4, −1, 2, 1, with sum 6.
	 */
	static int kadaneMaxSum(int a[]) {
		int maxending = a[0], maxsofar = a[0];
		for(int i = 1; i < a.length; i++) {
			if(maxending < 0) {
				maxending = a[i];
			} else {
				maxending += a[i];
			}
			if(maxending >= maxsofar) {
				maxsofar = maxending;
			}
		}
		return maxsofar;
	}

	static int kadaneMaxSumZero(int a[]) {
		int sum = a[0], max = a[0];
		for(int i = 1; i < a.length; i++) {
			sum = Math.max(sum+a[i], a[i]);
			max = Math.max(sum, max);
		}
		return max;
	}

	public static void main(String[] args) {
		int a[] = new int[]{-2,-3,-3,-4,-2,-2,-1,-5,-4};
		a = new int[] {-2,1,-3,4,-1,2,1,-5,-4};
		System.out.println(kadaneMaxSum(a));
		System.out.println(kadaneMaxSumZero(a));
		maxProd(a);
	}

	static int maxSum(int a[]) {
		int sum = a[0], max = a[0];
		for (int i=1; i< a.length; i++) {
			if (sum + a[i] >= a[i]) {
				sum = sum + a[i];
			} else {
				sum = a[i];
			}
			max = Math.max(max, sum);
		}
		return max;
	}

	static int maxProd(int a[]) {
		int prod = a[0], max = a[0], min = a[0];
		for (int i=1; i< a.length; i++) {
			int newmin = Math.min(min * a[i], a[i]);
			newmin = Math.min(max * a[i], newmin);
			max = Math.max(max * a[i], a[i]);
			max = Math.max(min * a[i], max);
			min = newmin;
			prod = Math.max(prod, max);
			System.out.println(String.format("Max: %d, Min: %d, Prod: %d", max, min, prod));
		}
		return prod;
	}

	@Test
	public void testMaxProd() {
		int[] a = new int[] {-2,1,-3,4,-1,2,1,-5,4};
		assertEquals(maxProd(a), 960);
		a = new int[] {-2,1,-3,4,-1,2,1,-5,-4};
		assertEquals(maxProd(a), 480);
		a = new int[] {2,-1,3, -4, 1,-2,1,-5,4,-1};
		assertEquals(maxProd(a), 960);

	}
	@Test
	public void testMaxSum() {
		int[] a = new int[] {-2,1,-3,4};
		assertEquals(maxSum(a), 4);
		a = new int[] {-2,1,3,-4};
		assertEquals(maxSum(a), 4);
		a = new int[] {-2,-1,-3, -4};
		assertEquals(maxSum(a), -1);

	}
}
