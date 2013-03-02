package com.vee.test;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;

public class DFS {
    private int adjMatrix[][];
    private int size;
    private int visited[];
	private int previsit[],postvisit[];
	private int clock;
	LinkedList<Integer>[] adjList;
	TreeMap<Integer,Integer> order = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) {return o2 - o1;};
	});
	
	public DFS(int[][] adjMatrix,int size) {
  	  init(adjMatrix,size);
  	  for (int v=0; v<size; v++)
		   if (visited[v] == 0)
			   dfs(v);
	}
    
    private void dfs(int v) {
		visited[v] = 1;
		previsit[v] = clock++;
		LinkedList<Integer> vList = adjList[v];
		for (LinkedListIterator<Integer> iterator = vList.iterator(); iterator.hasNext();) {
			int w = iterator.getNext();
			if(visited[w] == 0)
				dfs(w);
		}
		postvisit[v] = clock++;
		order.put(clock,v);
	}
    
    private void init(int[][] adjMatrix,int size) {
    	 this.size = size;
   	     this.adjMatrix = adjMatrix;
		 
		 clock = 0;
		 visited = new int[size];
		 previsit = new int[size];
		 postvisit = new int[size];
		 
		 for (int v=0; v<size; v++) visited[v]=0;
		 toAdjList();
	}
    
    @SuppressWarnings("unchecked")
	private void toAdjList() {
		adjList = new LinkedList[size];
		for(int i=0; i< adjMatrix.length; i++) {
			adjList[i] = new LinkedList<Integer>();
			for(int j=0; j< adjMatrix.length; j++)
				if(adjMatrix[i][j] != 0)
					adjList[i].insert(j);
		}
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
		  for(int i=0;i<size;i++)
			    for(int j=0;j<size;j++)
			    	if(X[i][j] == 1)
			    		System.out.println(i + " " + j);
		  DFS dfs = new DFS(X,size);
		  int previsit[] = dfs.getPrevisit();
		  int postvisit[] = dfs.getPostvisit();
		  
		  System.out.println("PreVisit");
		  
		  for(int i=0;i<size;i++) {
		     System.out.print(previsit[i]+" ");
		  }
		  
		  System.out.println("\nPostVisit");
		  
		  for(int i=0;i<size;i++) {
			     System.out.print(postvisit[i]+" ");
		  }
		  
		  System.out.println("\nOrdering");
		  for (Entry<Integer, Integer> entry : dfs.order.entrySet())
		      System.out.print(entry.getValue()+" ");
		}
	
	public int[] getPrevisit() {
		return previsit;
	}

	public int[] getPostvisit() {
		return postvisit;
	}
}
