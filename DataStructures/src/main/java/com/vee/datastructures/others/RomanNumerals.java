package com.vee.datastructures.others;

public class RomanNumerals {

	public enum ROMAN {
		I (1),
		IV (4),
		V (5), 
		IX (9),
		X (10),
		XL (40),
		L (50),
		XC (90),
		C (100),
		CD (400),
		D (500),
		CM (900),
		M (1000);
		
		int value;
		
		ROMAN(int value) {
			this.value = value;
		}
	}
	
	public static void main(String[] args) {
		
		ROMAN[] array = ROMAN.values();
		int n = 1404;
		while(n >0)
			for(int i = array.length-1; i>=0; i--) {
				ROMAN r = array[i];
				int q = n/r.value;
				if(q > 0) {
					n = n - r.value;
					System.out.println(r.name());
					break;
				}
			}
	}

}
