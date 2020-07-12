package dynamicprogramming;

/**
 * HighlightQuestion
 * 583. Delete Operation for Two Strings
 * Credit:
 * https://leetcode.com/problems/delete-operation-for-two-strings/discuss/103214/Java-DP-Solution-(Longest-Common-Subsequence)
 * @author Katherine
 *
 */
public class DeleteOperationforTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        // longest common sequence in dp
        int[][] dp = new int[m+1][n+1];

        for (int i = 1 ; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                dp[i][j] = w1[i-1] == w2[j-1]?dp[i-1][j-1]+1:Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return m+n-2*dp[m][n];
    }
    
    public static void main(String[] args) {
        DeleteOperationforTwoStrings dof = new DeleteOperationforTwoStrings();
        System.out.println(dof.minDistance("sea", "eat"));
    }
}
