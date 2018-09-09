package com.vee.algorithms.tree;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.vee.algorithms.datastructures.TreeNode;

/**
 * 
 * @author vee
 *
 * How many subtrees the trees have whose all values are same
 * 
	 *              9
	 *            /   \
	 *           8     7
	 *          / \   / \
	 *         8   8 7   7
	 *         
	 *         Returns 6
 * 
 */
public class UniVal {

	int countUniVal(TreeNode<Integer> root) {
		AtomicInteger count = new AtomicInteger(0);
		countUniVal(root, count);
		return count.get();
		
	}
	
	boolean countUniVal(TreeNode<Integer> root,  AtomicInteger count) {
		if (root == null) {
			return false; 
		}
		if (root.getLeft() == null && root.getRight() == null) {
			count.incrementAndGet();
			return true;
		}
		boolean left, right;
		if (root.getLeft() != null) {
			left = countUniVal(root.getLeft(), count) && root.getData().equals(root.getLeft().getData());
		} else {
			left = true;
		}
		if (root.getRight() != null) {
			right = countUniVal(root.getRight(), count) && root.getData().equals(root.getRight().getData());
		} else {
			right = true;
		}
		
		if (left && right) {
			count.incrementAndGet();
			return true;
		} else {
			return false;
		}
	}
	
	@Test
	public void test() {
		{
			TreeNode<Integer> root = new TreeNode<Integer>(0);
			TreeNode<Integer> _1 = new TreeNode<Integer>(3);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(3);
			TreeNode<Integer> _6 = new TreeNode<Integer>(3);
			TreeNode<Integer> _7 = new TreeNode<Integer>(7);
			TreeNode<Integer> _8 = new TreeNode<Integer>(8);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
		/*	
			 				1
			 			4
			 		2
			 	3
			 3		3
			  
		*/	 
			
			_1.setLeft(_4);
			_4.setLeft(_2);
			_2.setLeft(_3);
			_3.setLeft(_5);
			_3.setRight(_6);
			System.out.println(countUniVal(_1));
		}
		
		/*{
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
			
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_7.setRight(_5);
			_6.setLeft(_10);
			_5.setRight(_11);
			System.out.println(countUniVal(_9));
		}*/
		
		/*	
							9
						8		7
					6				5
				10						11
				*/	 
		{
			TreeNode<Integer> _7 = new TreeNode<Integer>(9);
			TreeNode<Integer> _8 = new TreeNode<Integer>(9);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
			
			
			_9.setLeft(_8);
			_9.setRight(_7);
			System.out.println(countUniVal(_9));
		}
		
		/*
			9
		9		9
		 */
	}
}
