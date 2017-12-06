package com.vee.algorithms.sort;

public abstract class BaseSort<M> {

	protected void exchange(M[] array, int i, int j) {
		M temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
