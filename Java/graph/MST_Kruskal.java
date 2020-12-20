package graph;

import java.util.PriorityQueue;

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
            //parents[a] = parents[parents[a]];
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
