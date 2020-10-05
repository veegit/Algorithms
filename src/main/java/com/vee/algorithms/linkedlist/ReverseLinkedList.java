package com.vee.algorithms.linkedlist;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.Node;

public class ReverseLinkedList {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.insertEnd(10);
		ll.insertEnd(11);
		ll.insertEnd(12);
		ll.insertEnd(13);
		ll.insertEnd(16);
		ll.display();
		
		Node<Integer> head = ll.getHeader();
		Node<Integer> node = reverseInPlace(head);
		while (node != null) {
			System.out.print(node.getData() + " ");
			node = node.getLink();
		}
	}
	
	static Node<Integer> reverseInPlace(Node<Integer> node) {
		Node<Integer> prev = null;
		Node<Integer> current = node.getLink();
		System.out.println(current);
		Node<Integer> next = null;
		while (current != null) {
			next = current.getLink();
			current.setLink(prev);
			prev = current;
			current = next;
			System.out.println("\t" + prev + " " + current + " " + next);
		}
		node = prev;
		return node;
	}
	
	 // FIXME Reverse and return a copy
	
	static Node<Integer> reverseAndCopy(Node<Integer> node) {
		return null;
	}
}
