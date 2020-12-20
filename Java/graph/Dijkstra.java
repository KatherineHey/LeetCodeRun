package graph;

import java.util.PriorityQueue;

public class Dijkstra {
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    int ROW = 5;
    int COL = 5;
    
    class Cell implements Comparable<Cell>{
        int x;
        int y;
        int cost;
        
        Cell(int X, int Y, int Cost) {
            x = X;
            y = Y;
            cost = Cost;
        }

        @Override
        public int compareTo(Cell o) {
            return cost - o.cost;
        }
    }
    
    // Utility method to check whether current
    // cell is inside grid or not
    public boolean isInsideGrid(int i, int j) {
        return (i >= 0 && i < ROW && j >= 0 && j < COL);
    }
    
    public int shortestPath(int[][] grid, int row, int col) {
        int[][] dist = new int[row][col];
        
        for (int i = 0 ; i < row; i++) {
            for (int j = 0 ; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
        
        // add starting point to the priority queue
        dist[0][0] = grid[0][0]; 
        pq.add(new Cell(0,0,dist[0][0]));
        
        while (!pq.isEmpty()) {
            Cell cur = pq.poll();
            for (int i = 0 ; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                
                if (isInsideGrid(x, y)) {
                    if (dist[x][y] > dist[cur.x][cur.y] + grid[x][y]) {
                        // Remove the cell from the queue if it's been visited already
                        if (dist[x][y] != Integer.MAX_VALUE) {
                            Cell c = new Cell(x, y, dist[x][y]);
                            pq.remove(c);
                        }
                        
                        dist[x][y] = dist[cur.x][cur.y] + grid[x][y];
                        pq.add(new Cell(x, y, dist[x][y]));
                        
                    }
                }
            }
        }
        
        return dist[row-1][col-1];
    }
    
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        int[][] grid = { { 31, 100, 65, 12, 18 }, 
                { 10, 13, 47, 157, 6 }, 
                { 100, 113, 174, 11, 33 }, 
                { 88, 124, 41, 20, 140 }, 
                { 99, 32, 111, 41, 20 } }; 
                  
        System.out.println(d.shortestPath(grid, d.ROW, d.COL));
    }
}
