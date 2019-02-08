package com.vee.algorithms.graph;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.HashMap;
import java.util.*;

public class Kruskal {
	
	public static void main(String args[]) {
		Map<Point, Integer> map = new HashMap<>();
		map.put(new Point(1,5), 1);
		map.put(new Point(3,4), 2);
		map.put(new Point(5,4), 7);
		map.put(new Point(1,2), 3);
		map.put(new Point(5,3), 6);
		map.put(new Point(2,5), 4);
		map.put(new Point(2,3), 5);
		Set<Point> s = new Kruskal().mst(map);
		s.forEach(p -> {System.out.println(p.x + " " + p.y); } );
	}
	
	Set<Point> mst(Map<Point, Integer> edges) {
		Map<Integer, Integer> map = new HashMap<>();
		edges.keySet().forEach(p -> {
			map.put(p.x, p.x);
			map.put(p.y, p.y);
		});
		Set<Point> A = new LinkedHashSet<>();
		edges.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e -> {
			Point edge = e.getKey();
			if (findSet(edge.x, map) != findSet(edge.y, map)) {
				A.add(edge);
				map.put(edge.y, findSet(edge.x, map));
			}
		});
		int[][] arr = new int[][] {{1,10},{2,9},{3,8},{4,7}};
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1] - b[1]);
		Arrays.asList(arr).stream().forEach(pq::add);
		System.out.println(Arrays.deepToString(pq.toArray()));
		Arrays.sort(arr, (int a[], int b[]) -> a[1] - b[1]);
		System.out.println(Arrays.deepToString(arr));
		return A;
	}
	
	private int findSet(int v, Map<Integer, Integer> map) {
		while (map.get(v) != v) {
			v = map.get(v);
		}
		return v;
	}
}
