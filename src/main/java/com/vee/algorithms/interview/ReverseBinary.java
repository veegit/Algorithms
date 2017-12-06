package com.vee.algorithms.interview;

import java.util.Scanner;

//interview = spotify
public class ReverseBinary {
	
	public static int reverse(int a) {
		int y = 0;
		while(a>0) {
			int x = 1 & a;
			y = x + (y << 1);
			a >>= 1;
		}
		return y;
	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println(reverse(n));
	}
}
