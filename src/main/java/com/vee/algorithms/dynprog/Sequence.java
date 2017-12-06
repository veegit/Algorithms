package com.vee.algorithms.dynprog;

public class Sequence {

  public static void main(String args[]) {
  	//assert (longestZigZagSequence() == 37);
  	longestNonDecSequence();
	}
	
  /**
   *  Given a sequence of N numbers - A[1] , A[2] , ..., A[N] . 
   *  Find the length of the longest non-decreasing sequence. 
   *  E.g 5, 3, 4, 8, 6, 7: returns 4
   */
	public static void longestNonDecSequence() {
		//int array[] = {5,3,4,8,6,7,4,12,51,2,7,1,9,34,7,8,9,3,6,3,12,1,34,56,3,6,8,3,5,7,9,3};
		int array[] = {5, 3, 4, 8, 6, 7};
		int size[] = new int[array.length];
		size[0] = 1;
		int max = 1;
		for(int i = 1; i< array.length; i++) {
			size[i] = 1;
			if(array[i] >= array[i-1] && size[i-1] + 1 > size[i])
				size[i] = size[i-1] + 1;
			if(max < size[i])
				max= size[i];
		}
		for (int i = 0; i < array.length; i++) {
			System.out.format("%2d ", array[i]);
		}
		System.out.println();
		for (int i = 0; i < size.length; i++) {
			System.out.format("%2d ", size[i]);
		}
		System.out.println();
		System.out.println(max);
	}
	
	
	/**
	 *  A sequence of numbers is called a zig-zag sequence if the differences 
	 *  between successive numbers strictly alternate between positive and 
	 *  negative. The first difference (if one exists) may be either positive 
	 *  or negative. A sequence with fewer than two elements is trivially 
	 *  a zig-zag sequence. For example, 1,7,4,9,2,5 is a zig-zag sequence 
	 *  because the differences (6,-3,5,-7,3) are alternately positive and 
	 *  negative. In contrast, 1,4,7,2,5 and 1,7,4,5,5 are not zig-zag 
	 *  sequences, the first because its first two differences are positive and
	 *  the second because its last difference is zero.
	 *  Given a sequence of integers, sequence, return the length of the longest
	 *  subsequence of sequence that is a zig-zag sequence. 
	 *  A subsequence is obtained by deleting some number of elements 
	 *  (possibly zero) from the original sequence, 
	 *  leaving the remaining elements in their original order. 
	 */
	
	public static int longestZigZagSequence() {
		int array[] ={  374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
						600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
						67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
						477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
						249, 22, 176, 279, 23, 22, 617, 462, 459, 244 };
		int size[] = new int[array.length];
		size[0] = 1;
		size[1] = 2;
		int max = Math.min(array.length,2);
		
		int prevsign = (array[1] - array[0]);
		prevsign = prevsign / Math.abs(prevsign);
		for (int i = 2; i < size.length; i++) {
			size[i] = size[i-1];
			int sign = (array[i] - array[i-1]);
			sign = (sign == 0) ? prevsign : sign / Math.abs(sign);
			if(size[i-1]+1 > size[i] && sign != prevsign)
				size[i] = size[i-1]+1;
			prevsign = sign;
			if(max < size[i])
				max= size[i];
		}
		for (int i = 0; i < array.length; i++) {
			System.out.format("%2d ", array[i]);
		}
		System.out.println();
		for (int i = 0; i < size.length; i++) {
			System.out.format("%2d ", size[i]);
		}
		System.out.println();
		System.out.println(max);
		return max;
	}
}