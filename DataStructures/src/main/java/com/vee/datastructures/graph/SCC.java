package com.vee.datastructures.graph;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;

public class SCC {
	String filename;
	public int scc[];
	boolean reverse=false;
	private int size, cnt = 0;
    private int visited[],previsit[],postvisit[];
	private int clock;
	List<Integer>[] adjList, adjListRev;
	TreeMap<Integer,Integer> order = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) {return o2 - o1;};
	});
	
	public SCC(int size,String filename) {
	  this.filename=filename;
  	  init(size);
  	}
	
	public void DFSLoop() {
		reverse = false;
		for (Entry<Integer, Integer> entry : this.order.entrySet()){
			  cnt = entry.getValue();
		      if (visited[entry.getValue()] == 0) {
				   dfs(entry.getValue());
		      }
		      //System.out.println();
		}
	}
	
	public void DFSLoopRev() {
		reverse = true;
		for (int v=size-1; v>0; v--)
			   if (visited[v] == 0)
				   dfsRev(v);
	}
	
	private void dfsRev(int v) {
		visited[v] = 1;
		previsit[v] = clock++;
		List<Integer> vList;
		vList = adjListRev[v];
		if(vList != null)
			for (Iterator<Integer> iterator = vList.iterator(); iterator.hasNext();) {
				int w = iterator.next();
				if(visited[w] == 0)
					dfsRev(w);
		}
		postvisit[v] = clock++;
		order.put(clock,v);
	}
    
    private void dfs(int v) {
		visited[v] = 1;
		previsit[v] = clock++;
		List<Integer> vList;
		vList = adjList[v];
		scc[cnt]++;
		if(vList != null)
			for (Iterator<Integer> iterator = vList.iterator(); iterator.hasNext();) {
				int w = iterator.next();
				if(visited[w] == 0)
					dfs(w);
		}
		postvisit[v] = clock++;
	}
    
    private void init(int size) {
    	 this.size = size;
   	     visited = new int[size];
		 previsit = new int[size];
		 postvisit = new int[size];
		 scc = new int[size];
		 reset();
		 toAdjList();
	}
    
    private void reset() {
   	   	 clock = 0;
		 for (int v=1; v<size; v++) {
			 visited[v]=0;
			 previsit[v]=0;
			 postvisit[v]=0;
			 scc[v]=0;
		 }
		 adjListRev = null;
		 //order.clear();
	}
   
    
    @SuppressWarnings("unchecked")
	private void toAdjList() {
    	
    	adjList = new LinkedList[size];
    	adjListRev = new LinkedList[size];
    	int lines = 0;
    	try {
		    //File file = new File(filename);
			Scanner scanner =  new Scanner(new InputStreamReader(this.getClass().
		    		getClassLoader().getResourceAsStream(filename)));
		    while(scanner.hasNextLine()) {
			   int start = scanner.nextInt();
			   int end = scanner.nextInt();
			   if(lines++ % 200000 == 0) {}
				   //System.out.println("Lines " + lines);
			   if(adjList[start] == null) {
				   List<Integer> adj = new LinkedList<Integer>();
				   adjList[start] = adj;
			   }
			   adjList[start].add(end);
			   if(adjListRev[end] == null) {
				   List<Integer> adj = new LinkedList<Integer>();
				   adjListRev[end] = adj;
			   }
			   adjListRev[end].add(start);
		    }
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	}
    
    public void display(boolean flag) {
    	  if(!flag)
    		  return;
    	  System.out.println(reverse);
    	  System.out.println("PreVisit");
		  
		  for(int i=1;i<size;i++) {
		     System.out.print(previsit[i]+" ");
		  }
		  
		  System.out.println("\nPostVisit");
		  
		  for(int i=1;i<size;i++) {
			     System.out.print(postvisit[i]+" ");
		  }
		  System.out.println("\nOrdering");
		  for (Entry<Integer, Integer> entry : this.order.entrySet())
		      System.out.print(entry.getValue()+" ");
		  System.out.println();
    }
    
    public static void main(String args[]) {
			//875714
    	  int size = 9+1;
    	  //System.out.println("Load Graph");
		  SCC dfs = new SCC(size,"scc9_1.txt");
		  //System.out.println("Start DFS Reverse");
		  dfs.DFSLoopRev();
		  dfs.display(false);
		  //System.out.println("Start Reset");
		  dfs.reset();
		  //System.out.println("Calc SCC");
		  dfs.DFSLoop();
		  dfs.display(false);
		  Arrays.sort(dfs.scc);
		  for (int i = size-1; i > size-6; i--)
			System.out.print(dfs.scc[i] + ",");
	}
	
	public int[] getPrevisit() {
		return previsit;
	}

	public int[] getPostvisit() {
		return postvisit;
	}
}
