package com.vee.test;

public class Alpha extends AlphaNum {
	private String value;
	
	public Alpha(String value, int position) {
		this.value = value;
		this.position = position;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
