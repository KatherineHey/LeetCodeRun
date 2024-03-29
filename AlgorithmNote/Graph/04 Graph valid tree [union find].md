Graph Valid Tree (Leetcode Premium) - https://leetcode.com/problems/graph-valid-tree/

Awesome Answers from LC

There are so many different approaches and so many different ways to implement each. I find it hard to decide, so here are several :-)

In all of them, I check one of these tree characterizations:

    Has n-1 edges and is acyclic.
    Has n-1 edges and is connected.

Solution 1 ... Union-Find

The test cases are small and harmless, simple union-find suffices (runs in about 50~60 ms).
```
def validTree(self, n, edges):
    parent = range(n)
    def find(x):
        return x if parent[x] == x else find(parent[x])
    def union(xy):
        x, y = map(find, xy)
        parent[x] = y
        return x != y
    return len(edges) == n-1 and all(map(union, edges))
```
A version without using all(...), to be closer to other programming languages:
```
def validTree(self, n, edges):
    parent = range(n)
    def find(x):
        return x if parent[x] == x else find(parent[x])
    for e in edges:
        x, y = map(find, e)
        if x == y:
            return False
        parent[x] = y
    return len(edges) == n - 1
```
A version checking len(edges) != n - 1 first, as parent = range(n) could fail for huge n:
```
def validTree(self, n, edges):
    if len(edges) != n - 1:
        return False
    parent = range(n)
    def find(x):
        return x if parent[x] == x else find(parent[x])
    def union(xy):
        x, y = map(find, xy)
        parent[x] = y
        return x != y
    return all(map(union, edges))
```
Solution 2 ... DFS
```
def validTree(self, n, edges):
    neighbors = {i: [] for i in range(n)}
    for v, w in edges:
        neighbors[v] += w,
        neighbors[w] += v,
    def visit(v):
        map(visit, neighbors.pop(v, []))
    visit(0)
    return len(edges) == n-1 and not neighbors
```
Or check the number of edges first, to be faster and to survive unreasonably huge n:
```
def validTree(self, n, edges):
    if len(edges) != n - 1:
        return False
    neighbors = {i: [] for i in range(n)}
    for v, w in edges:
        neighbors[v] += w,
        neighbors[w] += v,
    def visit(v):
        map(visit, neighbors.pop(v, []))
    visit(0)
    return not neighbors
```
For an iterative version, just replace the three "visit" lines with
```
    stack = [0]
    while stack:
        stack += neighbors.pop(stack.pop(), [])
```
Solution 3 ... BFS

Just like DFS above, but replace the three "visit" lines with
```
    queue = [0]
    for v in queue:
        queue += neighbors.pop(v, [])
```
or, since that is not guaranteed to work, the safer
```
    queue = collections.deque([0])
    while queue:
        queue.extend(neighbors.pop(queue.popleft(), []))
```

Java Union Find
```
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            
            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;
            
            // union
            nums[x] = y;
        }
        
        return edges.length == n - 1;
    }
    
    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}
```
