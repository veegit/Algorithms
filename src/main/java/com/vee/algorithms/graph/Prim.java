package com.vee.algorithms.graph;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.vee.algorithms.datastructures.Edge;

public class Prim {

	private Map<Integer,List<Edge>> adjList;
	int startVertex;

	private static class HeapVertex {
		double score;
		int id;

		HeapVertex(int id, double score) {
			this.id = id;
			this.score = score;
		}

		@Override
		public boolean equals(Object h) {
			return ((HeapVertex) h).id == id;
		}
	}

	private void initAddVertex(int from, int to, double weight) {
		if (adjList.get(from) == null) {
			List<Edge> adj = new ArrayList<Edge>();
			adj.add(new Edge(to, weight));
			adjList.put(from, adj);
		} else {
			adjList.get(from).add(new Edge(to, weight));
		}
	}

	private void init(String pathName) {
		adjList = new HashMap<Integer, List<Edge>>();
		try {
			Scanner scanner = new Scanner(new File(pathName));
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				String num[] = s.split("\\s");
				int from = Integer.parseInt(num[0]);
				int to = Integer.parseInt(num[1]);
				double weight = Double.parseDouble(num[2]);
				initAddVertex(from, to, weight);
				initAddVertex(to, from, weight);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public long calculate() {
		PriorityQueue<HeapVertex> heap = new PriorityQueue<HeapVertex>(
				adjList.size(),
				new Comparator<HeapVertex>() {
					public int compare(HeapVertex o1, HeapVertex o2) {
						return Double.compare(o1.score, o2.score);
					}
				});
		Map<Integer, Double> weights= new HashMap<Integer, Double>();
		for (Integer v : adjList.keySet()) {
			heap.add(new HeapVertex(v, Double.MAX_VALUE));
			weights.put(v, Double.MAX_VALUE);
		}
		HeapVertex s = heap.poll();
		startVertex = s.id;
		s.score = Integer.MIN_VALUE;
		heap.offer(s);
		weights.put(startVertex, Double.MIN_VALUE);
		Set<Integer> traversed = new HashSet<Integer>();
		while (!heap.isEmpty()) {
			HeapVertex u = heap.poll();
			System.out.println(u.id + " " + weights.get(u.id));
			traversed.add(u.id); 				//add to MST
			for (Edge e : adjList.get(u.id)) {		//update scores/weights
				if (traversed.contains(e.getTo())) {
					continue;
				}
				if (weights.get(e.getTo()).compareTo(e.getWeight()) > 0) {
					weights.put(e.getTo(), e.getWeight());
					HeapVertex v = new HeapVertex(e.getTo(), e.getWeight());
					heap.remove(v);
					heap.offer(v);
				}
			}
		}
		long sumOfWeights = 0;
		weights.remove(startVertex);
		for (Double w : weights.values()) {
			sumOfWeights += w;
		}
		return sumOfWeights;
	}

	public static Prim build(String fileName) {
		Prim p = new Prim();
		p.init(fileName);
		return p;
	}

	public static void main(String[] args) {
		long sum = Prim
				.build("/home/vee/workspace/data/coursera/prim-edges.txt")
				.calculate();
		System.out.println(sum);
	}
}
