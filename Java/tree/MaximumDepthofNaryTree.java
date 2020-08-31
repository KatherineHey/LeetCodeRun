package tree;

import java.util.List;

public class MaximumDepthofNaryTree {
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
    
    public int maxDepth(Node root) {
        return dfs(root);
    }
    
    public int dfs(Node root) {
        if (root == null) return 0;
        
        int maxChildHeight = 0;
        for (Node n : root.children) {
            int h = dfs(n);
            maxChildHeight = Math.max(maxChildHeight, h);
        }
        
        return maxChildHeight + 1;
    }
}
