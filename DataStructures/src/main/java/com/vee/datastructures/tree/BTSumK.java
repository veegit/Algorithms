package com.vee.datastructures.tree;

public class BTSumK {
	
	public boolean subSum(TreeNode<Integer> node, int sum) {
		if (node == null) {
			return sum == 0;
		} else {
			sum = sum - node.getData();
			boolean hasSumK = false;
			/* For logging the sum at leaf*/
			if (node.getLeft() == null && node.getRight() == null) {
				System.out.println(sum);
			}
			/* For logging the sum at leaf*/
			if (sum == 0 && node.getLeft() == null && node.getRight() == null) {
				return true;
			}
			if (node.getLeft() != null) {
				hasSumK = hasSumK || subSum(node.getLeft(), sum);
			}
			if (node.getRight() != null) {
				hasSumK = hasSumK || subSum(node.getRight(), sum);
			}
			return hasSumK;
		}
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
			System.out.println(new BTSumK().subSum(_1, 16));
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
			System.out.println(new BTSumK().subSum(_9, 1));
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
			System.out.println(new BTSumK().subSum(_9, 18));
		}
	}
}
