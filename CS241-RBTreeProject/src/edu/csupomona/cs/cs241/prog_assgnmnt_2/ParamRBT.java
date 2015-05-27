package edu.csupomona.cs.cs241.prog_assgnmnt_2;

import java.util.LinkedList;

/*
 * This class serves as a purpose to pursue any possible differing approaches
 * to the implementation of a red black tree
 */
public class ParamRBT<K extends Comparable<K>, V> implements Tree<K, V>{

	
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

	
	public class Node implements Comparable<Node> {

		protected LinkedList<Node> dir = new LinkedList<Node>();
		
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
		}
		
		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
	}
	
}
