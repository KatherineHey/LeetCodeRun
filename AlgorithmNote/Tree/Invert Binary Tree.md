Level order traversal 
```
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int len = q.size();
            
            for (int i = 0 ; i < len; i++) {
                TreeNode cur = q.poll();
                TreeNode leftTmp = cur.left;
                cur.left = cur.right;
                cur.right = leftTmp;
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        
        return root;
    }
}
```
