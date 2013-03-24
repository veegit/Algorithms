package com.vee.datastructures.disjoinset;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DisjointSetArray {
	int setCount;
	int node[];
	int size[];
	
	public void init(int N) {
		node = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			node[i] = i;
			size[i] = 1;
		}
		setCount = N;
	}
	
	public int find(int item) {
		while(node[item] != item) 
			item = node[item];
	    return item;
	}
	
	public void union(int item1, int item2){
		int itemRoot1 = find(item1);
		int itemRoot2 = find(item2);
		
		if(itemRoot1 == itemRoot2) return;
		
		if(size[itemRoot1] > size[itemRoot2]) {
			node[itemRoot2] = itemRoot1;
			size[itemRoot1] += size[itemRoot2];
		}
		else {
			node[itemRoot1] = itemRoot2;
			size[itemRoot2] += size[itemRoot1];
		}
		setCount--;
	}
	
	public static void main(String args[]) {
		new DisjointSetArray().readFile("tinyDisjointSet.txt");
	}
	
	public void readFile(String file) {
		BufferedReader bufferedReader = null;
    	try {
    		try {
    			String line;
    			bufferedReader = new BufferedReader(new InputStreamReader(
    					getClass().getClassLoader().getResourceAsStream(file)));
    			int N = Integer.parseInt(bufferedReader.readLine());
    			init(N);
    			while((line = bufferedReader.readLine()) != null) {
    				union(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
    			}
    			System.out.println(setCount);
    		} 
    		finally {
    			bufferedReader.close();
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
	}
}
