package com.vee.datastructures.others;

public class Duplicates {
	private final static int MAX = 5;
	int array[];
	
	Duplicates(int array[]) {
		this.array = array;
		dupes();
	}
	
	private void dupes() {
		int[] out = new int[MAX+1];
		for(int i = 0; i < out.length;i++)
			out[i] = 0;
		for(int i = 0; i < array.length;i++)
			out[array[i]]++;
		for(int i = 0; i < out.length;i++)
			if(out[i] > 1)
				System.out.println(i);
	}
	
	public static void main(String[] args) {
		int array[] = {0,3,1,2,4,1,2,3,4,5,1,2,3,4,1,2};
		new Duplicates(array);
	}
}
