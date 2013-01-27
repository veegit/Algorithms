package com.vee.datastructures.sort;

import java.util.Comparator;

public class MergeSort<M> implements Comparator<M> {
	  private M[] numbers;
	  private M[] helper;

	  private int number;

	  public void sort(M[] values) {
	    this.numbers = values;
	    number = values.length;
	    this.helper = numbers.clone();
	    mergesort(0, number - 1);
	  }

	  private void mergesort(int low, int high) {
	    // Check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      mergesort(low, middle);
	      // Sort the right side of the array
	      mergesort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	  }

	  private void merge(int low, int middle, int high) {

	    // Copy both parts into the helper array
	    for (int i = low; i <= high; i++) {
	      helper[i] = numbers[i];
	    }

	    int i = low;
	    int j = middle + 1;
	    int k = low;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= high) {
	      if (compare(helper[i],helper[j]) <= 0) {
	        numbers[k] = helper[i];
	        i++;
	      } else {
	        numbers[k] = helper[j];
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }

	  }

	  public int compare(M o1, M o2) {
			return (Integer)o1 - (Integer) o2;
	  }
} 
