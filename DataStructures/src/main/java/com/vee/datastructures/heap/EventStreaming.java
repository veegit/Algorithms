package com.vee.datastructures.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * 
 * @author vee
 * Events arrive to a service with {id, ts, start/finish}
 * only print the events which have both timestamps
 * always print in order of start date
 *
 */
public class EventStreaming {
	Queue<Request> queue = new PriorityQueue<>(new Comparator<Request>() {
		public int compare(Request o1, Request o2) {
			return Long.compare(o1.s_ts, o2.s_ts);
		}
	});
	
	Map<String, Request> map = new HashMap<>();
	
	public void log(String id, long ts, boolean start) {
		if (start) {
			start(id, ts);
		} else {
			finish(id, ts);
		}
	}
	
	private void start(String id, long s_ts) {
		Request r = new Request(id, s_ts);
		queue.add(r);
		map.put(id, r);
	}
	
	private void finish(String id, long e_ts) {
		Request r = map.get(id);
		r.e_ts = e_ts;
		while(!queue.isEmpty() && queue.peek().e_ts != null) {
			Request p = queue.poll();
			System.out.println(p);
			map.remove(p.id);
		}
	}
	
	public static void main(String args[]) {
		int count = 10;
		EventStreaming es = new EventStreaming();
		long now = 0L;
		Request r[] = new Request[count];
		IntStream.range(0, count).forEach(i -> { r[i] = new Request(UUID.randomUUID().toString().substring(0,4), now + new Random().nextInt(100)); } );
		IntStream.range(0, count).forEach(i -> { r[i].e_ts = r[i].s_ts + new Random().nextInt(100); } );
		IntStream.range(0, count).forEach(i -> { System.out.println(r[i]); });
		System.out.println("\n\n\n");
		es.log(r[0].id, r[0].s_ts, true);
		es.log(r[1].id, r[1].s_ts, true);
		es.log(r[2].id, r[2].s_ts, true);
			es.log(r[2].id, r[2].e_ts, false);
		es.log(r[3].id, r[3].s_ts, true);
		es.log(r[4].id, r[4].s_ts, true);
			es.log(r[3].id, r[3].e_ts, false);
			es.log(r[4].id, r[4].e_ts, false);
		es.log(r[5].id, r[5].s_ts, true);
		es.log(r[6].id, r[6].s_ts, true);
			es.log(r[1].id, r[1].e_ts, false);
			es.log(r[0].id, r[0].e_ts, false);
		es.log(r[7].id, r[7].s_ts, true);
		es.log(r[8].id, r[8].s_ts, true);
			es.log(r[8].id, r[8].e_ts, false);
			es.log(r[6].id, r[6].e_ts, false);
		es.log(r[9].id, r[9].s_ts, true);
			es.log(r[9].id, r[9].e_ts, false);
			es.log(r[5].id, r[5].e_ts, false);
			es.log(r[7].id, r[7].e_ts, false);
	}
}

class Request {
	String id;
	Long s_ts;
	Long e_ts;

	public Request(String id, Long s_ts) {
		this.id = id;
		this.s_ts = s_ts;
	}
	
	public String toString() {
		return id + " " + " " + s_ts + " " + e_ts;
	}
}
