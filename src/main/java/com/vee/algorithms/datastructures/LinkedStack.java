package com.vee.algorithms.datastructures;

public class LinkedStack<M extends Object> {
	private Node<M> top;
	private int size = -1;
	
	public int getSize() {
		return size;
	}

	public LinkedStack() {
		size = 0;
	}
	
	public boolean isEmpty() {
		if (top == null)
			return true;
		return false;
	}
	
	public Node<M> getTop() {
		if(isEmpty()) 
			return null;
		return top;
	}
	
	public void display() {
		Node<M> n = new Node<M>();
		n = top;
		while(n.getLink() != null) {
			System.out.print(n.getData() + " ");
			n = n.getLink();
		}
		System.out.println(n.getData());
	}
	
	public void pushNode(Node<M> newNode) {
		if(isEmpty()) {
			top = newNode;
			size = 1;
			return;
		}
		newNode.setLink(top);
		top = newNode;
		size++;
	}
	
	public Node<M> popNode() {
		if(isEmpty()) 
			return null;
		Node<M> oldNode = top;
		top = top.getLink();
		size--;
		return oldNode;
	}
	
	public void push(M data) {
		Node<M> node = new Node<M>();
		node.setData(data);
		if(isEmpty()) {
			top = node;
			size = 1;
			return;
		}
		node.setLink(top);
		top = node;
		size++;
	}
	
	public M pop() {
		if(isEmpty()) 
			return null;
		Node<M> oldNode = top;
		top = top.getLink();
		size--;
		return oldNode.getData();
	}
	
	public static void main(String args[]) {
		LinkedStack<Integer> ll = new LinkedStack<Integer>();
		Node<Integer> n1 = new Node<Integer>();n1.setData(1);
		Node<Integer> n2 = new Node<Integer>();n2.setData(2);
		Node<Integer> n3 = new Node<Integer>();n3.setData(3);
		Node<Integer> n4 = new Node<Integer>();n4.setData(4);
		
		ll.pushNode(n1);
		ll.pushNode(n2);
		ll.pushNode(n3);
		ll.popNode();
		ll.pushNode(n4);
		
		ll.display();
	}
}

