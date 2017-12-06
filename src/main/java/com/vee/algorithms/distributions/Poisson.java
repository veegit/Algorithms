package com.vee.algorithms.distributions;

public class Poisson implements RandomDistribution<Integer>{
	private int lambda;
	
	public Poisson(int lambda){
		this.lambda = lambda;
	}

	@Override
	public Integer getRandomNumber() {
		double L = Math.pow(Math.E, lambda * -1);
		int k = 0;
		double p = 1;
		do {
			k++;
			p = p * Math.random();
		} while (p > L);
		return k - 1;
	}
	
}
