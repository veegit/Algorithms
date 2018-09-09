package com.vee.algorithms.tree;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.vee.algorithms.datastructures.TreeNode;

public class BTMaxSum {

	public int maxPositive(TreeNode<Integer> root, int max, int sumTillNow) {
		if (root == null) {
			return Math.max(max, sumTillNow);
		}
		sumTillNow += root.getData();
		max = maxPositive(root.getLeft(), max, sumTillNow);
		max = maxPositive(root.getRight(), max, sumTillNow);
		return max;
	}
	
	@Test
	public void testMaxPositive() {
		{
			TreeNode<Integer> root = new TreeNode<Integer>(0);
			TreeNode<Integer> _1 = new TreeNode<Integer>(1);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(5);
			TreeNode<Integer> _6 = new TreeNode<Integer>(6);
			TreeNode<Integer> _7 = new TreeNode<Integer>(8);
			TreeNode<Integer> _8 = new TreeNode<Integer>(1);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
			/*
			 				1
			 			4
			 		2
			 	3		10
			 6		5
			 */
			
			_1.setLeft(_4);
			_4.setLeft(_2);
			_2.setLeft(_3);
			_3.setLeft(_6);
			_3.setRight(_5);
			_2.setRight(_7);
			System.out.println(maxPositive(_1, 0, 0));
		}
	}
}
