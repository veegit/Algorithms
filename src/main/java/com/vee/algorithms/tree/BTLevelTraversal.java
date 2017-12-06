package com.vee.algorithms.tree;

import java.util.ArrayDeque;
import java.util.Queue;

import com.vee.algorithms.datastructures.TreeNode;

/**
 * @author vee
 * @version 1.0
 *
 */
public class BTLevelTraversal {
	
	public void traverse(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> q = new ArrayDeque<TreeNode<Integer>>();
		q.add(root);
		int count = 1;
		int newcount = 0;
		while(!q.isEmpty()) {
			TreeNode<Integer> node = q.poll();
			count--;
			newcount += add(q,node.getLeft()) + add(q,node.getRight());
			System.out.print(node.getData() + " ");
			if(count == 0) { //TODO Trick is to check for zero or empty
				System.out.println();
				count = newcount;
				newcount = 0;
			}
		}
	}

	private int add(Queue<TreeNode<Integer>> q, TreeNode<Integer> n) {
		if(n == null)
			return 0;
		q.offer(n);
		return 1;
	}
	
	/**
	 * 
	 * Change
	 *              9
	 *            /   \
	 *           8     7
	 *          / \   / \
	 *         6   1 2   5
	 *         
	 * TO
	 * 
	 *         9
	 *        /
	 *       8 - 7
	 *      /
	 *     6 - 1 - 2 -5
	 */
	
	public void reconfig(TreeNode<Integer> root) {
		Queue<TreeNode<Integer>> q = new ArrayDeque<TreeNode<Integer>>();
		q.add(root);
		int count = 1;
		int newcount = 0;
		TreeNode<Integer> prev = root;
		TreeNode<Integer> firstnode = root;
		while(!q.isEmpty()) {
			TreeNode<Integer> node = q.poll();
			count--;
			newcount += add(q,node.getLeft()) + add(q,node.getRight());
			node.setLeft(null);
			node.setRight(null);
			if(count == 0) {
				count = newcount;
				newcount = 0;
				
				prev = q.peek();
				firstnode.setLeft(prev);
				firstnode = prev;
			}
			else {
				prev.setRight(q.peek());
				prev = q.peek();
			}
		}
	}
	
	private static void preorder(TreeNode<Integer> node) {
		if(node == null)
			return;
		System.out.print(node.getData() + " ");
		preorder(node.getLeft());
		preorder(node.getRight());
	}

	public static void main(String[] args) {
		{
			TreeNode<Integer> root = new TreeNode<Integer>(0);
			TreeNode<Integer> _1 = new TreeNode<Integer>(1);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(5);
			TreeNode<Integer> _6 = new TreeNode<Integer>(6);
			TreeNode<Integer> _7 = new TreeNode<Integer>(7);
			TreeNode<Integer> _8 = new TreeNode<Integer>(8);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
			_1.setLeft(_4);
			_4.setLeft(_2);
			_2.setLeft(_3);
			_3.setLeft(_5);
			_3.setRight(_6);
			new BTLevelTraversal().traverse(_1);
		}
		
		System.out.println("===============");
		
		{
			TreeNode<Integer> root = new TreeNode<Integer>(0);
			TreeNode<Integer> _1 = new TreeNode<Integer>(1);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(5);
			TreeNode<Integer> _6 = new TreeNode<Integer>(6);
			TreeNode<Integer> _7 = new TreeNode<Integer>(7);
			TreeNode<Integer> _8 = new TreeNode<Integer>(8);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_7.setRight(_5);
			_6.setLeft(new TreeNode<Integer>(_9));
			_5.setRight(new TreeNode<Integer>(_9));
			new BTLevelTraversal().traverse(_9);
			
		}
		
		System.out.println("===============");
		
		{
			TreeNode<Integer> root = new TreeNode<Integer>(0);
			TreeNode<Integer> _1 = new TreeNode<Integer>(1);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(5);
			TreeNode<Integer> _6 = new TreeNode<Integer>(6);
			TreeNode<Integer> _7 = new TreeNode<Integer>(7);
			TreeNode<Integer> _8 = new TreeNode<Integer>(8);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_8.setRight(_1);
			_7.setLeft(_2);
			_7.setRight(_5);
			new BTLevelTraversal().reconfig(_9);
			preorder(_9);
		}
	}
	
}
