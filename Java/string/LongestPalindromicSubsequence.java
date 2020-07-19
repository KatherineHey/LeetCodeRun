package string;

/**
 * 516. Longest Palindromic Subsequence
 * @author Katherine
 *
 */
public class LongestPalindromicSubsequence {
    public int longestPalindromeSubseq(String s) {
        int slen = s.length();
        
        int[][] dp = new int[slen][slen];
        
        for (int i = slen - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[0][slen - 1];
    }
    
    public static void main(String[] args) {
        LongestPalindromicSubsequence lps = new LongestPalindromicSubsequence();
        System.out.println(lps.longestPalindromeSubseq("bbbab"));
    }
}
