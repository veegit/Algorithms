package com.vee.algorithms.problems.foobar;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

import java.util.Map.Entry;

import com.vee.algorithms.datastructures.TreeNode;
import com.vee.algorithms.util.Tuple;

public class GooFooMF {
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
	@Test
	public void test() {
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
	
	//call this function and use the stdout responses to validate the above solution
	private void generateMFSample() {
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
}
