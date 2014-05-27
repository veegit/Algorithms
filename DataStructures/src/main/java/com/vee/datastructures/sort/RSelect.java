package com.vee.datastructures.sort;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
public class RSelect {

	public int select(int a[], int k) {
		return run(Arrays.copyOf(a, a.length), 0, a.length-1, k);
	}

	private int run(int a[], int low, int high, int k) {
		if (low >= high) {
			return a[low];
		}
		int r = low + new Random().nextInt(high-low+1);
		exchange(a, r, low);
		int index = partition(a, low, high);
		//since array is 0 based but k is 1 based; 4th largest is 3rd index
		int i = index - low + 1;
		if (k < i) {
			return run(a, low, index-1, k);
		} else if(k > i) {
			return run(a, index+1, high, k-i);
		} else {
			return a[index];
		}
	}

	private int partition(int a[], int low, int high) {
		int i = low, j = high;
		int pivot = a[low];
		while (i< j) {
			while(i< high && a[i] <= pivot) {
				i++;
			}
			while(j> low && a[j] > pivot) {
				j--;
			}
			if (i < j) {
				exchange(a, i, j);
				i++;
				j--;
			}
		}
		exchange(a, low, j);
		return j;
	}

	private void exchange(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@Test
	public void testPartition() {
		int a[] = new int[] { 3,4,5,1,2 };
		assertEquals(2, partition(a, 0, a.length-1));
		assertArrayEquals(new int[] {1,2,3,5,4}, a);
		assertEquals(0, partition(a, 0, 2-1));
		assertArrayEquals(new int[] {1,2,3,5,4}, a);
		assertEquals(4, partition(a, 2+1, a.length-1));
		assertArrayEquals(new int[] {1,2,3,4,5}, a);
	}

	@Test
	public void testSelect() {
		int a[] = new int[] { 3,4,5,1,2 };
		for (int i=0; i< a.length; i++) {
			//assertEquals(i+1, select(a, i+1));
			System.out.println(i+1 + ":"+select(a, i+1));
		}
	}
}
