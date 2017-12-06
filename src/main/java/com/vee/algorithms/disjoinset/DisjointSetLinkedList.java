package com.vee.algorithms.disjoinset;

public class DisjointSetLinkedList {
	
	public DisjointSetNode makeSet(Object x)
	{
		DisjointSetNode node = new DisjointSetNode(x);
		DisjointSet disjointSet = new DisjointSet(node);
		node.representative = disjointSet;
		return node;
	}

	public void union(DisjointSetNode x, DisjointSetNode y)
	{
		DisjointSet xSet = findSet(x);
		DisjointSet ySet = findSet(y);
		if (xSet.size >= ySet.size)
			append(xSet, ySet);
		else
			append(ySet, xSet);
	}

	private void append(DisjointSet first, DisjointSet second)
	{
		if (first.size == 0 || second.size == 0) {
			System.err.println("Empty");
			return;
		}

		for (DisjointSetNode x = second.head; x != null; x = x.next)
			x.representative = first;

		first.tail.next = second.head;
		first.tail = second.tail;
		first.size += second.size;

		second.head = null;
		second.tail = null;
		second.size = 0;
	}

	public DisjointSet findSet(DisjointSetNode x)
	{
		return x.representative;
	}

	public String printSet(DisjointSetNode x)
	{
		DisjointSet set = findSet(x); 
		return set.toString();
	}
	
	public static void main(String args[]) {
		DisjointSetLinkedList ds = new DisjointSetLinkedList();
		DisjointSetNode n1 = ds.makeSet(10);
		DisjointSetNode n2 = ds.makeSet(20);
		DisjointSetNode n3= ds.makeSet(30);
		DisjointSetNode n4= ds.makeSet(40);
		ds.union(n1, n2);
		ds.union(n1, n3);
		System.out.println(ds.printSet(n4));
	}
}
