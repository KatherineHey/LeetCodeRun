package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/minimum-cost-path-left-right-bottom-moves-allowed/
 * @author Katherine
 *
 */

public class PathWithMinimumEffort {
    int[] dx = { -1, 0, 1, 0 };
    int[] dy = { 0, 1, 0, -1 };
    int ROW = 5;
    int COL = 5;

    // Custom class for representing
    // row-index, column-index &
    // distance of each cell
    public class Cell {
        int x;
        int y;
        int distance;

        Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    // Custom comparator for inserting cells
    // into Priority Queue
    public class distanceComparator implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            if (a.distance < b.distance) {
                return -1;
            } else if (a.distance > b.distance) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // Utility method to check whether current
    // cell is inside grid or not
    public boolean isInsideGrid(int i, int j) {
        return (i >= 0 && i < ROW && j >= 0 && j < COL);
    }

    // Method to return shortest path from
    // top-corner to bottom-corner in 2D grid
    public int shortestPath(int[][] grid, int row, int col) {
        int[][] dist = new int[row][col];

        // Initializing distance array by INT_MAX
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        // Initialized source distance as
        // initial grid position value
        dist[0][0] = 0;

        PriorityQueue<Cell> pq = new PriorityQueue<Cell>(row * col, new distanceComparator());

        // Insert source cell to priority queue
        pq.add(new Cell(0, 0, dist[0][0]));
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int rows = curr.x + dx[i];
                int cols = curr.y + dy[i];

                if (isInsideGrid(rows, cols)) {
                    if (dist[rows][cols] > Math.max(dist[curr.x][curr.y] == Integer.MAX_VALUE? 0: dist[curr.x][curr.y], Math.abs(grid[curr.x][curr.y] - grid[rows][cols]))) {

                        // If Cell is already been reached once,
                        // remove it from priority queue
                        if (dist[rows][cols] != Integer.MAX_VALUE) {
                            Cell adj = new Cell(rows, cols, dist[rows][cols]);
                            pq.remove(adj);
                        }

                        // Insert cell with updated distance
                        dist[rows][cols] = Math.max(dist[curr.x][curr.y] == Integer.MAX_VALUE? 0: dist[curr.x][curr.y], Math.abs(grid[curr.x][curr.y] - grid[rows][cols]));
                        
                        pq.add(new Cell(rows, cols, dist[rows][cols]));
                    }
                }
            }
        }
        
        return dist[row-1][col-1];
    }

    public int minimumEffortPath(int[][] heights) {
        ROW = heights.length;
        COL = heights[0].length;
        return shortestPath(heights, ROW, COL);
    }
    
    public static void main(String[] args) {
        int[][] heights = {{1,10,6,7,9,10,4,9}};
        PathWithMinimumEffort pw = new PathWithMinimumEffort();
        System.out.println(pw.minimumEffortPath(heights));
    }
}
