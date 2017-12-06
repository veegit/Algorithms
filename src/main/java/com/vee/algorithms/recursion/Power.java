package com.vee.algorithms.recursion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Power {

	public double pow(int a, int b) {
		if (b < 0) {
			return 1D/pow(a, b*-1);
		}
		if(b == 1) {
			return a;
		}
		if (b%2 == 1) {
			return a*pow(a,b-1);
		} else {
			return pow(a*a, b-1);
		}
	}

	@Test
	public void testPow() {
		assertEquals(216, pow(6,3), 0.001);
		assertEquals(1D/216.0, pow(6,-3), 0.001);
	}
}
