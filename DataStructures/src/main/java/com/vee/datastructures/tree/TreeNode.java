package com.vee.datastructures.tree;

import com.vee.datastructures.Node;

public class TreeNode<M extends Object> extends Node<M> {
	protected TreeNode<M> left;
	protected TreeNode<M> right;
	protected TreeNode<M> parent;
	
	public TreeNode<M> getLeft() {
		return left;
	}
	public void setLeft(TreeNode<M> left) {
		this.left = left;
	}
	public TreeNode<M> getRight() {
		return right;
	}
	public void setRight(TreeNode<M> right) {
		this.right = right;
	}
	public TreeNode<M> getParent() {
		return parent;
	}
	public void setParent(TreeNode<M> parent) {
		this.parent = parent;
	}
	
	public TreeNode(M data) {
		super();
		this.setData(data);
	}
	
	public TreeNode(TreeNode<M> node) {
		super();
		this.setData(node.getData());
	}
	
}
