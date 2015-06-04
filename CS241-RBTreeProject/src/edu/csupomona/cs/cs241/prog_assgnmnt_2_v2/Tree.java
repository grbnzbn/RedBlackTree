package edu.csupomona.cs.cs241.prog_assgnmnt_2_v2;

public interface Tree<K extends Comparable<K>, V> {
	
	public void add(K key, V value);
	
	public V remove(K key);
	
	public V lookup(K key);
	
	public String display();
}
