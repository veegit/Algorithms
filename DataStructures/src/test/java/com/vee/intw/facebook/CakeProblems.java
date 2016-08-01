package com.vee.intw.facebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;
import org.testng.internal.collections.Pair;

public class CakeProblems {

	public static void main(String[] args) {
	
	}
	
	@Test
	void mergeList(int a[], int b[]) {
		int i = 0,j = 0, k= 0;
		int[] c = new int[a.length + b.length];
		while(i < a.length || j < b.length) {
			if (a[i] < b[j]) {
				c[k++] = a[i++];
			} else {
				c[k++] = b[j++];
			}
		}
		File f = new File("");
		Set<String> s = new HashSet<String>();
		
		
		if (i == a.length) {
			while(j < b.length) {
				c[k++] = b[j++];
			}
		}
		if (j == b.length) {
			while(i < a.length) {
				c[k++] = a[i++];
			}
		}
		for (k = 0; k < c.length; k++) {
			System.out.println(c[k]);
		}
	}
	
	@Test
	List<Pair<String, String>> findDupeFiles(String dir) {
		List<Pair<String, String>> list = new ArrayList<>();
		Map <String, File> map= new HashMap<String, File>();
		File root = new File(dir);
		traverse(root.listFiles(), map, list); 
		return list;
	}

	/**
	 * 
	 * You left your computer unlocked and your friend decided to troll you by copying a lot of your files to random spots all over your file system.

Even worse, she saved the duplicate files with random, embarrassing names ("this_is_like_a_digital_wedgie.txt" was clever, I'll give her that).

Write a function that returns a list of all the duplicate files. We'll check them by hand before actually deleting them, since programmatically deleting files is really scary. To help us confirm that two files are actually duplicates, return a list of tuples ↴ where:

    the first item is the duplicate file
    the second item is the original file
    
    Only once duped
	 */
	private void traverse(File[] files, Map<String, File> map, List<Pair<String, String>> list) {
		for (File f : files) {
			if (f.isDirectory()) {
				traverse(f.listFiles(), map, list);
			} else {
				String md5 = calcMd5(f);
				if (map.containsKey(md5)) {
					list.add(createPair(map.get(md5), f));
					map.remove(md5);
				} else {
					map.put(md5, f);
				}
			}
		}
	}

	private String calcMd5(File f) {
		try(InputStream i = new FileInputStream(f)) {
			return DigestUtils.md5Hex(i);
		}
	}

	private Pair<String, String> createPair(File f1, File f2) {
		if (f1.lastModified() > f2.lastModified()) {
			return new Pair<String, String>(f2.getName(), f1.getName());
		} else {
			return new Pair<String, String>(f1.getName(), f2.getName());
		}
	}

	/**
	 * 
	 * We have a list of integers, where:
    The integers are in the range 0..n
    The list has a length of n+1
It follows that our list has at least one integer which appears at least twice. But it may have several duplicates, and each duplicate may appear more than twice.
Write a function which finds any integer that appears more than once in our list
	 */
	@Test
	public List<Integer> findRepeating(int[] a) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< a.length; i++) {
			while (a[a[i]] != a[i]) { 
				swap(a, i, a[i]);
			}
		}
		for (int i=0; i< a.length; i++) {
			if (a[i] != i) { 
				list.add(i);
			}
		}
		return list;
	}
	
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/**
	 * 
	 * Writing programming interview questions hasn't made me rich. Maybe trading Apple stocks will.
Suppose we could access yesterday's stock prices as a list, where:

The indices are the time in minutes past trade opening time, which was 9:30am local time.
The values are the price in dollars of Apple stock at that time.
So if the stock cost $500 at 10:30am, stock_prices_yesterday[60] = 500.

Write an efficient function that takes stock_prices_yesterday and returns the best profit I could have made from 1 purchase and 1 sale of 1 Apple stock yesterday.

For example:

  stock_prices_yesterday = [10, 7, 5, 8, 11, 9]

get_max_profit(stock_prices_yesterday)
# returns 6 (buying for $5 and selling for $11)

No "shorting"—you must buy before you sell. You may not buy and sell in the same time step (at least 1 minute must pass).
	 */
	@Test
	public int stockMaxProfit(int[] a) {
		int min = a[0];
		int max = a[0];
		int maxProfit = Integer.MIN_VALUE;
		int i = 1;
		while (i < a.length) {
			min = Math.min(a[i], min);
			max = min == a[i] ? max : Math.max(a[i], max);
			maxProfit = Math.max(maxProfit, max-min);
			i++;
		}
		return maxProfit;
	}
	
	/**
	You have a list of integers, and for each index you want to find the product of every integer except the integer at that index.
	Write a function get_products_of_all_ints_except_at_index() that takes a list of integers and returns a list of the products.
	For example, given:
	  [1, 7, 3, 4]
	your function would return:
	  [84, 12, 28, 21]
	by calculating:
	  [7*3*4, 1*3*4, 1*7*4, 1*7*3]
	Do not use division in your solution.**/ 
	@Test
	public int[] excludedProduct(int a[]) {
		int []b = new int[a.length];
		int []op1 = new int[a.length];
		int []op2 = new int[a.length];
		op1[0] = 1;
		op2[a.length-1] = 1;
		for (int i = 1; i < a.length; i++) {
			op1[i] = op1[i-1] * a[i-1];
		}
		for (int i = a.length-2; i >= 0; i--) {
			op2[i] = op2[i+1] * a[i+1];
		}
		for (int i=0; i < a.length; i++) {
			b[i] = op1[i] * op2[i];
		}
		return b;
	}
	
	/**
	Given a list_of_ints, find the highest_product you can get from three of the integers.
	The input list_of_ints will always have at least three integers.
	**/
	
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

	/**
	 *  Your company built an in-house calendar tool called HiCal. You want to add a feature to see the times in a day when everyone is available.
To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as tuples ↴ of integers (start_time, end_time). These integers represent the number of 30-minute blocks past 9:00am.
For example:
(2, 3) # meeting from 10:00 – 10:30 am
(6, 9) # meeting from 12:00 – 1:30 pm
Write a function condense_meeting_times() that takes a list of meeting time ranges and returns a list of condensed ranges.
For example, given:
  [(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)]
your function would return:
  [(0, 1), (3, 8), (9, 12)]
Do not assume the meetings are in order. The meeting times are coming from multiple teams.
Write a solution that's efficient even when we can't put a nice upper bound on the numbers representing our time ranges. Here we've simplified our times down to the number of 30-minute slots past 9:00 am. But we want the function to work even for very large numbers, like Unix timestamps. In any case, the spirit of the challenge is to merge meetings where start_time and end_time don't have an upper bound.
0,10  11,15  8,10 
	 */

	List<Pair<Long, Long>> mergeMeetings(Pair<Long, Long>[] timings) {
		Arrays.sort(timings, new Comparator<Pair<Long, Long>>() {
			@Override
			public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
				return Long.compare(o1.first(), o2.first());
			}
		});
		Stack<Pair<Long, Long>> s = new Stack<Pair<Long,Long>>();
		s.push(timings[0]);
		for (int i = 1; i < timings.length; i++) {
			Pair<Long, Long> top = s.peek();
			if (top.second() >= timings[i].first()) {
				Pair<Long, Long> p = new Pair<Long, Long>(top.first(), Math.max(top.second(), timings[i].second()));
				s.pop();
				s.push(p);
			} else {
				s.push(timings[i]);
			}
		}
		return s;
	}
	
	/**
	 * 
	 * calculate streaming median
	 * Hint : 2 heaps and always keep 2 heap size diff to atmost 1
	 */
	double streamingMedian(List<Integer> s) {
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
