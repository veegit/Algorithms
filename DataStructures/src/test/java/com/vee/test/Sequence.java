package com.vee.test;

//http://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
public class Sequence {

  public static void main(String args[]) {
  	longestZigZagSequence();
	}
	
	public static void longestNonDecSequence() {
		int array[] = {5,3,4,8,6,7,4,12,51,2,7,1,9,34,7,8,9,3,6,3,12,1,34,56,3,6,8,3,5,7,9,3};
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
	
	public static void longestZigZagSequence() {
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
	}
}