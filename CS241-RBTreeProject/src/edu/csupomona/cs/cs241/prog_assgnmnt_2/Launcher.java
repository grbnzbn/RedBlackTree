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

	Tree<Integer, String> rbt = new RBT<Integer, String>();
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		Launcher registry = new Launcher();
		//K key = <Integer> Student ID
		//V value = <String> Student Name

		 ((RBT<Integer, String>) registry.rbt).insert(1, "Daniel");
		 ((RBT<Integer, String>) registry.rbt).insert(3, "Kevin");
		 ((RBT<Integer, String>) registry.rbt).insert(2, "Eric");
//		 ((RBT<Integer, String>) registry.rbt).print(((RBT<Integer, String>) registry.rbt).getRoot());
	}
	
	public void addEntry() {
		System.out.println("Adding...");
		System.out.print("Enter ID#: ");
		Integer ID = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter Name: ");
		String name = sc.nextLine();

		((RBTree<Integer, String>) rbt).insertRB(ID, name);
	}
	
	public void showEntry() {
		rbt.display();
	}
}
