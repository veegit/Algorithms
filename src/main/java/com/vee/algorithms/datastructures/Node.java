package com.vee.algorithms.datastructures;

public class Node<M extends Object> {
	
	protected M data;
	private Node<M> link;
	
	public M getData() {
		return data;
	}
	public void setData(M data) {
		this.data = data;
	}
	public Node<M> getLink() {
		return link;
	}
	public void setLink(Node<M> link) {
		this.link = link;
	}
	
	public Node() {
		
	}
	
	@Override
	public String toString() {
		if (data == null) {
			return "";
		} else {
			return data.toString();
		}
	}

}