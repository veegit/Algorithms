package com.vee.datastructures;

public class LinkedList {
	private Node header;
	
	LinkedList() {
		header = new Node();
	}
	
	public boolean isEmpty() {
		if (header == null)
			return true;
		if (header.getLink() == null)
			return true;
		return false;
	}
	public void insertAfter(int data, int after) {
		if(isEmpty()) {
			insertFirst(data);
			return;
		}
		Node newNode = new Node();
		newNode.setData(data);
		Node prevNode = find(after);
		if(prevNode == null) 
			return;
		newNode.setLink(prevNode.getLink());
		prevNode.setLink(newNode);
	}
	
	public void insertFirst(int data) {
		Node newNode = new Node();
		newNode.setData(data);
	    newNode.setLink(header.getLink());
		header.setLink(newNode);
	}
	
	public void insertEnd(int data) {
		if(isEmpty()) {
			insertFirst(data);
			return;
		}
		Node newNode = new Node();
		newNode.setData(data);
		Node last = findLast();
		last.setLink(newNode);
	}
	
	public void remove(int data) {
		if(isEmpty()) 
			return;
		Node prevNode = findPrev(data);
		if(prevNode == null) 
			return;
		Node currentNode = prevNode.getLink();
		prevNode.setLink(currentNode.getLink());
	}
	
	public Node find(int data) {
		Node it = header.getLink();
		while(it != null) {
			if(it.getData() == data)
				return it;
			it = it.getLink();
		}
		System.err.println("Cant find " + data);
		return null;
		
	}
	public Node findPrev(int data) {
		Node it = header.getLink();
		Node prev = header;
		while(it != null) {
			if(it.getData() == data)
				return prev;
			prev = it;
			it = it.getLink();
		}
		System.err.println("Cant find " + data);
		return null;
		
	}
	
	public Node findLast() {
		Node it = header.getLink();
		while(it.getLink() != null) {
			it = it.getLink();
		}
		return it;
		
	}
	
	public void display() {
		Node n = new Node();
		n = header.getLink();
		while(n.getLink() != null) {
			System.out.println(n.getData());
			n = n.getLink();
		}
		System.out.println(n.getData());
	}
	
	public static void main(String args[]) {
		LinkedList ll = new LinkedList();
		ll.insertEnd(10);
		ll.insertEnd(11);
		ll.insertEnd(12);
		ll.insertEnd(13);
		ll.insertFirst(9);
		ll.insertEnd(16);
		ll.insertAfter(15, 13);
		ll.remove(21);
		
		ll.display();
	}
}
