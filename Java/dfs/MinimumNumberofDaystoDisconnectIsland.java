package dfs;

/**
 * 1568. Minimum Number of Days to Disconnect Island
 * @author Katherine
 *
 */
public class MinimumNumberofDaystoDisconnectIsland {
    public int minDays(int[][] grid) {
        // check if 0, already >= 2 islands or == 0 islands
        int islandsCount = countNumOfIslands(grid);
        if (islandsCount == 0 || islandsCount >= 2) return 0;
        
        int rc = grid.length;
        int cc = grid[0].length;
        for (int i = 0 ; i < rc; i++) {
            for (int j = 0 ; j < cc; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (countNumOfIslands(grid) >= 2) 
                        return 1;
                    
                    grid[i][j] = 1;
                }
            }
        }
        
        return 2;
    }
    
    // Given a grid, count the total number of islands in the grid
    public int countNumOfIslands(int[][] grid) {
        int rc = grid.length;
        int cc = grid[0].length;
        
        boolean[][] seen = new boolean[rc][cc];
        int islands = 0;

        for (int i = 0 ; i < rc; i++) {
            for (int j = 0; j < cc; j++) {
                if (!seen[i][j] && grid[i][j] == 1) {
                    dfs(seen, grid, i, j);
                    islands++;
                }
            }
        }
        
        return islands;
    }
    
    public void dfs(boolean[][] seen, int[][] grid, int r, int c) {
        if (r >= grid.length || r < 0 || c >= grid[0].length || c < 0 || seen[r][c] || grid[r][c] == 0) return;
        
        seen[r][c] = true;
        
        dfs(seen, grid, r+1, c);
        dfs(seen, grid, r-1, c);
        dfs(seen, grid, r, c+1);
        dfs(seen, grid, r, c-1);
    }
    
    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        MinimumNumberofDaystoDisconnectIsland m = new MinimumNumberofDaystoDisconnectIsland();
        System.out.println(m.minDays(grid));
    }
}
