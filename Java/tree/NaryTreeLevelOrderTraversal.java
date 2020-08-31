package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N-ary Tree Level Order Traversal
 * @author Katherine
 *
 */
public class NaryTreeLevelOrderTraversal {
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
        
        public List<List<Integer>> levelOrder(Node root) {
            Queue<Node> q = new LinkedList<Node>();
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            if (root == null) return res;
            
            q.add(root);
            
            while (!q.isEmpty()) {
                List<Integer> curLevel = new LinkedList<>();
                int len = q.size();
                
                for (int i = 0 ; i < len; i++) {
                    Node node = q.poll();
                    curLevel.add(node.val);
                    for (Node n: node.children) {
                        q.add(n);
                    }
                }
                
                res.add(curLevel);
            }
            
            return res;
        }
    }
}
