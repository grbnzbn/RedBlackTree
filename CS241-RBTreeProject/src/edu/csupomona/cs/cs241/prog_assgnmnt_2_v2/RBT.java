package edu.csupomona.cs.cs241.prog_assgnmnt_2_v2;

public class RBT <K extends Comparable<K>, V> implements Tree<K, V>{

	@Override
	public void add(K key, V value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V lookup(K key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return null;
	}

	public class Node implements Comparable<Node> {
		
		protected K key;
		protected V value;
		protected Node p;
		protected Node left;
		protected Node right;
		
		public Node() {
			
		}

		public int compareTo(Node n) {
			return this.key.compareTo(n.key);
		}
	}
}
