package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BoundaryTraversalofbinarytree {
    public class Node {
        public int data;
        public Node left, right;

        Node(int item) {
            data = item;
            left = right = null;
        }
        
        Node(int item, int leftVal, int rightVal) {
            data = item;
            left = new Node(leftVal);
            right = new Node(rightVal);
        }
    }

    List<Integer> printLeftBoundary(Node node) {
        List<Integer> arr = new ArrayList<Integer>();
        // Print all the left boundary except the last left leaf
        while (node != null && (node.left != null || node.right != null)) {
            arr.add(node.data);
            node = node.left;
        }
        
        return arr;
    }

    List<Integer> printLeavesDfs(Node node) {
        List<Integer> arr = new ArrayList<Integer>();
        if (node == null) return arr;
        if (node.left == null && node.right == null) arr.add(node.data);
        
        arr.addAll(printLeavesDfs(node.left));
        arr.addAll(printLeavesDfs(node.right));
        
        return arr;
    }

    List<Integer> printRightBoundary(Node node) {
        // Print all the left boundary except the last right leave
        List<Integer> arr = new ArrayList<Integer>();
        if (node != null) node = node.right;
        
        while (node != null && (node.left != null || node.right != null)) {
            arr.add(0, node.data);
            node = node.right;
        }

        return arr;
    }

    List<Integer> printBoundary(Node node) {
        List<Integer> boundary = printLeftBoundary(node);
        boundary.addAll(printLeavesDfs(node));
        boundary.addAll(printRightBoundary(node));
        
        return boundary;
    }
    
    public static void main(String[] args) {
        BoundaryTraversalofbinarytree bt = new BoundaryTraversalofbinarytree();
        Node n = bt.new Node(20);
        Node n12 = bt.new Node(12, 10,14);
        Node n8 = bt.new Node(8); n8.left = bt.new Node(4); n8.right = n12;
        n.left = n8; n.right = bt.new Node(22);
        List<Integer> arr = bt.printBoundary(n);
        
        for(int i : arr) {
            System.out.println(i);
        }
    }
}
