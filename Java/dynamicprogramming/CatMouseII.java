package dynamicprogramming;

/**
 * 1728. Cat and Mouse II
 * @author Katherine
 *
 */
public class CatMouseII {
    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
    char[][] grids;
    int[][][][][] dp;
    int m;
    int n;
    int turns = 0;
    int mouseMaxJump;
    int catMaxJump;
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        m = grid.length;
        n = grid[0].length();
        grids = new char[m][n];
        mouseMaxJump = mouseJump;
        catMaxJump = catJump;

        int cx = 0, cy = 0, mx = 0, my = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                grids[i][j] = c;
                
                if (c != '#') turns++;
                if (c == 'C') {
                    cx = i;
                    cy = j;
                } else if (c == 'M') {
                    mx = i;
                    my = j;
                }
            }
        }
        
        // turns, mx, my, cx, cy
        //dp[turn][mx][my][cx][cy] == 1 means mouse wins, return true
        //dp[turn][mx][my][cx][cy] == 2 means cat wins
        dp = new int[turns][m][n][m][n];
        return dfs(0, mx, my, cx, cy);
    }
    
    public boolean dfs(int turn, int mx, int my, int cx, int cy) {
        if (turn > turns * 2) return false;
        
        if (dp[turn][mx][my][cx][cy] != 0)
            return dp[turn][mx][my][cx][cy] == 1;
        
        
        // mouse
        if (turn % 2 == 0) {
            for (int i = 0; i < directions.length; i++) {
                for (int step = 0; step <= mouseMaxJump; step++) {
                    int newmx = mx + directions[i][0] * step;
                    int newmy = my + directions[i][1] * step;
                    
                    if (grids[newmx][newmy] == '#' || newmx >= m || newmy >= n || newmx < 0 || newmy < 0)
                        break; //invalid, cannot jump further
                    else {
                        if (grids[newmx][newmy] == 'F') {
                            dp[turn][newmx][newmy][cx][cy] = 1;
                            return true;
                        }
                        if (dfs(turn+1, newmx, newmy, cx, cy)) {
                            dp[turn][newmx][newmy][cx][cy] = 1;
                            return true;
                        }
                    }
                }
            }
            
            // should have already returned if any option leads to mouse win
            dp[turn][mx][my][cx][cy] = 2;
            return false;
        } else { //cat
            for (int i = 0; i < directions.length; i++) {
                for (int step = 0; step <= catMaxJump; step++) {
                    int newcx = cx + directions[i][0] * step;
                    int newcy = cy + directions[i][1] * step;
                    
                    if (grids[newcx][newcy] == '#' || newcx >= m || newcy >= n || newcx < 0 || newcy < 0)
                        break; //invalid, cannot jump further
                    else {
                        if (grids[newcx][newcy] == 'F'|| (newcx == mx && newcy == my) || !dfs(turn+1, mx, my, newcx, newcy)) {
                            dp[turn][mx][my][newcx][newcy] = 2;
                            return false;
                        }
                    }
                }
            }
            
            dp[turn][mx][my][cx][cy] = 1;
            return true;
        }
    }
    
    public static void main(String[] args) {
        double e23 = 10_000_000;
        double big2 = 1e23;
    }
}
