package com.vee.algorithms.linkedlist;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.Node;

public class RotateLinkedList {

	public static void main(String args[]) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.insertEnd(10);
		ll.insertEnd(11);
		ll.insertEnd(12);
		ll.insertEnd(13);
		ll.insertFirst(9);
		ll.insertEnd(16);
		ll.display();
		new RotateLinkedList().rotate(ll, 15);
	}
	
	public void rotate(LinkedList<Integer> ll, int k) {
		Node<Integer> head = ll.getHeader().getLink();
		Node<Integer> n = head;
		int len = 1;
		while (n.getLink() != null) {
			n = n.getLink();
			len++;
		}
		k = k%len;
		Node<Integer> first = head;
		Node<Integer> prev = head;
		while (k > 0) {
			prev = first;
			first = first.getLink();
			k--;
		}
		ll.getHeader().setLink(first);
		prev.setLink(null);
		n.setLink(head);
		ll.display();
	}
}
