import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.File;

public class DFSNew {
	String filename;
	final static int CAPACITY = 100;
	boolean reverse=false;
	private int size;
    private int visited[];
	private int previsit[],postvisit[];
	private int clock;
	List<Integer>[] adjList;
	List<Integer>[] adjListRev;
	TreeMap<Integer,Integer> order = new TreeMap<Integer,Integer>(new Comparator<Integer>() {
		public int compare(Integer o1, Integer o2) {return o2 - o1;};
	});
	
	public DFSNew(int size,String filename) {
	  this.filename=filename;
  	  init(size);
  	}
	
	public void DFSLoop() {
		reverse = false;
		for (Entry<Integer, Integer> entry : this.order.entrySet())
		      if (visited[entry.getValue()] == 0)
				   dfs(entry.getValue());
	}
	
	public void DFSLoopRev() {
		reverse = true;
		for (int v=size-1; v>0; v--)
			   if (visited[v] == 0)
				   dfs(v);
	}
    
    private void dfs(int v) {
		visited[v] = 1;
		previsit[v] = clock++;
		List<Integer> vList;
		if(reverse)
			vList = adjListRev[v];
		else {
			vList = adjList[v];
			System.out.print(v+" ");
		}
		if(vList != null)
			for (Iterator<Integer> iterator = vList.iterator(); iterator.hasNext();) {
				int w = iterator.next();
				if(visited[w] == 0)
					dfs(w);
		}
		postvisit[v] = clock++;
		if(reverse)
			order.put(clock,v);
		else
			System.out.println();
	}
    
    private void init(int size) {
    	 this.size = size;
   	     visited = new int[size];
		 previsit = new int[size];
		 postvisit = new int[size];
		 reset();
		 toAdjList();
	}
    
    private void reset() {
   	   	 clock = 0;
		 for (int v=1; v<size; v++) {
			 visited[v]=0;
			 previsit[v]=0;
			 postvisit[v]=0;
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
		    File file = new File(filename);
			Scanner scanner = new Scanner (file);
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
    	  int size = Integer.parseInt(args[1]);
    	  //System.out.println("Load Graph");
		  DFSNew dfs = new DFSNew(size,args[0]);
		  //System.out.println("Start DFS Reverse");
		  dfs.DFSLoopRev();
		  dfs.display(false);
		  //System.out.println("Start Reset");
		  dfs.reset();
		  //System.out.println("Calc SCC");
		  dfs.DFSLoop();
		  dfs.display(false);
	}
	
	public int[] getPrevisit() {
		return previsit;
	}

	public int[] getPostvisit() {
		return postvisit;
	}
}
