/**
 * CS 241: Data Structures and Algorithms II
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment #2
 *
 * <description-of-assignment>
 * An individual assignment. A very SIMPLE programming project in
 * which I SIMPLY implement a Red-Black Tree, and include pretty printing
 * which returns a string with the values in the tree, in a pyramid
 * fashion, each value appearing along with its color to aid in 
 * this data structure's visualization
 *
 * Team #N / (or name if individual)
 *   <team-member-names-if-team-assignment>
 *   Daniel Gamboa
 */

package edu.csupomona.cs.cs241.prog_assgnmnt_2;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

import edu.csupomona.cs.cs241.prog_assgnmnt_2.RBT.Node;

/*
 * DISCLAIMER: THIS CLASS WAS USED FOR A ISOLATED TESTING
 * OF VARIOUS METHOD IMPLEMENTATIONS
 * 
 * DISREGARD THIS ENTIRE CLASS PLEASE
 * THANK YOU
 */
public class DemoRBT<K extends Comparable <K>, V> implements Tree<K, V> {

	public Node root; 						// X
	public Node nil = new Node(); 		// Y
	
	public void add(K key, V value) {
		//TODO replace when finished below
	}

	public V remove(K key) {
		return null;
	}


	public V lookup(K key) {
		return null;
	}

	public String display() { // toPrettyString();
		return null;
	}
	
//#################################################	
	
	public void print(Node node) { // node == root[T]
		// uses in-order traversal
		Node temp = node;
		if (temp != null) { // FIXME make this compatible with sentinels. replace null with sentinal in case you forget.
			print(temp.left);
			System.out.println("[" + node.key + "-" + node.getColor(node.color) + "]");
			print(temp.right);
		} else {
			System.out.println("NULL");
			// display parent
			// if it is left or right child relative to parent
		}
	}
	
	public Node getRoot() {
		return root;
	}
	
	public Node insert(Node node, K key, V value) { // node may actually be root? possibly subtree's root?
		
		if (root == null) {
			root = new Node(key, value);
		} else if (key != root.key) { // If key is not a duplicate
			
			if (key.compareTo(node.key) < 0) {
				node.left = new Node(key, value);
				node.left.dir = false;
			} else {
				node.right = new Node(key, value);
				node.right.dir = true;
			}
			
			/* REBALANCE OCCURS HERE */ // TODO
			
		}
		
		return node; // (???) FIXME do we really need to return this for recursion?
	}
	
	public void addWhat(K key, V value) {
		root = insert(root, key, value); // CONFUSION STARTS HERE
		root.color = 0;
	}
	
	public void insert(K key, V value) { // Insert according to book		
		
		if (root == null) {
			root = new Node(key, value);
		} else {
			
			Node pn = nil; // previous node
			Node nn = root; // next node
			Node node = new Node(key, value);
			
			while (nn != nil) {
				pn = nn;
				if (node.key.compareTo(nn.key) < 0) {
					nn = nn.left;
				} else {
					nn = nn.right;
				}				
			}
			
			node.p = pn;
			
			// check the following operations
			if (pn == nil) {
				root = node;
			} else if (node.key.compareTo(pn.key) < 0) {
				pn.left = node;
			} else {
				pn.right = node;
			}
			
			node.left = nil;
			node.right = nil;
			node.color = 1;
			/* RB INSERT FIXUP OCCURS HERE */ // TODO
		}
	}
	
//#################################################	
	public void fixAdd(Node node) {
		
		if (isRed(node.left)) { // LEFT SYMMETRIC CASE
			if (isRed(node.right)) { 				
				node.color = 1; // paint it red
				node.left.color = 0;
				node.right.color = 0;
			} else {
				if (isRed(node.left.left)) {
					node = rightRotate(node);
				} else if (isRed(node.left.right)) {
					
				}
			}
		} else { // RIGHT SYMMETRIC CASE 
			
		}
		
	}
	
	public V delete(K key) {
		return null;
	}
	
	public void fixRem() {
		
	}
	
	public Node leftRotate(Node node) {
		// node serves as the root of the subtree we perform the rotation on
		// MUST ASSUME THAT NODE.RIGHT != NULL
		
		Node pivot = node.right;
		node.right = pivot.left;
		
//		node.color = 1; // FIXME 
//		pivot.color = 0; // FIXME
		
		if (pivot.left != nil) {
			pivot.left.p = node;
		}
		
		pivot.p = node.p;
		
		if (node.p == nil) {
			root = pivot;
		} else if (node == node.p.left) {
			node.p.left = pivot;
		} else {
			node.p.right = pivot;
		}
		
		pivot.left = node;
		node.p = pivot;
		
		return pivot;
	}
	
	public Node rightRotate(Node node) {
		Node pivot = node.left;
		node.left = pivot.right;
		
		if (pivot.right != nil) {
			pivot.right.p = node;
		}
		
		pivot.p  = node.p;
		
		if (node.p == nil) {
			root = pivot;
		} else if (node == node.p.right) {
			node.p.right = pivot;
		} else {
			node.p.left = pivot;
		}
		
		pivot.right = node;
		node.p = pivot;
		
		return pivot;
	}
	
	public void singleRotate(Node node) {
		/*
		 * if (node.right != null) then leftRotate
		 * if (node.left != null) then rightRotate
		 */
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
	
// REALLY BAD STUFF
	public void tempfixRem(Node node) { // FIXME
		// Node: node == spliceChild
		Node sibling;
		
		while (node != root && node.color == 0) {
			if (node == node.p.left) { // if spliced node's child is the left
				sibling = node.p.right;
				
				if (sibling.color == 1) {
					sibling.color = 0;
					node.p.color = 1;
					leftRotate(node.p);
					sibling = node.p.right;
				}
				
				if (sibling.left.color == 0 && sibling.right.color == 0) {
					sibling.color = 1;
					node = node.p;
				} else if (sibling.right.color == 0) {
					sibling.left.color = 0;
					sibling.color = 1;
					rightRotate(sibling);
					sibling = node.p.right;
				}
				
				sibling.color = node.p.color;
				node.p.color = 0;
				sibling.right.color = 0;
				leftRotate(node.p);
	
				node = root;
			} else { // symmetric case 
				sibling = node.p.left;
				
				if (sibling.color == 1) {
					sibling.color = 0;
					node.p.color = 1;
					rightRotate(node.p);
					sibling = node.p.left;
				}
				
				if (sibling.right.color == 0 && sibling.left.color == 0) {
					sibling.color = 1;
					node = node.p;
				} else if (sibling.left.color == 0) {
					sibling.right.color = 0;
					sibling.color = 1;
					leftRotate(sibling);
					sibling = node.p.left;
				}
				
				sibling.color = node.p.color;
				node.p.color = 0;
				sibling.left.color = 0;
				rightRotate(node.p);
				
				node = root;
			}	
		}
		
		node.color = 0;
	}
	
	
	public V newremove(K key) { // FIXME
//		Node z = fetch(key); // node to delete
		Node z = new Node();
		Node y = new Node(); // successor node
		Node x = new Node(); // node that moves into y's original position
		Node temp = new Node(); // hold y's original color
		
		y = z;
		temp = y;
		
		if (z.left == nil) {
			x = z.right;
			transplant(z, z.right);
		} else if (z.right == nil) {
			x = z.left;
			transplant(z, z.left);
		} else {
//			y = min(z.right);
			temp.color = y.color;
			x = y.right;
			
			if (y.p == z) {
				x.p = y;
			} else {
				transplant(y, y.right);
				y.right = z.right;
				y.right.p = y;
			}
			
			transplant(z, y);
			y.left = z.left;
			y.left.p = y;
			y.color = z.color;			
		}
		
		if (temp.color == 0) {
			tempfixRem(x);
		}
		
		return z.value;
	}	
	
	public void transplant(Node node, Node transplant) {
		if (node.p == nil) {
			root = transplant;
		} else if (node == node.p.left) {
			node.p.left = transplant;
		} else {
			node.p.right = transplant;
		}
		transplant.p = node.p;
	}
	
	public void fixRem(Node node) { // TODO fixRem this was the one from the first time
		// Node: node == spliceChild
		Node sibling;
		
		while (node != root && node.color == 0) {
			if (node == node.p.left) { // if spliced node's child is the left
				sibling = node.p.right;
				
				if (sibling.color == 1) {
					sibling.color = 0;
					node.p.color = 1;
					leftRotate(node.p);
					sibling = node.p.right;
				}
				
				if (sibling.left.color == 0 && sibling.right.color == 0) {
					sibling.color = 1;
					node = node.p;
				} else if (sibling.right.color == 0) {
					sibling.left.color = 0;
					sibling.color = 1;
					rightRotate(sibling);
					sibling = node.p.right;
				}
				
				sibling.color = node.p.color;
				node.p.color = 0;
				sibling.right.color = 0;
				leftRotate(node.p);
				node = root;
			} else { // symmetric case 
				sibling = node.p.left;
				
				if (sibling.color == 1) {
					sibling.color = 0;
					node.p.color = 1;
					rightRotate(node.p);
					sibling = node.p.left;
				}
				
				if (sibling.right.color == 0 && sibling.left.color == 0) {
					sibling.color = 1;
					node = node.p;
				} else if (sibling.left.color == 0) {
					sibling.right.color = 0;
					sibling.color = 1;
					leftRotate(sibling);
					sibling = node.p.left;
				}
				
				sibling.color = node.p.color;
				node.p.color = 0;
				sibling.left.color = 0;
				rightRotate(node.p);
				node = root;
			}	
		}
		
		node.color = 0;
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

	public int getHeight(Node node) {
		if (node == null) {
			return 0;
		} else {
			int lh = getHeight(node.left);
			int rh = getHeight(node.right);
			return (lh > rh) ? lh + 1 : rh + 1;
		}
	}
	
	// TODO PrettyPrint
	public void print(Node node, int level, int indentSpace) {
		
		Deque<Node> dq = new LinkedList<Node>();
		
		int h = getHeight(root);
		int numNodes = 1;
		
		int branchLen =  2*((int)Math.pow(2.0,h)-1) - (3-level)*(int)Math.pow(2.0,h-1);
		int nodeSpaceLen = 2 + (level+1)*(int)Math.pow(2.0,h);
		int startLen = branchLen + (3-level) + indentSpace;
		
		dq.addLast(root);
		
		for (int r = 1; r < h; r++) {
			branchLen = branchLen/2 - 1;
			nodeSpaceLen = nodeSpaceLen/2 +1;
			startLen = branchLen + (3-level) + indentSpace;
			printNode(branchLen, nodeSpaceLen, startLen, numNodes, dq);
			
			for (int i = 0; i < numNodes; i++) {
				Node cn = dq.poll();
				
				if(cn != null) {
					dq.addLast(cn.left);
					dq.addLast(cn.right);
				} else {
					dq.addLast(null); // FIXME
					dq.addLast(null);
				}
			}
			numNodes *= 2;
		}
		printLeaves(indentSpace, level, numNodes, dq);
	}
	
	// TODO
	public void printNode(int branchLen, int nodeSpaceLen, int startLen, int numNodes, Deque<Node> dq) {
		Iterator iter = dq.iterator();
		for (int i = 0; i < numNodes/2; i++) {
			
		}
		System.out.println();
	}
	
	// TODO
	public void printLeaves(int indentSpace, int level, int numNodes, Deque<Node> dq) {
		
		for (int i = 0; i < numNodes; i++) {
			
		}
	}
	
	public void space(int count) {
		for (int i = 0; i < count; i++) {
			System.out.println(" ");
		}
	}
	
//##################################################
	public class Node implements Comparable<Node> {

		protected K key;
		protected V value;
		protected Node p;
		protected Node left;
		protected Node right;
		protected boolean dir; // DIRECTION: false = left && true = right (relative to the parent)
		protected int color; // BLACK = 0 && RED = 1
		//protected RBTree.COLOR color; // RED or BLK
		
		public Node() { // usually reserved for sentinel
			this.key = null;
			this.value = null;
			this.p = null;
			this.left = null;
			this.right = null;
			this.color = 0; // 0 = BLACK
			//this.color = RBTree.COLOR.BLK;
		}
		
//		public Node(K key, V value) {
//			this.key = key;
//			this.value = value;
//		}
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.color = 1; // set as red node
			this.left = null;
			this.right = null;
		}

		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
		public String getColor(int color) {
			
			String msg = null;
			
			if (color == 0) {
				msg = "BLK";
			} else {
				msg = "RED";
			}
			
			return msg;
		}
	}


}
