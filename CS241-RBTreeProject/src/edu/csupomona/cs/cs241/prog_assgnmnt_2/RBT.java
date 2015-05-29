package edu.csupomona.cs.cs241.prog_assgnmnt_2;

public class RBT<K extends Comparable<K>, V> implements Tree<K, V>{

	private Node root;
	private Node sentinel = new Node();
	
	public void add(K key, V value) {
		
	}

	public V remove(K key) {
		return null;
	}
	
	public V lookup(K key) {
		Node n = root;
		
		while (n != null) {
			
			if (key.compareTo(n.key) == 0) {
				return n.value;
			} else if (key.compareTo(n.key) < 0) {
				n = n.left;
			} else {
				n = n.right;
			}
			
		}
		return null;
	}

	public String display() { // toPrettyPrint();
		return null;
	}
	
//##################################################
	
	public void insert(K key, V value) {	
		
		if (root == null) {
			root = new Node(key, value);
			root.p = sentinel;
		} else {
			
			Node pn = sentinel; // previous node
			Node cn = root; // current node
			Node nn = new Node(key, value); // new node 
			
			while (cn != null) { // FIXME change from sentinel to null
				pn = cn;
				if (nn.key.compareTo(cn.key) < 0) {
					cn = cn.left;
				} else {
					cn = cn.right;
				}				
			}
			
			nn.p = pn;
			
			// takes care of parent of the new node's pointers
			if (pn == sentinel) {
				root = nn;
			} else if (nn.key.compareTo(pn.key) < 0) {
				pn.left = nn;
			} else {
				pn.right = nn;
			}
			
			nn.left = sentinel;
			nn.right = sentinel;
			nn.color = 1;
			/* RB INSERT FIXUP OCCURS HERE */
			fixAdd(nn);
		}
	}
	
	public void fixAdd(Node node) {
		
		while (node.p.color == 1) { // while the parent of our nn is red (which is also red)
			if (node.p == node.p.p.left) { // if the nn's parent is the same as the uncle 
				Node pn = node.p.p.right;
				if (pn.color == 1) {
					node.p.color = 0;
					pn.color = 0;
					node.p.p.color = 0;
					node = node.p.p;
				} else if (node == node.p.right) {
					node = node.p;
					leftRotate(node);
					node.p.color = 0;
					node.p.p.color = 1;
					rightRotate(node.p.p);
				}
			} else { // symmetric case
				Node pn = node.p.p.left;
				if (pn.color == 1) {
					node.p.color = 0;
					pn.color = 0;
					node.p.p.color = 0;
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
		root.color = 0;
	}
	
	public V delete(K key) { // TODO
		V value = null;
		
		Node z = root.locate(key);
		Node y = new Node();
		Node x = new Node();
		
		if (z.left == sentinel || z.right == sentinel) {
			y = z;
		} else {
			y = successor(z);
		}
		
		if (y.left != sentinel) {
			x = y.left;
		} else {
			x = y.right;
		}
		
		x.p = y.p;
		
		if (y.p == sentinel) {
			root = x;
		} else if (y == y.p.left) {
			y.p.left = x;
		} else {
			y.p.right = x;
		}
		
		if (y != z) {
			z.key = y.key;
		}
		
		return value;
	}
	
	public void fixRem(Node node) {
		
		Node w;
		
		while (node != root && node.color == 0) {
			if (node == node.p.left) {
				w = node.p.right;
				
				if (w.color == 1) {
					w.color = 0;
					node.p.color = 1;
					leftRotate(node.p);
					w = node.p.right;
				}
				
				if (w.left.color == 0 && w.right.color == 0) {
					w.color = 1;
					node = node.p;
				} else if (w.right.color == 0) {
					w.left.color = 0;
					w.color = 1;
					rightRotate(w);
					w = node.p.right;
				}
				
				w.color = node.p.color;
				node.p.color = 0;
				w.right.color = 0;
				leftRotate(node.p);
				node = root;
			} else { // symmetric case 
				w = node.p.left;
				
				if (w.color == 1) {
					w.color = 0;
					node.p.color = 1;
					rightRotate(node.p);
					w = node.p.left;
				}
				
				if (w.right.color == 0 && w.left.color == 0) {
					w.color = 1;
					node = node.p;
				} else if (w.left.color == 0) {
					w.right.color = 0;
					w.color = 1;
					leftRotate(w);
					w = node.p.left;
				}
				
				w.color = node.p.color;
				node.p.color = 0;
				w.left.color = 0;
				rightRotate(node.p);
				node = root;
			}	
		}
		
		node.color = 0;
	}
	
	public void print(Node node) { // node == root[T]
		// I'd like to call this uglyPrint()
		// must use in-order traversal to to traverse values
		// from left to right
		Node temp = node;
		if (temp != null) { // FIXME make this compatible with sentinels. replace null with sentinal in case you forget.
			print(temp.left);
			System.out.println("[" + node.key + "-" + node.printColor(node.color) + "]");
			print(temp.right);
		} else {
			System.out.println("NULL");
			// display parent
			// if it is left or right child relative to parent
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
		
		if (pivot.left != sentinel) {
			pivot.left.p = node;
		}
		
		pivot.p = node.p;
		
		if (node.p == sentinel) {
			root = pivot;
		} else if (node == node.p.left) {
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
		
		if (pivot.right != sentinel) {
			pivot.right.p = node;
		}
		
		pivot.p  = node.p;
		
		if (node.p == sentinel) {
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
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	public Node max(Node node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}
	
	public Node successor(Node node) {
		Node successor = null;
		
		if (node.right  != null) { // if there is a right subtree of node
			successor = min(node.right);
			return successor;
		}
		
		// in the case that the node has a successor but no right subtree, we must travel up 
		Node temp = node.p;
		while (temp != null && node == temp.right) {
			node = temp;
			temp = temp.p;
		}
		successor = temp;
		return successor;
	}
	
	public Node predecessor(Node node) {
		Node predecessor = null;
		
		if (node.left != null) {
			predecessor = max(node.left);
			return predecessor;
		}
		
		Node temp = node.p;
		
		while (temp != null && node == temp.left) {
			node = temp;
			temp = temp.p;
		}
		predecessor = temp;
		return predecessor;
	}
	
	public boolean isRed(Node node) {
		if (node == null) {
			return false;
		}
		return (node.color == 1);
	}
		
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
		}
		
		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}	
		
		public Node locate(K key) {
			Node temp = this;
			
			while (temp != null && key != this.key) {
				if (key.compareTo(temp.key) < 0) {
					temp = this.left;
				} else {
					temp = this.right;
				}
			}
			
			return temp;
		}
		
		public String printColor(int color) {
			String msg = null;
			
			if (color == 0) {
				msg = "BLK";
			} else {
				msg = "RED";
			}
			
			return msg;
		}
	} // end of Node.class
	
} // end of RBT.java
