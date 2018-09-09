package com.vee.algorithms.dynprog;

/**
 * 
 * @author vee Given a list of integers, write a function that returns the
 *         largest sum of non-adjacent numbers.
 * 
 *         For example, [2, 4, 6, 8] should return 12, since we pick 4 and 8.
 *         [5, 1, 1, 5] should return 10, since we pick 5 and 5.
 */
public class MaxNonAdjacentArray {
	
	 public static void main(String[] args) {
	     int a = new MaxNonAdjacentArray().maxSum(new int[] {5,1,1,5});
	     System.out.println(a);
	   }

	public int maxSum(int[] a) {
		int includedSum = a[0];
		int excludedSum = 0;
		for (int i = 1; i < a.length; i++) {
			int sum = Math.max(includedSum, excludedSum);
			includedSum = excludedSum + a[i];
			excludedSum = sum;
		}
		return Math.max(excludedSum, includedSum);
	}
}
