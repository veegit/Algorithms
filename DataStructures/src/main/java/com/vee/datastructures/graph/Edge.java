package com.vee.datastructures.graph;

public class Edge {
	int from;
	int to;
	
	public Edge(int from,int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return from;
	}
	
	public int getTo() {
		return to;
	}
}
