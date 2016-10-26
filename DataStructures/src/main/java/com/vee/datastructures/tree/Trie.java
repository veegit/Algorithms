package com.vee.datastructures.tree;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author narayanan_venkiteswaran, bragboy
 * @see <a href="http://tech.bragboy.com/2010/04/trie-data-structure-part-2-node-and.html">Blog</a> 
 */

public class Trie {
	private Node root = new Node('_');
	
	public void insert(String s){
		Node current = root;
		
		/* IMP: for empty character set isWord  = true */
		if(s.length() ==0)
			current.isWord = true;
		
		for(int i=0; i<s.length();i++){
			char ch = s.charAt(i);
			Node node = current.find(ch);
			if(node == null) {
				node = new Node(ch);
				current.children.add(node);
			}
			current = node;
			if(i == s.length()-1)
				current.isWord = true;
		}
	}
	
	public boolean search(String s){
		Node current = root;
		while(current != null){
			for(int i=0; i<s.length();i++){
				char ch = s.charAt(i);
				Node node = current.find(ch);
				if(node == null)
					return false;
				else 
					current = node;
			}	
			if(current.isWord)
				return true;
			else
				return false;
		}
		return false;
	}
	
	public static void main(String args[]) {
		Trie t = new Trie();
		t.insert("hello");
		t.insert("hell");
		t.insert("helium");
		t.insert("thelium");
		t.insert("hellboy");
		
		System.out.println(t.search("hell"));
		System.out.println(t.search("hellish"));
	}
	
}

class Node {
	boolean isWord;
	char content;
	LinkedList<Node> children;
	
	public Node(char c){
		content = c;
		isWord = false;
		children = new LinkedList<Node>();
	}
	
	/* Watch the return statements */
	public Node find(char c) {
		Node node = null;
		for (Iterator<Node> iterator = children.iterator(); iterator.hasNext();) {
			node = iterator.next();
			if(node.content == c)
				return node;
		}
		return null;
	}
}
