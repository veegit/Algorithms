package com.vee.datastructures;

public class TreeNode extends Node {
	private TreeNode left;
	private TreeNode right;
	
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
	public TreeNode(int data) {
		super();
		this.setData(data);
	}
	
}
