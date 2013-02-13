package com.vee.datastructures.divnconq;
/**
 * 
 * @author narayanan_venkiteswaran
 * @version 1.0
 * You are a given a unimodal array of n distinct elements, meaning that 
 * its entries are in increasing order up until its maximum element, after 
 * which its elements are in decreasing order. Give an algorithm to compute 
 * the maximum element that runs in O(log n) time. 
 *
 */
public class Unimodal {

	public static void main(String[] args) {
		int a[] = {1,2,8,7,6,5};
		System.out.println(maximum(a));
	}
	
	static int maximum(int a[]) {
		int low = 0, high = a.length - 1, mid=0;
		while(true) {
			mid = low + (high-low)/2;
			if(mid == low || mid == high) {
				mid = (a[low]<=a[high]) ? high : low; 
				break;
			}
				
			if(a[mid-1] < a[mid] && a[mid] < a[mid+1])
				low = mid+1;
			else if(a[mid-1] > a[mid] && a[mid] > a[mid+1])
				high = mid-1;
			else
				break;
		}
		return a[mid];
	}
	
	static void wrong1() {
		int a[] = {2,4,6,8,7,6};
		int low = 0, high = a.length - 1;
		int mid = low + (high-low)/2;
		while(true) {
			if(mid == 0 || mid == a.length-1)
				break;
			if(a[mid-1] < a[mid] && a[mid] < a[mid+1])
				low = mid+1;
			else if(a[mid-1] > a[mid] && a[mid] > a[mid+1])
				high = mid-1;
			else
				break;
		}
		System.out.println(a[mid]);
	}
	
	static void wrong2() {
		int a[] = {2,4,6,8};
		int low = 0, high = a.length - 1;
		int mid = low + (high-low)/2;
		while(true) {
			mid = low + (high-low)/2;
			if(mid == low || mid == high)
				break;
			if(a[mid-1] < a[mid] && a[mid] < a[mid+1])
				low = mid+1;
			else if(a[mid-1] > a[mid] && a[mid] > a[mid+1])
				high = mid-1;
			else
				break;
		}
		System.out.println(a[mid]);
	}

}
