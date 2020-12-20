package UnionFind;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/discuss/831506/Textbook-Union-Find-Data-Structure-Code-with-Explanation-and-comments
 * The idea here is to think that initially the graph is empty 
 * and now we want to add the edges into the graph such that graph is connected.
 *
 * @author Katherine
 *
 */
public class RemoveMaxNumberofEdgestoKeepGraphFullyTraversable {
    public class UF {
        int[] parent;
        int distinctComponents;
        
        public UF(int n) {
            parent = new int[n+1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
            
            distinctComponents = n;
        }
        
        /*
         *   Returns true when two nodes 'a' and 'b' are initially in different
         *   components. Otherwise returns false.
         */
        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            
            parent[rootA] = rootB;
            
            distinctComponents--;
            return true;
        }
        
        public int find(int a) {
            while (parent[a] != a) {
                parent[a] = parent[parent[a]];
                a = parent[a];
            }
            
            return parent[a];
        }
        
        public boolean united() {
            return distinctComponents == 1;
        }
    }
    
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> b[0] - a[0]);
        
        UF alice = new UF(n);
        UF bob = new UF(n);
        
        int edgeAdded = 0;
        
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int type = edge[0];
            int a = edge[1];
            int b = edge[2];
            
            switch(type) {
                case 3:
                    if (alice.union(a,b) | bob.union(a, b))
                        edgeAdded++;
                    break;
                case 2:
                    if (bob.union(a, b))
                        edgeAdded++;
                    break;
                case 1:
                    if (alice.union(a, b))
                        edgeAdded++;
                    break;
            }
            
        }
        
        return (alice.united() && bob.united()) ? edges.length - edgeAdded : -1;
    }
    
    public static void main(String[] args) {
        RemoveMaxNumberofEdgestoKeepGraphFullyTraversable rm = new RemoveMaxNumberofEdgestoKeepGraphFullyTraversable();
        
        int n = 4;
        int[][] edges = {{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}};
        System.out.println(rm.maxNumEdgesToRemove(n, edges));
        
    }
}
