package com.vee.algorithms.others;

public class DiceRoll {


	public static int roll(double prob_dist[]) {
		double val = Math.random();
		double sum = 0;
		int i = 0;
		while(sum < val) {
			sum += prob_dist[i++];
		}
		return i-1;	
	}
	
	public static int roll(int n) {
		double val = Math.random();
		double sum = 0;
		int i = 0;
		while(sum < val) {
			sum += (1d/n);
			i++;
		}
		return i-1;
	}
	
	public static void main(String[] args) {
		int n = 6;
		double arr[] = {0.1,0.1,3d/n,0.1,0.1,0.1};
		for(int i = 0; i< 100; i++)
			System.out.println(roll(arr));
	}

}
