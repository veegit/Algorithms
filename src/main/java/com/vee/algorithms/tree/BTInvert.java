package com.vee.algorithms.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import com.vee.algorithms.datastructures.TreeNode;

/**
 * @author vee
 * @version 1.0
 *
 */
public class BTInvert {
	
	public void invert(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> q = new ArrayDeque<>();
        add(q, root);
        while (!q.isEmpty()) {
            TreeNode<Integer> node = q.poll();
            TreeNode<Integer> left = node.getLeft();
            TreeNode<Integer> right = node.getRight();
            node.setLeft(right);
            node.setRight(left);
            add(q, left);
            add(q, right);
        }
    };
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
            new BTInvert().invert(_1);
            root.preorder(_1);
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
            new BTInvert().invert(_9);
            root.preorder(_9);
			
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
            new BTInvert().invert(_9);
            root.preorder(_9);
		}
	}
	
}
