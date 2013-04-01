package com.vee.datastructures.graph;


import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;
import com.vee.datastructures.util.Tuple;

public class DjikstraAssignment {
	/**
	 * Assignment 5
	 * In this programming problem you'll code up Dijkstra's shortest-path
	 * algorithm. Download the text file 
	 * http://spark-public.s3.amazonaws.com/algo1/programming_prob/dijkstraData.txt. 
	 * The file contains an adjacency list representation of an undirected
	 * weighted graph with 200 vertices labeled 1 to 200. Each row consists of
	 * the node tuples that are adjacent to that particular vertex along with
	 * the length of that edge. For example, the 6th row has 6 as the first
	 * entry indicating that this row corresponds to the vertex labeled 6. The
	 * next entry of this row "141,8200" indicates that there is an edge between
	 * vertex 6 and vertex 141 that has length 8200. The rest of the pairs of
	 * this row indicate the other vertices adjacent to vertex 6 and the lengths
	 * of the corresponding edges.
	 * 
	 * Your task is to run Dijkstra's shortest-path algorithm on this graph,
	 * using 1 (the first vertex) as the source vertex, and to compute the
	 * shortest-path distances between 1 and every other vertex of the graph. If
	 * there is no path between a vertex v and vertex 1, we'll define the
	 * shortest-path distance between 1 and v to be 1000000.
	 * 
	 * You should report the shortest-path distances to the following ten
	 * vertices, in order: 7,37,59,82,99,115,133,165,188,197. You should encode
	 * the distances as a comma-separated string of integers. So if you find
	 * that all ten of these vertices except 115 are at distance 1000 away from
	 * vertex 1 and 115 is 2000 distance away, then your answer should be
	 * 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. Remember the order of
	 * reporting DOES MATTER, and the string should be in the same order in
	 * which the above ten vertices are given. Please type your answer in the
	 * space provided.
	 * 
	 * IMPLEMENTATION NOTES: This graph is small enough that the straightforward
	 * O(mn) time implementation of Dijkstra's algorithm should work fine.
	 * OPTIONAL: For those of you seeking an additional challenge, try
	 * implementing the heap-based version. Note this requires a heap that
	 * supports deletions, and you'll probably need to maintain some kind of
	 * mapping between vertices and their positions in the heap.
	 * 
	 * Answer = 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
	 */
	private final static int INFINITY = 1000000;
	
	int [][]adjMatrix;
	LinkedList<Tuple<Integer,Integer>>[] adjList;
	int size;
	int src = 0;
	public int []shortesPathDistance;
	public int []shortesPathVertices;
	
	public DjikstraAssignment(int size) {
		init(size);
	}
	
	private void init(int size){
		this.size = size;
		shortesPathDistance = new int[size];
		shortesPathVertices = new int[size];
		initList();
	}
	
	private void initList(){
		Scanner scanner;
		  int i = 0;
		  try {
		    scanner = new Scanner(new InputStreamReader(getClass().
		    		getClassLoader().getResourceAsStream("dijkstraData.txt")));
		    adjList = new LinkedList[size];
		    while(scanner.hasNextLine()) {
			   String text = scanner.nextLine();
			   String splits[] = text.split("\\t");
			   LinkedList<Tuple<Integer,Integer>> list = 
				   new LinkedList<Tuple<Integer,Integer>>();
			   for (int j = 1; j < splits.length; j++) {
				   String[] cell = splits[j].split(",");
				   list.insert(new Tuple<Integer,Integer>
				   				(Integer.parseInt(cell[0])-1,
				   						Integer.parseInt(cell[1])));
			   }
			   adjList[i++] = list;
		    }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		toAdjMat();
	}
	
	private void toAdjMat() {
		adjMatrix = new int[size][size];
		for (int i = 0 ; i < adjList.length; i++) {
			LinkedList<Tuple<Integer,Integer>> list = adjList[i];
			for (LinkedListIterator<Tuple<Integer, Integer>> iterator = 
							list.iterator(); iterator.hasNext();) {
				Tuple<Integer, Integer> t = iterator.getNext();
				adjMatrix[i][t.head] = t.tail;
			}
		}
	}
	
	public void djikstrasShortesPath(){
		/* Should use a hashmap, BST has a bug which removes them instead */
		//BST<Integer> nodes = new BST<Integer>();
		Map<Integer,Integer> nodes = new HashMap<Integer,Integer>();
		
		/* IMP ARRAY visited */
		int []visited = new int[size];
		for (int i = 0; i < shortesPathDistance.length; i++) {
			shortesPathDistance[i] = INFINITY;
			visited[i] = 0;
			nodes.put(i,i);
		}
		
		shortesPathDistance[src] = 0;
		
		while(!nodes.isEmpty()) {
			int vertex = minimumIndex(shortesPathDistance,visited);
			
			/* If the distance is max/infinity, then that vertex is inaccessible from source */
			if(shortesPathDistance[vertex] == INFINITY)
				break;
			
			nodes.remove(vertex);
			visited[vertex] = 1;
			
			for(int j=0;j<size;j++){
				int weight;
				if((weight = adjMatrix[vertex][j]) > 0) {
					if(!nodes.containsKey(j))
						continue;
					/*
					 *   SRC
					 *  |   \
					 *  |    \       SRC,J > SRC,VERTEX + VERTEX,J  
					 *  |     \      SRC,J = SRC,VERTEX + VERTEX,J   
					 *  |      \     i.e. we can reach J via VERTEX 
					 *  |       \
					 *  Vertex--J
					 */
					if(shortesPathDistance[j] > add(shortesPathDistance[vertex], weight)) {
						shortesPathDistance[j] = shortesPathDistance[vertex] + weight;
						shortesPathVertices[j] = vertex;
					}
				}
			}
		}

	}

	private int minimumIndex(int[] shortesPathDistance,int[] visited){
		int index=0;
		int min = INFINITY;
		for(int i = 0; i< shortesPathDistance.length; i++)
			/* Important operator >= instead of > otherwise it will be 0 which is default
			   and will start all over again.
			   Also check for visited	*/
			if(min >= shortesPathDistance[i] && visited[i] == 0) {
				min = shortesPathDistance[i];
				index=i;
		   }
		return index;
	}
	
	/** 
	 * 
	 * To avoid Integer overflow return {INFINITY + something} as INFINITY
	 * 
	 */
	private int add(int a, int b){
		if(a == INFINITY || b == INFINITY)
			return a;
		return a+b;
	}
	
	public static void main(String args[]){
		  DjikstraAssignment sp = new DjikstraAssignment(4);
		  sp.djikstrasShortesPath();
		  System.out.println();
		  for (int i = 0; i < sp.shortesPathDistance.length; i++)
			  if(exists(i))
				 System.out.print(sp.shortesPathDistance[i]+",");		  
	}
	
	private static boolean exists(int num){
		int array[] = {1,2,3,4};
		for (int j = 0; j < array.length; j++) {
			if(array[j]-1==num)
				return true;
		}
		return false;
	}
}
