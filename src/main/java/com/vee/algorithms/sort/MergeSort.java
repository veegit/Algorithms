package com.vee.algorithms.sort;

import java.util.Comparator;

public class MergeSort<M> implements Comparator<M> {
	  private M[] numbers;
	  private M[] helper;
	  int count = 0;

	  private int number;

	  public M[] mergeSort(M[] values) {
	    this.numbers = values;
	    number = values.length;
	    this.helper = numbers.clone();
	    sort(0, number - 1);
	    return numbers;
	  }

	  private void sort(int low, int high) {
		System.out.println("\nSORT: Low = " + low + " High = " + high);
	    // Check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      sort(low, middle);
	      // Sort the right side of the array
	      sort(middle + 1, high);
	      // Combine them both
	      merge(low, middle, high);
	    }
	  }

	  private void merge(int low, int middle, int high) {
		  System.out.println("\nMERGE: Low = " + low + " High = " + high);
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
	      count++;
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    /* Why not for right? = since the right side if greater is already placed
	       correctly in the numbers array */
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	      count++;
	    }
	    System.out.println("COUNT: " + count);
	    for (int k2 = 0; k2 < numbers.length; k2++)
			System.out.print(numbers[k2] + " ");

	  }

	  public int compare(M o1, M o2) {
			return (Integer)o1 - (Integer) o2;
	  }
} 
