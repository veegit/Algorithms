package com.vee.algorithms.problems.cake;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class CakeMaxProdSubArray {
	/**
	Given a list_of_ints, find the highest_product you can get from three of the integers.
	The input list_of_ints will always have at least three integers.
	**/
	//FIXME
	public int productOf3Integers(int[] a) {
		int size = 3;
		Queue<Integer> minQ = new PriorityQueue<Integer>();
		Queue<Integer> maxQ = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o2, o1);
			}
		});
		for (int i = 0; i < a.length; i++) {
			if (minQ.size() < size) {
				minQ.offer(a[i]);
			} else {
				if (minQ.peek() < a[i]) {
					minQ.poll();
					minQ.offer(3);
				}
			}
			if (maxQ.size() < size) {
				maxQ.offer(a[i]);
			} else {
				if (maxQ.peek() > a[i]) {
					maxQ.poll();
					maxQ.offer(a[i]);
				}
			}
		}
		int max = minQ.poll();
		return Math.max(max * minQ.poll() * minQ.poll(), max * maxQ.poll() * maxQ.poll());
	}
}
