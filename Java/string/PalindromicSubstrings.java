package string;

public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int len = s.length();
        
        boolean[][] dp = new boolean[len][len];
        
        int palindromicSubstringCount = 0;
        
        // for len = 1 palindrom
        for(int i = 0 ; i < len; i++) {
            dp[i][i] = true;
            palindromicSubstringCount++;
        }
        
        // for len = 2 palindrom
        for (int i = 0; i < len-1; i++) {
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i+1] = true;
                palindromicSubstringCount++;
            }
        }
        
        for (int currentLen = 3; currentLen <= len; currentLen++) {
            for (int i = 0 ; i < len - currentLen + 1; i++) {
                if (s.charAt(i) == s.charAt(i + currentLen -1) && dp[i+1][i+currentLen-2]) {
                    dp[i][i+currentLen-1] = true;
                    palindromicSubstringCount++;
                }
            }
        }
        
        return palindromicSubstringCount;
    }
    
    public static void main(String[] args) {
        PalindromicSubstrings ps = new PalindromicSubstrings();
        System.out.println(ps.countSubstrings("aaa"));
    }
}
