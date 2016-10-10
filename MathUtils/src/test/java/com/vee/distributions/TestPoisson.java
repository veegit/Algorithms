package com.vee.distributions;


public class TestPoisson {
	public static void main(String args[]){
		Poisson poisson = new Poisson(3);
		for(int i = 0; i< 10; i++) {
			System.out.println(poisson.getRandomNumber());
		}
	}
}
