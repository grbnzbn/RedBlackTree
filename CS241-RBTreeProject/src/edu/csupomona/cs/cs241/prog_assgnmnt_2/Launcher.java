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

import java.util.Scanner;

public class Launcher {

	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		RBT<Integer, String> rbt = new RBT<Integer, String>();
		
//		Launcher registry = new Launcher();
		//K key = <Integer> Student ID
		//V value = <String> Student Name
		
		rbt.add(45, "X");
		rbt.add(5,  "X");
		rbt.add(16, "X");
		rbt.add(15, "X");
		rbt.add(8,  "X");
		
		rbt.add(81, "X");
		rbt.add(42, "X");
		rbt.add(18, "X");
		rbt.add(64, "X");
		rbt.add(90, "X");
		
		rbt.add(68, "X");
		rbt.add(17, "X");
		rbt.add(75, "X");
		rbt.add(7,  "X");
		rbt.add(16, "X");
		
//		rbt.lookup(42);
//		System.out.println(rbt.getSize(rbt.root));
		
		rbt.print();
		BTreePrinter.printNode(rbt.root);	
		System.out.println(rbt.getHeight(rbt.root));
	}
}
