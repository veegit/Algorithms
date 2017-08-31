package com.vee.datastructures.tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class MedianMaintenance {

	/**
	 * The goal of this problem is to implement the "Median Maintenance"
	 * algorithm (covered in the Week 5 lecture on heap applications). The text
	 * file contains a list of the integers from 1 to 10000 in unsorted order;
	 * you should treat this as a stream of numbers, arriving one by one.
	 * Letting xi denote the ith number of the file, the kth median mk is
	 * defined as the median of the numbers x1...,xk. (So, if k is odd, then mk
	 * is ((k+1)/2)th smallest number among x1,..,xk; if k is even, then mk is
	 * the (k/2)th smallest number among x1,..,xk.)
	 * 
	 * In the box below you should type the sum of these 10000 medians, modulo
	 * 10000 (i.e., only the last 4 digits). That is, you should compute
	 * (m1+m2+m3+..+m10000)mod10000.
	 * 
	 * OPTIONAL EXERCISE: Compare the performance achieved by heap-based and
	 * search-tree-based implementations of the algorithm.
	 * 
	 * Answer = 1213
	 */
	final static String FILENAME = 
			"/home/narayanan/workspace/data/MedianMaintenance.txt";
	
	void treeBased() {
		long sum = 0, cnt =0;
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		try {
			Scanner scan = new Scanner(new File(FILENAME));
			while(scan.hasNextInt()) {
				map.put(scan.nextInt(), 1);
				Integer[] arr = (Integer[]) map.keySet().toArray(new Integer[0]);
				int index = Math.round(arr.length/2f);
				sum = sum + arr[index-1];
				cnt++;
			}
			System.out.println(cnt + " " + sum % cnt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new MedianMaintenance().treeBased();
	}
	
	void heapBased() {
		long sum = 0, cnt =0;
		try {
			Scanner scan = new Scanner(new File(FILENAME));
			Queue<Integer> minHeap = new PriorityQueue<Integer>();
			Queue<Integer> maxHeap = new PriorityQueue<Integer>((Integer i1, Integer i2) -> i2.compareTo(i1));
			int median;
			while(scan.hasNextInt()) {
				int num = scan.nextInt();
				Integer max = maxHeap.peek();
				Integer min = minHeap.peek();
				boolean smallerThanMax = max == null || max - num > 0;
				boolean largerThanMin = min == null || num - min > 0;
				int diff = maxHeap.size() - minHeap.size();
				if (diff >= 0) {
					if (largerThanMin) {
						minHeap.add(num);
					} else if (smallerThanMax) {
						minHeap.offer(maxHeap.poll());
						maxHeap.add(num);
					} else {
						minHeap.add(num);
					}
				} else if (diff < 0) {
					if (smallerThanMax) {
						maxHeap.add(num);
					} else if (largerThanMin) {
						maxHeap.offer(minHeap.poll());
						minHeap.add(num);
					} else {
						maxHeap.add(num);
					}
				}
				diff = maxHeap.size() - minHeap.size();
				if (diff == 0) {
					median = (maxHeap.peek() + minHeap.peek())/2;
				} else if (diff <0) {
					median = minHeap.peek();
				} else {
					median = maxHeap.peek();
				}
				System.out.println(median);
			}
			System.out.println(cnt + " " + sum % cnt);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
