package com.vee.datastructures;

enum Traverse {
	INORDER,PREORDER,POSTORDER;
}

public class BST {
	   private TreeNode root;
	   
	   public BST(int data) {
	       root = new TreeNode(data);
	   }
	   
	   public boolean isEmpty() {
	       if(root == null)
	           return true;
	       else
	           return false;
	   }
	   
	   public void insert(int data) {
	    TreeNode newNode = new TreeNode(data);
	    TreeNode node = root;
	    TreeNode prevNode = root;
	    while(node != null) {
	        prevNode = node;
	        if(data <= node.getData())
	            node = node.getLeft();
	        else
	            node = node.getRight();
	    }
	    if(data <= prevNode.getData())
	        prevNode.setLeft(newNode);	
	    else
	    	prevNode.setRight(newNode);
	   }
	   
	   public boolean delete(int data) {
		   TreeNode node = root;
	       TreeNode leftmostNode = root;
	       while(node != null && node.getData() != data) {
	    	  if(data <= node.getData())
	    		   node = node.getLeft();
	    	   else
	    		   node = node.getRight();
	       }
	    
	       if(node == null)
	    	   return false;
	       
	       leftmostNode = node.getRight();
	       TreeNode leftmostNodeParent = leftmostNode;
	       while(leftmostNode.getLeft() !=null) {
	    	   leftmostNodeParent = leftmostNode;
	    	   leftmostNode = leftmostNode.getLeft();
	       }
	       
	       node.setData(leftmostNode.getData());
	       
	       if(leftmostNode.getRight() != null)
	    	   leftmostNodeParent.setLeft(leftmostNode.getRight());
	       else
	    	   leftmostNodeParent.setLeft(null);
	       return true;
	  }
	   
	   public void traverse(Traverse order) {
		   TreeNode node = root;
		   switch(order) {
		   case INORDER: inorder(node);break;
		   case PREORDER: preorder(node);break;
		   case POSTORDER: postorder(node);break;
		   default: inorder(node);
		   }
	   }
	   
	   private void inorder(TreeNode node) {
			if(node == null)
				return;
			inorder(node.getLeft());
			System.out.print(node.getData() + " ");
			inorder(node.getRight());
		}
	   
	   private void preorder(TreeNode node) {
			if(node == null)
				return;
			System.out.print(node.getData() + " ");
			inorder(node.getLeft());
			inorder(node.getRight());
		}
	   
	   private void postorder(TreeNode node) {
			if(node == null)
				return;
			inorder(node.getRight());
			inorder(node.getLeft());
			System.out.print(node.getData() + " ");
		}
	   
	   public static void main(String args[]) {
		   BST bst = new BST(10);
		   bst.insert(20);
		   bst.insert(5);
		   bst.insert(4);
		   bst.insert(8);
		   bst.insert(3);
		   bst.insert(6);
		   bst.insert(9);
		   bst.insert(7);
		   bst.delete(5);
		   bst.traverse(Traverse.INORDER);
	   }
	  }