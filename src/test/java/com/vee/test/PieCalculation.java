package com.vee.test;

/**
* Given that Pi can be estimated using the function 4 * (1 – 1/3 + 1/5 – 1/7 + …) 
* with more terms giving greater accuracy, 
* write a function that calculates Pi to an accuracy of 5 decimal places.
*/


public class PieCalculation {
	public static void main(String args[]) {
		System.out.print("\n"+calcPI(100000) + " " + Math.PI);
	}
	
	public static double calcPI (int detail) {
		double result = 0;
		int sign  = 1;
		for (int i = 0; i < detail; i++) {
			result = result + (sign/(2D*i+1));
			sign*=-1;
			//System.out.print((int)Math.pow(-1, i) + "/" + (int)(2.0*i+1) + " ");
		}
		return result * 4;
	}
	
}
