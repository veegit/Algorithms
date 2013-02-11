package com.vee.test;

import java.util.HashSet;
import java.util.Set;

import com.vee.datastructures.LinkedList;
import com.vee.datastructures.LinkedListIterator;

public class SubSets {

	public static void main(String[] args) {
		Set<Integer> s= new HashSet<Integer>();
		for (int i = 1; i <= 16; i++) {
			s.add(i);
		}
		subsets(s);
	}
	
	static void subsets(Set<Integer> s) {
		Integer []a = s.toArray(new Integer[0]);
		int i =0;
		LinkedList<String> list = new LinkedList<String>();
		list.insert("");
		while(i < a.length) {
			LinkedList<String> alist = new LinkedList<String>();
			LinkedListIterator<String> it = list.iterator();
			while(it.hasNext()) {
				alist.insert(it.getNext() + " " + a[i]);
			}
			list.join(alist);
			i++;
		}
		list.display();
	}
}
