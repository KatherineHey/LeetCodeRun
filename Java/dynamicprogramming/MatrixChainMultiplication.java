package dynamicprogramming;

/**
 * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * Algorithm 15.2
 * @author Katherine
 *
 */

public class MatrixChainMultiplication {
    //  Matrix Ai has dimension p[i-1] x p[i] for i = 1..n 
    static int MatrixChainOrder(int p[], int n) {

        /* m[i,j] = Minimum number of scalar multiplications needed 
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where 
        dimension of A[i] is p[i-1] x p[i] */
        int[][] m = new int[n][n];
        
        // L is chain length. 
        for (int L=2; L<n; L++) {
            for (int i = 1 ; i <= n - L- 1; i++) {
                int j = i + L - 1; // end of the chain
                
                for (int k = i; k <= j-1; k++) {
                    int ijmin = m[i][k] + m[k+1][j] + p[i-1] * p[k] * p[j];
                    if (ijmin < m[i][j])
                        m[i][j] = ijmin;
                }
            }
        }
        
        return m[1][n-1];
    }
}
