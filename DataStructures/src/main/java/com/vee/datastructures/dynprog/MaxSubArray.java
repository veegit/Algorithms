package com.vee.datastructures.dynprog;

public class MaxSubArray {

	/*
	 * maximum subarray problem is the task of finding the contiguous subarray
	 * within a one-dimensional array of numbers (containing at least one
	 * positive number) which has the largest sum. For example, for the sequence
	 * of values -2,1,-3,4,-1,2,1,-5,4; 
	 * the contiguous subarray with the largest sum is 
	 * 4, −1, 2, 1, with sum 6.
	 */
	static int maxSum(int a[]) {
		int maxending = a[0], maxsofar = a[0];
		for(int i = 1; i < a.length; i++) {
			if(maxending < 0)
				maxending = a[i];
			else
				maxending += a[i];
			if(maxending >= maxsofar)
				maxsofar = maxending;
		}
		return maxsofar;
	}
	
	static int maxSumZero(int a[]) {
		int maxending = a[0], maxsofar = a[0];
		for(int i = 1; i < a.length; i++) {
			//maxending = Math.max(Math.max(a[i],0), maxending+a[i]);
			maxending = Math.max(a[i],maxending+a[i]);
			maxsofar = Math.max(maxending, maxsofar);
		}
		return maxsofar;
	}
	
	public static void main(String[] args) {
		int a[] = new int[]{-2,-3,-3,-4,-2,-2,-1,-5,-4};
		System.out.println(maxSum(a));
		System.out.println(maxSumZero(a));
	}

}
