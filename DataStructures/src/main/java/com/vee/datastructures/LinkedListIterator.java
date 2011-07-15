package com.vee.datastructures;

public class LinkedListIterator<M extends Object> {
	
	Node<M> current;
	public LinkedListIterator(Node<M> node) {
		current = node;
	}
	
	public boolean hasNext() {
		return current != null && current.getLink() != null;
	}
	
	public Node<M> getNextNode() {
		current =  current.getLink();
		return current;
	}
	
	public M getNext() {
		current =  current.getLink();
		return current.getData();
	}
}
