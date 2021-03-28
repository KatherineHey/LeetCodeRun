package backtracking;

public class TilingRectanglewiththeFewestSquares {
    int ans = Integer.MAX_VALUE;
    
    public int tilingRectangle(int n, int m) {
        boolean[][] occupied = new boolean[n][m];
        dfs(0, 0, occupied, 0);
        
        return ans;
    }
    
    // (x,y) is the starting point, n*m
    private void dfs(int x, int y, boolean[][] occupied, int cnt) {
        int n = occupied.length, m = occupied[0].length;
        
        if (cnt > ans) return;
        if (x >= n) {
            ans = cnt;
            return;
        }
        
        // Successfully cover the area [0, ..., n][0, ..., c] => Move to next row
        if (y >= m) {
            // move to the next spot
            dfs(x+1, 0, occupied, cnt);
            return;
        }
        
        // If (r, c) is already covered => move to next point (r, c+1)
        if (occupied[x][y]) {
            dfs(x, y+1, occupied, cnt);
        }
        
        for (int k = Math.min(n-x, m-y); k >0 && isAvailable(x, y, occupied, k); k--) {
            cover(x, y, occupied, k);
            dfs(x, y+1, occupied, cnt+1);
            uncover(x, y, occupied, k);
        }
    }
    
    // Check if the area [r, ..., r+k][c, ..., c+k] is alreadc covered
    public boolean isAvailable(int r, int c, boolean[][] occupied, int k) {
        for (int i = 0 ; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (occupied[i+r][j+c]) return false;
            }
        }
        
        return true;
    }
    
    public void cover(int r, int c, boolean[][] occupied, int k) {
        for (int i = r ; i < r+k; i++) {
            for (int j = c; j < c+k; j++) {
                occupied[i][j] = true;
            }
        }
    }
    
    public void uncover(int r, int c, boolean[][] occupied, int k) {
        for (int i = r ; i < r+k; i++) {
            for (int j = c; j < c+k; j++) {
                occupied[i][j] = false;
            }
        }
    }
}
