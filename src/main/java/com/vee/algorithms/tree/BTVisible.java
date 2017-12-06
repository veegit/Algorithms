package com.vee.algorithms.tree;

import com.vee.algorithms.datastructures.TreeNode;

public class BTVisible {

	/**
	 * Find the number of "visible" nodes in a binary tree. A node is a
	 * "visible" node if the path from root to that node does not encounter any
	 * node of value higher than that node.
	 */
	int visible(TreeNode<Integer> root) {
		return visible(root,root.getData());
	}
	
	int visible(TreeNode<Integer> root, int max) {
		if(root == null)
			return 0;
		if(max > root.getData())
			return visible(root.getLeft(),max) + visible(root.getRight(),max);
		else 
			return visible(root.getLeft(),root.getData())+ visible(root.getRight(),root.getData()) + 1;
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
			
			/*
			 				1
			 			4
			 		2
			 	3
			 5		6
			  
			 */
			
			_1.setLeft(_4);
			_4.setLeft(_2);
			_2.setLeft(_3);
			_3.setLeft(_5);
			_3.setRight(_6);
			System.out.println(new BTVisible().visible(_1));
		}
		
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
			TreeNode<Integer> _10 = new TreeNode<Integer>(10);
			TreeNode<Integer> _11 = new TreeNode<Integer>(11);

			
			/*
			 				9
			 			8		7
			 		6				5
			 	10						11
			 */
			
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_7.setRight(_5);
			_6.setLeft(_10);
			_5.setRight(_11);
			System.out.println(new BTVisible().visible(_9));
		}
	}
	
}
