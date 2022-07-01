
# Representing graphs

### Edge lists
O(E) to search through edges if no specific order

### Adjacency matrices
O(V^2) space even for sparse graph

which vertices are adjacent to a given vertex i, check all ∣V∣ entries in row i, even if only a small number of vertices are adjacent to vertex i.

undirected graph - symmetric

### Adjacency lists

Edge (i,j) is in the graph? we go to i's adjacency list in constant time and then look for j in i's adjacency list. 
How long does that take in the worst case? The answer is Θ(d) where d is the degree of vertex i, because that's how long i's adjacency list is.

In an undirected graph, vertex j is in vertex i's adjacency list if and only if i is in j's adjacency list. If the graph is weighted, then each item in each adjacency list is either a two-item array or an object, giving the vertex number and the edge weight.


# DFS, BFS Explanation by csgator(BEST)
https://medium.com/leetcode-patterns/leetcode-pattern-1-bfs-dfs-25-of-the-problems-part-1-519450a84353

### A tree can be thought of as a connected acyclic graph with N nodes and N-1 edges. 

Any two vertices are connected by _exactly_ one path. So naturally the question arises, what about a DFS or a BFS on binary trees ? well there are 6 possible DFS traversals for binary trees ( 3 rose to fame while the other 3 are just symmetric )

    1. left, right, root ( Postorder) ~ 4. right, left, root
    2. left, root, right ( Inorder) ~ 5. right, root, left
    3. root, left, right ( Preorder) ~ 6. root, right, left
    
### DFS magic spell
1]push to stack, 2] pop top , 3] retrieve unvisited neighbours of top, push them to stack 4] repeat 1,2,3 while stack not empty. Now form a rap !

144. Preorder Traversal

Let’s walk through the above spell using an example tree.
![image](https://user-images.githubusercontent.com/62370578/176948288-4cfc6d6e-91dd-4d77-be1b-74ba043de5f7.png)

```java
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

### Stack for DFS, queue for BFS | extend DFS to graphs by including visited mechanism

1] Why are we using stack for DFS , couldn’t we use a queue ? ( always remember : stack for DFS, imagine a vertical flow | queue for BFS, horizontal flow, more on this later)

2] How do we extend this DFS process to general graphs or graphs disguised as matrices ( as in most LC problems). ( Include a mechanism to track visited)

#### Why

![image](https://user-images.githubusercontent.com/62370578/176949216-033172fc-e5ac-4ff5-8705-31fe9805d2a7.png)


#### Concluding thoughts on BFS :
- Problems in which you have to find shortest path are most likely calling for a BFS.
- For graphs having unit edge distances, shortest paths from any point is just a BFS starting at that point, no need for Dijkstra’s algorithm.
- Maze solving problems are mostly shortest path problems and every maze is just a fancy graph so you get the flow.

### Algorithms and Patterns


#### 1. Topological Sorting/ Kahn's Algorithm

207.Course Schedule
```java
    public boolean canFinish(int n, int[][] prerequisites) {
        ArrayList<Integer>[] G = new ArrayList[n];
        int[] degree = new int[n];
        ArrayList<Integer> bfs = new ArrayList();
        for (int i = 0; i < n; ++i) G[i] = new ArrayList<Integer>();
        for (int[] e : prerequisites) {
            G[e[1]].add(e[0]);
            degree[e[0]]++;
        }
        for (int i = 0; i < n; ++i) if (degree[i] == 0) bfs.add(i);
        for (int i = 0; i < bfs.size(); ++i)
            for (int j: G[bfs.get(i)])
                if (--degree[j] == 0) bfs.add(j);
        return bfs.size() == n;
    }
```


269.Alien Dictionary

```
There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.

You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.

Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.

A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.

Example 1:

Input: words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Example 2:

Input: words = ["z","x"]
Output: "zx"

Example 3:

Input: words = ["z","x","z"]
Output: ""
Explanation: The order is invalid, so return "".
```

```java
class Solution {
    // Easy to fail examples
    // ["ab","adc"], expect: "abcd"
    // ["z","x","a","zb","zx"], expect: ""
    public String alienOrder(String[] words) {
        // Compare every two words' lexicographical order to populate graph
        // From, Destinations
        HashMap<Character, HashSet<Character>> g = new HashMap<Character, HashSet<Character>>();
        int[] pre = new int[26];
        
        // Count of characters that ever existed
        int cnt = 0;
        
        for (int i = 0; i < 26; i++) {
            pre[i] = -1; // mark letter not exists
        }
        
        // Show these chars exist 
        for (String s : words) {
            for (char c: s.toCharArray()) {
                if (pre[c-'a'] == -1) {
                    pre[c-'a'] = 0;
                    cnt++;
                }
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            
            // below would break ["z","z"]
            // Special case: w2 contains w1 and longer, continue
            //if (w2.startsWith(w1)) continue;
            
            // Find the first character that differs
            int len1 = w1.length();
            int len2 = w2.length();
            
            for (int j = 0; j < len1; j++) {
                // Reach w2's end and no difference
                if (j >= len2) return "";
                
                if (w1.charAt(j) != w2.charAt(j)) {
                    char from = w1.charAt(j);
                    char to = w2.charAt(j);
                    
                    if (!g.containsKey(from)) {
                        g.put(from, new HashSet<Character>());
                    }

                    if (!g.get(from).contains(to)) {
                        g.get(from).add(to);
                        pre[to-'a']++;
                    }
                    
                    break; // later characters do not matter anymore
                }
            }
        }
        
        // Topological sort the graph
        Queue<Character> q = new LinkedList<Character>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 26; i++) {
            if (pre[i] == 0) {
                
                char c = (char) (i+'a');
                q.add(c);
                sb.append(c);
            }
        }
        
        while (!q.isEmpty()) {
            char c = q.poll();
            if (g.containsKey(c)) {
                for (char to : g.get(c)) {
                    pre[to-'a']--;
                    if (pre[to-'a'] == 0) {
                        sb.append(to);
                        q.add(to);
                    }
                }
            }
        }
        
        return sb.length() == cnt? sb.toString():"";
    }
}
```

#### 2. Connected components/ Grid
DFS 

323.Number of Connected Components in an Undirected Graph

200.Number of Islands: Each time going into a land, dfs and mark all attaching land to water, keep a counter out of dfs.

547.Friend Circles


#### 3. Prims and Kruskal
#### 4. Dijikstra


# Dijkstra on sparse graphs - Competitive Programming Algorithms

# Friend Circles
# Decode String

#### Clone Graph
```java
    public Node cloneGraph(Node node) {
        if (node == null)
            return null;
        
        // Old to new mapping
        HashMap<Node, Node> map = new HashMap<Node, Node>();
        
        // Clone graph using bfs traversing old graph
        Deque<Node> q = new LinkedList<>();
        
        // Create clone only when saving into map
        // Always access clone through the map
        map.put(node, new Node(node.val));
        q.offer(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            
            // Going through neighbors to rebuild edges of cur
            for (Node nei: cur.neighbors) {
                // Clone the neighbor if not exist
                if (!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.val));
                    q.offer(nei);
                }
                
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        
        return map.get(node);
    }
```
