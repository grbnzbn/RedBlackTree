package edu.csupomona.cs.cs241.prog_assgnmnt_2;

public interface RedBlackTree<V extends Comparable<V>> {
	
	public static enum COLOR {RED, BLACK};
	
	public void add();
	
	public void remove();
	
	public void search();
	
	public void display();
}
