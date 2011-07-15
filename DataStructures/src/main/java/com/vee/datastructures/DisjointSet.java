package com.vee.datastructures;

public class DisjointSet
{
	public DisjointSetNode head;
	public DisjointSetNode tail;
	public int size;

	public DisjointSet(DisjointSetNode x)
	{
		head = x;
		tail = x;
		size = 1;
	}
	
	public String toString()
	{
		String result = "";
		for (DisjointSetNode x = head; x != null; x = x.next)
			result += x.toString() + " ";
		return result;
	}
	
}