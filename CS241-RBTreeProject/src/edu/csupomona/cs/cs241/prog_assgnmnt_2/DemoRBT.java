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

/*
 * DISCLAIMER: THIS CLASS WAS USED FOR A ISOLATED TESTING
 * OF VARIOUS METHOD IMPLEMENTATIONS
 * 
 * DISREGARD THIS ENTIRE CLASS PLEASE
 * THANK YOU
 */
public class DemoRBT<K extends Comparable <K>, V> implements Tree<K, V> {

	public Node root; 						// X
	public Node sentinel = new Node(); 		// Y
	
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
			
			Node pn = sentinel; // previous node
			Node nn = root; // next node
			Node node = new Node(key, value);
			
			while (nn != sentinel) {
				pn = nn;
				if (node.key.compareTo(nn.key) < 0) {
					nn = nn.left;
				} else {
					nn = nn.right;
				}				
			}
			
			node.parent = pn;
			
			// check the following operations
			if (pn == sentinel) {
				root = node;
			} else if (node.key.compareTo(pn.key) < 0) {
				pn.left = node;
			} else {
				pn.right = node;
			}
			
			node.left = sentinel;
			node.right = sentinel;
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
		
		return pivot;
	}
	
	public Node rightRotate(Node node) {
		Node pivot = node.left;
		node.left = pivot.right;
		
		if (pivot.right != sentinel) {
			pivot.right.parent = node;
		}
		
		pivot.parent  = node.parent;
		
		if (node.parent == sentinel) {
			root = pivot;
		} else if (node == node.parent.right) {
			node.parent.right = pivot;
		} else {
			node.parent.left = pivot;
		}
		
		pivot.right = node;
		node.parent = pivot;
		
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
		protected boolean dir; // DIRECTION: false = left && true = right (relative to the parent)
		protected int color; // BLACK = 0 && RED = 1
		//protected RBTree.COLOR color; // RED or BLK
		
		public Node() { // usually reserved for sentinel
			this.key = null;
			this.value = null;
			this.parent = null;
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

	@Override
	public String toPrettyPrint() {
		// TODO Auto-generated method stub
		return null;
	}

}
