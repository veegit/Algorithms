package com.vee.intw.icims;
/**
 * 
 * @author Narayanan
 *
 * A tree is binary
 * Find number of visible nodes
 * A node is visible if each of its parent values are less than it
 * A root is always visible
 * All nodes less than root are not visible
 * e.g
 *         5
 *      /     \
 *     3       10
 *    / \      / 
 *   20  21    1
 *   
 *   5,10,20,21 = 4
 */
public class VisibleTree {
	
	public int visible(Tree T) {
		return 0;
	}
}

class Tree {
	int x;
	Tree l;
	Tree r;
}