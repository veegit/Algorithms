package com.vee.algorithms.linkedlist;

import java.io.*;
import java.util.*;
import java.lang.Integer;

/* Given two iterators that return SORTED integers, build an iterator with a `next` method that
iterates over the intersection of it1 and it2, i.e. only the elements that appear in both.
Example:
Iterator<Integer> i1 = testIter(1, 2, 3, 4, 5, 6, 10)
Iterator<Integer> i2 = testIter(2, 3, 10, 11)
Output:
IntersectionIterator iter = IntersectionIterator(i1, i2)
iter.next() -> 2
iter.next() -> 3
iter.next() -> 10
The official Java iterator documentation can be found here: https://docs.oracle.com/javase/7/docs/api/java/util/Iterator.html
*/

public class IntersectionIterator implements Iterator<Integer> {
	Iterator<Integer> i1;
	Iterator<Integer> i2;
	Integer next;
	boolean hasNext = true;

	IntersectionIterator(Iterator<Integer> i1, Iterator<Integer> i2) {
		this.i1 = i1;
		this.i2 = i2;
	}

	// i1: 2, 5
	// i2: 3, 4, 5
	public Integer next() {
		if (hasNext()) {
			int current = next;
			try {
				next = getNextUtil();
				hasNext = true;
			} catch (NoSuchElementException e) {
				hasNext = false;
			}
			return current;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	private Integer getNextUtil() {
		Integer i1v = i1.next();
		Integer i2v = i2.next();

		while (!i1v.equals(i2v)) {
			if (i1v.compareTo(i2v) > 0) {
				i2v = i2.next();
			} else {
				i1v = i1.next();
			}
		}
		return i1v;
	}

	public boolean hasNext() {
		if (!hasNext) {
			return false;
		} else if (next != null) {
			return true;
		} else {
			try {
				next = getNextUtil();
				hasNext = true;
			} catch (NoSuchElementException e) {
				hasNext = false;
			}
			return hasNext;
		}
	}

	public static void main(String[] args) {
		IntersectionIterator it = new IntersectionIterator(testIter(1, 2, 3, 4, 5, 6, 10), testIter(2, 3, 10, 11));
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static Iterator<Integer> testIter(Integer... ints) {
		return Arrays.asList(ints).iterator();
	}

}
