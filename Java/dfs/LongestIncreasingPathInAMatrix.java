package dfs;

/**
 * 329. Longest Increasing Path in a Matrix
 * @author Katherine
 *
 */
public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println("------------i: "+i+" j:"+j);
                boolean[][] visited = new boolean[m][n];
                result = Math.max(result, dfs(i, j, m, n, visited, matrix, Integer.MIN_VALUE, 1));
            }
        }
        
        return result;
    }
    
    public int dfs(int i, int j, int m, int n, boolean[][] visited, int[][] matrix, int prev, int len) {
        System.out.println("i: "+i+" j:"+j+"prev: "+ prev+" len:"+len);
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || matrix[i][j] <= prev) return len-1;
        
        System.out.println("--i: "+i+" j:"+j+" cur:" + matrix[i][j]+" prev: "+ prev+" len:"+len);
        
        visited[i][j] = true;
        int len1 = dfs(i+1, j, m,n, visited, matrix, matrix[i][j], len+1);
        int len2 = dfs(i-1, j, m,n, visited, matrix, matrix[i][j], len+1);
        int len3 = dfs(i, j+1, m,n, visited, matrix, matrix[i][j], len+1);
        int len4 = dfs(i, j-1, m,n, visited, matrix, matrix[i][j], len+1);
        visited[i][j] = false;
        
        return Math.max(Math.max(len1, len2), Math.max(len3, len4));
    }
    
    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix li = new LongestIncreasingPathInAMatrix();
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(li.longestIncreasingPath(matrix));
    }
}
