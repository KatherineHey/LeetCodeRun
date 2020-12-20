package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 5532. Even Odd Tree
 * @author Katherine
 *
 */
public class EvenOddTree {
    public boolean isEvenOddTree(TreeNode root) {
        ArrayList<TreeNode> evenrow = new ArrayList<TreeNode>();
        evenrow.add(root);
        ArrayList<TreeNode> oddrow = new ArrayList<TreeNode>();
        
        boolean even = true; // even row for row 0
        
        while (!evenrow.isEmpty() || !oddrow.isEmpty()) {
            int rowCount = even? evenrow.size() : oddrow.size();
            for (int i = 0 ; i < rowCount; i++) {
                if (even && isEven(evenrow.get(i).val)) return false;
                if (!even && !isEven(oddrow.get(i).val)) return false;
                
                if (even /*should be strictly increasing*/ && i+1 < rowCount) {
                    if (evenrow.get(i).val >= evenrow.get(i+1).val) return false;
                }
                
                if (!even && i+1 < rowCount) {
                    if (oddrow.get(i).val <= oddrow.get(i+1).val) return false;
                }
                
                if (even) {
                    if (evenrow.get(i).left != null) oddrow.add(evenrow.get(i).left);
                    if (evenrow.get(i).right != null) oddrow.add(evenrow.get(i).right);
                } else {
                    if (oddrow.get(i).left != null) evenrow.add(oddrow.get(i).left);
                    if (oddrow.get(i).right != null) evenrow.add(oddrow.get(i).right);
                }
            }
            
            if (even) {
                evenrow = new ArrayList<TreeNode>();
            } else {
                oddrow = new ArrayList<TreeNode>();
            }
            
            even = !even;
        }
        
        return true;
    }
    
    public boolean isEven(int num) {
        return num % 2 == 0;
    }
    
    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(3);
        TreeNode n6 = new TreeNode(7);
        TreeNode n3 = new TreeNode(4); n3.left = n4; n3.right = n5;
        TreeNode n2 = new TreeNode(2); n2.left = n6;
        TreeNode n1 = new TreeNode(5); n1.left = n2; n1.right = n3;
        
        EvenOddTree e = new EvenOddTree();
        System.out.println(e.isEvenOddTree(n1));
    }
}
