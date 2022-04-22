```
    public boolean InorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<>();
        long pre = Long.MIN_VALUE;
        while (!treeNodes.isEmpty() || root != null) {
            if (root != null) {
                treeNodes.push(root);
                root = root.left;
            } else {
                root = treeNodes.pop();
                if (pre >= root.val) return false;
                pre = root.val;
                root = root.right;
                
            }
        }
        
        return true;
    }
    
    public boolean isValidBST(TreeNode root) {
        return InorderTraversal(root);
    }

```
