package UnionFind;

import java.util.HashSet;

/**
 * 323. Number of Connected Components in an Undirected Graph
Problem:

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to find the number of connected components in an undirected graph.

Example 1:
     0          3
     |          |
     1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:
     0           4
     |           |
     1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0, 1] is the same as [1, 0] and thus will not appear together in edges.

 * @author Katherine
 *
 */
public class NumberofConnectedComponentsinanUndirectedGraph {
    int totalComponents = 0;
    
    public int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        totalComponents = n;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int[] edge: edges) {
            union(parent, edge[0], edge[1]);
        }
        
        return totalComponents;
    }
    
    public void union(int[] parent, int a, int b) {
        int pa = find(parent, a);
        int pb = find(parent, b);
        
        if (pa != pb) {
            parent[pa] = pb;
            totalComponents--;
        }
    }
    
    // return parent of a node
    public int find(int[] parent, int i) {
        if (parent[i] == i) {
            return parent[i];
        }
        
        return parent[i] = find(parent, parent[i]);
    }
    
    public static void main(String[] args) {
        int[][] edges1 = {{0, 1}, {1, 2}, {3, 4}};
        NumberofConnectedComponentsinanUndirectedGraph nn = new NumberofConnectedComponentsinanUndirectedGraph();
        System.out.println(nn.countComponents(5, edges1) == 2);
        
        int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(nn.countComponents(5, edges2) == 1);
    }
}
