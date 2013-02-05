package com.vee.test;

import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Scanner;

public class Inversions<M> implements Comparator<M> {
	  private M[] numbers;
	  private M[] helper;
	  long num = 0;

	  private int number;

	  public long countInversions(M[] values) {
	    this.numbers = values;
	    number = values.length;
	    this.helper = numbers.clone();
	    sortANDcount(0, number - 1);
	    return num;
	  }

	  private void sortANDcount(int low, int high) {
		// Check if low is smaller then high, if not then the array is sorted
	    if (low < high) {
	      // Get the index of the element which is in the middle
	      int middle = low + (high - low) / 2;
	      // Sort the left side of the array
	      sortANDcount(low, middle);
	      // Sort the right side of the array
	      sortANDcount(middle + 1, high);
	      // Combine them both
	      mergeANDcount(low, middle, high);
	    }
	  }

	  private void mergeANDcount(int low, int middle, int high) {
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
	        /**
	         * This is where inversions are counted
	         */
	        num = num + middle + 1 - i;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    /* Why not for right? = since the right side if greater is already placed
	       correctly in the numbers array */
	    while (i <= middle) {
	      numbers[k] = helper[i];
	      k++;
	      i++;
	    }

	  }

	  public int compare(M o1, M o2) {
			return (Integer)o1 - (Integer) o2;
	  }
	  
	  public static void main(String args[]) {
		  {
			  Integer in[] = {5,3,4,2};
			  long inversions = new Inversions<Integer>().countInversions(in);
			  assert inversions==5 : inversions;
		  }
		  {
			  Integer in[] = {5,3,2,1};
			  long inversions = new Inversions<Integer>().countInversions(in);
			  assert inversions==6 : inversions;
		  }
		  {
			  Integer in[] = {1,2,3,4};
			  long inversions = new Inversions<Integer>().countInversions(in);
			  assert inversions==0 : inversions;
		  }
		  {
			  Integer in[] = {1,3,5,2,4,6};
			  long inversions = new Inversions<Integer>().countInversions(in);
			  assert inversions==3 : inversions;
		  }
		  {
			  Scanner scanner;
			  Integer[] in = new Integer[100000];
			  int i = 0;
			  Inversions<Integer> inv = new Inversions<Integer>();
			  try {
			    scanner = new Scanner(new InputStreamReader(inv.getClass().
			    		getClassLoader().getResourceAsStream("IntegerArray.txt")));
			    while(scanner.hasNextInt())
				   in[i++] = scanner.nextInt();
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
			  long inversions = new Inversions<Integer>().countInversions(in);
			  System.out.println(i + " " + inversions);
		  }
	  }
} 
