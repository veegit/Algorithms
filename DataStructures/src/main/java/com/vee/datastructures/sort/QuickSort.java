package com.vee.datastructures.sort;

import java.util.Comparator;

public class QuickSort <M> extends BaseSort<M> implements Comparator<M>{
	public M[] array;
	public int size;
		
	public M[] quickSort(M[] a) {
		// Check for empty or null array
		if (a ==null || a.length==0){
			return null;
		}
		array = a;
		size = a.length;
		runQuickSort(0, size - 1);
		return array;
	}
	
	private void runQuickSort(int low, int high) {
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		M pivot = array[low + (high-low)/2];
			// Divide into two lists
		while (i <= j) {
			// If the current value from the left list is smaller then the pivot
			// element then get the next element from the left list
			while (compare(array[i],pivot) < 0) {
				i++;
			}
			// If the current value from the right list is larger then the pivot
			// element then get the next element from the right list
			while (compare(array[j],pivot) > 0) {
				j--;
			}
				// If we have found a value in the left list which is larger then
			// the pivot element and if we have found a value in the right list
			// which is smaller then the pivot element then we exchange the values.
			// As we are done we can increase i and j
			if (i <= j) {
				exchange(array, i, j);
				i++;
				j--;
			}
		}
		// Recursion
		if (low < j)
			runQuickSort(low, j);
		if (i < high)
			runQuickSort(i, high);
	}

	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
	
}
