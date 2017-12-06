package com.vee.algorithms.tree;
import com.vee.algorithms.datastructures.BST;
import com.vee.algorithms.datastructures.TreeNode;


public class BSTKLargest {

	/*
	 * TODO
	 * Retrieve the Kth largest element from the BST
	 */
	class Holder {
		private int i = 0;
		void increment() {i++;}
		int get() {return i;};
	}
	BSTKLargest(TreeNode<Integer> node, int k) {
		TreeNode<Integer> nnode = largest(node,k,new Holder());
		System.out.println(nnode.getData());
	}
	
	TreeNode<Integer> largest(TreeNode<Integer> node, int k, Holder j) {
		if(node == null)
			return null;
		TreeNode<Integer> lnode = largest(node.getLeft(),k,j);
		if(lnode != null) return lnode;
		j.increment();
		if(j.get() == k) {return node;}
		TreeNode<Integer> rnode = largest(node.getRight(),k,j);
		if(rnode != null) return rnode;
		return null;
	}
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		   bst.insert(5);
		   bst.insert(3);
		   bst.insert(6);
		   bst.insert(2);
		   bst.insert(4);
		   bst.insert(1);
		//new BSTKLargest(bst.getRoot(), 4);
	}
}
