package com.vee.algorithms.sort;

public class DutchFlagSort {

	void sort(int[] arr, int p, int k) {
		int pi = 0;
		int ki = arr.length-1;
		int i = 0;
		while (i <= ki) {
			if(arr[i] == p) {
				exchange(arr, i, pi);
				pi++;
				i++;
			}
			else if(arr[i] >= k) {
				exchange(arr, i, ki);
				ki--;
			}
			else { 
				i++;
			}
		}
	}
	
	void exchange(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	public static void main(String[] args) {
		int a[] = new int[] { 2,2,1,3,2,3};
		new DutchFlagSort().sort(a,1,3);
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
