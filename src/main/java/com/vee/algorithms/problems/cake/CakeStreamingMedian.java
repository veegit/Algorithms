package com.vee.algorithms.problems.cake;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class CakeStreamingMedian {
	/**
	 * 
	 * calculate streaming median
	 * Hint : 2 heaps and always keep 2 heap size diff to atmost 1
	 */
	
	@Test
	public void test() {
		System.out.println(Arrays.asList(2,3,4,1,5,8,7,9));
	}

	public double streamingMedian(List<Integer> s) {
		Queue<Integer> minQ = new PriorityQueue<Integer>();
		Queue<Integer> maxQ = new PriorityQueue<Integer>();
		Iterator<Integer> it = s.iterator();
		int first = it.next();
		int second = it.next();
		minQ.add(Math.max(first, second));
		maxQ.add(Math.min(first, second));
		while (it.hasNext()) {
			int i = it.next();
			if (minQ.peek() > i) {
				minQ.add(i);
			} else {
				maxQ.add(i);
			}
			int sizeDiff = maxQ.size() - minQ.size();
			if (sizeDiff > 1) {
				int val = maxQ.remove();
				minQ.add(val);
			} else if (sizeDiff < -1) {
				int val = minQ.remove();
				maxQ.add(val);
			}
		}
		int sizeDiff = maxQ.size() - minQ.size();
		if (sizeDiff == 0) {
			return (maxQ.peek() + minQ.peek()) / 2;
		} else if (sizeDiff > 0) {
			return maxQ.peek();
		} else {
			return minQ.peek();
		}
	}
}
