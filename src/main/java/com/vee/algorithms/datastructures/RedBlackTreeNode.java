package com.vee.algorithms.datastructures;

public class RedBlackTreeNode<M extends Object> extends TreeNode<M>{
	
	public enum Color {RED,BLACK};
	
	private Color color;
	
	public Color getColor() {
		return color;
	}
	
	/* Is red  should always check for red and NOT NULL */
	public boolean isRed() {
		return this!= null && color == Color.RED;
	}
	
	/* Is red  should always check for black and NOT NULL */
	public boolean isBlack() {
		return this!= null && color == Color.BLACK;
	}
	
	public void setRed() {
		color = Color.RED;
	}
	
	public void setBlack() {
		color = Color.BLACK;
	}
	
	public RedBlackTreeNode<M> getLeft() {
		return (RedBlackTreeNode<M>) left;
	}
	public void setLeft(RedBlackTreeNode<M> left) {
		this.left = left;
	}
	
	public RedBlackTreeNode<M> getRight() {
		return (RedBlackTreeNode<M>) right;
	}
	public void setRight(RedBlackTreeNode<M> right) {
		this.right = right;
	}
	
	public RedBlackTreeNode(M data) {
		super(data);
	}
}
