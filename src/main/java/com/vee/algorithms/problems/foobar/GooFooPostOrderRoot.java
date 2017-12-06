package com.vee.algorithms.problems.foobar;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.vee.algorithms.datastructures.TreeNode;
import com.vee.algorithms.util.Tuple;

//FIXME

public class GooFooPostOrderRoot {

	/*
	 * Oh no! Commander Lambda's latest experiment to improve the efficiency of
	 * her LAMBCHOP doomsday device has backfired spectacularly. She had been
	 * improving the structure of the ion flux converter tree, but something
	 * went terribly wrong and the flux chains exploded. Some of the ion flux
	 * converters survived the explosion intact, but others had their position
	 * labels blasted off. She's having her henchmen rebuild the ion flux
	 * converter tree by hand, but you think you can do it much more quickly -
	 * quickly enough, perhaps, to earn a promotion!
	 * 
	 * Flux chains require perfect binary trees, so Lambda's design arranged the
	 * ion flux converters to form one. To label them, she performed a
	 * post-order traversal of the tree of converters and labeled each converter
	 * with the order of that converter in the traversal, starting at 1. For
	 * example, a tree of 7 converters would look like the following:
	 * 
	 *         7
     		 /   \
   		    3      6
  		   /  \   / \
 		  1    2 4   5
	 * 
	 * Write a function answer(h, q) - where 
	 * h is the height of the perfect tree of converters and,
	 * q is a list of positive integers representing different flux converters - 
	 * which returns a list of integers p where each element in p is the label of the converter that
	 * sits on top of the respective converter in q, or -1 if there is no such
	 * converter. 
	 * For example, answer(3, [1, 4, 7]) would return the converters above the converters
	 * at indexes 1, 4, and 7 in a perfect binary tree of height 3, which is [3, 6, -1].
	 * 
	 * The domain of the integer h is 1 <= h <= 30, where 
	 * h = 1 represents a perfect binary tree containing only the root, 
	 * h = 2 represents a perfect binary tree with the root and two leaf nodes, 
	 * h = 3 represents a perfect binary tree with the root, two internal nodes and four leaf nodes (like the example above), 
	 * and so forth. 
	 * The lists q and p contain at least one but no more than 10000 distinct integers, all of which will be between 1 and 2^h-1, inclusive. 
	 * The test cases provided are:
	 * 
	 * Inputs:
	 * (int) h = 3 (int list) q = [7, 3, 5, 1] 
	 * 
	 * Output:
	 * (int list) [-1, 7, 6, 3] 
	 * 
	 * Inputs:
	 * (int) h = 5 (int list) q = [19, 14, 28] 
	 * 
	 * Output:
	 * (int list) [21, 15, 29]
	 */
	@Test
	public void test0() {
		int h = 2;
		int[] q = new int[] {1,2,3,4,5,6,7};
		Map<Integer, Integer> map = new LinkedHashMap<>();
		for (int i=0; i< q.length; i++) {
			map.put(q[i], 0);
		}
		int max = (int) (Math.pow(2, h)-1);
		TreeNode<Integer> node = new TreeNode<>(max);
		build(node, new AtomicInteger(max), h-1, true);
		//node.postorder(node);
		search(node, map);
		System.out.println(map);
	}
	
	private void search(TreeNode<Integer> node, Map<Integer, Integer> map) {
		if(node == null)
			return;
		if (map.containsKey(node.getData()) && map.get(node.getData()) == 0) {
			int parent = node.getParent() == null ? -1 : node.getParent().getData();
			map.put(node.getData(), parent);
		}
		search(node.getLeft(), map);
		search(node.getRight(), map);
	}
	
	private void build(TreeNode<Integer> parent, AtomicInteger val, int height, boolean isRight) {
		if (val.get() == 0 || parent == null) return;
		System.out.println("1. " + val + " " + isRight + " " + height + " " + parent.getData());
		if (height == 0 || 
				parent.getLeft() != null && parent.getRight() != null) {
			return;
		}
		TreeNode<Integer> newNode = new TreeNode<>(val.decrementAndGet());
		parent.setRight(newNode);
		newNode.setParent(parent);
		//System.out.println("2. " + val + " " + "true" + " " + height + " " + parent.getData());
		build(newNode, val, height-1, true);
		//System.out.println("3. " + val + " " + "false" + " " + height + " " + parent.getData());
		newNode = new TreeNode<>(val.decrementAndGet());
		parent.setLeft(newNode);
		newNode.setParent(parent);
		build(newNode, val, height-1, false);
	}
}
