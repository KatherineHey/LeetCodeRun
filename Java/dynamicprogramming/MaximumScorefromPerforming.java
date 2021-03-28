package dynamicprogramming;

public class MaximumScorefromPerforming {
    public int maximumScore(int[] nums, int[] multipliers) {
        int m = multipliers.length;
        int n = nums.length;
        
        int[][] dp = new int[n][2]; //0 - from left | 1 - from right
        int res = 0;
        for (int j = m-1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (j == m-1) {
                    dp[i][0] = nums[i] * multipliers[j];
                    dp[i][1] = nums[i] * multipliers[j];
                }
                else {
                    if (i == 0) {
                        dp[i][0] = 0;
                        dp[i][1] = dp[i+1][1] + nums[i+1] * multipliers[j];
                    } else if (i == n-1) {
                        dp[i][1] = 0;
                        dp[i][0] = dp[i-1][0] + nums[i-1] * multipliers[j];
                    } else {
                        dp[i][1] = dp[i-1][0] + nums[i-1] * multipliers[j];
                        dp[i][0] = dp[i-1][0] + nums[i+1] * multipliers[j];
                    }
                }
                
                if (j == 0) {
                    res = Math.max(Math.max(res, dp[i][0]), dp[i][1]);
                    
                }
            }
            
           
        }
        
        return res;
    }
    
    int[] pre;
    Integer[][][] memo;
    int[] multi;
    int[] numss;
    public int maximumScore2(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        
        memo = new Integer[n][n][m+1];
        multi = multipliers;
        numss = nums;
        
        return dp2(0, n-1, 0);
        
       // return memo[0][n-1][m];
    }
    
    public int dp2(int left, int right, int midx) {
        if (left > right || left >= numss.length || right >= numss.length || left < 0) return 0;
        if (midx >= multi.length) return 0;
        if (memo[left][right][midx] != null) return memo[left][right][midx];
        
        // left
        int a = dp2(left+1, right, midx+1) + numss[left]*multi[midx];
        int b = dp2(left, right-1, midx+1) + numss[right] * multi[midx];
        return memo[left][right][midx] = Math.max(a, b);
        
    }
    
    public static void main(String[] args) {
        int[] nums = {-5,-3,-3,-2,7,1};//, multipliers = []};
        int[] mul = {-10,-5,3,4,6};
        
        MaximumScorefromPerforming ms = new MaximumScorefromPerforming();
        System.out.println(ms.maximumScore(nums, mul));
    }
//    public int stoneGameVII(int[] stones) {
//        int n = stones.length;
//        pre = new int[n + 1];
//        memo = new Integer[n][n][2];
//        for (int i = 0; i < n; i++)
//            pre[i + 1] = pre[i] + stones[i];
//        return dp(0, n - 1, 1);
//    }
//    int getSum(int left, int right) {
//        return pre[right + 1] - pre[left];
//    }
//    int dp(int left, int right, int isAlice) {
//        if (left > right) return 0;
//        if (memo[left][right][isAlice] != null) return memo[left][right][isAlice];
//        if (isAlice == 1) {
//            int a = dp(left + 1, right, 1 - isAlice) + getSum(left + 1, right); // Take leftmost
//            int b = dp(left, right - 1, 1 - isAlice) + getSum(left, right - 1); // Take rightmost
//            return memo[left][right][isAlice] = Math.max(a, b);
//        } else {
//            int a = dp(left + 1, right, 1 - isAlice) - getSum(left + 1, right); // Take leftmost
//            int b = dp(left, right - 1, 1 - isAlice) - getSum(left, right - 1); // Take rightmost
//            return memo[left][right][isAlice] = Math.min(a, b);
//        }
//    }
}
