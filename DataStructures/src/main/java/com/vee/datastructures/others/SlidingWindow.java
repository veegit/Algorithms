package com.vee.datastructures.others;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SlidingWindow {

	public static void main(String args[]) {
		int a[] = new int[] { 2,1,5,4,3,6};
		new SlidingWindow().slidingwindowmin(a,3);
	}
	
	public void slidingwindowmin(int a[], int size) {
		Queue<Integer> window = new ArrayDeque<Integer>();
		List<Integer> minwindow = new ArrayList<Integer>();
		
		/*
		 * 1. Delete all elements from rear to front which are greater than 
		 *    encountered element
		 * 2. Add the element to minwindow
		 * 3. If the element removed from window is the front element on the
		 *    minwindow, then remove that element from minwindow
		 *    
		 * Note: needed a array instead of Queue for minwindow, since we
		 *    need to remove elements from behind which is not possible in 
		 *    queue
		 */
		int front=0, rear=-1;
		for (int i = 0; i < a.length; i++) {
			window.offer(a[i]);
			while(!minwindow.isEmpty() && minwindow.get(rear) > a[i])
				minwindow.remove(rear--);
			minwindow.add(a[i]);
			rear++;
			if(window.size() > size) {
				int e = window.poll();
				if(e == minwindow.get(front))
					front++;
			}
			System.out.println(minwindow.get(front));
		}
	}
}
