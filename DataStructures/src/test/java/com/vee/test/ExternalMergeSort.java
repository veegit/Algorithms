package com.vee.test;

import java.util.Random;

public class ExternalMergeSort {
	public int numbers[];
	int size = 2;
	
	void sort(int low, int high) {
		if(low<high) {
			int middle = low + (high-low)/2;
			sort(low, middle);
			sort(middle+1,high);
			merge(low,middle,high);
		}			
	}
	
	void merge(int low, int middle, int high) {
		int[] helpers = new int[high-low+1];
		for (int i = low; i <= high; i++) {
		      helpers[i-low] = numbers[i];
		}
		int i = low;
		int j = middle+1;
		int k = low;
		while(i <= middle && j <= high) {
			if(helpers[i-low] <= helpers[j-low])
				numbers[k++] = helpers[i++ - low];
			else
				numbers[k++] = helpers[j++ - low];
		}
		while(i<=middle) {
			numbers[k++] = helpers[i++ - low];
		}
	}
	
	public static void main(String args[]) {
		int in[] = {5,3,4,2,1,7,11,13,6,9,15,17,13,12,20,10,14,18};
		ExternalMergeSort e = new ExternalMergeSort();
		e.numbers = in;
		e.sort(0, in.length-1);
		for (int i = 0; i < e.numbers.length; i++) {
			System.out.print(e.numbers[i] + " ");
		}
	}
}
