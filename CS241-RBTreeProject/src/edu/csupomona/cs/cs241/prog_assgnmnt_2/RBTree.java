package edu.csupomona.cs.cs241.prog_assgnmnt_2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RBTree<K extends Comparable <K>, V> implements Tree<K, V> {

	private Queue<Node> theQueue;
	private Stack<Node> theStack;
	public Node root;
	
	public void add(K key, V value) {
		
		if (root == null) {
			
		} else {
			
		}
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
	
	public class Node implements Comparable<Node> {

		protected K key;
		protected V value;
		protected Node parent;
		protected Node left;
		protected Node right;
		protected RBTree.COLOR color;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
		
	}

}
