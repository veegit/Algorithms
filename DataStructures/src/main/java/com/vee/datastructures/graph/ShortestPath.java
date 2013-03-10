package com.vee.datastructures.graph;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;
import com.vee.datastructures.util.Tuple;

public class ShortestPath {

	private final static int INFINITY = 1000000;
	
	int [][]adjMatrix;
	LinkedList<Tuple<Integer,Integer>>[] adjList;
	int size;
	int src = 0;
	public int []shortesPathDistance;
	public int []shortesPathVertices;
	
	public ShortestPath(int size) {
		init(size);
	}
	
	private void init(int size){
		this.size = size;
		shortesPathDistance = new int[size];
		shortesPathVertices = new int[size];
		initList();
	}
	
	private void initMat(){
		toAdjList();
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
	
	@SuppressWarnings("unchecked")
	private void toAdjList() {
		adjList = new LinkedList[size];
		for(int i=0; i< adjMatrix.length; i++) {
			adjList[i] = new LinkedList<Tuple<Integer,Integer>>();
			for(int j=0; j< adjMatrix.length; j++)
				if(adjMatrix[i][j] != 0)
					adjList[i].insert(
							new Tuple<Integer,Integer>(j+1,adjMatrix[i][j]));
		}
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
	
	public void bellmanFordShortestPath() {
		int v = size-1;
		for (int i = 0; i < shortesPathDistance.length; i++) 
			shortesPathDistance[i] = INFINITY;
		shortesPathDistance[src] = 0;
		
		while(v-- > 0 )
			for(int vertex=0;vertex< size; vertex++) {
				LinkedListIterator<Tuple<Integer,Integer>> it = adjList[vertex].iterator();
				while(it.hasNext()) {
					Tuple<Integer,Integer> node = it.getNext(); 
					int j = node.head;
					int weight = node.tail;
					if(shortesPathDistance[j] > add(shortesPathDistance[vertex], weight)) {
						shortesPathDistance[j] = shortesPathDistance[vertex] + weight;
						shortesPathVertices[j] = vertex;
					}
				}
			}
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
		  ShortestPath sp = new ShortestPath(200);
		  sp.djikstrasShortesPath();
		  System.out.println();
		  for (int i = 0; i < sp.shortesPathDistance.length; i++)
			  if(exists(i))
				 System.out.print(sp.shortesPathDistance[i]+",");		  
	}
		
}
	private static boolean exists(int num){
		int array[] = {7,37,59,82,99,115,133,165,188,197};
		//int array[] = {1,2,3,4,5,6};
		for (int j = 0; j < array.length; j++) {
			if(array[j]-1==num)
				return true;
		}
		return false;
	}
}
