package com.vee.test;
/**
 * if a building has 25 floors-the elevator will show-
1 	1
2 	2
3 	3
4 	5
5 	6
6 	7
7 	8
8 	9
9 	10
10 	11
11 	12
12 	13
13 	15
14 	16
15 	17
16 	18
17 	19
18 	20
19 	21
20 	22
21 	23
22 	25
23 	26
24 	27
25 	28
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
