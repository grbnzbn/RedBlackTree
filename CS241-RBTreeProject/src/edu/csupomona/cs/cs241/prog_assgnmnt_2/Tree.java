package edu.csupomona.cs.cs241.prog_assgnmnt_2;

public interface Tree<K extends Comparable<K>, V> {
	
	public static enum COLOR {RED, BLK};
	
	public void add(K key, V value);
	
	public V remove(K key);
	
	public V lookup(K key);
	
	public String display(); // toPrettyString
	
	/*
	 * You are to implement a Red-Black Tree. To simplify the project, the values 
	 * stored in the nodes will be just integers (so you don't have to worry about 
	 * comparable types and such, or differentiating between keys and values). 
	 * The interface for the RBT should provide the expected operations for adding, 
	 * removing, and looking up values. In addition to that, it will support a void method 
	 * prettyPrint() that will print to the console the values in the tree, in a pyramid fashion, 
	 * each value printed along with its color, so as to make it easy to visualize the structure of the tree. 
	 */
}
