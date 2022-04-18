```
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return maxDepth(root, 1);
    }
    
    public int maxDepth(TreeNode root, int depth) {
        if (root == null) return depth - 1;
        int left = maxDepth(root.left, depth + 1);
        int right = maxDepth(root.right, depth + 1);
        return left > right? left: right;
    }
}
```
