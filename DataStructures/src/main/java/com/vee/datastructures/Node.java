package com.vee.datastructures;

public class Node {
	
	protected int data;
	private Node link;
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	Node getLink() {
		return link;
	}
	void setLink(Node link) {
		this.link = link;
	}
	
	public Node() {
		
	}

}
