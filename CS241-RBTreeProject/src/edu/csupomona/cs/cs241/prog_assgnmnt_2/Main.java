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

import java.util.Random;

public class Main {
	
	public static void main(String[] args) {
		RBT<Integer, String> rbt = new RBT<Integer, String>();
		Debug debug = new Debug();
		
//        Random rand = new Random();
//        rand.setSeed(System.currentTimeMillis());
//        //int  n = rand.nextInt(50) + 1;
//        Integer [] nArr = new Integer [800000];
//        for(int i = 0; i<50; i++){
//            Integer k = rand.nextInt(99999 + 1);
//            nArr[i] = k;
//            rbt.add(k, "x");
//            //System.out.println("nArr[" + i + "] = " + k);
//        }
//        for(int i = 0; i<40; i++){
//            rbt.remove(nArr[i]);
//        }
		
		rbt.add(45, "X");
		rbt.add(5,  "X");
		rbt.add(16, "X");
		rbt.add(15, "X");
		rbt.add(8,  "X");
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.add(81, "X");
		rbt.add(42, "X");
		rbt.add(18, "X");
		rbt.add(64, "X");
		rbt.add(90, "X");
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.add(68, "X");
		rbt.add(17, "X");
		rbt.add(75, "X");
		rbt.add(7,  "X");
		System.out.println("Verification: " + rbt.verify(rbt.root));
		
		System.out.println("remove..");
		
		rbt.remove(5);
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.remove(16);
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.remove(42);
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.remove(90);
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.remove(64);
		System.out.println("Verification: " + rbt.verify(rbt.root));
		rbt.remove(45);
		System.out.println("Verification: " + rbt.verify(rbt.root));

		rbt.print();
		
		debug.printNode(rbt.root);
	}
}
