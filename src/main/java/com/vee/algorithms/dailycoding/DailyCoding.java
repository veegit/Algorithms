package com.vee.algorithms.dailycoding;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

import org.junit.Test;
import org.testng.internal.collections.Pair;

import com.vee.algorithms.datastructures.LinkedList;
import com.vee.algorithms.datastructures.Node;

public class DailyCoding {

	@Test
	public void test() {
		Stack<Pair<String, Integer>> stack = new Stack<>();
		String str1 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
		String str2 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
		String tokens[] = str1.split("\n");
		int length = tokens[0].length();
		stack.push(Pair.of(tokens[0], length));
		int lastTabCount = 0;
		
		int max = 0;
		for (int i = 1; i< tokens.length; i++) {
			String token = tokens[i];
			int tabCount = token.lastIndexOf("\t")+1;
			String word = token.substring(tabCount);
			//System.out.print(word + " " + tabCount + " " + lastTabCount + " " + max + " " + length + "-> ");
			if (tabCount <= lastTabCount) {
				max = Math.max(max, length);
				for (int k =lastTabCount ; k <= tabCount; k++) {
					stack.pop();
				}
				length = stack.peek().second();
			}
			length += word.length()+1;
			//System.out.println(word + " " + tabCount + " " + lastTabCount + " " + max + " " + length);
			stack.push(Pair.of(word, length));
			lastTabCount = tabCount;
		}
		System.out.println(max);
	}
	
	@Test
	public void problem27_ValidBraces() {
		Stack<Character> stack = new Stack<>();
		String str1 = "([])[]({})";
		String str2 = "([)]";
		String str3 = "((()";
		boolean valid = true;
		Map<Character, Character> map = new HashMap<Character, Character>() { { put('}', '{');  put(']', '[');  put(']', '['); } }; 
		for (char ch : str2.toCharArray()) {
			if (map.keySet().contains(ch)) {
				if (stack.peek() == map.get(ch)) {
					stack.pop();
				} else {
					valid = false;
					break;
				}
			} else {
				stack.push(ch);
			}
		}
		System.out.println(valid);
	}
	
	@Test
	public void problem26_KthLastLinkedList() {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		IntStream.range(1, 21).forEach(i -> ll.insert(i));
		int k = 2;
		Node<Integer> n1 = ll.getHeader();
		Node<Integer> n2 = ll.getHeader();
		while (k-- > 0) {
			n1 = n1.getLink();
		}
		while (n1 != null) {
			n1 = n1.getLink();
			n2 = n2.getLink();
		}
		System.out.println(n2.getData());
	}
	
	@Test
	public void problem20_LinkedListIntersection() {
		LinkedList<Integer> ll1 = new LinkedList<Integer>();
		LinkedList<Integer> ll2 = new LinkedList<Integer>();
		IntStream.range(1, 21).forEach(i -> ll1.insert(i));
		IntStream.range(1, 5).forEach(i -> ll2.insert(i*100));
		Node<Integer> n = ll1.find(17);
		ll2.findLast().setLink(n);
		
		Node<Integer> node = ll1.getHeader();
		int c1 = 0, c2 = 0, diff = 0;
		while (node != null) {
			node = node.getLink();
			c1++;
		}
		node = ll2.getHeader();
		while (node != null) {
			node = node.getLink();
			c2++;
		}
		Node<Integer> larger;
		Node<Integer> smaller;
		if (c1 > c2) {
			larger = ll1.getHeader();
			smaller = ll2.getHeader();
		} else {
			larger = ll2.getHeader();
			smaller = ll1.getHeader();
		}
		diff = Math.abs(c1-c2);
		while (diff-- > 0) {
			larger = larger.getLink();
		}
		while (larger!= null && larger != smaller) {
			larger = larger.getLink();
			smaller = smaller.getLink();
		}
		System.out.println(larger.getData());
	}
	
	@Test
	public void problem28_runLengthEncoding() {
		String str = "AAAABBCCD";
		String out = "";
		int count = 1;
		char ch = str.charAt(0);
		for (int i = 1; i < str.length(); i++) {
			if (str.charAt(i) == ch) {
				count++;
			} else {
				out += count < 2 ? ch + "" : count + "" + ch;
				count = 1;
			}
			ch = str.charAt(i);
		}
		out += count < 2 ? ch + "" : count + "" + ch;
		System.out.print(str + " " + out + " ");
		
		String in = "";
		for (int i = 0; i < out.length(); i++) {
			char c = out.charAt(i);
			if (Character.isDigit(c)) {
				count = Character.getNumericValue(c);
			} else {
				while (count-- > 0) {
					in += "" + c;
				}
				count = 1;
			}
		}
		System.out.println(in);
	}
	
	@Test
	/*
	 * You are given an array of non-negative integers that represents a
	 * two-dimensional elevation map where each element is unit-width wall and
	 * the integer is the height. Suppose it will rain and all spots between two
	 * walls get filled up. Compute how many units of water remain trapped on
	 * the map in O(N) time and O(1) space. For example, given the input 
	 * [2, 1, 2], we can hold 1 unit of water in the middle. Given the input 
	 * [3, 0, 1, 3, 0, 5], we can hold 3 units in the first index, 2 in the second, and 3
	 * in the fourth index (we cannot hold 5 since it would run off to the
	 * left), so we can trap 8 units of water.
	 */
	public void problem30_heightOfWater() {
		
	}

	@Test
	/*
	Given a list of words, return the shortest unique prefix of each word. For example, given the list:

	dog
	cat
	apple
	apricot
	fish
	Return the list:

	d
	c
	app
	apr
	f
	*/
	public void problem162_uniquePrefix() {

	}
}
