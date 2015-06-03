/*
- * DISCLAIMER: I DID NOT WRITE THIS CODE.
- * THE FOLLOWING CLASS WAS USED IN ORDER TO MAKE DEBUGGING EASIER
- * USE THIS TO MAKE IT EASIER TO DEBUG AS WELL
- */
package edu.csupomona.cs.cs241.prog_assgnmnt_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.csupomona.cs.cs241.prog_assgnmnt_2.RBT.Node;
 
class Debug{
 
    public static <K extends Comparable<K>,V> void printNode(Node root) {
        int maxLevel = Debug.maxLevel(root);
 
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }
 
    private static <K extends Comparable<K>,V> void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || Debug.isAllElementsNull(nodes))
            return;
 
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
 
        Debug.printWhitespaces(firstSpaces);
 
        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                if(node.key == null)
                    System.out.print("x");
                else if(node.color == 1) {
                    System.out.print(node.key + node.getColor());
                } else
                    System.out.print(node.key + node.getColor());
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
 
            Debug.printWhitespaces(betweenSpaces);
        }
        System.out.println("");
 
        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                Debug.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    Debug.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }
 
                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    Debug.printWhitespaces(1);
 
                Debug.printWhitespaces(i + i - 1);
 
                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    Debug.printWhitespaces(1);
 
                Debug.printWhitespaces(endgeLines + endgeLines - i);
            }
 
            System.out.println("");
        }
 
        printNodeInternal(newNodes, level + 1, maxLevel);
    }
 
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }
 
    private static <K extends Comparable<K>,V> int maxLevel(Node node) {
        if (node == null)
            return 0;
 
        return Math.max(Debug.maxLevel(node.left), Debug.maxLevel(node.right)) + 1;
    }
 
    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
 
        return true;
    }
    
}