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

import java.util.LinkedList;
import java.util.Queue;

public class RBT<K extends Comparable<K>, V> implements Tree<K, V>{
	
	private Node nil; // this is the sentinel
	protected Node root; // change to private later. protected only used for bugfixing
	
	public RBT() {
		this.nil = new Node();
		this.root = nil;
		this.root.p = nil;
	}
	
	public void add(K key, V value) {
		Node nn = new Node(key, value);
		Node cn = root;
		Node pn = nil;
		
		while (cn != nil) { // while the current node does not equal nil
			pn = cn;
			if (nn.key.compareTo(cn.key) < 0) {
				cn = cn.left;
			} else {
				cn = cn.right;
			}				
		}
		
		nn.p = pn; // skip to this instruction if cn = root = nil
		
		// takes care of parent of the new node's pointers
		if (pn == nil) { // basically adds to root if there is no tree
			root = nn;
		} else if (nn.key.compareTo(pn.key) < 0) {
			pn.left = nn;
		} else {
			pn.right = nn;
		}
		
		nn.left = nil;
		nn.right = nil;
		nn.color = 1;

		fixAdd(nn);
	}

	public V remove(K key) { 
		
		Node removed = fetch(key); // node to be removed
		Node sucessor = new Node(); // node's successor
		Node transplant = new Node(); // node that takes removed nodes place
		
		if (removed.left == nil || removed.right == nil) { // Test 1: Find out how many children
			sucessor = removed;
		} else {
			sucessor = successor(removed);
		}
		
		if (sucessor.left != nil) { // Test 2: Get child of the spliced node 
			transplant = sucessor.left;
		} else {
			transplant = sucessor.right;
		}
		
		transplant.p = sucessor.p; 
		
		if (sucessor.p == nil) { 
			root = transplant;
		} else if (sucessor == sucessor.p.left) {
			sucessor.p.left = transplant;
		} else {
			sucessor.p.right = transplant;
		}
		
		if (sucessor != removed) { // only occurs if the spliced node was the successor
			removed.key = sucessor.key;
			removed.value = sucessor.value;
		}
		
		if (sucessor.color == 0) { // Problems arise only when the splice child is black	
			fixRem(transplant); 
		}
		
		return removed.value;
	}
	
	public V lookup(K key) {
		Node n = fetch(key);
		
		if (n != null) {
			n.printData(); // temporary for bugfixing
			return n.value;
		}
		
		return null;
	}

	// TODO
	public String display() { // toPrettyPrint()
		return null;
	}
	
	public void print() {
		
		Queue<Node> current = new LinkedList<Node>();
		Queue<Node> next = new LinkedList<Node>();
		
		int depth;
		int height = getHeight(root);
		
		if (root != nil) {
			
			current.add(root);
			
			while (!current.isEmpty()) {
				Node cn = current.poll();
				
				if (cn != null) {
					
					if (cn == nil) {
						System.out.print("[X" + cn.getColor() + "]");
					} else if (cn != null){
						System.out.print("[" + cn.key + cn.getColor() + "]");
					}
					
					next.add(cn.left);
					next.add(cn.right);
				} 
				
				if (current.isEmpty()) { // swapping occurs here
					System.out.println();
					Queue<Node> temp = new LinkedList<Node>();
					temp = current;
					current = next;
					next = temp;
				}
			}
		}
	}
	
	public Node fetch(K key) { // lookup helper
		Node n = root;
		
		while (n != null) {
			
			if (key.compareTo(n.key) == 0) {
				return n;
			} else if (key.compareTo(n.key) < 0) {
				n = n.left;
			} else {
				n = n.right;
			}	
		} // end while
		return null;
	}
	
	public void fixAdd(Node node) {
		
		while (node.p.color == 1) { // while the parent of our nn is red (which is also red)
			if (node.p == node.p.p.left) { // if dad is the left child
				
				Node uncle = node.p.p.right;
				
				if (uncle.color == 1) {
					node.p.color = 0;
					uncle.color = 0;
					node.p.p.color = 1; // has possibility to break invariant 4
					node = node.p.p;
				} else {
					if (node == node.p.right) { // if nn is the right child
						node = node.p;
						leftRotate(node); // left rotate new node
					}
					
					node.p.color = 0;
					node.p.p.color = 1;
					rightRotate(node.p.p); // right rotate grandpa
				}
			} else {
				
				Node uncle = node.p.p.left;
				
				if (uncle.color == 1) {
					node.p.color = 0;
					uncle.color = 0;
					node.p.p.color = 1;
					node = node.p.p;
				} else {
					if (node == node.p.left) {
						node = node.p;
						rightRotate(node);
					}
					node.p.color = 0;
					node.p.p.color = 1;
					leftRotate(node.p.p);
				}
			}
		} // end while loop
		root.color = 0; // just in case the root becomes red in the process
	}
	
	public void fixRem(Node node) {
		
		/* Case 1: Sibling is red
		 * Case 2: Sibling is black & its children are black
		 * Case 3: Sibling is black & its left child is red and its right child is black
		 * Case 4: Sibling is black & its right child is red
		 */
		
		while (node != root && node.color == 0) {
			
			Node sibling =  node.getSibling();
			
			if (sibling.color == 1) {
				sibling = fixRemCase1(node, sibling);
			}
			
			if (sibling.left.color == 0 && sibling.right.color == 0) {
				node = fixRemCase2(node, sibling);
			} else {
				sibling = fixRemCase3(node, sibling);
			}
			
			node = fixRemCase4(node, sibling);
		}
		
		node.color = 0;
	}

	public Node fixRemCase1(Node node, Node sibling) {
		sibling.color = 0;
		node.p.color = 1;
		
		if (node.isLeft()) {
			leftRotate(node.p);
			sibling = node.p.right;
		} else {
			rightRotate(node.p);
			sibling = node.p.left;
		}
		
		return sibling;
	}
	
	public Node fixRemCase2(Node node, Node sibling) {
		sibling.color = 1;
		node = node.p;
		
		return node;
	}
	
	public Node fixRemCase3(Node node, Node sibling) {
		
		if (node.isLeft()) {
			if (sibling.right.color == 0) {
				sibling.left.color = 0;
				sibling.color = 1;
				rightRotate(sibling);
				sibling = node.p.right;
			}
		} else {
			if (sibling.left.color == 0) {
				sibling.right.color = 0;
				sibling.color = 1;
				leftRotate(sibling);
				sibling = node.p.left;
			}
		}
		return sibling;
	}
	
	public Node fixRemCase4(Node node, Node sibling) {
		sibling.color = node.p.color;
		node.p.color = 0;
		
		if (node.isLeft()) {
			sibling.right.color = 0;
			leftRotate(node.p);	
		} else {
			sibling.left.color = 0;
			rightRotate(node.p);
		}
		
		node = root;
		return node;
	}
	
	public void leftRotate(Node node) {
		// node serves as the root of the subtree we perform the rotation on
		// MUST ASSUME THAT NODE.RIGHT != NULL
		
		/* The pivot is actually the branch between the subtree root
		 * and one of its children but in this case I define the 
		 * child to be included in the rotation as a pivot
		 */
		Node pivot = node.right;
		node.right = pivot.left;
		
		if (pivot.left != nil) {
			pivot.left.p = node;
		}
		
		pivot.p = node.p;
		
		if (node.p == nil) { // was the node the root?
			root = pivot;
		} else if (node == node.p.left) { // was the node a left or right child?
			node.p.left = pivot;
		} else {
			node.p.right = pivot;
		}
		
		pivot.left = node;
		node.p = pivot;
		
	}
	
	public void rightRotate(Node node) {
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

	}
	
	public Node min(Node node) { // symmetric case = max
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}
	
	public Node successor(Node node) { // symmetric case = predecessor
		Node successor = null;
		
		if (node.right  != nil) { // if there is a right subtree of node
			successor = min(node.right);
			return successor;
		}
		
		// in the case that the node has a successor but no right subtree, we must travel up 
		Node temp = node.p;
		while (temp != nil && node == temp.right) {
			node = temp;
			temp = temp.p;
		}
		successor = temp;
		return successor;
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
	
	public int getHeight(Node node) {
		if (node == null) {
			return 0;
		} else {
			int lh = getHeight(node.left);
			int rh = getHeight(node.right);
			return (lh > rh) ? lh + 1 : rh + 1;
		}
	}
	
	/* Verification of RBT
	 * Returns 0 if it is an invalid RBTree else it returns the height of the entire tree
	 * Test 1: See if a red node has red children.
	 * Test 2: Ensures the tree is a valid binary search tree
	 * Test 3: Counts the black nodes along a path and ensures equal length
	 */
	public int verify(Node node) { // node will typically refer to the current node we are on

		int lh, rh; // left height & right height
		
		if (node == nil) {
			return 1;
		} else {
			Node ln = node.left;
			Node rn = node.right;

			if (node.isRed()) { // Consecutive red nodes
				if (ln.isRed() || rn.isRed()) {
					System.out.println("Red Violation (#4: Children of a red node are black)");
					return 0;
				}
			}
			
			lh = verify(ln);
			rh = verify(rn);
			
			if ((ln != nil && ln.key.compareTo(node.key) > 0) || (rn != nil && rn.key.compareTo(node.key) < 0)) {	// Invalid binary search tree
				System.out.println("Binary Tree Violation");
				return 0;
			}
			
			if (lh != 0 && rh != 0 && lh != rh) { // Black height mismatch
				System.out.println("Black Violation (#5: Any path from root to leaf contains the same number of black nodes.");
				return 0;
			}
			
			// Only count black links
			if (lh != 0 && rh != 0) {
				// Additional Note: Subtract 1 from the number returned because NIL's color is black
				return node.isRed() ? lh : lh + 1; // maybe I should remove the +1
			} else {
				System.out.println("Invalid");
				return 0;
			}
		}
	}
	
	public class Node implements Comparable<Node> {
		
		protected K key;
		protected V value;
		protected Node p;
		protected Node left;
		protected Node right;
		protected int color; // BLACK = 0, RED = 1
		
		public Node() {
			this.key = null;
			this.value = null;
			this.p = null;
			this.left = null;
			this.right = null;
			this.color = 0;
		}
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			this.color = 1;
			this.left = nil;
			this.right = nil;
		}
		
		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
		public boolean isRed() {
			if (this == nil) {
				return false;
			}
			return (this.color == 1); // true
		}
		
		public boolean isInternal() {
			if (this == this.p.p.left.left || this == this.p.p.right.right) {
				return false;
			}
			return true;
		}
		
		public boolean isLeaf() {
			if (this.left != nil || this.right != nil) {
				return false;
			}
			return true;
		}
		
		public boolean isNil() {
			if (this != nil) {
				return false;
			}
			return true;
		}
		
		public boolean isLeft() {
			if (this == this.p.right) {
				return false;
			}
			return true;
		}
		
		public String getColor() {
			if (color == 1) {
				return "R";
			} else {
				return "B";
			}
		}
		
		public Node getSibling() {
			if (this == this.p.left) {
				return this.p.right;
			} else {
				return this.p.left;
			}
		}
		
		public void printData() {
			String nodeKey = this.key.toString();
			@SuppressWarnings("unused")
			String nodeValue = this.value.toString();
			String nodeColor = this.getColor();
			
			System.out.println("[" + nodeKey + "-" + nodeColor + "]");
			
			if (this.p != nil) {
				String nodeParent = this.p.key.toString();
				System.out.println("^: " + nodeParent);
			}
			
			if (this.left != nil) {
				String nodeLeft = this.left.key.toString();
				System.out.println("<: " + nodeLeft );
			}
			
			if (this.right != nil) {
				String nodeRight = this.right.key.toString();
				System.out.println(">: " + nodeRight);
			}
		} // end getData()
	} 	
}