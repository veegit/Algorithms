package com.vee.algorithms.tree;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import com.vee.algorithms.datastructures.TreeNode;

/**
 * @author vee
 * @version 1.0
 * Tree is balanced if 
 * - left is balanced,
 * - right is balanced,
 * - | left - right | <= 1
 */
public class BSTIsBalanced {
	
	public boolean isBalanced(TreeNode<Integer> root, AtomicInteger height) {
		if (root == null) {
			return true;
		}
		AtomicInteger ltHeight = new AtomicInteger(height.get());
		boolean lt = isBalanced(root.getLeft(), ltHeight);
		AtomicInteger rtHeight = new AtomicInteger(height.get());
		boolean rt = isBalanced(root.getRight(), rtHeight);
		System.out.println("node:" + root + " left:" + root.getLeft() + " right " + root.getRight() + " l-height " + ltHeight + " r-height " + rtHeight + " " + (lt && rt));
		if (lt && rt && Math.abs(ltHeight.get() - rtHeight.get()) <= 1) {
			height.set(Math.max(ltHeight.get(), rtHeight.get()) + 1);
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String args[]) {
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
			//_8.setLeft(_6);
			//_7.setRight(_5);
			//_6.setLeft(_10);
			//_5.setRight(_11);
			System.out.println(new BSTIsBalanced().isBalanced(_9, new AtomicInteger(0)));
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
			 				1
			 			2		3
			 		4	10		6	5
			 	9					11
			 */
			
			_1.setLeft(_2);
			_1.setRight(_3);
			_2.setLeft(_4);
			_2.setRight(_10);
			_4.setLeft(_9);
			_3.setLeft(_6);
			_3.setRight(_5);
			_5.setRight(_11);
			System.out.println(new BSTIsBalanced().isBalanced(_1, new AtomicInteger(0)));
		}
	}
}
