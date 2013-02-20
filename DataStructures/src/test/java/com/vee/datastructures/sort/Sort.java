package com.vee.datastructures.sort;

import java.util.Random;

public class Sort {
	
	public static void main(String args[]){
		//Integer in[] = {10, 1, 200, 31, 24, 118, 122, 3, 151, 104};
		//Integer in[] = getRandomArray();
		{	Integer in[] = {5,3,4,2};
			testQuickSort(in);
		}
		{	Integer in[] = {10, 1, 200, 31, 24, 118, 122, 3, 151, 104};
			testQuickSort(in);
		}
		{	Integer in[] = {6,5,4,2};
			testQuickSort(in);
		}
		{	Integer in[] = {1,10,100,1000};
			testQuickSort(in);
		}
		//testHeapSort(in);
		//testMergeSort(in);
	}
	
	public static void testQuickSort(Integer[] in) {
		System.out.println("\nFirst QuickSort");
		Integer []out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.FIRST);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nFirst 2 QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.FIRST_2);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nLast QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.LAST);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nMiddle QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.MIDDLE);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nMiddle 2 QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.MIDDLE_2);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nMedian QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.MEDIAN);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println("\nRandom QuickSort");
		out = new QuickSort<Integer>().quickSort(copy(in),QuickSort.PIVOT.RANDOM);
		for (int i = 0; i < out.length; i++) {
			System.out.print(out[i] + " ");
		}
		System.out.println();
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
	
	public static Integer[] copy(Integer[] in) {
		Integer out[] = new Integer[in.length];
		for (int i = 0; i < in.length; i++) {
			out[i] = in[i];
		}
		return out;
	}
	
}
