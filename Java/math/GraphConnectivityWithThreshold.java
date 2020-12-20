package math;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/graph-connectivity-with-threshold/discuss/899450/
 * Java-union-find-compress-path-%2B-ranking-do-union-from-small-numbers
 * @author Katherine
 *
 */
public class GraphConnectivityWithThreshold {

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        int[] parents = new int[n+1];
        boolean[] checked = new boolean[n+1]; 
        
        for (int i = 0 ; i <= n; i++) {
            parents[i] = i;
        }
        
        for (int i = threshold+1; i <= n; i++) {
            if (checked[i]) continue;
            for (int j = 2; i * j <= n; j++) {
                checked[i*j] = true;
                
                union(parents, i, j*i);
            }
        }
        
        List<Boolean> arr = new ArrayList<Boolean>();
        
        for (int i = 0 ; i < queries.length; i++) {
            int c1 = queries[i][0];
            int c2 = queries[i][1];
            
            int c1ancestor = find(parents, c1);
            int c2ancestor = find(parents, c2);
            
            if (c1ancestor == c2ancestor)
                arr.add(true);
            else
                arr.add(false);
        }
        
        return arr;
    }
    
    public int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        
        return find(parent, parent[i]);
    }
    
    public void union(int[] parent, int i, int j) {
        int ancestorI = find(parent, i);
        int ancestorJ = find(parent, j);
        
        if (ancestorI != ancestorJ) 
        {
            parent[ancestorI] = ancestorJ;
        }
    }

    public static void main(String[] args) {
        int[][] queries = {{8,3},{14,9},{12,6},{4,12},{16,12},{16,9}};
        GraphConnectivityWithThreshold g = new GraphConnectivityWithThreshold();
        int  n = 16, threshold = 3;
        System.out.println(g.areConnected(n, threshold, queries));
    }
}
