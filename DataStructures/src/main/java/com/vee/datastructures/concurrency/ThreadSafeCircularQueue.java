package com.vee.datastructures.concurrency;

import com.vee.datastructures.CircularQueue;

public class ThreadSafeCircularQueue {
  private final Object lock = new Object();
  private final CircularQueue queue;
  private final int maxSize;
 
  public ThreadSafeCircularQueue(int maxSize) {
    this.queue = new CircularQueue(maxSize);
    this.maxSize = maxSize;
  }
 
  public void enqueue(String value) {
    synchronized (lock) {
      while (size() == maxSize) {
        waitForNotification();
      }
      queue.enqueue(value);
      lock.notifyAll();
    }
  }
 
  private synchronized void waitForNotification() {
    try {
    	lock.wait();
    } catch (InterruptedException e) {
      
    }
  }
 
  public String dequeue() {
    synchronized (lock) {
      while (isEmpty()) {
        waitForNotification();
      }
 
      String value = queue.dequeue();
      lock.notifyAll();
      return value;
    }
  }
  
  public int size() {
	  return queue.size();
  }
  
  private boolean isEmpty() {
	  return queue.isEmpty();
  }
}