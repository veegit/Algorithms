package com.vee.intw.google;
/**
 * Imagine a building where there are no such floor numbers which have 4 in any
 * of their digits. Find the floor number you would find in such a building 
 * given an actual floor number
 * e.g if a building has 25 floors-the elevator will show-
 * 25 	28
 * @author Narayanan
 *
 */

public class Floors {
	static int NUM = 4;
	static int cnt = 0;
	
	public static void main(String args[]) {
		long start = System.currentTimeMillis();
		getElevatorNum(1000000);
		System.out.println(System.currentTimeMillis() - start);
	}
	
	static void getElevatorNum(int a) {
		int n = 1;
		for(int i = 2; i < a+1; i++) {
			n = n + check(n + check(n));
			System.out.println(i + " " + n);
		}	
	}
	
	static int check(int i) {
		int digits = 0;
		  while(i>0) {
		    int r = i%10;
		    digits++;
		    if(r == NUM) {
		    	return (int) Math.pow(10,digits-1) + 1;
			}
			i /= 10;
		  }
		  return 1;
	}
}
