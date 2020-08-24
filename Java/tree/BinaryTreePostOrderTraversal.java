package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?
 * @author Katherine
 *
 */
public class BinaryTreePostOrderTraversal {
    List<Integer> results = new ArrayList<>();
    public List<Integer> postorderTraversalRecursive(TreeNode root) {
        dfs(root);
        return results;
    }
    
    public void dfs(TreeNode root) {
        if (root == null) return;
        
        dfs(root.left);
        dfs(root.right);
        
        results.add(root.val);
    }
    
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();
        if (root == null) return result;
        treeNodes.push(root);
        
        while (!treeNodes.isEmpty()) {
            TreeNode tmp = treeNodes.pop();
            result.add(0, tmp.val);

            if (tmp.left != null) {
                treeNodes.push(tmp.left);
            }
            
            if (tmp.right != null) {
                treeNodes.push(tmp.right);
            }
        }
        return result;
    }
}
