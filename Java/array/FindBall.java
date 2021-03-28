package array;

public class FindBall {
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] res = new int[n];
        int[] curPos = new int[n];
        
        for (int i = 0; i < n; i++) 
            curPos[i] = i;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currentPosition = curPos[j];
                if (currentPosition == -1) continue;
                
                if ((currentPosition == 0 && grid[i][currentPosition] == -1)||
                   (currentPosition == n-1 && grid[i][currentPosition] == 1) ||
                   (currentPosition < n-1 && grid[i][currentPosition+1] == 1 && grid[i][currentPosition+1]==-1))
                    curPos[j] = -1;
                else {
                    if (grid[i][currentPosition] == 1)
                        curPos[j] = currentPosition+1;
                    else
                        curPos[j] = currentPosition -1;
                }
            }
        }  
        
        for (int i = 0; i < n; i++) 
            res[i] = curPos[i] < 0? -1: 1;
        
        return res;
    }
}
