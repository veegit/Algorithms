package com.vee.algorithms.problems.foobar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;

import org.junit.Test;

public class GooFooKnightMoves {
	/*
	 * 2D chess board with numbers from 0-63. Find the minimum number of moves a
	 * knight takes from cell X to cell Y
	 */
	@Test
	public void test() {
		for (int i = 0; i < 64; i++) {
			System.out.println(distance(0, 0));
		}
	}

	public int distance(int src, int dest) {
		if (src == dest) {
			return 0;
		}
		List<Point> coords = getCoordDelta();
		int size = 8;
		Point s = getPoint(src, size);
		int[] distance = new int[size * size];
		int[] prev = new int[size * size];
		prev[src] = 0;
		Arrays.fill(prev, -1);
		Queue<Point> q = new ArrayDeque<>();
		q.offer(s);
		while (!q.isEmpty()) {
			Point u = q.poll();
			List<Point> neighbours = getNeighbours(u, coords, size);
			for (Point n : neighbours) {
				int v = n.x * size + n.y % size;
				if (prev[v] != -1) {
					continue;
				} else {
					int p = u.x * size + u.y % size;
					distance[v] = distance[p] + 1;
					prev[v] = p;
					if (dest == v) {
						return distance[v];
					}
					q.offer(n);
				}
			}
		}
		return -1;
	}

	private Point getPoint(int p, int size) {
		return new Point(p / size, p % size);
	}

	private List<Point> getNeighbours(Point p, List<Point> coords, int size) {
		List<Point> points = new ArrayList<>();
		for (Point c : coords) {
			int newX = p.x + c.x;
			int newY = p.y + c.y;
			if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
				points.add(new Point(newX, newY));
			}
		}
		return points;
	}

	private List<Point> getCoordDelta() {
		List<Point> points = new ArrayList<>();
		points.add(new Point(1, 2));
		points.add(new Point(2, 1));
		points.add(new Point(-1, 2));
		points.add(new Point(2, -1));
		ListIterator<Point> it = points.listIterator();
		while (it.hasNext()) {
			Point p = it.next();
			it.add(new Point(p.x * -1, p.y * -1));
		}
		return points;
	}

	private static class Point {
		int x;
		int y;

		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
