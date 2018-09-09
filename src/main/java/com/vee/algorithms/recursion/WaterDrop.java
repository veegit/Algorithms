package com.vee.algorithms.recursion;

import java.util.Arrays;

public class WaterDrop {

	public static void main(String[] args) {
		//new HelloWorld().print(new int[] {5,1,2,0,3,1,6,1});
		new WaterDrop().drop(new int[] {4,3,2,3}, 3, 1);
		new WaterDrop().drop(new int[] {5,1,2,3,1,6,1}, 5, 3);
		new WaterDrop().dropNPrint(new int[] {5,1,2,3,1,6,1}, 13, 3);
	}
	
	public void print(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i : a) {
			max = Math.max(i, max);
		}
		for (int i = max; i > 0; i--) {
			int val = i;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == val) {
					System.out.print("X");
					a[j]--;
				} else {
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}
	
	public void dropNPrint(int[] a, int drops, int pos) {
		int[] w = drop(a, drops, pos);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < w.length;  i++) {
			max = Math.max(w[i], max);
		}
		for (int i = max; i > 0; i--) {
			int val = i;
			for (int j = 0; j < w.length; j++) {
				if (w[j] == val && w[j]-a[j] > 0) {
					System.out.print("W");
					w[j]--;
				} else if (w[j] == val) {
					System.out.print("X");
					w[j]--;
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	
	public int[] drop(int[] a, int drops, int pos) {
		int[] w = Arrays.copyOf(a, a.length);
		for (int i = 0; i < drops; i++) {
			int posl = findValley(w, pos, w[pos],-1)+1;
			int posr = findValley(w, pos, w[pos], 1)-1;
			int newPos = pos;
			if (w[posl] < w[pos]) {
				newPos = posl;
			} else if (w[posr] < w[pos]) {
				newPos = posr;
			}
			w[newPos]++;
		}
		System.out.println(Arrays.toString(w));
		return w;
	}
	
	public int findValley(int []a, int pos, int prevVal, int increment) {
		if (pos == 0 || pos == a.length || a[pos] > prevVal) {
			return pos;
		}
		return findValley(a, pos+increment, a[pos], increment);
	}
}
