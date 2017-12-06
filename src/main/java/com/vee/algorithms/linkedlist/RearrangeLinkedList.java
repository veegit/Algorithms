package com.vee.algorithms.linkedlist;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.Node;

/**
 * TODO
 * Examples:
Input:  1 -> 2 -> 3 -> 4
Output: 1 -> 4 -> 2 -> 3

Input:  1 -> 2 -> 3 -> 4 -> 5
Output: 1 -> 5 -> 2 -> 4 -> 3 

Soln
1) Find the middle point using tortoise and hare method.
2) Split the linked list in two halves using found middle point in step 1.
3) Reverse the second half.
4) Do alternate merge of first and second halves. 
 *
 */
public class RearrangeLinkedList {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.insertEnd(10);
		ll.insertEnd(11);
		ll.insertEnd(12);
		ll.insertEnd(13);
		ll.insertEnd(16);
		ll.display();
		
		Node<Integer> head = ll.getHeader();
		Node<Integer> node = reverse(head);
		while (node != null) {
			System.out.print(node.getData() + " ");
			node = node.getLink();
		}
	}
	
	static Node<Integer> reverse(Node<Integer> node) {
		Node<Integer> prev = null;
		Node<Integer> current = node.getLink();
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
}
