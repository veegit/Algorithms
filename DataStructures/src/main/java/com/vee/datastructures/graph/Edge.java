package com.vee.datastructures.graph;

public final class Edge {
	private final int from;
	private final int to;
	private double weight = 1.0;

	public Edge(int from,int to) {
		this.from = from;
		this.to = to;
	}

	public Edge(int from,int to, double weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Edge(int to, double weight) {
		this.from = Integer.MIN_VALUE;
		this.to = to;
		this.weight = weight;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public double getWeight() {
		return weight;
	}
}
