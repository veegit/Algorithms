package com.vee.datastructures.concurrency;
public class ThreadSafeQueue {
	private final Object lock = new Object();
	private static int BUFFERSIZE = 6;

	String[] buffer;
	private int front = -1;
	private int rear = -1;
	private int size = 0;
	private int maxSize;

	public ThreadSafeQueue() {
		this(BUFFERSIZE);
	}

	public ThreadSafeQueue(int maxSize) {
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
		synchronized (lock) {
			while (isFull()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					
				}
			}
			if(isEmpty())
				front = 0;
			if(!isFull()) {
				rear = (rear + 1)% maxSize;
				buffer[rear] = s;
				size++;
			}
			lock.notifyAll();
		}
	}

	public String dequeue() {
		synchronized (lock) {
			while (isEmpty()) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					
				}
			}
			String top = buffer[front];
			size--;
			buffer[front] = null;
			front = (front + 1) % maxSize;
			lock.notifyAll();
			return top;
		}
	}

	public int size() {
		return size;
	}
	
	/* Not a thread safe operation and expensive to make threadsafe*/
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
	
	public static void main(String[] args) {
		ThreadSafeQueue q = new ThreadSafeQueue(10);
		q.enqueue("a");
		q.enqueue("b");
		q.enqueue("c");
		q.enqueue("d");
		q.enqueue("e");
		q.enqueue("f");
		q.display();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.display();
		q.enqueue("g");
		q.dequeue();
		q.enqueue("h");
		q.dequeue();
		q.display();
	}
}