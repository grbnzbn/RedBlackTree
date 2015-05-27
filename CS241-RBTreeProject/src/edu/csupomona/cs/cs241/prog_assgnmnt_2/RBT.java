package edu.csupomona.cs.cs241.prog_assgnmnt_2;

/*
 * This class serves as a purpose to pursue any possible differing approaches
 * to the implementation of a red black tree
 */
public class RBT<K extends Comparable<K>, V> implements Tree<K, V>{

	
	public void add(K key, V value) {
	}

	public V remove(K key) {
		return null;
	}

	public V lookup(K key) {
		return null;
	}

	public String display() { // toPrettyPrint();
		return null;
	}

//##################################################	

	
	public class Node implements Comparable<Node> {
		
		protected K key;
		protected V value;
		protected Node parent;
		protected Node left;
		protected Node right;
		protected int color; // RED or BLK
		
		public Node() {
			this.key = null;
			this.value = null;
			this.parent = null;
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
	}
	
}
