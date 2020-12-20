package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/115541/JavaPython-Priority-Queue-Solution
 * @author Katherine
 *
 */
public class CheapestFlightsWithinKStops {
    
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // translate flights into prices hashmap
        HashMap<Integer, HashMap<Integer, Integer>> prices = new HashMap<Integer, HashMap<Integer, Integer>>();
        
        for (int[] f: flights) {
            if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<Integer, Integer>());
            
            prices.get(f[0]).put(f[1], f[2]);
        }
        
        // 0: current total price, 1: current source city, 2: max stops to destinations allowed
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[] {0, src, K+1});
        
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int currentTotalPrice = top[0];
            int currentSource = top[1];
            int currentMaxStops = top[2];
            
            if (currentSource == dst) return currentTotalPrice;
            
            if (currentMaxStops > 0) {
                // relax all neighbors
                Map<Integer, Integer> adj = prices.getOrDefault(currentSource, new HashMap<>());
                for (int neighbor: adj.keySet()) {
                    pq.add(new int[] {adj.get(neighbor) + currentTotalPrice, neighbor, currentMaxStops-1});
                }
            }
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        CheapestFlightsWithinKStops c = new CheapestFlightsWithinKStops();
//        int n = 5;
//        int[][] flights = {{4,1,1},{1,2,3},{0,3,2},{0,4,10},{3,1,1},{1,4,3}};
//        int src = 2;
//        int dst = 1;
//        int K = 1;
//        int n = 3;
//        int[][] flights = {{0,1,100},{1,2,100},{0,2,500}};
//        int src = 0; 
//        int dst = 2;
//        int K = 1            ;
        
        int n = 4;
        int[][] flights = {{0,1,1},{0,2,5},{1,2,1},{2,3,1}};
        int src = 0;
        int dst = 3;
        int K = 1;
        
        System.out.println(c.findCheapestPrice(n, flights, src, dst, K));
    }
}
