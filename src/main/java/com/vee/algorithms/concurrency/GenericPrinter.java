package com.vee.algorithms.concurrency;

import java.util.ArrayList;
import java.util.List;

public class GenericPrinter implements IPrinter{

	List<String> buffer;
	private int bufferSize = 20;
	private final Thread dispatcher;
	boolean blocking = false;
	boolean closed = true;

	GenericPrinter() {
		buffer = new ArrayList<String>(bufferSize);
		dispatcher = new Thread(new Dispatcher(this,buffer));
		dispatcher.setDaemon(true);
		dispatcher.setName("AsynGenericPrinter-"+dispatcher.getId());
		dispatcher.start();
	}

	public void print(String str) {
		synchronized (buffer) {
			while(true) {
				int prevSize = buffer.size();
				if(prevSize < bufferSize) {
					buffer.add(str);
				/*	if buffer had been empty
					signal all threads waiting on buffer
					to check their conditions.
					ie a new event is added to process*/
					if(prevSize == 0) {
						buffer.notifyAll();
					}
					break;
				}
			}
			// Following code is only reachable if buffer is full

			/*
			 * if blocking then wait for a buffer notification
			 * TODO Check Thread  interrupt and isDispatcher
			 */
			if(blocking) {
				try {
					buffer.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static class Dispatcher implements Runnable {

		IPrinter parentPrinter;
		List<String> buffer;

		Dispatcher(IPrinter p,List<String> buffer) {
			parentPrinter = p;
			this.buffer = buffer;
		}

		public void run() {

			try {
				while(true) {
					String[] events = null;

					synchronized (buffer) {
						int bufferSize = buffer.size();

						while(bufferSize == 0) {
							//Wait till buffer has an event
							/* If you don't add a buffer notifyall after
							 * processing of events, then buffer will
							 * perpetually wait for new events
							 */
							buffer.wait();
							//buffer has a event, continue processing
							bufferSize = buffer.size();
						}

						if(bufferSize > 0) {
							//TODO track discarded events
							events = new String[bufferSize];
							//Add the events from buffer to array
							buffer.toArray(events);

							//TODO add discarded events to end of array

							//Clear Buffer
							buffer.clear();

							//Allow blocked events continue
							/*
							 * If not, any thread waiting on this buffer will
							 * not close even the current thread (see buffer.wait)
							 */
							buffer.notifyAll();
						}
					}
					//process events
					for(String event : events) {
						process(event);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		//TODO Do we need to have a closed flag
		synchronized (buffer) {
			buffer.notifyAll();
		}

		try {
			dispatcher.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void finalize() {
		close();
	}

	//TODO setBufferSize
	public void setBufferSize() {

	}
}