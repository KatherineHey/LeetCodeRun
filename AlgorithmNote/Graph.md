
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

# Topological Sorting
269. Alien Dictionary

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

# Prims and Kruskal
# Dijikstra
# Dijkstra on sparse graphs - Competitive Programming Algorithms
# Number of Islands

Each time going into a land, dfs to mark all attaching land to water, keep a counter out of dfs.

# Friend Circles
# Decode String

# Clone Graph
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
