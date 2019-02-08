package com.vee.algorithms.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

import com.vee.algorithms.datastructures.TreeNode;

import org.junit.Test;

class BTSerDeser {
    public String serialize(TreeNode<Integer> root) {
        return serialize(root, new StringBuilder()).toString();
    }

    public StringBuilder serialize(TreeNode<Integer> root, StringBuilder str) {
        if (root == null) {
            return str.append("NULL ");   
        }
        str.append(root.getData() + " ");
        serialize(root.getLeft(), str);
        serialize(root.getRight(), str);
        return str;
    }

    public TreeNode<Integer> deserialize(String str) {
		Scanner scan = new Scanner(str);
		scan.useDelimiter(" ");
		return deserialize(scan);
	}
	
	public TreeNode<Integer> deserialize(Scanner scan) {
		if (!scan.hasNext()) {
			return null;
		}
		String data = scan.next();
		if ("NULL".equals(data)) {
			return null;
		}
		TreeNode<Integer> node = new TreeNode<>(Integer.parseInt(data));
		node.setLeft(deserialize(scan));
		node.setRight(deserialize(scan));
		return node;
    }

    public static void main(String args[]) {
		BTSerDeser sd = new BTSerDeser();
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
            String str = sd.serialize(_1);
            System.out.println(str);
        }
        
    {
        
			TreeNode<Integer> _1 = new TreeNode<Integer>(1);
			TreeNode<Integer> _2 = new TreeNode<Integer>(2);
			TreeNode<Integer> _3 = new TreeNode<Integer>(3);
			TreeNode<Integer> _4 = new TreeNode<Integer>(4);
			TreeNode<Integer> _5 = new TreeNode<Integer>(5);
			TreeNode<Integer> _6 = new TreeNode<Integer>(6);
			TreeNode<Integer> _7 = new TreeNode<Integer>(7);
			TreeNode<Integer> _8 = new TreeNode<Integer>(8);
			TreeNode<Integer> _9 = new TreeNode<Integer>(9);
	/**
	 *       9
	 *     8   7
	 *   6       5
	 * 1           2
	 *  */		
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_7.setRight(_5);
			_6.setLeft(_1);
			_5.setRight(_2);
			_9.levelTraversal();
            String str = sd.serialize(_9);
			System.out.println(str);
			TreeNode<Integer> root = sd.deserialize(str);
			root.levelTraversal(); 
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
			
			_9.setLeft(_8);
			_9.setRight(_7);
			_8.setLeft(_6);
			_8.setRight(_1);
			_7.setLeft(_2);
			_7.setRight(_5);
            String str = sd.serialize(_9);
            System.out.println(str);
    	}
	}
}