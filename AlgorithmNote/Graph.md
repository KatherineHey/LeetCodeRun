
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
- [x] 2115.Find All Possible Recipes from Given Supplies
- [x] 207.Course Schedule
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


- [ ] 269.Alien Dictionary

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

#### 2. [BFS] Shortest path | HashMap + BFS Traversal
- [x] 1293. Shortest Path in a Grid with Obstacles Elimination 
```java
public int shortestPath(int[][] grid, int k) {
    int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1, 0}};
    int m = grid.length;
    int n = grid[0].length;

    if (m == 1 && n == 1) return 0;

    // Number of obstacles need to be removed reaching i j
    int[][] obstacles = new int[m][n];
    for (int[] row: obstacles) {
        Arrays.fill(row, Integer.MAX_VALUE);
    }

    Deque<int[]> queue = new LinkedList<int[]>();

    queue.add(new int[]{0, 0});
    obstacles[0][0] = 0;
    int steps = 1;

    // BFS
    while (!queue.isEmpty()) {
        int size = queue.size();
        while (size > 0) {
            int[] node = queue.poll();
            size--;
            int i = node[0]; 
            int j = node[1];
            int curNodeObstacles = obstacles[i][j];

            for (int[] dir: dirs) {
                int newi = i + dir[0];
                int newj = j + dir[1];
                if (newi >= 0 && newi < m && newj >= 0 && newj < n) {
                    int newObstacle = grid[newi][newj] == 1? 1: 0;

                    // There's another route reaching here with fewer obstacles
                    if (obstacles[newi][newj] <= curNodeObstacles + newObstacle) {
                        continue;
                    }

                    if (curNodeObstacles + newObstacle <= k) {
                        if (newi == m-1 && newj == n-1)
                            return steps;

                        obstacles[newi][newj] = curNodeObstacles + newObstacle;
                        queue.add(new int[]{newi, newj});
                    }
                }
            }
        }

        steps++;
    }

    return -1;
}
```

- [x] 133 Clone Graph - https://leetcode.com/problems/clone-graph/ 
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
                // Since it's a graph (could have cycle), you could have cloned the node already
                // Clone the neighbor node only if not already exists
                if (!map.containsKey(nei)) {
                    map.put(nei, new Node(nei.val));
                    q.offer(nei);
                }
                
                // Always add neighbor to cur's neighbors list
                // Since this is the first time reaching neighbor node from cur
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        
        return map.get(node);
    }
```


#### 3 [DFS] memo | connected components/ grid | DP mask

- [x] 417.Pacific Atlantic Water Flow - https://leetcode.com/problems/pacific-atlantic-water-flow/
- [x] 200.Number of Islands: Each time going into a land, dfs and mark all attaching land to water, keep a counter out of dfs.
- [x] 216.Graph valid tree: # of edge = # of nodes-1 && use visited hashSet to go through all nodes

```java
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) return false;
        
        // DFS to visit all nodes, check if there's cycle or separated island
        HashSet<Integer> visited = new HashSet<Integer>();
        
        List<Integer>[] graph = new ArrayList[n];
        for (int i= 0 ; i < n; i++) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        dfs(graph, 0, visited);
        
        return visited.size() == n;
    }
    
    public void dfs(List<Integer>[] graph, int node, HashSet<Integer> visited) {
        if (visited.contains(node)) {
            return;
        }
        
        visited.add(node);
        for (int neighbor : graph[node]) {
            dfs(graph, neighbor, visited);
        }
    }
}
```

#### 4 Union Find | Connected components


- [x] 547.Friend Circles
- [x] 323.Number of Connected Components in an Undirected Graph

```java
public class NumberofConnectedComponentsinanUndirectedGraph {
    // if index == value, it's a parent/ representative of the component
    int[] Parent;
    int Cnt = 0;
    public int countComponents(int n, int[][] edges) {
        Parent = new int[n+1];
        Cnt = n;
        
        // Initialize every node to be it's own component
        for (int i = 0; i < n; i++) {
            Parent[i] = i;
        }
        
        for (int[] edge : edges) {
            if (union(edge[0], edge[1]))
                Cnt--;
        }
        
        return Cnt;
    }
    
    public int find(int n) {
        if (n == Parent[n])
            return n;
        
        Parent[n] = find(Parent[n]);
        return Parent[n];
    }
    
    // return true, if initially in different components
    // false if already the same components
    public boolean union(int i, int j) {
        int parentI = find(i);
        int parentJ = find(j);
        if (parentI == parentJ)
            return false;
        
        Parent[parentI] = parentJ;
        return true;
    }
}

```

#### 5. Kruskal

```java

/**
 * Kruskal's algorithm finds a minimum spanning forest of an undirected edge-weighted graph.
 * If the graph is connected, it finds a minimum spanning tree.
 * It is a greedy algorithm in graph theory as in each step it adds the next lowest-weight edge that will not 
 * form a cycle to the minimum spanning forest
 * @author Katherine
 *
 */
public class MST_Kruskal {
    int[] parents;
    int islands;
    
    public int minCostConnectPoints(int[][] points) {
        // 1. get and save all the weight of all the edges in points
        int v = points.length;
        int[][] edges = new int[v][v];
        islands = v;
        parents = new int[v];
        int cost = 0;
        
        // initialize uf
        for (int i = 0 ; i < v; i++) parents[i] = i;
        
        // acsending order of edge weight
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->edges[a[0]][a[1]] - edges[b[0]][b[1]]);
        
        for (int i = 0 ; i < v; i++) {
            for (int j = i+1; j < v; j++) {
                //edge of the point i, j
                int edge = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[i][j] = edge;edges[j][i] = edge;
                pq.add(new int[] {i, j});
            }
        }
        
        // Kruskal algorithm
        
        while(!pq.isEmpty() && islands > 1) {
            int[] edge = pq.poll();
            
            int a = edge[0];
            int b = edge[1];
            if (union(a, b)) {
                islands--;
                cost+= edges[a][b];
            }
        }
        
        return cost;
    }
    
    /*
     *   Returns true when two nodes 'a' and 'b' are initially in different
     *   components. Otherwise returns false if they are in the same set.
     */
    public boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa == pb) return false;
        
        parents[pa] = pb;

        return true;
    }
    
    public int find(int a) {
        while (parents[a] != a) {
            a = parents[a];
        }
        
        return a;
    }
    
    public static void main(String[] args) {
        int[][] points =  {{-14,-14},{-18,5},{18,-10},{18,18},{10,-2}};
        
        MST_Kruskal mk = new MST_Kruskal();
        System.out.println(mk.minCostConnectPoints(points));
    }
}

```

#### 6.1 Dijkstra

- [x] 1786. Number of Restricted Paths From First to Last Node
```java
class Solution {
    public int countRestrictedPaths(int n, int[][] edges) {
        if (n == 1) return 0;
        List<int[]>[] graph = new List[n+1];
        
        // u->v: u, [v, weight]
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] e: edges) {
            graph[e[0]].add(new int[] {e[1], e[2]});
            graph[e[1]].add(new int[] {e[0], e[2]});
        }
        
        int[] dist = dijstra(graph, n);
        
        return dfs(1, n, graph, dist, new Integer[n+1]);
    }
    
    /**
    Dijstra's algorithm to find the shortest path from n to all other nodes
    */
    public int[] dijstra(List<int[]>[] graph, int n) {
        // Distances from n to all other nodes
        // Extra 1 in length for easy access
        int[] distances = new int[n+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        
        // Min heap for distances
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{n, 0});
        distances[n] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0];
            if (cur[1] != distances[u]) continue; // dijstra looks for the shortest edge in unvisited vertex
            
            for (int[] neighborPair: graph[u]) {
                int v = neighborPair[0];
                int distanceUV = neighborPair[1];
                
                if (distances[u] + distanceUV < distances[v]) {
                    distances[v] = distances[u] + distanceUV;
                    
                    pq.offer(new int[]{v, distances[v]});
                }
            }
        }

        return distances;
    }
    
    // Return total count of restricted path from src to n
    public int dfs(int src, int n, List<int[]>[] graph, int[] distances, Integer[] memo) {
        if (memo[src] != null) return memo[src];
        if (src == n) return 1;
        
        int cnt = 0;
        
        // Visit all src's neighbors
        for (int[] neighborPair: graph[src]) {
            int neighbor = neighborPair[0];
            if (distances[src] > distances[neighbor]) {
                cnt = (cnt + dfs(neighbor, n, graph, distances, memo)) % 1000000007; 
            }
        }
        
        memo[src] = cnt;
        return cnt;
    }
}
```

#### 6.2 Dijkstra Variant | Dijkstra on mask

- [x] 1066. Campus Bikes II

```java

/**
 * On a campus represented as a 2D grid, there are n workers and m bikes, with n <= m. Each worker and bike is a 2D coordinate on this grid.
We assign one unique bike to each worker so that the sum of the Manhattan distances between each worker and their assigned bike is minimized.
Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 */
public class CampusBikeii {
    // Approach 1: Backtracking
    int M;
    int N;
    int SumDistances = Integer.MAX_VALUE; 
    public int assignBikes(int[][] workers, int[][] bikes) {
        M = workers.length;
        N = bikes.length;
        boolean[] bikesUsed = new boolean[N];
        
        dfs(workers, bikes, bikesUsed, 0, 0);
        return SumDistances;
    }
    
    // Manhattan distance
    public int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0]-bike[0]) + Math.abs(worker[1] - bike[1]);
    }
    
    // Greedy backtracking through all combinations
    public void dfs(int[][] workers, int[][] bikes, boolean[] bikesUsed, int i, int distance) {
        // Prune
        if (distance > SumDistances)
            return;
        
        if (i == workers.length) {
            SumDistances = Math.min(SumDistances, distance);
            return;
        }
        
        for (int j = 0; j < bikes.length; j++) {
            if (bikesUsed[j]) continue;
            
            bikesUsed[j] = true;
            dfs(workers, bikes, bikesUsed, i+1, distance + getDistance(workers[i], bikes[j]));
            bikesUsed[j] = false;
        }
    }
    
    // Approach 2: DP + mask
    /*
     * Complexity Analysis
    Here N is the number of workers, and M is the number of bikes.
    Time complexity: O(M* 2^M)
    Time complexity is equal to the number of unique states in the memo table multiplied by the average time 
    that the minimumDistanceSum function takes. 
    The number of states is equal to unique values of mask that is 2^M and 
    the minimumDistanceSum function takes O(M) time. So the time complexity is O(M* 2^M)

    Space complexity: O(2^M)
    We have used an array memo to store the results corresponding to mask. 
    Also, there will be some stack space used during recursion. 
    The recursion space will be equal to the maximum number of the active function calls in the stack that will be 
    equal to the number of workers i.e., N. 
    Hence the space complexity will be equal to O(2^M + N).
     */
    public int assignBikesDpMask(int[][] workers, int[][] bikes) {
        Arrays.fill(memo, -1);
        return minimumDistanceSum(workers, bikes, 0, 0);
    }
    
    // Maximum value of mask will be 2^(Number of bikes)
    // and number of bikes can be 10 at max
    int memo [] = new int[1024];
    
    private int minimumDistanceSum(int[][] workers, int[][] bikes, int workerIndex, int mask) {
        if (workerIndex >= workers.length) {
            return 0;
        }
        
        // If result is already calculated, return it no recursion needed
        if (memo[mask] != -1)
            return memo[mask];
        
        int smallestDistanceSum = Integer.MAX_VALUE;
        for (int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++) {
            // Check if the bike at bikeIndex is available
            if ((mask & (1 << bikeIndex)) == 0) {
                // Adding the current distance and repeat the process for next worker
                // also changing the bit at index bikeIndex to 1 to show the bike there is assigned
                smallestDistanceSum = Math.min(smallestDistanceSum, 
                   getDistance(workers[workerIndex], bikes[bikeIndex]) + 
                   minimumDistanceSum(workers, bikes, workerIndex + 1, mask | (1 << bikeIndex)));
            }
        }
        
        // Memoizing the result corresponding to mask
        return memo[mask] = smallestDistanceSum;
    }
    
    // Approach 3: similar to Dijstra
    public int assignBikesPQ(int[][] workers, int[][] bikes) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> Integer.compare(a[0], b[0]));
        HashSet<Integer> visitedMasks = new HashSet<Integer>();
        
        pq.add(new int[] {0, 0});
        
        while (!pq.isEmpty()) {
            int[] pairs = pq.poll();
            int mask = pairs[1];
            if (visitedMasks.contains(mask)) continue;
            
            visitedMasks.add(mask);
            int cost = pairs[0];
            
            int workerIdx = getNumberOfOnes(mask);
            
            if (workerIdx == workers.length) return cost;
            
            // Check all bikes from current state
            for (int i = 0; i < bikes.length; i++) {
                if ((mask & (1 << i)) == 0) {
                    pq.add(new int[] {cost+getDistance(workers[workerIdx], bikes[i]), mask | (1<<i)});
                }
            }
        }
        
        return -1;
    }
    
    public int getNumberOfOnes(int mask) {
        int cnt = 0;
        while (mask != 0) {
            mask &= (mask-1);
            cnt++;
        }
        
        return cnt;
    }
}

```

#### 6.3 Dijkstra on sparse graphs - Competitive Programming Algorithms
