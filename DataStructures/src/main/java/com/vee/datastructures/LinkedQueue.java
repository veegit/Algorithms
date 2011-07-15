package com.vee.datastructures;

public class LinkedQueue<M extends Object> {
	private Node<M> front;
	private Node<M> rear;
	private int size = -1;
	
	public int getSize() {
		return size;
	}

	public LinkedQueue() {
		size = 0;
	}
	
	public boolean isEmpty() {
		if (rear == null)
			return true;
		return false;
	}
	
	public M getFront() {
		if(isEmpty()) 
			return null;
		return front.getData();
	}
	
	public M getRear() {
		if(isEmpty()) 
			return null;
		return rear.getData();
	}
	
	public void display() {
		Node<M> n = new Node<M>();
		n = front;
		while(n.getLink() != null) {
			System.out.print(n.getData() + " ");
			n = n.getLink();
		}
		System.out.println(n.getData());
	}
	
	public void enqueue(M data) {
		Node<M> node = new Node<M>();
		node.setData(data);
		if(isEmpty()) {
			rear = node;
			front = rear;
			size = 1;
			return;
		}
		rear.setLink(node);
		rear = node;
		size++;
	}
	
	public M dequeue() {
		if(isEmpty()) 
			return null;
		Node<M> oldNode = front;
		front = front.getLink();
		size--;
		if(front == null)
			rear = null;
		return oldNode.getData();
	}
	
	public static void main(String args[]) {
		LinkedQueue<Integer> ll = new LinkedQueue<Integer>();
		ll.enqueue(1);
		System.out.println(ll.getFront() +" " + ll.getRear());
		ll.enqueue(2);
		ll.enqueue(3);
		ll.dequeue();
		ll.enqueue(4);
		System.out.println(ll.getFront() +" " + ll.getRear());
		ll.dequeue();
		System.out.println(ll.getFront() +" " + ll.getRear());
		ll.dequeue();
		System.out.println(ll.getFront() +" " + ll.getRear());
		ll.enqueue(10);
		System.out.println(ll.getFront() +" " + ll.getRear());	
	}
}

