package com.vee.datastructures.graph;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;

/*
* Tree edges: edge (u; v) is a tree edge if v was first discovered by exploring
edge (u; v). The tree edges form a spanning forest of G (no cycles).
* Back edges: an edge (u; v) is a back edge if it connects vertex u to an
ancestor v in a DFS tree.
* Forward edges: an edge (u; v) is a forward edge if it connects a vertex u
to a descendant v in a DFS tree.
* Cross edges: all other edges.
It is easy to show that:
* (u; v) is a tree edge or a forward edge , d[u] < d[v] < f[v] < f[u]
* (u; v) is back edge , d[v] < d[u] < f[u] < f[v]
* (u; v) is a cross edge , d[v] < f[v] < d[u] < f[u]
*/

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
			check(v,w);
			if(visited[w] == 0)
				dfs(w);
		}
		postvisit[v] = clock++;
		order.put(clock,v);
	}
    
    private boolean check(int u, int v) {
    	if(previsit[v] > 0 && postvisit[v] == 0)
			System.out.println("BackEdge " + (u+1) + "->" + (v+1));
		else if(previsit[u] > previsit[v] && previsit[v] == 0)
			System.out.println("FwdEdge " + (u+1) + "->" + (v+1));
		else 
			System.out.println("CrossEdge " + (u+1) + "->" + (v+1));
    	return true;
    }
    
    private void init(int[][] adjMatrix,int size) {
    	 this.size = size;
   	     this.adjMatrix = adjMatrix;
		 
		 clock = 1;
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
		  int Y[][] =
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
		  int X[][] = 
		  {
				  {0,1,1,0},
				  {0,0,0,1},
				  {0,1,0,1},
				  {1,0,0,0},
		  };
		  int size = X[0].length;
		  for(int i=0;i<size;i++)
			    for(int j=0;j<size;j++)
			    	if(X[i][j] == 1)
			    		System.out.println(i+1 + " " + (j+1));
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
