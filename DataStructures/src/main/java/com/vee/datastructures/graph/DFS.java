package com.vee.datastructures.graph;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;
import com.vee.datastructures.sort.QuickSort;
import com.vee.datastructures.util.Tuple;

public class DFS {
    private int adjMatrix[][];
    private int size;
    private int visited[];
	private int spanningForest[][];
	private int previsit[],postvisit[];
	private int clock;
	private LinkedList<Edge> edges;
	
	private void dfs(int v) {
		visited[v] = 1;
		previsit[v] = clock++;
		  for (int w=0; w<size; w++){
		    if (adjMatrix[v][w] != 0) {
		    	edges.insert(new Edge(v,w));
		    	if (visited[w] == 0) {
		    		spanningForest[v][w] = 1;
		    		dfs(w);
		    	}
		    }
		  }
		postvisit[v] = clock++;
	}
	
    public DFS(int[][] adjMatrix,int size) {
    	  init(adjMatrix,size);
    	  for (int v=0; v<size; v++)
		   if (visited[v] == 0)
		     dfs(v);
	}
    
    private void init(int[][] adjMatrix,int size) {
    	 this.size = size;
   	     this.adjMatrix = adjMatrix;
		 spanningForest = new int[size][size];
		 edges = new LinkedList<Edge>();
		 
		 clock = 0;
		 visited = new int[size];
		 previsit = new int[size];
		 postvisit = new int[size];
		 
		 for(int i=0;i<size;i++)
		    for(int j=0;j<size;j++)
		       spanningForest[i][j]=0;
		 
		 for (int v=0; v<size; v++) visited[v]=0;
	}
    
    public boolean hasCycles() {
    	LinkedListIterator<Edge> itr = edges.iterator();
    	while(itr.hasNext()) {
    		Edge edge = itr.getNext();
    		if(postvisit[edge.getFrom()] < postvisit[edge.getTo()])
    			return true;
    	}
    	return false;
    }
    
    private void removeCycle(LinkedListIterator<Edge> itr, int[][] newAdjMatrix) {
    	if(itr.hasNext()) {
    		Edge edge = itr.getNext();
    		if(postvisit[edge.getFrom()] < postvisit[edge.getTo()])
    			newAdjMatrix[edge.getFrom()][edge.getTo()] = 0;
    		removeCycle(itr,newAdjMatrix);
    	}
    }
    		
    public int[][] removeCycles() {
    	LinkedListIterator<Edge> itr = edges.iterator();
    	int[][] newAdjMatrix = adjMatrix;
    	while(itr.hasNext()) {
    		removeCycle(itr,newAdjMatrix);
    	}
    	return newAdjMatrix;
    }
    
    public int[] topSort() {
    	@SuppressWarnings("unchecked")
		Tuple<Integer,Integer>[] array= new Tuple[size];
    	int topSorted[] = new int[size];
    	
    	for(int i=0;i<postvisit.length;i++) {
    		array[i] = new Tuple<Integer,Integer>(i,postvisit[i]);
    	}
    	
    	QuickSort<Tuple<Integer,Integer>> s = new QuickSort<Tuple<Integer,Integer>>() {
    		@Override
    		public int compare(Tuple<Integer,Integer> o1, Tuple<Integer,Integer> o2) {
				return o2.tail - o1.tail;
			}
    	};
    	s.quickSort(array);
    	
    	for(int i=0;i<s.array.length;i++) {
    		topSorted[i] = s.array[i].head;
    	}
    	return topSorted;
    	
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
		  DFS dfs = new DFS(X,size);
		  int C[][] = dfs.getSpanningForest();
		  int previsit[] = dfs.getPrevisit();
		  int postvisit[] = dfs.getPostvisit();
		  for(int i=0;i<size;i++) {
		        for(int j=0;j<size;j++)
		                System.out.print(C[i][j] + " ");
		        System.out.println();
		  }
		  
		  System.out.println();
		  
		  for(int i=0;i<size;i++) {
		     System.out.print(previsit[i]+" ");
		  }
		  
		  System.out.println();
		  
		  for(int i=0;i<size;i++) {
			     System.out.print(postvisit[i]+" ");
		  }
		  
		  System.out.println();
		  
		  System.out.println(dfs.hasCycles());
		  
		  System.out.println();
		  
		  int[] topSorted = dfs.topSort();
		  for(int i=0;i<size;i++)
			 System.out.print(topSorted[i]+" ");
		  
		  System.out.println(dfs.hasCycles());
		  System.out.println();
		  int[][] newAdjMatrix = dfs.removeCycles();
		  for(int i=0;i<size;i++) {
		        for(int j=0;j<size;j++)
		                System.out.print(newAdjMatrix[i][j] + " ");
		        System.out.println();
		  }
		  System.out.println(new DFS(newAdjMatrix, size).hasCycles());
	}
	
	public int[][] getSpanningForest() {
		return spanningForest;
	}

	public int[] getPrevisit() {
		return previsit;
	}

	public int[] getPostvisit() {
		return postvisit;
	}
	
	public LinkedList<Edge> getEdges() {
		return edges;
	}
}
