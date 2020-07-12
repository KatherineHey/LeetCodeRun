package dynamicprogramming;

/**
 * 115. Distinct Subsequences
 * https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
 * @author Katherine
 *
 */
public class DistinctSubsequence {
    public int numDistinct(String s, String t) {
        int len1 = t.length();
        int len2 = s.length(); //longer one
        
        int[][] dp = new int[len1+1][len2+1];
        
        for (int i = 1; i < len1+1; i++) {
            dp[i][0] = 0;
        }
        
        for (int j = 0; j < len2+1; j++) {
            dp[0][j] = 1;
        }
        
        for (int i = 1 ; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                dp[i][j] = dp[i][j-1];
                if (t.charAt(i-1) == s.charAt(j-1))
                    dp[i][j] += dp[i-1][j-1]; /*!!!*/
            }
        }
        
        return dp[len1][len2];
    }
    
    public static void main(String[] args) {
        DistinctSubsequence ds = new DistinctSubsequence();
        System.out.println(ds.numDistinct("rabbbit", "rabbit"));
    }
}
