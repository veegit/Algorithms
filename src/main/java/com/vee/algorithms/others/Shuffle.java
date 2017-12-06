package com.vee.algorithms.others;
import java.util.ArrayList;
import java.util.Random;


public class Shuffle {
	private final static int MAX = 5;
	ArrayList<Integer> srcArray = new ArrayList<Integer>();
	int n;
	
	Shuffle(int n) {
		this.n = n;
		for(int i = 0; i < n; i++)
			srcArray.add(i);
	}
	
	int[] randomize() {
		int []array = new int[n];
		for(int i = n-1; i >= 0; i--) {
			int index = new Random().nextInt(srcArray.size());
			array[i] = srcArray.get(index);
			srcArray.remove(index);
		}
		return array;
	}
	
	public static void main(String[] args) {
		int[] array = new Shuffle(10).randomize();
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);		
	}
}
