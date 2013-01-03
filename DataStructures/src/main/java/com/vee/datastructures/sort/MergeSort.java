package com.vee.datastructures.sort;

import java.util.Comparator;

public class MergeSort <M> implements Comparator<M>{
	public M[] array;
	public int size;
	
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
		M[] result = (M[]) new Integer[size1 + size2];
		int i = 0;
		while(i < size1) {
			result[i] = left[i];
			i++;
		}
		while(i<size1+size2) {
			result[i] = right[i-size1];
			i++;
		}
		return result;
	}
	
	public int compare(M o1, M o2) {
		return (Integer)o1 - (Integer) o2;
	}
}
