package com.vee.algorithms.datastructures;

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
 * @author vee, bragboy
 * @see <a href="http://www.technicalypto.com/2010/04/trie-data-structure-part-2-node-and.html">Blog</a> 
 */

public class Trie {
	private TrieNode root = new TrieNode('_', "");
	
	public void insert(String s){
		TrieNode current = root;
		
		/* IMP: for empty character set isWord  = true */
		if(s.length() ==0)
			current.isWord = true;
		
		for(int i=0; i<s.length();i++){
			char ch = s.charAt(i);
			TrieNode node = current.find(ch);
			if(node == null) {
				node = new TrieNode(ch, current.wordTillNow + ch);
				current.children.add(node);
			}
			current = node;
			if(i == s.length()-1)
				current.isWord = true;
		}
	}
	
	public boolean search(String s){
		TrieNode current = findNode(s);
		return current != null && current.isWord;
	}
	
	public List<String> autocomplete(String s, int count) {
		TrieNode current = findNode(s);
		List<String> list = new ArrayList<String>();
		Deque<TrieNode> q = new ArrayDeque<TrieNode>();
		q.offer(current);
		while (count > 0 && !q.isEmpty()) {
			TrieNode node = q.pop();
			if (node.isWord) {
				list.add(node.wordTillNow);
				count--;
			}
			q.addAll(node.children);
		}
		return list;
	}
	
	private TrieNode findNode(String s) {
		TrieNode current = root;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			TrieNode node = current.find(ch);
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

class TrieNode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	boolean isWord;
	char content;
	String wordTillNow;
	List<TrieNode> children;
	
	public TrieNode(char c, String s){
		content = c;
		isWord = false;
		wordTillNow = s;
		children = new LinkedList<TrieNode>();
	}
	
	/* Watch the return statements */
	public TrieNode find(char c) {
		TrieNode node = null;
		for (Iterator<TrieNode> iterator = children.iterator(); iterator.hasNext();) {
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
