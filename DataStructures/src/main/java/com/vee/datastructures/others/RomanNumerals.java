package com.vee.datastructures.others;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
	
	public String toRoman(int n) {
		String roman = "";
		ROMAN[] array = ROMAN.values();
		while(n >0)
			for(int i = array.length-1; i>=0; i--) {
				ROMAN r = array[i];
				int q = n/r.value;
				if(q > 0) {
					n = n - r.value;
					roman+= r.name();
					break;
				}
			}
		return roman;
	}
	
	@Test
	public void testValid() {
		assertEquals("X", toRoman(10) );
		assertEquals("VIII", toRoman(8) );
		assertEquals("MCMLIV", toRoman(1954));
		assertEquals("DCCCIX", toRoman(809));
	}

}
