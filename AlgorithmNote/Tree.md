
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


# BFS / Level Order Traversal


```java
class Solution {
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root == null)
            return result;
        
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        while(q.size() > 0) {
            root = q.poll();
            result.add(root.val);
            if(root.left != null)
                q.add(root.left);
            if(root.right != null)
                q.add(root.right);
        }
        
        return result;
    }
}
```

#    Maximum Depth of N-ary Tree
#    Serialization and deserialization of trees - Leetcode
#    Binary Search Tree
#    Lowest Common Ancestor - Leetcode

#  超纲
### Morris In-order traversal by Tushar Roy (Video)

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

 
