package edu.csupomona.cs.cs241.prog_assgnmnt_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RBTree<K extends Comparable <K>, V> implements Tree<K, V> {

	private Queue<Node> theQueue;
	private Stack<Node> theStack;
	
	public Node root; 			// X
	public Node sentinel = new Node(); 		// Y
	
	public void add(K key, V value) {
		
	}

	public V remove(K key) {
		return null;
	}


	public V lookup(K key) {
		return null;
	}

	public String display() {
		return null;
	}
	
//##################################################	
	public void fixAdd() {
		
	}
	
	public void fixRem() {
		
	}
	
	public void leftRotate(Node node) {
		// node serves as the root of the subtree we perform the rotation on
		// MUST ASSUME THAT NODE.RIGHT != NULL
		
		Node pivot = node.right;
		node.right = pivot.left;
		
		if (pivot.left != sentinel) {
			pivot.left.parent = node;
		}
		
		pivot.parent = node.parent;
		
		if (node.parent == sentinel) {
			root = pivot;
		} else if (node == node.parent.left) {
			node.parent.left = pivot;
		} else {
			node.parent.right = pivot;
		}
		
		pivot.left = node;
		node.parent = pivot;
		
	}
	
	public void rightRotate() {
		
	}
	
	public void singleRotate() {
		
	}
	
	public void doubleRotate() {
		
	}
	
	// Returns 0 if it is an invalid RBTree else it returns the height of the entire tree
	/* Test 1: See if a red node has red children.
	 * Test 2: Ensures the tree is a valid binary search tree
	 * Test 3: Counts the black nodes along a path and ensures equal length
	 */
	public int assertRBT(Node node) { // node will typically refer to the current node we are on
		
		int lh, rh; // left height & right height
		
		if (node == null) {
			return 1;
		} else {
			Node ln = node.left;
			Node rn = node.right;
			
			// Consecutive red links
			if (isRed(node)) {
				if (isRed(ln) || isRed(rn)) {
					System.out.println("Red Violation (#4: Children of a red node are black)");
					return 0;
				}
			}
			
			lh = assertRBT(ln);
			rh = assertRBT(rn);
			
			// Invalid binary search tree
			if ((ln != null && ln.key.compareTo(node.key) >= 0) || (rn != null && rn.key.compareTo(node.key) <= 0)) {
//			if ((ln != null && ln.key >= node.key) || (rn != null && rn.key <= node.key)) {				
				System.out.println("Binary Tree Violation");
				return 0;
			}
			
			// Black height mismatch
			if (lh != 0 && rh != 0 && lh != rh) {
				System.out.println("Black Violation (#5: Any path from root to leaf contains the same number of black nodes.");
				return 0;
			}
			
			// Only count black links
			if (lh != 0 && rh != 0) {
				return isRed(node) ? lh : lh + 1;
			} else {
				return 0;
			}
		}
	}
	
	public boolean isRed(Node node) { // used for insertion 
//		return node.key != null && node.color = 1;
		
		if (node.key == null) {
			node.color = 1;
			return true;
		}
		return false;
	}
	
/* ********************
 * POSSIBLE METHODS:
 * getUncle();
 * isInner();
 * isRed();
 * ====================
 * search();
 * min();
 * max();
 * successor();
 * predecessor();
 * assert();
 * ********************
 */
	
//##################################################
	public class Node implements Comparable<Node> {

		protected K key;
		protected V value;
		protected Node parent;
		protected Node left;
		protected Node right;
		protected int color; // BLACK = 0 && RED = 1
		//protected RBTree.COLOR color; // RED or BLK
		
		public Node() {
			this.key = null;
			this.value = null;
			this.parent = null;
			this.left = null;
			this.right = null;
			this.color = 0; // 0 = BLACK
			//this.color = RBTree.COLOR.BLK;
		}
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
	}

}
