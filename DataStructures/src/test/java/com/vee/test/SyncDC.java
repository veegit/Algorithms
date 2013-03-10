package com.vee.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Narayanan
 * NOT WORKING YET
 * Used a set datastructure to get the list of all dataitems
 * Make a powerset out of it
 * Create a Map by dataset id to see on which datacenter set the dataset exists
 * Eventually in the  Map each dataset id should have a list of all datacenters
 *

public class SyncDC {
	
	public static void main(String[] args) {
		new SyncDC().init();
	}

	public void init() {
		Scanner scan = new Scanner(System.in);
		int count = Integer.parseInt(scan.nextLine());
		int i = 0;
		datacenter[] dcs = new datacenter[count];
		Set<Integer> power = new HashSet<Integer>();
		Map<Integer,List<datacenter>> map = 
			new HashMap<Integer, List<datacenter>>();
		
		while(i<count) {
			String text = scan.nextLine();
			String splits[] = text.split("\\s");
			System.out.println(text);
			datacenter dc = new datacenter(); 
			for (int j = 0; j < splits.length; j++) {
				Integer data = Integer.parseInt(splits[j]);
				dc.add(i,data);
			}
			dcs[i] = dc;
			power.addAll(dc.data);
			i++;
		}
		
		for (Iterator<Integer> iterator = power.iterator(); iterator.hasNext();) {
			int integer = iterator.next();
			datacenter dc = null;
			for(i =0; i<dcs.length;i++) {
				if(dcs[i].contains(integer)) {
					dc = dcs[i];
				} else
					dcs[i].missing(integer);
			}
			List<datacenter> list = map.get(integer);
			if(list == null) {
				list = new ArrayList<datacenter>();
			}
			list.add(dc);
			map.put(integer, list);
		}
		
		for(i = 0;i< dcs.length;i++){
			for (Iterator<Integer> iterator = dcs[i].missing.iterator(); iterator.hasNext();){
				int item = iterator.next();
				System.out.print(dcs[i].id+ " " + item);
			}
			System.out.println();
		}
		
		for(i = 0;i< dcs.length;i++){
			for (Iterator<Integer> iterator = dcs[i].missing.iterator(); iterator.hasNext();){
				int item = iterator.next();
				datacenter dc = map.get(item).get(0);
				System.out.println(item + " " + dc.id + " " + dcs[i].id);
			}
		}

	}
	
	class datacenter {
		int id;
		Map<Integer,Integer> data = new HashMap<Integer,Integer>();
		Map<Integer,Integer> missing = new HashMap<Integer,Integer>();
		
		void add(int i,int data){
			id=i+1;
			Integer cnt = this.data.get(data);  
			if(cnt == null)
				this.data.put(data);
		}
		
		boolean contains(int data){
			return this.data.contains(data);
		}
		
		void missing(int data){
			this.missing.add(data);
		}
	}
	
	class dataset {
		int item;
		int occurrence;
		
		dataset(int item,int occurrence){
			this.item = item;
			this.occurrence = occurrence;
		}
		
		public boolean equals(Object o) {
			return 
			(item == ((dataset)o).item && 
					occurrence == ((dataset)o).occurrence);
		}
	}

}
*/