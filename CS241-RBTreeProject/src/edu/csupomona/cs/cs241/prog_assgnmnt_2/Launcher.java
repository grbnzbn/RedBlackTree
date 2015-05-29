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
		 ((RBT<Integer, String>) registry.rbt).print(((RBT<Integer, String>) registry.rbt).getRoot());
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
