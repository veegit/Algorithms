package com.vee.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Twosum {

	/**
	 * The goal of this problem is to implement a variant of the 2-SUM algorithm
	 * (covered in the Week 6 lecture on hash table applications). The file
	 * contains 500,000 positive integers (there might be some
	 * repetitions!).This is your array of integers, with the ith row of the
	 * file specifying the ith entry of the array.
	 * 
	 * Your task is to compute the number of target values t in the interval
	 * [2500,4000] (inclusive) such that there are distinct numbers x,y in the
	 * input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a
	 * one-line addition to the algorithm from lecture.)
	 * 
	 * Write your numeric answer (an integer between 0 and 1501) in the space
	 * provided.
	 * 
	 * 
	 * OPTIONAL COMMENT: You might notice that the chosen targets are relatively
	 * small numbers. (There is a good reason for this. Can you guess what would
	 * be the problem with a similar interval of larger targets?) You are
	 * welcome to add extra optimizations that take advantage of this fact,
	 * though it is not required for the assignment.
	 * 
	 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing
	 * your own hash table for it. For example, you could compare performance
	 * under the chaining and open addressing approaches to resolving
	 * collisions.
	 */
	
	final static String FILENAME = "/home/narayanan/workspace/data/HashInt.txt";
	final static int start = 2500;
	final static int end = 4000;
	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
	
	public Twosum(){
		try {
			Scanner scan = new Scanner(new File(FILENAME));
			while(scan.hasNextInt())
				map.put(scan.nextInt(), 1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		iterate();
	}
	
	public void iterate() {
		Set<Integer> keys = map.keySet();
		Set<Integer> targets = new HashSet<Integer>();
		for(int i = start; i<=end;i++)		
			for (Iterator<Integer> iterator = keys.iterator(); iterator.hasNext();) {
				Integer key = iterator.next();
					if(key < i/2 && map.containsKey(i-key))
						targets.add(i);
		}
		System.out.println(targets.size());
		
	}
	
	public static void main(String[] args) {
		new Twosum();
	}

}
