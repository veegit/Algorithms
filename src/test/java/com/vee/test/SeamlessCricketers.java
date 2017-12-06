package com.vee.test;
/**
 * Problem:
 * 
 * You have a list of people with First Name (F) and Last Name (L)
 * F1L1,F2L2,F3L3,L3F2,F1L2,L2F1
 * Now you have to find the longest list of names which form a chain 
 * e.g. F3L3-L3F2-F2L2-L2F1 for above problem
 * 
 * Answer:
 * Looks a Hamiltonian Path but that's NP Hard, so an optimised solution
 * is needed.
 * 
 * This is part of a game which I played with some friends while young to list 
 * out names of cricketers in a chain
 * Saqlain Mushtaq Mohammed Amir Sohail Khan Mohammed Asif Iqbal Qasim Umar 
 * Gul Mohammed Wasim Akram Raza Hasan Raza = 17
 * The idea is to beat this list via an algo
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.LinkedListIterator;
import com.vee.algorithms.sort.QuickSort;
import com.vee.algorithms.util.Tuple;

import java.util.TreeMap;
import java.util.Vector;

public class SeamlessCricketers {
    public int[][] adjMatrix;
    private LinkedList<Name[]> edges;
    private LinkedList<Name> nodes;
    private TreeMap<Name,Integer> names;
    private TreeMap<Integer,Name> inv_names;
    private Vector<Name> vector;
    private ArrayList<Vector<Name>> array;
    
    public SeamlessCricketers() {
    	names = new TreeMap<Name, Integer>(new Comparator<Name>() {
			public int compare(Name name1, Name name2) {
				return name1.getFullName().compareTo(name2.getFullName());
			}
		});
    	inv_names = new TreeMap<Integer, Name>();
    }
    
    public void readFile(String file) {
    	BufferedReader bufferedReader = null;
    	try {
    		try {
    			String line;
    			int cnt = -1;
    			bufferedReader = new BufferedReader(new InputStreamReader(
    					getClass().getClassLoader().getResourceAsStream(file)));
    			
    			while((line = bufferedReader.readLine()) != null) {
    				Name name = new Name(line);
    				name.setId(++cnt);
    				names.put(name,cnt);
    				inv_names.put(cnt, name);
    			}
    		} 
    		finally {
    			bufferedReader.close();
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public void display() {
    	for (Entry<Name,Integer> entry: names.entrySet()) {
			System.out.println(entry.getKey().getFullName()+ "," + entry.getKey().getId());
		}
    }
    
    public void displayMatrix() {
    	for(int i=0;i<adjMatrix.length;i++) {
    		for(int j=0;j<adjMatrix.length;j++)
    			System.out.print(adjMatrix[i][j] + " ");
    		System.out.println();
    	}
    }
    
    public void prepareMatrix() {
    	adjMatrix = new int[names.size()][names.size()];
    	
    	for (Entry<Name,Integer> entry: names.entrySet()) {
    		for (Entry<Name,Integer> entry2: names.entrySet()) {
    			if(entry.getKey().getLastName().equals(entry2.getKey().getFirstName())) {
    				/*System.out.println(entry.getKey().getFullName().replaceAll(" ", "_") + 
    						            " " + 
    						           entry2.getKey().getFullName().replaceAll(" ", "_"));*/
    				adjMatrix[entry.getKey().getId()][entry2.getKey().getId()] = 1;
    			}
    		}
    	}
    	
    }
    
    public void longestDistanceinDAG() {
    	int[] length_to = new int[names.size()];
    	array = new ArrayList<Vector<Name>>();
    	for(int i =0;i<length_to.length;i++)
    		length_to[i] = 0;
    	
    	LinkedListIterator<Name> itr = nodes.iterator();
    	while(itr.hasNext()) {
    		Name name = itr.getNext();
    		LinkedListIterator<Name[]> itr1 = edges.iterator();
    		vector = new Vector<Name>();
        	vector.add(name);
    		while(itr1.hasNext()) {
    			Name[] edge = itr1.getNext();
    			if(name == edge[0])
    				if (length_to[edge[1].getId()] <= length_to[name.getId()] + 1) {
    					vector.add(edge[1]);
                    	length_to[edge[1].getId()] = length_to[edge[1].getId()] + 1;
                    }
    			array.add(vector);
    		}
    	}
    	Iterator<Vector<Name>> ita = array.iterator();
    	while(ita.hasNext()) {
    		Vector<Name> v = ita.next();
    		Iterator<Name> itv = v.iterator();
    		while(itv.hasNext()) {
    			Name name = itv.next();
    			System.out.print(name.getFullName() + " ");
    		}
    		System.out.println();
    	}
    }
    
    public void readNodes(String nodesFile) {
	    nodes = new LinkedList<Name>();
		BufferedReader bufferedReader = null;
		try {
			try {
				String line;
				bufferedReader = new BufferedReader(new FileReader(nodesFile));
				while((line = bufferedReader.readLine()) != null) {
					Name name = names.ceilingKey(new Name(line));
					nodes.insert(name);
				}
			} 
			finally {
				bufferedReader.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void readEdges(int[][] matrix) {
	    edges = new LinkedList<Name[]>();
		for(int i=0;i<matrix.length;i++)
			for(int j=0;i<matrix.length;j++)
				if(matrix[i][j] == 1)
				{
					Name[] edge = new Name[2];
					edge[0] = inv_names.get(i);
					edge[1] = inv_names.get(j);
					edges.insert(edge);
				}
    }
    
    public void readEdges(String edgesFile) {
    	edges = new LinkedList<Name[]>();
    	BufferedReader bufferedReader = null;
    	try {
    		try {
    			String line;
    			bufferedReader = new BufferedReader(new InputStreamReader(
    					getClass().getClassLoader().getResourceAsStream(edgesFile)));
    			while((line = bufferedReader.readLine()) != null) {
    				String[] edgeArray = line.split(",");
    				Name[] edge = new Name[2];
    		    	edge[0] = names.ceilingKey(new Name(edgeArray[0]));
    				edge[1] = names.ceilingKey(new Name(edgeArray[1]));
    				edges.insert(edge);
    				//edgesHash.put(edge[0], edge[1]);
    			}
    		} 
    		finally {
    			bufferedReader.close();
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @SuppressWarnings("unchecked")
	public void longestDistance() {
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
    	int[][] updatedAdjMatrix = new GraphExample(adjMatrix, adjMatrix.length).removeCycles();
    	GraphExample dfs = new GraphExample(updatedAdjMatrix, updatedAdjMatrix.length);
    	int[] topSorted = dfs.topSort();
    	int size = topSorted.length;
    	Tuple<Integer,String>[] length = new Tuple[size];
    	for(int i=0;i<size;i++)
    		length[i]=new Tuple<Integer,String>(0,"");
    	for(int i=0;i<size;i++)
    		for(int j=0;j<size;j++) 
    			if(updatedAdjMatrix[i][j] == 1) 
    				if(length[j].head <= length[i].head + 1) {
    					length[j].head = length[i].head + 1;
    					length[j].tail = (i)+ " " + length[i].tail;
    				}
    			
    	QuickSort<Tuple<Integer,String>> s = new QuickSort<Tuple<Integer,String>>(){
    		@Override
    		public int compare(Tuple<Integer,String> o1, Tuple<Integer,String> o2) {
				return o2.head - o1.head;
			}
    	};
    	//s.quickSort(length);
    	//adjMatrix = updatedAdjMatrix;displayMatrix();
    	for(int i=0;i<length.length;i++) {
    		String str="";
    		str += inv_names.get(i).getFullName();
    		str+= "\t";
    		String[] arr = length[i].tail.trim().split(" ");
    		for (int j = 0; j < arr.length-1; j++) {
				str += inv_names.get(Integer.parseInt(arr[j])).getFullName() + " ";
			}
    		//System.out.println(i+1 + "\t" + length[i].tail + "\t" + length[i].tail.split(" ").length);
    		System.out.println(str+"\t" + length[i].tail.split(" ").length);
    	}
   }
    
    public static void main(String args[]) {
    		SeamlessCricketers sc = new SeamlessCricketers();
    		sc.readFile("all_unique_players.txt");
    		sc.prepareMatrix();
    		//sc.display();
    		//sc.displayMatrix();
    		//sc.readNodes("topological_sorted.txt");
    		//sc.readEdges("edges.txt");
    		//sc.longestDistanceinDAG();
    		sc.longestDistance();
    		System.gc();
    		
    }
}

class Name {
	private String firstName;
	private String lastName;
	private String fullName;
	private int id;
	
	public Name(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Name(String fullName) {
		this.setFullName(fullName);
		String names[] = fullName.split(" ");
		setFirstName(names[0]);
		if(names.length == 1)
			setLastName(names[0]);
		setLastName(names[names.length-1]);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
