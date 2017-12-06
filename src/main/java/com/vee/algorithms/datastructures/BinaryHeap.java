package com.vee.algorithms.datastructures;

import com.vee.algorithms.datastructures.BST.Traverse;
/*
 * TODO
 */
public class BinaryHeap<M>  {
	private int[] array;
	int size;
	
	public BinaryHeap(int capacity) {
		array = new int[capacity];
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	/*public int insert(int data) {
		if (isEmpty()) {
			array[0] = data;
			return 0;
		}

		TreeNode<M> node = root;
		TreeNode<M> prevNode = root;
		while (node != null) {
			prevNode = node;
			if (compare(data, node.getData()) <= 0)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		if (compare(data, prevNode.getData()) <= 0)
			prevNode.setLeft(newNode);
		else
			prevNode.setRight(newNode);
		newNode.setParent(prevNode);
		return newNode;
	}

	public boolean delete(M data) {

		TreeNode<M> leftmostNode = root;
		TreeNode<M> node = find(data);

		if (node == null)
			return false;

		 If is leaf 
		if (node.getLeft() == null && node.getRight() == null) {
			node = null;
			root = null;
			return true;
		}

		leftmostNode = node.getRight();
		TreeNode<M> leftmostNodeParent = leftmostNode;
		while (leftmostNode.getLeft() != null) {
			leftmostNodeParent = leftmostNode;
			leftmostNode = leftmostNode.getLeft();
		}
		node.setData(leftmostNode.getData());

		 If it has only 1 element in right sub tree 
		if (leftmostNode == leftmostNodeParent)
			node.setRight(leftmostNode.getRight());

		if (leftmostNode.getRight() != null)
			leftmostNodeParent.setLeft(leftmostNode.getRight());
		else
			leftmostNodeParent.setLeft(null);

		leftmostNode.setParent(null);
		leftmostNode = null;

		return true;
	}

	public TreeNode<M> find(M data) {
		TreeNode<M> node = root;
		while (node != null && compare(data, node.getData()) != 0) {
			if (compare(data, node.getData()) <= 0)
				node = node.getLeft();
			else
				node = node.getRight();
		}
		return node;
	}

	public void traverse(Traverse order) {
		TreeNode<M> node = root;
		switch (order) {
		case INORDER:
			inorder(node);
			break;
		case PREORDER:
			preorder(node);
			break;
		case POSTORDER:
			postorder(node);
			break;
		default:
			inorder(node);
		}
	}

	private void inorder(TreeNode<M> node) {
		if (node == null)
			return;
		inorder(node.getLeft());
		System.out.print(node.getData() + " ");
		inorder(node.getRight());
	}

	private void preorder(TreeNode<M> node) {
		if (node == null)
			return;
		System.out.print(node.getData() + " ");
		inorder(node.getLeft());
		inorder(node.getRight());
	}

	private void postorder(TreeNode<M> node) {
		if (node == null)
			return;
		inorder(node.getRight());
		inorder(node.getLeft());
		System.out.print(node.getData() + " ");
	}

	public int[] getParentsData(TreeNode<M> node) {
		LinkedList<Integer> parents = new LinkedList<Integer>();
		*//**
		 * DONT do node.getParent != null Otherwise you may fail to include that
		 * element itself in its parent
		 *//*
		while (node != null) {
			parents.insert((Integer) node.getData());
			node = node.getParent();
		}
		int parent_arr[] = parents.toIntArray();
		return parent_arr;
	}

	public int LCA(TreeNode<M> node1, TreeNode<M> node2, int temp) {
		int[] node1parents = getParentsData(node1);
		int[] node2parents = getParentsData(node2);
		int node1Size = node1parents.length;
		int node2Size = node2parents.length;
		while (node1Size > 0 && node2Size > 0)
			if (node1parents[--node1Size] != node2parents[--node2Size])
				break;
		return node1parents[node1Size];
	}

	public LinkedStack<TreeNode<M>> getParents(TreeNode<M> treeNode) {
		LinkedStack<TreeNode<M>> parents = new LinkedStack<TreeNode<M>>();
		*//**
		 * DONT do node.getParent != null Otherwise you may fail to include that
		 * element itself in its parent
		 *//*
		while (treeNode != null) {
			Node<TreeNode<M>> node = new Node<TreeNode<M>>();
			node.setData(treeNode);
			parents.pushNode(node);
			treeNode = treeNode.getParent();
		}
		return parents;
	}

	public TreeNode<M> LCA(TreeNode<M> node1, TreeNode<M> node2) {
		LinkedStack<TreeNode<M>> l1 = getParents(node1);
		LinkedStack<TreeNode<M>> l2 = getParents(node2);
		Node<TreeNode<M>> node1Ctr = null, node2Ctr = null, parentNode = null;
		while (l1.getTop() != null && l2.getTop() != null) {
			parentNode = node1Ctr;
			node1Ctr = l1.popNode();
			node2Ctr = l2.popNode();
			if (node1Ctr.getData() != node2Ctr.getData())
				break;
		}
		return parentNode.getData();
	}

	public static void main(String args[]) {
		
		 * BST<Integer> tree = new BST<Integer>(10); Random generator1 = new
		 * Random( 19580427 ); int cnt1,max1; cnt1 = 1000;max1 =cnt1;
		 * while(cnt1-- > 0) tree.insert(cnt1);
		 * 
		 * return;
		 

		BST<Integer> bst = new BST<Integer>(10);
		bst.insert(20);
		bst.insert(5);
		bst.insert(4);
		bst.insert(8);
		bst.delete(5);
		TreeNode<Integer> node1 = bst.insert(6);
		bst.insert(9);
		TreeNode<Integer> node2 = bst.insert(7);
		bst.traverse(Traverse.INORDER);
		System.out.println();
		
		 * System.out.println(bst.LCA(node1, node2).getData()); bst.delete(5);
		 * 
		 * System.out.println();
		 * 
		 * int max = 1000000; BST<Integer> bst1 = new BST<Integer>(max); Random
		 * generator = new Random(102134); TreeNode<Integer> nodex = null;
		 * TreeNode<Integer> nodey = null; while(max-- > 50000) { int n =
		 * generator.nextInt(max); if(n%80 == 0) nodex = bst1.insert(n);
		 * if(n%100 == 0) nodey = bst1.insert(n); bst1.insert(n); } int[]
		 * nodexarr = bst1.getParentsData(nodex); int[] nodeyarr =
		 * bst1.getParentsData(nodey); for (int i = 0; i < nodexarr.length; i++)
		 * System.out.print(nodexarr[i] + " "); System.out.println(); for (int i
		 * = 0; i < nodeyarr.length; i++) System.out.print(nodeyarr[i] + " ");
		 * System.out.println(); System.out.println(bst1.LCA(nodex,
		 * nodey).getData());
		 
	}*/
}