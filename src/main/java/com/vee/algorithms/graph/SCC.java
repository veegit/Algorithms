package com.vee.algorithms.graph;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class SCC {
	/**
	 * Assignment 4 
	 * Download the text file here. Zipped version here.
	 * http://spark-public.s3.amazonaws.com/algo1/programming_prob/SCC.zip 
	 * 
	 * The file contains the edges of a directed graph. Vertices are labeled as
	 * positive integers from 1 to 875714. Every row indicates an edge, the
	 * vertex label in first column is the tail and the vertex label in second
	 * column is the head (recall the graph is directed, and the edges are
	 * directed from the first column vertex to the second column vertex). So
	 * for example, the 11th row looks liks : "2 47646". This just means that
	 * the vertex with label 2 has an outgoing edge to the vertex with label
	 * 47646
	 * 
	 * Your task is to code up the algorithm from the video lectures for
	 * computing strongly connected components (SCCs), and to run this algorithm
	 * on the given graph.
	 * 
	 * Output Format: You should output the sizes of the 5 largest SCCs in the
	 * given graph, in decreasing order of sizes, separated by commas (avoid any
	 * spaces). So if your algorithm computes the sizes of the five largest SCCs
	 * to be 500, 400, 300, 200 and 100, then your answer should be
	 * "500,400,300,200,100". If your algorithm finds less than 5 SCCs, then
	 * write 0 for the remaining terms. Thus, if your algorithm computes only 3
	 * SCCs whose sizes are 400, 300, and 100, then your answer should be
	 * "400,300,100,0,0".
	 * 
	 * WARNING: This is the most challenging programming assignment of the
	 * course. Because of the size of the graph you may have to manage memory
	 * carefully. The best way to do this depends on your programming language
	 * and environment, and we strongly suggest that you exchange tips for doing
	 * this on the discussion forums.
	 * 
	 * Answer = 434821,968,459,313,211
	 */
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
