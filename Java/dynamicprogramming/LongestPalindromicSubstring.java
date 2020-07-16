package dynamicprogramming;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxlen = 0;
        int start = 0;
        boolean[][] dp = new boolean[len][len];
        
        // for len = 1 palindrom
        for(int i = 0 ; i < len; i++) {
            dp[i][i] = true;
            maxlen = 1;
        }
        
        // for len = 2 palindrom
        for (int i = 0; i < len-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                maxlen = 2;
                start = i;
            }
        }
        
        for (int currentLen = 3; currentLen <= len; currentLen++) {
            for (int i = 0 ; i < len - currentLen + 1; i++) {
                if (s.charAt(i) == s.charAt(i + currentLen -1) && dp[i+1][i+currentLen-2]) {
                    dp[i][i+currentLen-1] = true;
                    maxlen = Math.max(maxlen, currentLen);
                    start = i;
                }
            }
        }
        
        return s.substring(start, start+maxlen);
    }
}
