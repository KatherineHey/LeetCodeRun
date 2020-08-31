package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class NaryTreePostorderTraversal {
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
    
    public List<Integer> postorder(Node root) {
        List<Integer> results = new ArrayList<Integer>();
        Stack<Node> s = new Stack<Node>();
        
        if (root == null) return results;
        
        s.push(root);
        
        while (!s.isEmpty()) {
            root = s.pop();
            results.add(root.val);
            
            for (Node n : root.children) {
                s.add(n);
            }
        }
        
        Collections.reverse(results);
        
        return results;
    }
}
