package com.vee.datastructures;

public class Node<M extends Object> {
	
	protected M data;
	private Node<M> link;
	
	public M getData() {
		return data;
	}
	public void setData(M data) {
		this.data = data;
	}
	Node<M> getLink() {
		return link;
	}
	void setLink(Node<M> link) {
		this.link = link;
	}
	
	public Node() {
		
	}

}
