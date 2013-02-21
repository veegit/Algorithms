package com.vee.datastructures.sort;

import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

import com.vee.datastructures.sort.QuickSort.PIVOT;

public class Sort {
	
	public static void main(String args[]){
		//Integer in[] = {10, 1, 200, 31, 24, 118, 122, 3, 151, 104};
		//Integer in[] = getRandomArray();
		{	Integer in[] = {2,3,4};
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
		assignment();
		//testHeapSort(in);
		//testMergeSort(in);
	}
	
	public static void assignment() {
		  Scanner scanner;
		  Integer[] in = new Integer[10000];
		  int i = 0;
		  QuickSort<Integer> q = new QuickSort<Integer>();
		  try {
		    scanner = new Scanner(new InputStreamReader(q.getClass().
		    		getClassLoader().getResourceAsStream("QuickSort.txt")));
		    while(scanner.hasNextInt())
			   in[i++] = scanner.nextInt();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  for(PIVOT p : QuickSort.PIVOT.values()) {
				System.out.println("\n"+p.toString() + " Sort");
				Integer []out = new QuickSort<Integer>().quickSort(copy(in),p);
				boolean flag = true;
				for (i = 1; i < out.length; i++) {
					flag = out[i-1] <= out[i]? true:false;
					if(!flag) break;
				}
				System.out.println(flag + " " + i);
				assert flag:flag;
		  }
	}
	
	public static void testQuickSort(Integer[] in) {
		
		for(PIVOT p : QuickSort.PIVOT.values()) {
			System.out.println("\n"+p.toString() + " Sort");
			Integer []out = new QuickSort<Integer>().quickSort(copy(in),p);
			for (int i = 0; i < out.length; i++) {
				System.out.print(out[i] + " ");
			}
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
