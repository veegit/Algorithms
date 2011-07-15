package com.vee.test;

public class Tuple<M,N> {
	public M head;
	public N tail;
	
	public Tuple(M head,N tail) {
		this.head = head;
		this.tail = tail;
	}
}
