package com.vee.datastructures;

public class DisjointSetNode {
	public Object theObject;
	public DisjointSetNode next;
	public DisjointSet representative;

	public DisjointSetNode(Object x)
	{
		theObject = x;
		next = null;
		representative = null;
	}

	public String toString()
	{
		return theObject.toString();
	}
}
