package com.vee.algorithms.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Tracker {

	public static void main(String[] args) {
		AsyncTracker tracker = AsyncTracker.getAsyncTracker();
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(tracker);
		for (int i = 0; i < 51; i = i + 10) {
			tracker.track(i);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tracker.track(1);
	}
}

class MyRunnable implements Runnable {
	private final long countUntil;

	MyRunnable(long countUntil) {
		this.countUntil = countUntil;
	}

	public void run() {
		long sum = 0;
		for (long i = 1; i <= 10; i++) {
			sum += countUntil;
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(sum);
	}
}

class AsyncTracker implements Runnable {
	private static AsyncTracker asyncTracker = new AsyncTracker();
	private ExecutorService executor;

	private AsyncTracker() {
	}

	public static AsyncTracker getAsyncTracker() {
		return asyncTracker;
	}

	public void track(int i) {
		Runnable worker = new MyRunnable(i);
		executor.execute(worker);
	}

	public void run() {
		long start = System.currentTimeMillis();
		executor = Executors.newFixedThreadPool(10);
		/*
		 * // This will make the executor accept no new threads // and finish
		 * all existing threads in the queue executor.shutdown(); // Wait until
		 * all threads are finish while (!executor.isTerminated()) {
		 *
		 * }
		 */
		System.out.println("Finished in " + (System.currentTimeMillis() - start));

	}
}
