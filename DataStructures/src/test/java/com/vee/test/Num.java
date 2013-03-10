package com.vee.test;

public class Num extends AlphaNum{
	private int value;
	
	public Num(int value, int position) {
		this.value = value;
		this.position = position;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
