package dynamicprogramming;

public class stonegameiii {
    public String stoneGameIII(int[] stones) {
        int len = stones.length;

        // dp[i] means, if we ignore before A[i],
        // what's the highest score that Alex can win over the Bob？
        int[] dp = new int[len+1];

        for (int l = len-1; l >= 0; l--) {
            dp[l] = Integer.MIN_VALUE;
            
            for (int k = 0, take = 0; k<3 && k+l < len; k++) {
                take += stones[l+k];
                dp[l] = Math.max(dp[l], take - dp[l+k+1]);
            }
        }

        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
    
    public static void main(String[] args) {
        stonegameiii sg = new stonegameiii();
        int[] stones = {1,2,3,7};
        sg.stoneGameIII(stones);
    }
}
