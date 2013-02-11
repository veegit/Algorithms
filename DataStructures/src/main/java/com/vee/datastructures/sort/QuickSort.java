package com.vee.datastructures.sort;

import java.util.Comparator;

public class QuickSort <M> extends BaseSort<M> implements Comparator<M>{
	public M[] array;
	public int size;
	public enum PIVOT {FIRST, MIDDLE, MEDIAN};
		
	public M[] quickSort(M[] a) {
		return quickSort(a, PIVOT.FIRST);
	}
	
	public M[] quickSort(M[] a, PIVOT pivot) {
		// Check for empty or null array
		if (a ==null || a.length==0){
			return null;
		}
		array = a;
		size = a.length;
		switch(pivot) {
			case FIRST: runQuickSort_1(0,size - 1); break;
			case MIDDLE: runQuickSort_2(0,size - 1); break;
			default: runQuickSort_1(0,size - 1); 
		}
		return array;
	}
	
	private void runQuickSort_2(int low, int high) {
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
			runQuickSort_2(low, j);
		if (i < high)
			runQuickSort_2(i, high);
	}

	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
	
	void runQuickSort_1(int low, int high){
		if(low >= high)
			return;
		int index = partition(low, high);
		runQuickSort_1(low,index-1);
		runQuickSort_1(index+1,high);
	}
	//5 5 3 4 2
	int partition(int low,int high) {
		M pivot_value = array[low];
		int i = low;
		int j = high;
		while(i<j) {
			while(i < high && compare(array[i],pivot_value) <= 0) i++;
		    while(j > low && compare(array[j],pivot_value) > 0) j--;
		    //Only swap when they have nt crossed each other
		    if(i<j)
		    	exchange(array, i++, j--);
		    for (int k = 0; k < array.length; k++) 
				System.out.print(array[k] + " ");
		    System.out.println("["+i+","+j+"]"+"End Exchange");
		}
		/* right is final position for the pivot */
		exchange(array, low, j);
		for (int k = 0; k < array.length; k++) 
			System.out.print(array[k] + " ");
		System.out.println("["+low+","+j+"]"+"End Partition");
		return j;
	}	
}
