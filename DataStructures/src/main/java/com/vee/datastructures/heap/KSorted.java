package com.vee.datastructures.heap;

import java.util.PriorityQueue;

/**
 * 
 * @author narayanan_venkiteswaran
 * @version 1.0 
 * You're given an array that is almost sorted, in that each of the
 * N elements may be misplaced by no more than k positions from the
 * correct sorted order. Find a space-and-time efficient algorithm to
 * sort the array.
 */
public class KSorted {
	public static void main(String args[]) {
		int k = 3;
		int x[] = {0,1,2,3,4,5,6,7};
		int a[] = {3,4,0,6,1,2,5,7};
		int b[] = a.clone();
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < x.length; j++)
				if(a[i] == x[j])
					System.out.print(Math.abs(i-j)+" ");
		double cnt = insertion(b,k);
		System.out.println("\nInsertion O("+cnt +")");
		display(b);
		
		int c[] = a.clone();
		cnt = heap(c, k);
		System.out.println("\nHeap O("+cnt +")");
		display(c);
	}
	
	/* O(nk) solution */
	static double insertion(int a[],int k) {
		int cnt = 0;
		for (int i = 1; i < a.length; i++) {
			int j = i<k ? 0 : i-k; 
			while(j < i) {
				if(a[j] > a[i])
					exchange(a, i, j);
				cnt++;
				j++;
			}
		}
		return Math.round(cnt);
	}
	
	static double heap(int a[],int k) {
		PriorityQueue<Integer> heap  = new PriorityQueue<Integer>();
		int j =0;
		for (int i = 0; i < a.length; i++) {
			heap.add(a[i]);
			if(heap.size() > k)
				a[j++] = heap.poll();
		}
		while(!heap.isEmpty())
			a[j++] = heap.poll();
		
		return Math.round(a.length*Math.log(k)); 
	}
	
	static void display(int a[]) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	static void exchange(int a[], int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
