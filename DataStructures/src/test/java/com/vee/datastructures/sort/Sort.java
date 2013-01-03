package com.vee.datastructures.sort;

import java.util.Random;

public class Sort {
	
	public static void main(String args[]){
		//Integer in[] = {10, 1, 200, 31, 24, 118, 122, 3, 151, 104};
		Integer in[] = getRandomArray();
		testQuickSort(in);
		testHeapSort(in);
	}
	
	public static void testQuickSort(Integer[] in) {
		Integer[] out = new QuickSort<Integer>().quickSort(in);
		long start = System.currentTimeMillis();
		System.out.println("QuickSort");
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println();
		System.out.println(getTime(start));
	}
	
	public static void testMergeSort(Integer[] in) {
		Integer[] out = new MergeSort<Integer>().mergeSort(in);
		System.out.println("MergeSort");
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println();
	}
	
	public static void testHeapSort(Integer[] in) {
		Integer[] out = new HeapSort<Integer>().heapSort(in);
		long start = System.currentTimeMillis();
		System.out.println("HeapSort");
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println();
		System.out.println(getTime(start));
	}
	
	public static Integer[] getRandomArray() {
		int size = 10000;
		Integer in[] = new Integer[size];
		Random r = new Random();
		for(int i = 0;i<size;i++)
			in[i] = r.nextInt();
		return in;
	}
	
	public static long getTime(long start) {
		return System.currentTimeMillis() - start;
	}
	
}
