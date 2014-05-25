package com.vee.datastructures.sort;

public class CountingSort {

	int arr[];
	int count[];
	int min;
	
	CountingSort(int[] arr) {
		this.arr = arr;
		min = arr[0];
		int max = min;
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		count = new int[max-min+1];
		for (int k = 0; k < count.length; k++)
			count[k]=0;
		for (int i = 0; i < arr.length; i++) 
			count[arr[i]-min]++;
	}
	
	int[] sort() {
		int i =0,k=0;
	    while(k < count.length) {
			if(count[k] == 0)
				k++;
			else {
				arr[i++] = k+min;
				count[k]--;
			}
		}
	    return arr;
	}
	
	
	public static void main(String[] args) {
		int arr[] = new int[] {8,5,3,9,6,5,3,9,4,3,5,5,8,-1};
		arr = new CountingSort(arr).sort();
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

}
