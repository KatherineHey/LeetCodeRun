package UnionFind;

import java.util.HashMap;
import java.util.Map;

public class MinHum {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        int[] parent = new int[n];
        
        // initialize the parent arr to -1
        for (int i = 0 ; i < n; i++) {
            parent[i] = i;
        }
        
        for (int[] pair : allowedSwaps) {
            union(parent, pair[0], pair[1]);
        }
        
        // <parent of the index, <number, count>>
        HashMap<Integer, HashMap<Integer, Integer>> pnc = new HashMap<Integer, HashMap<Integer, Integer>>();
        for (int i = 0; i < n; i++) {
            int num = source[i];
            int root = find(parent, i);

            pnc.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> store = pnc.get(root);
            store.put(num, store.getOrDefault(num, 0) + 1);
        }
        
     // greedy fit the target, if not, diff++
        int res = 0;
        for (int i = 0; i < n; i++) {
            int num = target[i];
            int root = find(parent, i);

            Map<Integer, Integer> store = pnc.get(root);

            if (store.getOrDefault(num, 0) == 0) res++;
            else store.put(num, store.get(num) - 1);
        }

        return res;
    }
    
    public int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }

        return parent[i] = find(parent, parent[i]);
    }
   
    public void union(int[] parent, int i, int j) {
        int i_ancestor = find(parent, i);
        int j_ancestor = find(parent, j);
        
        if (i_ancestor != j_ancestor) {
            //totalUnionComponents--;
            parent[i_ancestor] = j_ancestor;
        }
    }
}
