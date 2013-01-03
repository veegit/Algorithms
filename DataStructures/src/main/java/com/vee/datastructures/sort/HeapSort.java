package com.vee.datastructures.sort;

import java.util.Comparator;

public class HeapSort <M> extends BaseSort<M> implements Comparator<M>{
	public M[] array;
	public int size;
		
	public M[] heapSort(M[] a){
		if (a ==null || a.length==0){
			return null;
		}
		array = a;
		size = a.length;
		
		heapify(size); //Make a heap out of the initial array
		
		int i = a.length;
		while(i-- >0) {
			exchange(array,0,i); //move the first element to last
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
				exchange(array, temp, start);
				start = temp;
			}
			else            //Run this ELSE statement for below
				return;     //RETURN otherwise infinite loop
		}
	}
		
	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
	
}
