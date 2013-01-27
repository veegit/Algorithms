package com.vee.datastructures.graph;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;
import com.vee.datastructures.tree.BST;
import com.vee.test.Tuple;

public class ShortestPath {

	private final static int INFINITY = Integer.MAX_VALUE;
	
	int [][]adjMatrix;
	LinkedList<Tuple<Integer,Integer>>[] adjList;
	int size;
	int src = 2;
	public int []shortesPathDistance;
	public int []shortesPathVertices;
	
	public ShortestPath(int[][] X, int size) {
		init(X,size);
	}
	
	private void init(int[][] X, int size){
		adjMatrix = X;
		this.size = size;
		shortesPathDistance = new int[size];
		shortesPathVertices = new int[size];
		//toAdjList();
	}
	
	@SuppressWarnings("unchecked")
	private void toAdjList() {
		adjList = new LinkedList[size];
		for(int i=0; i< adjMatrix.length; i++) {
			adjList[i] = new LinkedList<Tuple<Integer,Integer>>();
			for(int j=0; j< adjMatrix.length; j++)
				if(adjMatrix[i][j] != 0)
					adjList[i].insert(new Tuple<Integer,Integer>(j,adjMatrix[i][j]));
		}
	}
	
	public void djikstrasShortesPath(){
		/* Should use a hashmap instead */
		BST<Integer> nodes = new BST<Integer>();
		
		/* IMP ARRAY visited */
		int []visited = new int[size];
		for (int i = 0; i < shortesPathDistance.length; i++) {
			shortesPathDistance[i] = INFINITY;
			visited[i] = 0;
			nodes.insert(i);
		}
		
		shortesPathDistance[src] = 0;
		
		while(!nodes.isEmpty()) {
			int vertex = minimumIndex(shortesPathDistance,visited);
			
			/* If the distance is max/infinity, then that vertex is inaccessible from source */
			if(shortesPathDistance[vertex] == INFINITY)
				break;
			
			nodes.delete(vertex);
			visited[vertex] = 1;
			
			for(int j=0;j<size;j++){
				int weight;
				if((weight = adjMatrix[vertex][j]) > 0) {
					if(nodes.find(j) == null)
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
		int Y[][] =
		   {
		    {0,1,0,1,0,0,0,0,0,0},
		    {0,0,1,0,0,1,0,0,0,0},
		    {1,0,0,1,1,0,0,0,0,0},
		    {0,0,0,0,-1,0,0,0,0,0},
		    {0,0,0,0,0,0,0,0,0,0},
		    {0,0,1,0,0,0,0,0,0,0},
		    {0,0,0,0,0,1,0,1,0,0},
		    {0,0,0,0,0,1,0,0,0,1},
		    {0,0,0,0,0,0,0,1,0,0},
		    {0,0,0,0,0,0,0,0,1,0}
		   };
		{
		  int X[][] = 
		  {
		    {0,2,1,0},
		    {2,0,5,1},
		    {1,5,0,5},
		    {0,1,5,0}
		   };
		  int size = X.length;
		  ShortestPath sp = new ShortestPath(X, size);
		  //sp.bellmanFordShortestPath();
		  //for (int i = 0; i < sp.shortesPathDistance.length; i++)
		//	 System.out.print(sp.shortesPathDistance[i] + "  ");
		  sp.djikstrasShortesPath();
		  System.out.println();
		  for (int i = 0; i < sp.shortesPathDistance.length; i++)
			 System.out.print(sp.shortesPathDistance[i] + "  ");
	}

}
}
