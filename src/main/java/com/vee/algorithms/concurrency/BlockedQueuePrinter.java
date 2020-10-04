package com.vee.algorithms.concurrency;

/**
 * @author
 *
 */

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockedQueuePrinter implements IPrinter {

	Queue<String> buffer;
	private int bufferSize = 20;
	private final Thread dispatcher;
	boolean blocking = true;
	boolean closed = true;

	BlockedQueuePrinter() {
		buffer = new LinkedBlockingQueue<String>(bufferSize);
		dispatcher = new Thread(new Dispatcher(this,buffer));
		dispatcher.setDaemon(true);
		dispatcher.setName("AsynBlockedQueuePrinter-"+dispatcher.getId());
		dispatcher.start();
	}

	public void print(String str) {
		while(true) {
			if(((LinkedBlockingQueue<String>) buffer).remainingCapacity() > 0) {
				buffer.add(str);
				break;
			}
		}
	}

	private static class Dispatcher implements Runnable {

		IPrinter parentPrinter;
		Queue<String> buffer;

		Dispatcher(IPrinter p,Queue<String> buffer) {
			parentPrinter = p;
			this.buffer = buffer;
		}

		public void run() {
			while(true) {
				if(!buffer.isEmpty()) {
					//FIXME track discarded events

					//Process Event
					process(buffer.poll());
				}
			}
		}

		void process(String str) {
			System.out.println(str);
			fib(Integer.parseInt(str.split("-")[1]));
		}

		public int fib(int n) {
			if(n<=1) {
				return n;
			}
			else{
				return fib(n-1)+fib(n-2);
			}
		}
	}

	public void close() {
		buffer.notifyAll();
		try {
			dispatcher.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void finalize() {
		close();
	}

	//FIXME setBufferSize
	public void setBufferSize() {

	}
}
