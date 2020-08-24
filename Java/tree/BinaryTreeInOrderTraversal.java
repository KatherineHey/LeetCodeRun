package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. Binary Tree Inorder Traversal
 * @author Katherine
 *
 */
public class BinaryTreeInOrderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<Integer>();
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                
                results.add(root.val);
                root = root.right;
            }
        }
        
        return results;
    }
    
    public List<Integer> InorderMorisTraversal(TreeNode current) {
        List<Integer> results = new ArrayList<Integer>();
        while (current != null) {
            if (current.left == null) {
                results.add(current.val);
                current = current.right;
            } else {
                TreeNode pre = current.left;
                
                while (pre.right != null && pre.right != current) {
                    pre = pre.right;
                }
                
                if (pre.right == current) {
                    pre.right = null; // already visited, return to original state
                    results.add(current.val);
                    current = current.right;
                } else {
                    pre.right = current;
                    current = current.left;
                }
            }
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        BinaryTreeInOrderTraversal bt = new BinaryTreeInOrderTraversal();
        TreeNode root = new TreeNode(1);
        root.left = null; root.right = new TreeNode(2);
        root.right.left = new TreeNode(3); root.right.right = null;
        System.out.println(bt.inorderTraversal(root));
        System.out.println(bt.InorderMorisTraversal(root));
    }
}
