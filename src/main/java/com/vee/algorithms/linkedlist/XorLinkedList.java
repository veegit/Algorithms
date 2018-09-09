package com.vee.algorithms.linkedlist;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Dispatch;

import org.junit.Test;

/*
 * An XOR linked list is a more memory efficient doubly linked list. 
 * Instead of each node holding next and prev fields, it holds a field named both, which is a XOR of the next node and the previous node. 
 * Implement a XOR linked list; it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.
 */

public class XorLinkedList {
	
	Node<Integer> head;
	Map<Integer, Node<Integer>> map = new HashMap<>();
	
	public void add(int element) {
		Node<Integer> node = new Node<>();
		node.data = element;
		node.both = 0;
		if (head == null) {
			head = node;
		} else if (head.both == 0){
			node.both = head.hashCode();
			head.both = node.hashCode();
		} else {
			Node<Integer> prev = head;
			Node<Integer> current = map.get(head.both);
			while (current.both != prev.hashCode()) {
				Node<Integer> next = map.get(prev.hashCode() ^ current.both);
				prev = current;
				current = next;
			}
			node.both = node.both ^ current.hashCode();
			current.both = prev.hashCode() ^ node.hashCode();
		}
		map.put(node.hashCode(), node);
	}
	
	public void display() {
		Node<Integer> prev = head;
		Node<Integer> current = map.get(head.both);
		while (current !=  null && current.both != prev.hashCode()) {
			System.out.print(current.data + "->");
			Node<Integer> next = map.get(prev.hashCode() ^ current.both);
			prev = current;
			current = next;
		}
		System.out.println(prev.data);
	}

	@Test
	public void test() {
		add(1);
		display();
		add(2);
		add(3);
		add(4);
		add(5);
		display();
	}
}

class Node<T> {
	T data;
	Integer both;
}


/*
 *   011 -> 010 -> 110 -> 100 
 * 
 *   010 -> 101 -> 110 -> 110
 * 
 *0  a      b      c       d
 *         a^c
 *         carry = a^b
 *         both = a^c
 *         self = b
 *         next = carry ^ both ^ self
 * */
