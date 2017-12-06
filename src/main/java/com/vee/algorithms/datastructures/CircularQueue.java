package com.vee.algorithms.datastructures;
public class CircularQueue {
	private static int BUFFERSIZE = 6;
	
	String[] buffer;
	private int front = -1;
	private int rear = -1;
	private int size = 0;
	private int maxSize;
	boolean blocking = false;
	
	public CircularQueue() {
		this(BUFFERSIZE);
	}
	
	public CircularQueue(int maxSize) {
		this.maxSize = maxSize;
		buffer = new String[maxSize];
	}
	
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}
	
	public boolean isFull() {
		if(size == maxSize)
			return true;
		return false;
	}
	
	public void enqueue(String s) {
		if(isEmpty())
			front = 0;
		if(!isFull()) {
			rear = (rear + 1)% maxSize;
			buffer[rear] = s;
			size++;
		}
	}
	
	public String dequeue() {
		if(!isEmpty()) {
			String top = buffer[front]; 
			size--;
			buffer[front] = null;
			front = (front+1)% maxSize;
			return top;
		}
		return null;
	}
	
	public void display() {
		System.out.println(String.format("Size= %d, Front= %d, Rear = %d" , size,front,rear));
		int count = 0;
		if(isEmpty())
			return;
		for (int i = front;  ;i = (i+1)% maxSize) {
			System.out.println(buffer[i]);
			if(count++ == size-1)
				break;
		}
	}
	
	public int size() {
		return size;
	}
	
	public static void main(String[] args) {
		CircularQueue q = new CircularQueue();
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.enqueue("g");
		q.dequeue();
		q.enqueue("h");
		q.dequeue();
		q.display();
	}
}
