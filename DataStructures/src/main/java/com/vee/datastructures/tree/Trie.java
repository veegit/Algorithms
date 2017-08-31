package com.vee.datastructures.tree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author narayanan_venkiteswaran, bragboy
 * @see <a href="http://www.technicalypto.com/2010/04/trie-data-structure-part-2-node-and.html">Blog</a> 
 */

public class Trie {
	private Node root = new Node('_', "");
	
	public void insert(String s){
		Node current = root;
		
		/* IMP: for empty character set isWord  = true */
		if(s.length() ==0)
			current.isWord = true;
		
		for(int i=0; i<s.length();i++){
			char ch = s.charAt(i);
			Node node = current.find(ch);
			if(node == null) {
				node = new Node(ch, current.wordTillNow + ch);
				current.children.add(node);
			}
			current = node;
			if(i == s.length()-1)
				current.isWord = true;
		}
	}
	
	public boolean search(String s){
		Node current = findNode(s);
		return current != null && current.isWord;
	}
	
	public List<String> autocomplete(String s, int count) {
		Node current = findNode(s);
		List<String> list = new ArrayList<String>();
		Deque<Node> q = new ArrayDeque<Node>();
		q.offer(current);
		while (count > 0 && !q.isEmpty()) {
			Node node = q.pop();
			if (node.isWord) {
				list.add(node.wordTillNow);
				count--;
			}
			q.addAll(node.children);
		}
		return list;
	}
	
	private Node findNode(String s) {
		Node current = root;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			Node node = current.find(ch);
			if (node == null)
				return node;
			else
				current = node;
		}
		return current;
	}
	
	public static void main(String args[]) {
		Trie t = new Trie();
		try(BufferedReader bf = new BufferedReader(new FileReader("/tmp/words_alpha.txt"))) {
			String word;
			while ((word = bf.readLine()) != null) {
				t.insert(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		long time = System.nanoTime();
		List<String> results = t.autocomplete("quintes", 10);
		System.out.println("Results in " + (System.nanoTime() - time) / 1000D + " micrsoseconds");
		System.out.println(results);
	}
}

class Node implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean isWord;
	char content;
	String wordTillNow;
	List<Node> children;
	
	public Node(char c, String s){
		content = c;
		isWord = false;
		wordTillNow = s;
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

	public String toString() {
		return String.format("%c, %s, %s", content, wordTillNow, isWord);
	}
}
