package com.vee.algorithms.problems.cake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.testng.internal.collections.Pair;

public class CakeMergeMeetings {
	/**
	 *  Your company built an in-house calendar tool called HiCal. You want to add a feature to see the times in a day when everyone is available.
To do this, you’ll need to know when any team is having a meeting. In HiCal, a meeting is stored as tuples ↴ of integers (start_time, end_time). These integers represent the number of 30-minute blocks past 9:00am.
For example:
(2, 3) # meeting from 10:00 – 10:30 am
(6, 9) # meeting from 12:00 – 1:30 pm
Write a function condense_meeting_times() that takes a list of meeting time ranges and returns a list of condensed ranges.
For example, given:
  [(0, 1), (3, 5), (4, 8), (10, 12), (9, 10)]
your function would return:
  [(0, 1), (3, 8), (9, 12)]
Do not assume the meetings are in order. The meeting times are coming from multiple teams.
Write a solution that's efficient even when we can't put a nice upper bound on the numbers representing our time ranges. Here we've simplified our times down to the number of 30-minute slots past 9:00 am. But we want the function to work even for very large numbers, like Unix timestamps. In any case, the spirit of the challenge is to merge meetings where start_time and end_time don't have an upper bound.
0,10  11,15  8,10 
	 */

	@Test
	public void test() {
		int []start = new int[]{0,3,4,10,9};
		int []end = new int[]{1,5,8,12,10};
		List<Pair<Long, Long>> list = new ArrayList<>();
		for (int i = 0; i < start.length; i++) {
			list.add(Pair.of(Long.valueOf(start[i]), Long.valueOf(end[i])));
		}
		System.out.println(mergeMeetings(list));
	}

	List<Pair<Long, Long>> mergeMeetings(List<Pair<Long, Long>> timings) {
		Collections.sort(timings, new Comparator<Pair<Long, Long>>() {
			@Override
			public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
				return Long.compare(o1.first(), o2.first());
			}
		});
		Stack<Pair<Long, Long>> s = new Stack<Pair<Long,Long>>();
		s.push(timings.get(0));
		for (int i = 1; i < timings.size(); i++) {
			Pair<Long, Long> top = s.peek();
			if (top.second() >= timings.get(i).first()) {
				Pair<Long, Long> p = new Pair<Long, Long>(top.first(), Math.max(top.second(), timings.get(i).second()));
				s.pop();
				s.push(p);
			} else {
				s.push(timings.get(i));
			}
		}
		return s;
	}
}
