package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreePreOrderTraversal {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
    
    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        Stack<TreeNode> s = new Stack<>();
        while (!s.isEmpty() || root != null) {
            if (root != null) {
                result.add(root.val);
                s.push(root);
                root = root.left;
            }
            else {
                root = s.pop();
                root = root.right;
            }
        }
        
        return result;
    }
    
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        preorderTraversalHelper(root);
        return result;
    }
    
    public void preorderTraversalHelper(TreeNode root) {
        if (root == null) return;
        if(root != null) result.add(root.val);
        
        preorderTraversalHelper(root.left);
        preorderTraversalHelper(root.right);
    }
    
    /**
     * 0. add root val to array
     * 1. if root.left is null, go to root.right
     * 2. is root.left is not null, find left subtree right most child, link it to root, then go to root.left
     * @param root
     * @return
     */
    public List<Integer> morisPreorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                results.add(root.val);
                root = root.right;
            } else {
                // find the right most node of left subtree
                TreeNode leftSubTreeRightMostNode = root.left;
                while (leftSubTreeRightMostNode.right != null && leftSubTreeRightMostNode.right != root) {
                    leftSubTreeRightMostNode = leftSubTreeRightMostNode.right;
                }
                
                if (leftSubTreeRightMostNode.right == null) {
                    results.add(root.val);// the only difference with inorder-traversal
                    leftSubTreeRightMostNode.right = root; //set right node to the current root
                    root = root.left;
                } else {
                    leftSubTreeRightMostNode.right = null; // has already visited
                    root = root.right;
                }
            }
        }
        
        return results;
    }
    
    List<Integer> res = new ArrayList<Integer>();
    
    public List<Integer> preorder(Node root) {
        preorderNAry(root);
        return res;
    }
    
    public void preorderNAry(Node root) {
        if (root == null) return;

        res.add(root.val);
        List<Node> children = root.children;
        for (Node n: children) {
            preorder(n);
        }
    }
}
