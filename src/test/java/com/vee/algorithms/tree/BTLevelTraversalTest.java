package com.vee.algorithms.tree;

import org.junit.Test;

import com.vee.algorithms.datastructures.TreeNode;
import com.vee.algorithms.tree.BTLevelTraversal;

public class BTLevelTraversalTest {
	
	@Test
	public void testTraverse1() {
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
		_1.preorder(_1);
		System.out.println();
		new BTLevelTraversal().traverse(_1);
	}
	
	//@Test
	public void testTraverse2() {
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
		_6.setLeft(_3);
		_5.setRight(_2);
		new BTLevelTraversal().traverse(_9);
	}
	
	//@Test
	public void testReconfig() {
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
		_9.preorder(_9);
	}
}
