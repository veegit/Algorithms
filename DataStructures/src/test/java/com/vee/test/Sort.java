package com.vee.test;

import java.util.Comparator;

import com.vee.datastructures.graph.Edge;

public class Sort<M extends Object> implements Comparator<M>{
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
				exchange(i, j);
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
	
	public M[] mergeSort(M[] a){
		if (a ==null || a.length==0){
			return null;
		}
		array = a;
		size = a.length;
		runMergeSort(a,0, size - 1);
		return array;
	}
	
	private M[] runMergeSort(M[] a, int i, int j){
		if(a.length <= 1)
			return a;
		int mid = a.length /2;
		M[] left = runMergeSort(a,0,mid);
		M[] right = runMergeSort(a,mid+1,a.length-1);
		M[] result = merge(left,right); 
		return result;
	}
	
	private M[] merge(M[] left, M[] right){
		int size1 = left.length;
		int size2 = right.length;
		return left;
	}

	public M[] heapSort(M[] a){
		if (a ==null || a.length==0){
			return null;
		}
		array = a;
		size = a.length;
		
		heapify(size); //Make a heap out of the initial array
		
		int i = a.length;
		while(i-- >0) {
			exchange(0,i); //move the first element to last
			shiftDown(0, i-1); /*reHeap the partial array.  
			                    dont include i-1 which is 
			                    already in correct place. */
		}
		
		return array;
	}
	
	private void heapify(int size) {
		int i = size/2 -1; //get the last parent
		while(i >= 0) {    // go till the first parent
			shiftDown(i,size-1); // reHeap the partial array from i till last 
			i--;
		}
	}
	
	private void shiftDown(int start, int end) {
		while(start*2 + 1 <= end) {
			int child = start*2 + 1; //get first child
			int temp = start;
			if(compare(array[temp],array[child]) < 0)
				temp = child;
			if(child+1 <=end && compare(array[temp],array[child+1]) < 0) 
				//@above check boundary condition first for child
				temp = child+1;
			if(temp != start){
				exchange(temp, start);
				start = temp;
			}
			else            //Run this ELSE statement for below
				return;     //RETURN otherwise infinite loop
		}
	}
	
	private void exchange(int i, int j) {
		M temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String args[]){
		Tuple<Integer,Integer>[] tuple = new Tuple[10];
		int x[] = {0, 1, 2, 3, 4, 8, 12, 13, 15, 14};
		for(int i= 0;i< 10; i++){
			tuple[i] = new Tuple<Integer,Integer>(i,x[i]);
		}
		Sort<Tuple<Integer,Integer>> s = new Sort<Tuple<Integer,Integer>>() {
			@Override
			public int compare(Tuple<Integer,Integer> o1, Tuple<Integer,Integer> o2) {
				return o1.tail -  o2.tail;
			}
		};
		Tuple<Integer,Integer>[] b = s.heapSort(tuple);	
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i].tail + " ");
		}
	}
}
