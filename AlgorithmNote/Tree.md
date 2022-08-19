
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


#    Lowest Common Ancestor - Leetcode

```java
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode l = lowestCommonAncestor(root.left, p, q);
        TreeNode r = lowestCommonAncestor(root.right, p, q);
        if (l != null && r != null) return root;
        return l==null?r:l; 
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
    public List<Integer> InorderMorisTraversal(TreeNode current) {
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

 
