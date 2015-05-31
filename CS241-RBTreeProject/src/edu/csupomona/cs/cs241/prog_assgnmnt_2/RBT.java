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

	private Queue<Node> queue = new LinkedList<Node>();
	
	private Node nil; // this is the sentinel
	protected Node root;
	private int size; // number of elements excluding null leaves
	private int count; // black-height of tree
	
	public RBT() {
		this.nil = new Node();
		this.root = nil;
		this.root.p = nil;
	}
	
	public void insert(K key, V value) {
		
		size++;
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
		// see delete();
		return null;
	}
	
	public V lookup(K key) {
		Node n = fetch(key);
		
		if (n != null) {
			n.getData(); // FIXME temporary for bugfixing
			return n.value;
		}
		
		return null;
	}

	public String display() {
		
		return null;
	}
	
//##################################################
	
	public void fixAdd(Node node) {
		
		while (node.p.color == 1) { // while the parent of our nn is red (which is also red)
			if (node.p == node.p.p.left) { // if dad is the left child
				
				Node uncle = node.p.p.right;
				
				if (uncle.color == 1) { // handle double red case
					node.p.color = 0;
					uncle.color = 0;
					node.p.p.color = 1;
					node = node.p.p;
				} else if (node == node.p.right) { // if nn is the right child
					node = node.p;
					leftRotate(node); // left rotate new node
					node.p.color = 0;
					node.p.p.color = 1;
					rightRotate(node.p.p); // right rotate grandpa
				}
			} else { // symmetric case (if dad is right child)
				
				Node uncle = node.p.p.left;
				
				if (uncle.color == 1) {
					node.p.color = 0;
					uncle.color = 0;
					node.p.p.color = 1;
					node = node.p.p;
				} else if (node == node.p.left) {
					node = node.p;
					rightRotate(node);
					node.p.color = 0;
					node.p.p.color = 1;
					leftRotate(node.p.p);
				}
			}
		} // end while loop
		root.color = 0; // just in case the root becomes red in the process
	}
	
	public V delete(K key) { // TODO bugcheck implementation
		
		Node result = fetch(key); // node to be removed
		
		Node ngv = new Node(); // successor or next greatest value
		Node temp = new Node(); // FIXME temp
		
		if (result.left == nil || result.right == nil) {
			ngv = result;
		} else {
			ngv = successor(result);
		}
		
		if (ngv.left != nil) {
			temp = ngv.left;
		} else {
			temp = ngv.right;
		}
		
		temp.p = ngv.p; // FIXME delete error occurring here
		
		if (ngv.p == nil) {
			root = temp;
		} else if (ngv == ngv.p.left) {
			ngv.p.left = temp;
		} else {
			ngv.p.right = temp;
		}
		
		if (ngv != result) {
			result.key = ngv.key;
			result.value = ngv.value;
			// TODO obtain satellite data from ngv into result
		}
		
		if (ngv.color == 0) {
			fixRem(temp);
		}
		
		return result.value;
	}
	
	public void fixRem(Node node) { // TODO bugcheck implementation
		
		Node sibling;
		
		while (node != root && node.color == 0) {
			if (node == node.p.left) { // if rmvd node is left child
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
				
				sibling.color = node.p.color; //TODO
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
	
	public Node fetch(K key) {
		Node n = root;
		
		while (n != null) {
			
			if (key.compareTo(n.key) == 0) {
				return n;
			} else if (key.compareTo(n.key) < 0) {
				n = n.left;
			} else {
				n = n.right;
			}
			
		} // end while loop
		System.out.println("Node node found."); // FIXME remove this later
		return null;
	}
	
	public void uglyDisplay() { // TODO uglyDisplay()
		
		Node temp = new Node();
		int currentlevel = 1;
		int nextlevel = 0;
		queue.add(root);
		
		while (!queue.isEmpty()) {
			temp = queue.poll();
			currentlevel--;
			
			temp.getData();
		}
		
	}
	
//##################################################
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
	
	public Node min(Node node) {
		while (node.left != nil) {
			node = node.left;
		}
		return node;
	}
	
	public Node max(Node node) {
		while (node.right != nil) {
			node = node.right;
		}
		return node;
	}
	
	public Node successor(Node node) {
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
	
	public Node predecessor(Node node) {
		Node predecessor = null;
		
		if (node.left != nil) {
			predecessor = max(node.left);
			return predecessor;
		}
		
		Node temp = node.p;
		
		while (temp != nil && node == temp.left) {
			node = temp;
			temp = temp.p;
		}
		predecessor = temp;
		return predecessor;
	}
	
	public boolean isRed(Node node) {
		if (node == nil) {
			return false;
		}
		return (node.color == 1); // true
	}

//##################################################	
	
	public int height(Node node) {
		// height of given node needed for prettyPrint()
		// depth is num of lines from root to node
		// height is the max depth of any node in the tree
		if (node == null) {
			return -1;
		} else {
			return (1 + Math.max(height(node.left), height (node.right)));
		}
	}
	
	public int size(Node root) {
		// tree size needed for prettyPrint()
		return 0;
	}
	
	public int numLeaves(Node root) {
		// number of leaves needed for prettyPrint()
		return 0;
	}

	public void print() {
		
		Queue<Node> current = new LinkedList<Node>();
		Queue<Node> next = new LinkedList<Node>();
		
		if (root != nil) {
			current.add(root);
			
			while (!current.isEmpty()) {
				Node cn = current.poll();
				
				if (cn != null) {
					System.out.print("[" + cn.key + "+" + cn.getColor() + "]  ");
					next.add(cn.left);
					next.add(cn.right);
				} 
				
				if (current.isEmpty()) {
					System.out.println();
					Queue<Node> temp = new LinkedList<Node>();
					temp = current;
					current = next;
					next = temp;
				}
			}
		}
	}
	
	
//##################################################	
	// Returns 0 if it is an invalid RBTree else it returns the height of the entire tree
	/* Test 1: See if a red node has red children.
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

			if (isRed(node)) { // Consecutive red nodes
				if (isRed(ln) || isRed(rn)) {
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
				return isRed(node) ? lh : lh + 1; // FIXME maybe I should remove the +1
			} else {
				System.out.println("Invalid");
				return 0;
			}
		}
	}
	
	
//##################################################	
	
	public class Node implements Comparable<Node> {
		
		protected K key;
		protected V value;
		protected Node p;
		protected Node left;
		protected Node right;
		protected int color; // RED or BLK
		
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
			this.left = nil; // FIXME bug check
			this.right = nil;
		}
		
		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
		public String getColor() {
			if (color == 1) {
				return "R";
			} else {
				return "B";
			}
		}
		
		public void getData() {
			String nodeKey = this.key.toString();
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