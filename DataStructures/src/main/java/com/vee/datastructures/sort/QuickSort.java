package com.vee.datastructures.sort;

import java.util.Comparator;
import java.util.Random;

public class QuickSort <M> extends BaseSort<M> implements Comparator<M>{
	public M[] array;
	public int size;
	public int cnt=0;
	public enum PIVOT {FIRST, MIDDLE, MEDIAN, FIRST_2,LAST,MIDDLE_2,RANDOM};
		
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
		cnt=0;
		switch(pivot) {
			case FIRST: runQuickSort(0,size - 1); break;
			case FIRST_2: runQuickSort_First(0,size - 1); break;
			case LAST: runQuickSort_Last(0,size - 1); break;
			case MIDDLE: runQuickSort_Midd(0,size - 1); break;
			case MIDDLE_2: runQuickSort_Middle(0,size - 1); break;
			case MEDIAN: runQuickSort_Median(0,size - 1); break;
			case RANDOM: runQuickSort_Random(0,size - 1); break;
			default: runQuickSort_First(0,size - 1); 
		}
		System.out.println("# of recursions " + cnt);
		return array;
	}
	
	private void runQuickSort_Middle(int low, int high) {
		cnt++;
		int i = low, j = high;
		// Get the pivot element from the middle of the list
		M pivot = array[low + (high-low)/2];
			// Divide into two lists
		while (i <= j) {
			/* If the current value from the left list is smaller then the pivot
			 element then get the next element from the left list
			 */
			while (compare(array[i],pivot) < 0) i++;
			/* If the current value from the right list is larger then the pivot
			element then get the next element from the right list
			*/
			while (compare(array[j],pivot) > 0) j--;
		    /* If we have found a value in the left list which is larger then
			   the pivot element and if we have found a value in the right list
			   which is smaller then the pivot element then we exchange the values.
			   As we are done we can increase i and j
			   */
			if (i <= j)
				exchange(array, i++, j--);
		}
		// Recursion
		if (low < j)
			runQuickSort_Middle(low, j);
		if (i < high)
			runQuickSort_Middle(i, high);
	}

	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
	
	void runQuickSort(int low, int high){
		cnt++;
		if(low >= high)
			return;
		int index = partition(low,high);
		runQuickSort(low,index-1);
		runQuickSort(index+1,high);
	}
	//5 3 4 2
	int partition(int low,int high) {
		int i = low, j = high;
		M pivot = array[low];
		while(i<j) {
			while(i < high && compare(array[i],pivot) <= 0) i++;
		    while(j > low && compare(array[j],pivot) > 0) j--;
		    //Only swap when they have nt crossed each other
		    if(i<j)
		    	exchange(array, i++, j--);
		}
		/* right is final position for the pivot */
		exchange(array, low, j);
		return j;
	}
	
	int partition_2(int low,int high) {
		int i = low+1, j = low+1;
		M pivot = array[low];
		while(i<=high) {
			if(compare(array[i],pivot)<0) {
				exchange(array,i,j);
				j++;
			}
			i++;	
		}
		exchange(array,low,j-1);// exchange rightmost element less than p
		return j-1;
	}

	//6 5 4 2
	void runQuickSort_First(int low, int high){
		cnt++;
		if(low >= high)
			return;
		int index = partition_2(low,high);
		runQuickSort_First(low,index-1);
		runQuickSort_First(index+1,high);
	}
	
	void runQuickSort_Last(int low, int high){
		cnt++;
		if(low >= high)
			return;
		exchange(array, low, high);
		int index = partition_2(low,high);
		runQuickSort_Last(low,index-1);
		runQuickSort_Last(index+1,high);
	}
	
	void runQuickSort_Midd(int low, int high){
		cnt++;
		if(low >= high)
			return;
		int midd = low + (high-low)/2;
		exchange(array, low, midd);
		int index = partition_2(low,high);
		runQuickSort_Midd(low,index-1);
		runQuickSort_Midd(index+1,high);
	}
	
	void runQuickSort_Median(int low, int high){
		cnt++;
		if(low >=high)
			return;
		int mid = low+(high-low)/2;
		int median = calcMedian(low, mid, high);
		exchange(array,low,median);
		int index = partition_2(low, high);
		//if (low < index-1)
		runQuickSort_Median(low, index-1);
		//if (index+1 < high)
		runQuickSort_Median(index+1, high);
	}
	
	void runQuickSort_Random(int low, int high){
		cnt++;
		if(low >=high)
			return;
		int random = calcRandom(low, high);
		exchange(array,low,random);
		int index = partition_2(low, high);
		//if (low < index-1)
		runQuickSort_Random(low, index-1);
		//if (index+1 < high)
		runQuickSort_Random(index+1, high);
	}
	
	int calcMedian(int a,int b, int c) {
		if (compare(array[a], array[b])>0) {
		  if (compare(array[b],array[c])>0) {
		    return b;
		  } else if (compare(array[a],array[c])>0) {
		    return c;
		  } else {
		    return a;
		  }
		} 
		else {
		  if (compare(array[a],array[c])>0) {
		    return a;
		  } else if (compare(array[b],array[c])>0) {
		    return c;
		  } else {
		    return b;
		  }
		}
	}
	
	int calcRandom(int low, int high) {
		int range = high - low ;
	    return (int) (low+range * Math.random());
	}
}
