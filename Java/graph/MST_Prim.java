package graph;

import java.util.PriorityQueue;

public class MST_Prim {
    public int minCostConnectPoints(int[][] points)  {
        int v = points.length; // number of vertices
        boolean[] visited = new boolean[v];
        
        int visitedVertex = 0;
        int cost = 0;

        // edges keep the weight of all the edges
        int[][] edges = new int[v][v];
        
        // Priority queue save the edge, [0] is the start vertex index, [1] is the destination vertex index
        // Asc by weight of edge
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> edges[a[0]][a[1]]-edges[b[0]][b[1]]);

        // start by picking index 0
        visited[0] = true;
        visitedVertex++;
        
        int i = 0;
        for (int j = i+1; j < v; j++) {
            //edge of the point i, j
            int edge = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
            edges[i][j] = edge;edges[j][i] = edge;
            pq.add(new int[] {i, j});
        }
        
        while (!pq.isEmpty() && visitedVertex < v) {
            int[] potentialedge = pq.poll();
            if (visited[potentialedge[1]]) continue;
            
            i = potentialedge[1];
            visited[i] = true;
            visitedVertex++;
            cost += edges[potentialedge[0]][i];
            
            // Add all the potential edges to the pq
            for (int j = 1; j < v; j++) {
                if (j == i || visited[j]) continue; // ignore vertixes that have already visited
                
                //edge weight of the point i, j
                int edge = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[i][j] = edge;
                pq.add(new int[] {i, j});
            }
        }
        
        return cost;
    }
    
    public static void main(String[] args) {
        int[][] points =  {{-14,-14},{-18,5},{18,-10},{18,18},{10,-2}};
        
        MST_Prim mp = new MST_Prim();
        System.out.println(mp.minCostConnectPoints(points));
    }
}
