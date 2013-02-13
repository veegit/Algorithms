package com.vee.datastructures.divnconq;

/**
 * You are given a sorted (from smallest to largest) array A of n distinct 
 * integers which can be positive, negative, or zero. You want to decide 
 * whether or not there is an index i such that A[i] = i. Design the fastest 
 * algorithm that you can for solving this problem.
 * @author narayanan_venkiteswaran
 * @version 1.0
 *
 */
public class ArrIndexValueEq {

	public static void main(String[] args) {
		int a[] = {-1,0,3,4,5};
		System.out.println(match(a));
	}
	
	static int match(int[] a) {
		int found = -1;
		int low = 0, high = a.length - 1, mid=0;
		while(low <= high) {
			mid = low + (high-low)/2;
			if(mid < a[mid])
				high = mid -1;
			else
			if(mid > a[mid])
				low = mid +1;
			else 
			{
				found = mid;
				break;
			}
		}
	   return found;
	}
	
	static void wrong1() {
		int a[] = {-1,0,1,3,5};
		int found = -1;
		int low = 0, high = a.length - 1, mid=0;
		while(low <= high) {
			mid = low + (high-low)/2;
			if(mid < a[mid])
				high = mid -1;
			else
			if(mid > a[mid])
				high = mid -1;
			else 
			{
				found = mid;
				break;
			}
		}
	   System.out.println(found);
	}
}
