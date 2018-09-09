package com.vee.algorithms.linkedlist;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ListOfListIterator<T> implements Iterator<T> {
	
	Iterator<List<T>> rowIter;
	Iterator<T> cellIter;
	
	ListOfListIterator(List<List<T>> list) {
		rowIter = list.iterator();
		cellIter = rowIter.next().iterator();
	}

	@Override
	public boolean hasNext() {
		return cellIter != null && cellIter.hasNext();
	}

	@Override
	public T next() {
		T val = cellIter.next();
		while (!hasNext() && rowIter.hasNext()) {
			cellIter = rowIter.next().iterator();
		}
		return val;
	}
	
	public void remove() {
		
	}
	
	public static void main(String args[]) {
		List<Integer> l1 = Arrays.asList(1,2);
		List<Integer> l2 = Arrays.asList(11,22,33);
		List<Integer> l3 = Arrays.asList(111,222,333);
		List<List<Integer>> list = Arrays.asList(l1, l2, l3);
		ListOfListIterator<Integer> it = new ListOfListIterator<>(list);
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
