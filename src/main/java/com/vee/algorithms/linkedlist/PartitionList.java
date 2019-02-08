package com.vee.algorithms.linkedlist;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.Node;

public class PartitionList {

	void partition(LinkedList<Integer> list, int pivot) {
		Node<Integer> head = list.getHeader();
		Node<Integer> tail = list.getHeader();
		while (tail.getLink() != null) {
			tail = tail.getLink();
		}
		Node<Integer> curr = head;
		Node<Integer> last = tail;
		while (curr != last) {
			Node<Integer> next = curr.getLink();
			if (curr.getData() >= pivot) {
				tail.setLink(curr);
			}
			curr = next;
		}
	}
}