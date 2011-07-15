package com.vee.datastructures.graph;

import com.vee.datastructures.LinkedQueue;

/**
 * @author narayanan_venkiteswaran
 *  <h5>Breadth First Search</h5>
 *  <b>Applications</b>
 *  <li> Finding connected components </li>
 *  <li> Shortest Path between 2 vertices in a graph of equal length </li>
 *  <li> {@link} <a href="http://en.wikipedia.org/wiki/Bipartite_graph">Bipartite Graph</a></li>
 */
public class BFS {
	private int adjMatrix[][];
    private int size;
    private int visited[];
	private int spanningForest[][];
	private LinkedQueue<Integer> queue = new LinkedQueue<Integer>();;
	
	private void bfs(int v) {
		int w;
		queue.enqueue(v);
		while (!queue.isEmpty()) {
		    v = queue.dequeue();
		    visited[v] = 1;
		    for (w = 0; w< size; w++)
			  if (adjMatrix[v][w] == 1)
			    if (visited[w] == 0) {
			      queue.enqueue(w);
			      spanningForest[v][w] = 1;
			      visited[w] = -1;
			    } 
			 }
		}
	
	public BFS(int[][] adjMatrix,int size) {
  	  init(adjMatrix,size);
  	  for (int v=0; v<size; v++)
		   if (visited[v] == 0)
			   bfs(v);
	}
	
	private void init(int[][] adjMatrix,int size) {
   	 	 this.size = size;
  	     this.adjMatrix = adjMatrix;
		 spanningForest = new int[size][size];
		 visited = new int[size];
		 
		 for(int i=0;i<size;i++)
		    for(int j=0;j<size;j++)
		       spanningForest[i][j]=0;
		 
		 for (int v=0; v<size; v++) visited[v]=0;
	}

	public static void main(String args[]) {
		int X[][] =
		   {
		    {0,1,0,1,0,0,0,0,0,0},
		    {0,0,1,0,0,1,0,0,0,0},
		    {1,0,0,1,1,0,0,0,0,0},
		    {0,0,0,0,1,0,0,0,0,0},
		    {0,0,0,0,0,0,0,0,0,0},
		    {0,0,1,0,0,0,0,0,0,0},
		    {0,0,0,0,0,1,0,1,0,0},
		    {0,0,0,0,0,1,0,0,0,1},
		    {0,0,0,0,0,0,0,1,0,0},
		    {0,0,0,0,0,0,0,0,1,0}
		   };
		  int size = 10;
		  BFS bfs = new BFS(X,size);
		  int C[][] = bfs.getSpanningForest();
		  for(int i=0;i<size;i++) {
		        for(int j=0;j<size;j++)
		                System.out.print(C[i][j] + " ");
		        System.out.println();
		  }
	}
	
	public int[][] getSpanningForest() {
		return spanningForest;
	}
}
