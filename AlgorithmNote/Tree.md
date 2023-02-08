
#    In-order(BOTH recursive and iterative) :star:

### Tips: tackle various tree questions using inorder traversal, since BST has the property left subtree all nodes' values < middle < right subtree all nodes' values, essentially inorder traversal for BST creates an increasing list.

```java
   public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        dfs(root, list);
        return list;
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        if(root == null)
            return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    } 
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<Integer>();
        
        Stack<TreeNode> s = new Stack<TreeNode>();
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            
            root = s.pop();
            results.add(root.val);
            root = root.right;
        }
        
        return results;
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> treeNodes = new Stack<TreeNode>();
        List<Integer> result = new ArrayList<Integer>();

        while (root != null || !treeNodes.isEmpty()) {
            if (root != null) {
                treeNodes.push(root);
                root = root.left;
            } else {
                root = treeNodes.pop();
                result.add(root.val); // only different from preorder
                root = root.right;
            }
        }

        return result;
    }

```

#    Pre-order(BOTH recursive and iterative)
```java

    List<Integer> result = new ArrayList<>();

    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        preorderTraversalHelper(root);
        return result;
    }

    public void preorderTraversalHelper(TreeNode root) {
        if (root == null)
            return;
        if (root != null)
            result.add(root.val);

        preorderTraversalHelper(root.left);
        preorderTraversalHelper(root.right);
    }

  // iterative
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> s = new Stack<>();
        while (!s.isEmpty() || root != null) {
            if (root != null) {
                result.add(root.val);
                s.push(root);
                root = root.left;
            } else {
                root = s.pop();
                root = root.right;
            }
        }

        return result;
    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur != null) {
                s.push(cur.right);
                s.push(cur.left);

                res.add(cur.val);
            }
        }

        return res;
    }

```

#    Post-order(BOTH recursive and iterative)

```java

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

```

# N-ary Tree Preorder Traversal

```java
class Solution {
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
```

# N-ary Tree Postorder Traversal

```java

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
```

# BFS / Level Order Traversal

N-ary Tree Level Order Traversal


```java
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
```

#    :heart: Construct Binary Tree from Preorder and Inorder Traversal 
```java

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        
        return buildTree(preorder, inorder, 0, len-1, 0, len-1);
    }
    
    // preStart: start index of preorder array, includsive
    // preEnd: end index of preorder array, inclusive
    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {
        TreeNode node = new TreeNode(preorder[preStart]);
        
        // Since preorder and inorder arrays contain unique values
        // Find node'val in inorder
        int nodeIdxInInorder = findVal(node.val, inorder, inStart, inEnd);
        int leftSubTreeSize = nodeIdxInInorder - inStart;
        int rightSubTreeSize = inEnd - nodeIdxInInorder;
        
        if (leftSubTreeSize > 0)
            node.left = buildTree(preorder, inorder, preStart+1, preStart + leftSubTreeSize, inStart, nodeIdxInInorder-1);
        
        if (rightSubTreeSize > 0)
            node.right = buildTree(preorder, inorder, preStart + leftSubTreeSize + 1, preEnd, nodeIdxInInorder + 1, inEnd);
        
        return node;
    }
    
    // return index of a given value in the array within left and right boundaries
    public int findVal(int val, int[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            if (val == arr[i]) return i;
        }
        
        return -1; // shouldn't happen
    }
}
```

#    Construct Binary Tree from String

https://www.jiakaobo.com/leetcode/536.%20Construct%20Binary%20Tree%20from%20String.html

```java
// e.g. Input: s = "4(2(3)(1))(6(5))"
// Output: [4,2,6,3,1,5]
    public TreeNode str2tree(String s) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        for (int i = 0, j = i; i < s.length(); i++, j = i) {
            char c = s.charAt(i);
            if (c == '(') {
                continue;
            } else if (c == ')') {
                TreeNode child = stack.pop();
                TreeNode parent = stack.peek();
                if (parent.left == null) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else if(Character.isDigit(c) || c == '-'){
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    i++;
                }

                TreeNode node = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                stack.push(node);
            }
        } 

        return stack.peek();
    }

```

# :heart:   Flatten Binary Tree to Linked List

```java

   public void flatten(TreeNode root) {
        if (root == null) return;

        Deque<TreeNode> s = new ArrayDeque<>();
        s.push(root);

        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            if (cur.right != null) s.push(cur.right);
            if (cur.left != null) s.push(cur.left);

            if (!s.isEmpty()) cur.right = s.peek();

            cur.left = null;
        }
    }
```


#    Maximum Depth of N-ary Tree

```java
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
```


#    :heart: Lowest Common Ancestor - Leetcode

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) return root;
        return l==null?r:l; 
    }
```

#    :heart: 1110. Delete Nodes And Return Forest
```java
class Solution {
    List<TreeNode> result = new ArrayList<TreeNode>();
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<Integer>();
        for (int d: to_delete) {
            set.add(d);
        }
        
        if (!post(root, set)) result.add(root);
      
        
        return result;
    }
    
    public boolean post(TreeNode root, Set<Integer> set) {
        if (root == null) return false;
        
        if (post(root.left, set)) root.left = null;
        if (post(root.right, set)) root.right = null;
        
        // check if root exists in set
        if (set.contains(root.val)) {
            if (root.left != null) result.add(root.left);
            if (root.right != null) result.add(root.right);
            
            return true;
        }
        
        return false;
    }
}

```

#    Serialization and deserialization of trees - Leetcode

```java
public class Codec {
    // Serialization and deserialization of trees: Encodes a tree to a single string.
    // # to represent null node
    // , to separate every node
    private static final String NN = "#";
    private static final String separator = ",";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelperPreorder(root, sb);
        
        return sb.toString();
    }
    
    public void serializeHelperPreorder(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(separator);
            return;
        }    
        
        sb.append(node.val).append(separator);
        
        serializeHelperPreorder(node.left, sb);
        serializeHelperPreorder(node.right, sb);
        
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(separator)));
    
        return deserializeHelper(nodes);
    }
    
    public TreeNode deserializeHelper(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = deserializeHelper(nodes);
            node.right = deserializeHelper(nodes);
            return node;
        }
    }
}

```

# Right side view
```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        rvdfs(root, arr, 0);
        return arr;    
    }
    
    public void rvdfs(TreeNode curr, List<Integer> result, int currDepth) {
        if (curr == null)
            return;
        
        if (currDepth == result.size()) {
            result.add(curr.val);
        }
        
        rvdfs(curr.right, result, currDepth+1);
        rvdfs(curr.left, result, currDepth+1);
    }
}
```

# Boundary Traversal Anticlockwise

```java
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null)
            return result;
            
        result.add(root.val);
        leftSide(root.left, result);
        leafNode(root.left, result);
        leafNode(root.right, result);
        rightSide(root.right, result);
        
        return result;
    }
    
    private void leftSide(TreeNode node, List<Integer> result) {
        if(node == null)
            return;
        if(node.left != null) {
            result.add(node.val);
            leftSide(node.left, result);
        } else if(node.right != null) {
            result.add(node.val);
            leftSide(node.right, result);
        }
    }
    
    private void leafNode(TreeNode node, List<Integer> result) {
        if(node == null)
            return;
        leafNode(node.left, result);
        if(node.left == null && node.right == null)
            result.add(node.val);
        leafNode(node.right, result);
    }
    
    
    private void rightSide(TreeNode node, List<Integer> result) {
        if(node == null)
            return;
        if(node.right != null) {
            rightSide(node.right, result);
            result.add(node.val);
        } else if(node.left != null) {
            rightSide(node.left, result);
            result.add(node.val);
        }
    }
}
```

# Binary Tree Maximum Path Sum
``` java
class Solution {
    int maxPath = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        pathHelper(root);
        return maxPath;
    }
    
    // return max value of path going through root
    public int pathHelper(TreeNode root) {
        if (root == null) return 0;
        
        // max value from left tree 
        // now find the max of all the four paths
        int left = pathHelper(root.left);
        int right = pathHelper(root.right);
        int leftPath = root.val + left;
        int rightPath = root.val + right;
        int completePath = root.val + right + left;

        maxPath = Math.max(maxPath, Math.max(root.val, Math.max(leftPath, Math.max(rightPath, completePath))));
        return Math.max(leftPath, Math.max(rightPath, root.val));
    }
}
```


# 987 Vertical Order Traversal of a Binary Tree
```java
// first sort on x, then sort on y, then sort on the node's val
    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> m = new TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>();
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        
        dfs(root, 0, 0);
        
        // Treemap is ordered
        for (TreeMap<Integer, PriorityQueue<Integer>> tm : m.values()) {
            res.add(new ArrayList<>());
            
            for (PriorityQueue<Integer> pq : tm.values()) {
                while (!pq.isEmpty()) {
                    res.get(res.size()-1).add(pq.poll());    
                }
            }
        }
        
        return res;
    }
    
    public void dfs(TreeNode cur, int x, int y) {
        if (!m.containsKey(x)) {
            m.put(x, new TreeMap<>());
            
            m.get(x).put(y, new PriorityQueue<>());
        } else if (!m.get(x).containsKey(y)) {
            m.get(x).put(y, new PriorityQueue<>());
        }
        
        m.get(x).get(y).offer(cur.val);
        
        if (cur.left != null)
            dfs(cur.left, x-1, y+1);
        if (cur.right != null)
            dfs(cur.right, x+1, y+1);
    }
```
   
# :heart:2096. Step-By-Step Directions From a Binary Tree out of memory, needs bottom up approach

```java
private boolean find(TreeNode n, int val, StringBuilder sb) {
    if (n.val == val) 
        return true;
    if (n.left != null && find(n.left, val, sb))
        sb.append("L");
    else if (n.right != null && find(n.right, val, sb))
        sb.append("R");
    return sb.length() > 0;
}
public String getDirections(TreeNode root, int startValue, int destValue) {
    StringBuilder s = new StringBuilder(), d = new StringBuilder();
    find(root, startValue, s);
    find(root, destValue, d);
    int i = 0, max_i = Math.min(d.length(), s.length());
    while (i < max_i && s.charAt(s.length() - i - 1) == d.charAt(d.length() - i - 1))
        ++i;
    return "U".repeat(s.length() - i) + d.reverse().toString().substring(i);
}
```

# Word Search
```java
class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        boolean[][] used = new boolean[m][n];
        
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (board[x][y] == word.charAt(0)) {
                    if (dfs(board, used, word, 0, x, y))
                        return true;
                }
            }
        }        
        
        return false;
    }
    
    public boolean dfs(char[][] board, boolean[][] used, String word, int i, int x, int y) {
        if (i == word.length())
            return true;
        
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != word.charAt(i) || used[x][y])
            return false;
        
        used[x][y] = true;
        
        int[][] dirs = {{0,1}, {0,-1}, {1, 0}, {-1, 0}};
        
        if (dfs(board, used, word, i+1, x+dirs[0][0], y+dirs[0][1]) ||
           dfs(board, used, word, i+1, x+dirs[1][0], y+dirs[1][1]) ||
           dfs(board, used, word, i+1, x+dirs[2][0], y+dirs[2][1]) ||
           dfs(board, used, word, i+1, x+dirs[3][0], y+dirs[3][1])) {
            return true;
        }
        
        used[x][y] = false;
        return false;
    }
}
```

#    Binary Search Tree

Also called an ordered or sorted binary tree, is a rooted binary tree data structure with the key of each internal node being greater than all the keys in the respective node's left subtree and less than the ones in its right subtree



## Morris In-order traversal by Tushar Roy (Video)

```java

    /**
     * 0. add root val to array 1. if root.left is null, go to root.right 2. is
     * root.left is not null, find left subtree right most child, link it to root,
     * then go to root.left
     * 
     * @param root
     * @return
     */
    public List<Integer> morrisPreorderTraversal(TreeNode root) {
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
                    leftSubTreeRightMostNode.right = root; // set right node to the current root
                    root = root.left;
                } else {
                    leftSubTreeRightMostNode.right = null; // has already visited
                    root = root.right;
                }
            }
        }

        return results;
    }
```
```
1. Initialize current as root 
2. While current is not NULL
   If the current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Find rightmost node in current left subtree OR
              node whose right child == current.
         If we found right child == current
             a) Update the right child as NULL of that node whose right child is current
             b) Print current’s data
             c) Go to the right, i.e. current = current->right
         Else
             a) Make current as the right child of that rightmost 
                node we found; and 
             b) Go to this left child, i.e., current = current->left
```
             
```java 
    public List<Integer> InorderMorrisTraversal(TreeNode current) {
        List<Integer> results = new ArrayList<>();
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

```

### Threaded Binary Tree

[Threaded Binary Tree](https://algorithms.tutorialhorizon.com/introduction-to-threaded-binary-tree/)

##### What is Threaded Binary Tree??

A binary tree is threaded by making all right child pointers that would normally be null point to the inorder successor of the node (if it exists), and all left child pointers that would normally be null point to the inorder predecessor of the node.   

    - We have the pointers reference the next node in an inorder traversal; called threads
    - We need to know if a pointer is an actual link or a thread, so we keep a boolean for each pointer

 
