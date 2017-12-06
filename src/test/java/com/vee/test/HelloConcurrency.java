package com.vee.test;

/**
 *      @author vee
 */

public class HelloConcurrency {

	public static void main(String args[]) {
		try {
			run1();
			System.out.println("\n\n------------\n\n\n");
			run2();
		} catch (Exception e) {
		}
	}

	static void run1() throws Exception {
		HelloWorldRunnable h1 = new HelloWorldRunnable(1);
		HelloWorldRunnable h2 = new HelloWorldRunnable(2);

		Thread t1 = new Thread(h1);
		Thread t3 = new Thread(h1);
		t1.start();
		t3.start();
		t1.join();
		t3.join();
	}

	static void run2() {
		HelloWorldVolRunnable h1 = new HelloWorldVolRunnable(1);
		HelloWorldVolRunnable h2 = new HelloWorldVolRunnable(2);

		Thread t1 = new Thread(h1);
		Thread t3 = new Thread(h1);
		t1.start();
		t3.start();
	}
}

class HelloWorldRunnable implements Runnable {
	private int count = 0;
	private int id;

	public HelloWorldRunnable(int id) {
		this.id = id;
	}

	public void run() {
		try {
			while (count++ < 10) {
				System.out.println("Id:" + id + " " + count + " Hello World");
				Thread.sleep(100);
			}
		} catch (Exception e) {
		}
	}
}

class HelloWorldVolRunnable implements Runnable {
	private int count = 0;
	private int id;

	public HelloWorldVolRunnable(int id) {
		this.id = id;
	}

	public void run() {
		try {
			while (count++ < 10) {
				System.out.println("Id:" + id + " " + count + " Hello World");
				Thread.sleep(100);
			}
		} catch (Exception e) {
		}
	}
}

/**
 * STANDARD Id:1 2 Hello World Id:1 1 Hello World Id:1 3 Hello World Id:1 3
 * Hello World Id:1 4 Hello World Id:1 4 Hello World Id:1 5 Hello World Id:1 6
 * Hello World Id:1 7 Hello World Id:1 8 Hello World Id:1 9 Hello World Id:1 10
 * Hello World
 *
 *
 *
 *
 * VOLALTILE Id:1 1 Hello World Id:1 2 Hello World Id:1 3 Hello World Id:1 3
 * Hello World Id:1 4 Hello World Id:1 5 Hello World Id:1 6 Hello World Id:1 7
 * Hello World Id:1 8 Hello World Id:1 9 Hello World Id:1 10 Hello World
 *
 *
 *
 **/

