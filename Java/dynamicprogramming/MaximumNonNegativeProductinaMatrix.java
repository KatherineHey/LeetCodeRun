package dynamicprogramming;

public class MaximumNonNegativeProductinaMatrix {
    class Pair {
        long small;
        long big;
        
        Pair(long smallest, long biggest) {
            small = smallest;
            big = biggest;
        }
    }
    
    public int maxProductPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        
        Pair[][] prod = new Pair[m][n];
        
        for (int i = 0 ; i < m ; i++) {
            if (i == 0)
                prod[i][0] = new Pair(grid[i][0], grid[i][0]);
            else {
                long product = prod[i-1][0].small * grid[i][0];
                prod[i][0] = new Pair(product, product);
            }
        }
        
        for (int j = 0 ; j < n; j++) {
            if (j == 0)
                prod[0][j] = new Pair(grid[0][j],grid[0][j]);
            else {
                long product = prod[0][j-1].small * grid[0][j];
                prod[0][j] = new Pair(product, product);
            }
        }
        
        for (int i = 1 ; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long bigProd = Math.max(
                        Math.max(prod[i-1][j].small * grid[i][j], prod[i-1][j].big* grid[i][j]),
                        Math.max(prod[i][j-1].small * grid[i][j], prod[i][j-1].big* grid[i][j]));
                long smallProd = Math.min(
                        Math.min(prod[i-1][j].small * grid[i][j], prod[i-1][j].big* grid[i][j]),
                        Math.min(prod[i][j-1].small * grid[i][j], prod[i][j-1].big* grid[i][j]));
                
                prod[i][j] = new Pair(smallProd, bigProd);
            }
        }
        
        return (int) (prod[m-1][n-1].big >= 0? prod[m-1][n-1].big % (1000000007L) : -1);
    }
    
    public static void main(String[] args) {
//        int[][] grid = {{-1,-2,-3},
//                {-2,-3,-3},
//                {-3,-3,-2}};
//        int[][] grid = {{1, 3},
//                        {0,-4}};
//        int[][] grid = {{1, 4,4,0},
//        {-2, 0,0,1},
//        { 1,-1,1,1}};
        int[][] grid = 
                {{-1,-4,2},
                 {4,3,-1},
                 {2,-4,4},
                 {1,-1,-4}};
        
        MaximumNonNegativeProductinaMatrix m = new MaximumNonNegativeProductinaMatrix();
        System.out.println(m.maxProductPath(grid));
        
    }
}
