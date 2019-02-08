package com.vee.algorithms.datastructures;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

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
	
	public void preorder(TreeNode<M> node) {
		if(node == null)
			return;
		System.out.print(node.getData() +  " ");
		preorder(node.getLeft());
		preorder(node.getRight());
	}
	
	public void postorder(TreeNode<M> node) {
		if(node == null)
			return;
		postorder(node.getLeft());
		postorder(node.getRight());
		System.out.print(node.getData() +  " ");
	}

	public void levelTraversal() {
		Deque<TreeNode<M>> q = new ArrayDeque<>();
		q.offer(this);
		int count = 1;
		int newCount = 0;
		while (!q.isEmpty()) {
			TreeNode<M> n = q.poll();
			count--;
			if (n.getLeft() != null) {
				newCount++;
				q.offer(n.getLeft());
			}
			if (n.getRight() != null) {
				newCount++;
				q.offer(n.getRight());
			}
			System.out.print(n.getData() + " ");
			if (count == 0) {
				System.out.println();
				count = newCount;
				newCount = 0;
			}
		}
	}
}