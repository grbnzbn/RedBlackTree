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
		
		rbt.insert(45, "John Doe");
		rbt.insert(5, "John Doe");
		rbt.insert(16, "John Doe");
		rbt.insert(15, "John Doe");
		rbt.insert(8, "John Doe");
		System.out.println(rbt.verify(rbt.root));
		rbt.uglyDisplay();
		
		System.out.println("----------");
		
		rbt.delete(16);
		System.out.println(rbt.verify(rbt.root));		
		rbt.uglyDisplay();
	}
}
