package com.vee.algorithms.datastructures;

import java.util.Comparator;
import java.util.Random;

public class RedBlackTree<M extends Object> implements Comparator<M>{

	private RedBlackTreeNode<M> root;
	
	public RedBlackTree() {
		
	}
	
	public void insert(M data) {
		root = insert_r(root,data);
		root.setBlack();
	}
	
	private RedBlackTreeNode<M> insert_r(RedBlackTreeNode<M> root, M data) {
		if(root==null)
			root = MakeNewNode(data);
		else if (compare(data,root.getData()) != 0) {
			boolean isLeft = (compare(data,root.getData()) <= 0);
			if(isLeft)
				root.setLeft(insert_r(root.getLeft(),data));
			else 
				root.setRight(insert_r(root.getRight(),data));
			
			if(isLeft?_isRed(root.getLeft()) : _isRed(root.getRight())) {
				if(isLeft?_isRed(root.getRight()):_isRed(root.getLeft())) {
					root.setRed();
					root.getLeft().setBlack();
					root.getRight().setBlack();
				}
				else {
					if(isLeft?_isRed(root.getLeft().getLeft()):_isRed(root.getRight().getRight()))
						root = isLeft ? rotateRight(root):rotateLeft(root);
					else if(isLeft?_isRed(root.getLeft().getRight()):_isRed(root.getRight().getLeft()))
						root = isLeft ? rotateLeftRight(root):rotateRightLeft(root);
				}
			}
		}
		return root;
	}
	
	private RedBlackTreeNode<M> rotateLeft(RedBlackTreeNode<M> root) {
		RedBlackTreeNode<M> temp = root.getRight();
		root.setRight(temp.getLeft());
		temp.setLeft(root);
		root.setRed();
		temp.setBlack();
		return temp;
	}
	
	private RedBlackTreeNode<M> rotateRight(RedBlackTreeNode<M> root) {
		RedBlackTreeNode<M> temp = root.getLeft();
		root.setLeft(temp.getRight());
		temp.setRight(root);
		root.setRed();
		temp.setBlack();
		return temp;
	}
	
	private RedBlackTreeNode<M> rotateRightLeft(RedBlackTreeNode<M> root) {
		root.setRight(rotateRight(root.getRight()));
		root = rotateLeft(root);
		return root;
	}
	
	private RedBlackTreeNode<M> rotateLeftRight(RedBlackTreeNode<M> root) {
		root.setLeft(rotateLeft(root.getLeft()));
		root = rotateRight(root);
		return root;
	}

	private RedBlackTreeNode<M> MakeNewNode(M data) {
		RedBlackTreeNode<M> newNode = new RedBlackTreeNode<M>(data);
		newNode.setRed();
		return newNode;
	}
	
	private void inorder(RedBlackTreeNode<M> node) {
		if(node == null)
			return;
		inorder(node.getLeft());
		System.out.print(node.getData()+ "\n");
		inorder(node.getRight());
	}
	
	public void traverse() {
		RedBlackTreeNode<M> node = root;
		inorder(node);
	}
	
	private boolean _isRed(RedBlackTreeNode<M> node) {
		if(node == null)
			return false;
		return node.isRed();
	}
	
	public static void main(String args[]) {
		
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		//tree.insert(50);tree.insert(20);tree.insert(30);tree.insert(40);tree.insert(10);
		Random generator = new Random( 19580427 );
		int cnt,max;
		cnt = 1000;max =cnt;
		while(cnt-- > 0)
			tree.insert(cnt);
		tree.traverse();
	}

	public int compare(M o1, M o2) {
		return (Integer) o1 - (Integer) o2;
	}
}
