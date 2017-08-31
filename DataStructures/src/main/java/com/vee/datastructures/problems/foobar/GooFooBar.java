package com.vee.datastructures.problems.foobar;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.vee.datastructures.tree.TreeNode;
import com.vee.datastructures.util.Tuple;

public class GooFooBar {

	/*
	 * Given a sequence of numbers from 1..2^n-1, build a post order traversal balanced symmetrical tree. Return the parent of the input values
	 */
	//@Test
	public void test0() {
		int h = 2;
		int[] q = new int[] {1,2,3,4,5,6,7};
		Map<Integer, Integer> map = new LinkedHashMap<>();
		for (int i=0; i< q.length; i++) {
			map.put(q[i], 0);
		}
		int max = (int) (Math.pow(2, h)-1);
		TreeNode<Integer> node = new TreeNode<>(max);
		build(node, new AtomicInteger(max), h-1, true);
		//node.postorder(node);
		search(node, map);
		System.out.println(map);
	}
	
	private void search(TreeNode<Integer> node, Map<Integer, Integer> map) {
		if(node == null)
			return;
		if (map.containsKey(node.getData()) && map.get(node.getData()) == 0) {
			int parent = node.getParent() == null ? -1 : node.getParent().getData();
			map.put(node.getData(), parent);
		}
		search(node.getLeft(), map);
		search(node.getRight(), map);
	}
	
	private void build(TreeNode<Integer> parent, AtomicInteger val, int height, boolean isRight) {
		if (val.get() == 0 || parent == null) return;
		System.out.println("1. " + val + " " + isRight + " " + height + " " + parent.getData());
		if (height == 0 || 
				parent.getLeft() != null && parent.getRight() != null) {
			return;
		}
		TreeNode<Integer> newNode = new TreeNode<>(val.decrementAndGet());
		parent.setRight(newNode);
		newNode.setParent(parent);
		//System.out.println("2. " + val + " " + "true" + " " + height + " " + parent.getData());
		build(newNode, val, height-1, true);
		//System.out.println("3. " + val + " " + "false" + " " + height + " " + parent.getData());
		newNode = new TreeNode<>(val.decrementAndGet());
		parent.setLeft(newNode);
		newNode.setParent(parent);
		build(newNode, val, height-1, false);
	}
	
	/*
	 * Split a string with repeating string and return the number of splits
	 */
	
	//@Test
	public void test1() {
		String s = "abccbaabccba";
		s = "ababcabababcab";
		s = "abcabcabc";
		int d = split(s);
		System.out.println(d);
	}
	
	private int split(String s) {
		for (int i = s.length()-1; i > 0; i--) {
			String first = s.substring(i);
			String second = s.substring(0, s.length()-i);
			if (first.equals(second) && s.length()%first.length() == 0 && String.format("%0" + s.length()/first.length() + "d", 0).replace("0",first).equals(s) ) {
				return s.length()/first.length();
			}
		}
		return 1;
	}
/*
 * 2D chess board with numbers from 0-63. Find the minimum number of moves a knight takes from cell X to cell Y
 */
	public void test2() {
		for (int i = 0;i < 64;i++) {
			System.out.println(distance(0,0));
		}
	}
	
	private int distance(int src, int dest) {
		if (src == dest) {
			return 0;
		}
		List<Point> coords = getCoordDelta();
		int size = 8;
		Point s = getPoint(src, size);
		int[] distance = new int[size*size];
		int[] prev = new int[size*size];
		prev[src] = 0;
		Arrays.fill(prev, -1);
		Queue<Point> q = new ArrayDeque<>();
		q.offer(s);
		while(!q.isEmpty()) {
			Point u = q.poll();
			List<Point> neighbours = getNeighbours(u, coords, size);
			for (Point n: neighbours) {
				int v = n.x*size+n.y%size;
				if (prev[v] != -1) {
					continue;
				} else {
					int p = u.x*size+u.y%size; 
					distance[v] = distance[p]+1;
					prev[v] = p;
					if(dest == v) {
						return distance[v];
					}
					q.offer(n);
				}
			}
		}
		return -1;
	}
	
	private Point getPoint(int p, int size) {
		return new Point(p/size, p%size);
	}
	
	private List<Point> getNeighbours(Point p, List<Point> coords, int size) {
		List<Point> points = new ArrayList<>();
		for (Point c : coords) {
			int newX = p.x + c.x;
			int newY = p.y + c.y;
			if (newX >=0 && newX < size && newY >=0 && newY < size) {
				points.add(new Point(newX, newY));
			}
		}
		return points;
	}
	
	private List<Point> getCoordDelta() {
		List<Point> points = new ArrayList<>(); 
		points.add(new Point(1,2));
		points.add(new Point(2,1));
		points.add(new Point(-1,2));
		points.add(new Point(2,-1));
		ListIterator<Point> it = points.listIterator();
		while(it.hasNext()) {
			Point p = it.next();
			it.add(new Point(p.x*-1, p.y*-1));
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
	
	/*
	 * Calculate the number if times > will interact with following <
	 */
	public void test3() {
		String s = "<><--->-><-><-->-";
		char[] a = s.toCharArray();
		int crossings=0;
		int right=0;
		for (int i=0; i<a.length;i++) {
			if (a[i]=='>') right++;
			if (a[i]=='<') crossings+=right;
		}
		System.out.println(crossings);
	}
	
	/*
	 * Return number of tripes [a,b,c] from a sorted array where b = Xa and c = Yb for any arbitary X and Y  
	 */
	//@Test
	public void test4() {
		int S[] = new int[] { 1,1,1,1 };
		int[] num = new int[S.length];
		int triples = 0;
		for (int i = 1; i < S.length; i++) {
			for (int j = 0; j <= i-1; j++) {
				if (S[i] % S[j] == 0) {
					num[i]++;
					triples += num[j];
				}
			}
		}
		System.out.println(Arrays.toString(num));
		System.out.println(triples);
	}
	
	//@Test
	public void test5() {
		int length = 100000;//1651559072
		//2,147,483,647
		long stime = System.currentTimeMillis();
		int start = 17;
		int ans = 0;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length+1; j++) {
				if (i+j < length) {
					ans = ans ^ start++;
				}
				if (i+j == length) {
					start = start + length-j;
					j = length+1;
					continue;
				}
				
			}
/*			if (i%1000 == 0)
			System.out.println(i);*/
		}
		System.out.println(System.currentTimeMillis()-stime);
		System.out.println(ans);
	}
	
	public void test6() {
		int length = 4;//1651559072
		long stime = System.currentTimeMillis();
		int start = 17;
		int ans = 0;
		int j = length;	
		for (int i = 0; i < length; i++) {
			int end = start + --j;
			ans = ans^xor(start-1);
			ans = ans^xor(end);
			start = start+length;
		}
		System.out.println(ans);
	}
	
	private int xor(int a) {
		int rem = a%4;
		switch (rem) {
			case 0: return a;
			case 1: return 1;
			case 2: return a+1;
			case 3: return 0;
		}
		return 0;
	}
	/*
	 * You're so close to destroying the LAMBCHOP doomsday device you can taste
	 * it! But in order to do so, you need to deploy special self-replicating
	 * bombs designed for you by the brightest scientists on Bunny Planet. There
	 * are two types: Mach bombs (M) and Facula bombs (F). The bombs, once
	 * released into the LAMBCHOP's inner workings, will automatically deploy to
	 * all the strategic points you've identified and destroy them at the same
	 * time.
	 * 
	 * But there's a few catches. First, the bombs self-replicate via one of two
	 * distinct processes: Every Mach bomb retrieves a sync unit from a Facula
	 * bomb; for every Mach bomb, a Facula bomb is created; Every Facula bomb
	 * spontaneously creates a Mach bomb.
	 * 
	 * For example, if you had 3 Mach bombs and 2 Facula bombs, they could
	 * either produce 3 Mach bombs and 5 Facula bombs, or 5 Mach bombs and 2
	 * Facula bombs. The replication process can be changed each cycle.
	 * 
	 * Second, you need to ensure that you have exactly the right number of Mach
	 * and Facula bombs to destroy the LAMBCHOP device. Too few, and the device
	 * might survive. Too many, and you might overload the mass capacitors and
	 * create a singularity at the heart of the space station - not good!
	 * 
	 * And finally, you were only able to smuggle one of each type of bomb - one
	 * Mach, one Facula - aboard the ship when you arrived, so that's all you
	 * have to start with. (Thus it may be impossible to deploy the bombs to
	 * destroy the LAMBCHOP, but that's not going to stop you from trying!)
	 * 
	 * You need to know how many replication cycles (generations) it will take
	 * to generate the correct amount of bombs to destroy the LAMBCHOP. Write a
	 * function answer(M, F) where M and F are the number of Mach and Facula
	 * bombs needed. Return the fewest number of generations (as a string) that
	 * need to pass before you'll have the exact number of bombs necessary to
	 * destroy the LAMBCHOP, or the string "impossible" if this can't be done! M
	 * and F will be string representations of positive integers no larger than
	 * 10^50. For example, if M = "2" and F = "1", one generation would need to
	 * pass, so the answer would be "1". However, if M = "2" and F = "4", it
	 * would not be possible.
	 */
	
	private void buildMFSample() {
		int MAX = 4096;
		Tuple<Integer, Integer> t = new Tuple<>(1,1);
		TreeNode<Tuple<Integer, Integer>> node = new TreeNode<>(t);
		int count = 1;
		int newCount = 0;
		Map<Tuple<Integer, Integer>, Integer> map  = new LinkedHashMap<>();
		Queue<TreeNode<Tuple<Integer, Integer>>> q = new ArrayDeque<>();
		q.offer(node);
		int level = 1;
		for (int i = 2; i<MAX; i++) {
			node = q.poll();
			count--;
			Tuple<Integer, Integer> data = node.getData();
			if (!map.containsKey(data)) {
				map.put(data, level);
			}
			TreeNode<Tuple<Integer, Integer>> l = new TreeNode<>(new Tuple<>(data.head+data.tail, data.tail));
			TreeNode<Tuple<Integer, Integer>> r = new TreeNode<>(new Tuple<>(data.head, data.head+ data.tail));
			
			node.setLeft(l);
			node.setRight(r);
			newCount+=2;
			q.offer(l);
			q.offer(r);
			if(count == 0) { //TODO Trick is to check for zero or empty
				count = newCount;
				newCount = 0;
				level++;
			}
		}
		while(!q.isEmpty()) {
			node = q.poll();
			Tuple<Integer, Integer> data = node.getData();
			if (!map.containsKey(data)) {
				map.put(data, level);
			}
		}
		for (Entry<Tuple<Integer, Integer>, Integer>  e : map.entrySet()) {
			int f = e.getKey().head;
			int s = e.getKey().tail;
			System.out.println(String.format("%d, %d - %d", f,s, e.getValue()));
		}
	}
	
	public void test7() {
		String[] a = {"2","4","1", "9","33", "5", "31", "17", "19", "12497125815806718571802751027512057176753465398623"};
		String[] b = {"1","7","1", "6", "5", "21", "74", "3", "23", "1241241240998968792038592859236307678658823642327"};
		for (int i = 0; i<a.length; i++) {
			System.out.println(a[i] +"," + b[i] + "=" + mf(a[i], b[i]));
		}
	}
	
	private String mf(String a, String b) {
		BigInteger m = new BigInteger(a);
		BigInteger f = new BigInteger(b);
		BigInteger steps = BigInteger.ZERO;
		while(!m.subtract(f).abs().equals(BigInteger.ONE) && !m.min(f).equals(BigInteger.ONE)) {
			if (m.remainder(f).equals(BigInteger.ZERO) || m.remainder(f).equals(BigInteger.ZERO)) {
				return "impossible";
			}
			BigInteger step = BigInteger.ZERO;
			//System.out.print(m +"," + f + "=" );
			if (m.compareTo(f) > 0) {
				BigInteger[] sub = m.subtract(f).divideAndRemainder(f);
				step= sub[0].add(sub[1].equals(BigInteger.ZERO) ? BigInteger.ZERO : BigInteger.ONE);
				m= m.subtract(step.multiply(f));
			} else {
				BigInteger[] sub = f.subtract(m).divideAndRemainder(m);
				step= sub[0].add(sub[1].equals(BigInteger.ZERO) ? BigInteger.ZERO : BigInteger.ONE);
				f= f.subtract(step.multiply(m));
			}
			//System.out.println(step);
			steps=steps.add(step);
		}
		if (m.subtract(f).abs().equals(BigInteger.ONE)) {
			steps=steps.add(m.max(f));
		} else if (m.equals(BigInteger.ONE)) {
			steps=steps.add(f);
		} else if (f.equals(BigInteger.ONE)) {
			steps=steps.add(m);
		}
		return steps.subtract(BigInteger.ONE).toString();
	}

	/*
	 * Free the B  ========================
	 * 
	 * You need to free the bunny prisoners before Commander Lambda's space
	 * station explodes! Unfortunately, the commander was very careful with her
	 * highest-value prisoners - they're all held in separate, maximum-security
	 * cells. The cells are opened by putting keys into each console, then
	 * pressing the open button on each console simultaneously. When the open
	 * button is pressed, each key opens its corresponding lock on the cell. So,
	 * the union of the keys in all of the consoles must be all of the keys. The
	 * scheme may require multiple copies of one key given to different minions.
	 * 
	 * The consoles are far enough apart that a separate minion is needed for
	 * each one. Fortunately, you have already freed some bunnies to aid you -
	 * and even better, you were able to steal the keys while you were working
	 * as Commander Lambda's assistant. The problem is, you don't know which
	 * keys to use at which consoles. The consoles are programmed to know which
	 * keys each minion had, to prevent someone from just stealing all of the
	 * keys and using them blindly. There are signs by the consoles saying how
	 * many minions had some keys for the set of consoles. You suspect that
	 * Commander Lambda has a systematic way to decide which keys to give to
	 * each minion such that they could use the consoles.
	 * 
	 * You need to figure out the scheme that Commander Lambda used to
	 * distribute the keys. You know how many minions had keys, and how many
	 * consoles are by each cell. You know that Command Lambda wouldn't issue
	 * more keys than necessary (beyond what the key distribution scheme
	 * requires), and that you need as many bunnies with keys as there are
	 * consoles to open the cell.
	 * 
	 * Given the number of bunnies available and the number of locks required to
	 * open a cell, write a function answer(num_buns, num_required) which
	 * returns a specification of how to distribute the keys such that any
	 * num_required bunnies can open the locks, but no group of (num_required -1) bunnies can.
	 * 
	 * Each lock is numbered starting from 0. The keys are numbered the same as
	 * the lock they open (so for a duplicate key, the number will repeat, since
	 * it opens the same lock). For a given bunny, the keys they get is
	 * represented as a sorted list of the numbers for the keys. To cover all of
	 * the bunnies, the final answer is represented by a sorted list of each
	 * individual bunny's list of keys. Find the lexicographically least such
	 * key distribution - that is, the first bunny should have keys sequentially
	 * starting from 0.
	 * 
	 * num_buns will always be between 1 and 9, and num_required will always be
	 * between 0 and 9 (both inclusive). For example, if you had 3 bunnies and
	 * required only 1 of them to open the cell, you would give each bunny the
	 * same key such that any of the 3 of them would be able to open it, like
	 * so: 
	 *  [
		  [0],
		  [0],
		  [0],
		]
		If you had 2 bunnies and required both of them to open the cell, they would receive different keys (otherwise they wouldn't both actually be required), and your answer would be as follows:
		[
		  [0],
		  [1],
		]
		Finally, if you had 3 bunnies and required 2 of them to open the cell, then any 2 of the 3 bunnies should have all of the keys necessary to open the cell, but no single bunny would be able to do it.  Thus, the answer would be:
		[
		  [0, 1],
		  [0, 2],
		  [1, 2],
		]
		
		Languages
		=========
		
		To provide a Python solution, edit solution.py
		To provide a Java solution, edit solution.java
		
		Test cases
		==========
		
		Inputs:
		    (int) num_buns = 2
		    (int) num_required = 1
		Output:
		    (int) [[0], [0]]
		
		Inputs:
		    (int) num_buns = 5
		    (int) num_required = 3
		Output:
		    (int) [[0, 1, 2, 3, 4, 5], [0, 1, 2, 6, 7, 8], [0, 3, 4, 6, 7, 9], [1, 3, 5, 6, 8, 9], [2, 4, 5, 7, 8, 9]]
		
		Inputs:
		    (int) num_buns = 4
		    (int) num_required = 4
		Output:
		    (int) [[0], [1], [2], [3]]
	 */
	
   /* 
    f = list(itertools.combinations(range(num_buns),num_required))
    total = len(f)*num_required
    repeat_times = num_buns - num_required + 1
    f1 = list(itertools.combinations(range(num_buns),repeat_times))
    for i in range(num_buns):
        r.append([])

    for i in range(total/repeat_times):
        for j in f1[i]:
            r[j].append(i)
            */
	
	
	@Test
	public void test8() {	
		int num_buns = 4, num_required = 0;
		if (num_required == 0) {
			int [][] s = new int[num_buns][0];
			return;
		}
		int occurence = num_buns-num_required+1;
		int size = C(num_buns-1, num_required-1);
		int max = C(num_buns, occurence);
		int[][] com = combinations(num_buns, occurence);
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < num_buns; i++) {
			list.add(new ArrayList<Integer>());
		}
		for (int i=0; i < max; i++) {
			for (int j : com[i]) {
				list.get(j).add(i);
			}
		}
		int[][] result = new int[num_buns][size];
		int i = 0;
		for (List<Integer> l : list) {
			int j = 0;
			for (int num : l) {
				result[i][j++] = num;
			}
			i++;
		}
		System.out.println(Arrays.deepToString(result));
	}
	
	private int[][] combinations(int n, int r) {
		int ncr = C(n,r);
		int[][] nr = new int[ncr][r];
		int [] s = new int[r];
		for(int i = 0; i < r; i++) {
			s[i] = i;
		}
		nr[0] = Arrays.copyOf(s, s.length);
		for(int i = 1; i < ncr; i++) {
			int j = r-1;
			int max = n-1;
			while(s[j] == max) {
				j--; max--;
			}
			s[j] = s[j] + 1;
			for(int k = j+1; k < r; k++)
				s[k] = s[k-1] + 1;
			nr[i] = Arrays.copyOf(s, s.length);
		}
		return nr;
	}
	
	private int C(int n, int r) {
		int prod = 1;
		for(int i = 0; i <r ;i++) {
			prod *= (n-i);
			prod /= (i+1);
		}
		return prod;
	}
	
	/*
	 * Uh-oh - you've been cornered by one of Commander Lambdas elite guards!
	 * Fortunately, you grabbed a beam weapon from an abandoned guardpost while
	 * you were running through the station, so you have a chance to fight your
	 * way out. But the beam weapon is potentially dangerous to you as well as
	 * to the elite guard: its beams reflect off walls, meaning youll have to be
	 * very careful where you shoot to avoid bouncing a shot toward yourself!
	 * 
	 * Luckily, the beams can only travel a certain maximum distance before
	 * becoming too weak to cause damage. You also know that if a beam hits a
	 * corner, it will bounce back in exactly the same direction. And of course,
	 * if the beam hits either you or the guard, it will stop immediately
	 * (albeit painfully).
	 * 
	 * Write a function answer(dimensions, your_position, guard_position,
	 * distance) that gives an array of 2 integers of the width and height of
	 * the room, an array of 2 integers of your x and y coordinates in the room,
	 * an array of 2 integers of the guard's x and y coordinates in the room,
	 * and returns an integer of the number of distinct directions that you can
	 * fire to hit the elite guard, given the maximum distance that the beam can
	 * travel.
	 * 
	 * The room has integer dimensions [1 < x_dim <= 1000, 1 < y_dim <= 1000].
	 * You and the elite guard are both positioned on the integer lattice at
	 * different distinct positions (x, y) inside the room such that [0 < x <
	 * x_dim, 0 < y < y_dim]. Finally, the maximum distance that the beam can
	 * travel before becoming harmless will be given as an integer 1 < distance
	 * <= 10000.
	 * 
	 * For example, if you and the elite guard were positioned in a room with
	 * dimensions [3, 2], 
	 * you_position [1, 1], 
	 * guard_position [2, 1], 
	 * and a maximum shot distance of 4, 
	 * you could shoot in seven different directions to hit the elite guard (given as vector bearings from your location): 
	 * [1, 0], [1, 2], [1, -2], [3, 2], [3, -2], [-3, 2], and [-3, -2]. 
	 * As specific examples, the shot at bearing [1, 0] is the straight line horizontal shot of distance 1, 
	 * the shot at bearing [-3, -2] bounces off the left wall and then the bottom wall before hitting the elite guard with a total shot distance of sqrt(13), 
	 * and the shot at bearing [1, 2] bounces off just the top wall before hitting the elite guard with a total shot distance of sqrt(5).
	 * 
	 * 
	 * Inputs:
	    (int list) dimensions = [3, 2]
	    (int list) captain_position = [1, 1]
	    (int list) badguy_position = [2, 1]
	    (int) distance = 4
		Output:
		    (int) 7
	
		Inputs:
		    (int list) dimensions = [300, 275]
		    (int list) captain_position = [150, 150]
		    (int list) badguy_position = [185, 100]
		    (int) distance = 500
		Output:
		    (int) 9
	 */
	
	@Test
	public void test9() {
		
	}

}
