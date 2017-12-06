package com.vee.algorithms.divnconq;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * find_range({0 2 3 3 3 10 10}, 3) should return (2,4).
 * @author vee
 *
 */
public class SortedRange {

	int[] findRange(int a[], int x) {
		int range[] = new int[] {-1, -1};
		int low = 0, high = a.length-1, mid = -1;
		while(low <= high) {
			mid = low + (high -low)/2;
			if (x > a[mid]) {
				low = mid + 1;
			} else if (x < a[mid]){
				high = mid - 1;
			} else {
				break;
			}
		}
		if (mid == -1) {
			return range;
		}
		int found = mid;
		low = 0; high = found-1;
		if (a[low] == x) {
			range[0] = low;
		} else {
			while(low<=high) {
				mid = low + (high -low)/2;
				if (x == a[mid]) {
					high = mid-1;
				} else if (x > a[mid] && a[mid+1] == x) {
					range[0] = mid+1;
					break;
				} else {
					low = mid+1;
				}
			}
		}
		low = found+1; high = a.length-1;
		if (a[high] == x) {
			range[1] = high;
		} else {
			while(low<=high) {
				mid = low + (high -low)/2;
				if (x == a[mid]) {
					low = mid+1;
				} else if (x < a[mid] && a[mid-1] == x) {
					range[1] = mid-1;
					break;
				} else {
					high = mid-1;
				}
			}
		}
		return range;
	}

	@Test
	public void testValid() {
		assertArrayEquals(new int[] { 2, 4}, findRange(new int[] {0,2,3,3,3,10,10}, 3));
		assertArrayEquals(new int[] { 1, 4}, findRange(new int[] {0,3,3,3,3,10,10}, 3));
		assertArrayEquals(new int[] { 0, 4}, findRange(new int[] {3,3,3,3,3,10,10}, 3));
		assertArrayEquals(new int[] { 1, 6}, findRange(new int[] {0,3,3,3,3,3,3}, 3));
	}
}
