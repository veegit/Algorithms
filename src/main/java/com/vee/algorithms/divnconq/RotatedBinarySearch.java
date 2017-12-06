package com.vee.algorithms.divnconq;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RotatedBinarySearch {

	public static void main(String[] args) {
	}

	public int search(int a[], int key) {
		int left = 0, right = a.length-1;
		while(left<=right) {
			int mid = left + (right - left)/2;
			if (a[mid] == key) {
				return mid;
			}
			if (a[left] <= a[mid] && key < a[mid] && a[left] <= key) {
				right = mid - 1;
			} else if (a[mid] <= a[right] && key > a[mid] && key <= a[right]) {
				left = mid + 1;
			} else if (a[left] > a[mid]) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	@Test
	public void testWorks() {
		loop(new int[] { 4, 5, 6, 1, 2 });
		loop(new int[] { 40, 50, 60, 10, 20, 21, 22, 23, 24, 25,26, 27 });
		loop(new int[] { 40, 50, 60, 10 });
		loop(new int[] { 40, 10, 20 });
		loop(new int[] { 40, 50, 60 });
		loop(new int[] { 40, 50, 60 });
		loop(new int[] { -40, 50, 60, -50 });
		//loop(new int[] { 40, 50, 50, 30 }); returns 0, 1, 1, 3
	}

	@Test
	public void notWorks() {
		loop(new int[] { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 }); //doesnt work
	}

	private void loop(int a[]) {
		for (int i = 0; i< a.length; i++) {
			assertEquals(i, search(a, a[i]));
		}
	}

}
