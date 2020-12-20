package dynamicprogramming;

/**
 * 486. Predict the Winner
 * @author Katherine
 *
 */
public class PredictTheWinner {
    public boolean PredictTheWinner(int[] nums) {
        int N = nums.length;

        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) { dp[i][i] = nums[i]; }
        
        for (int d = 1; d < N; d++) {
            for (int i = 0; i + d < N; i++) {
                dp[i][i+d] = Math.max(nums[i]-dp[i+1][i+d], nums[i+d]-dp[i][i+d-1]);
            }
        }
        
        return dp[0][N-1]>=0;
    }
    
    public static void main(String[] args) {
        PredictTheWinner pw = new PredictTheWinner();
        int[] nums = {0};
        System.out.println(pw.PredictTheWinner(nums));
    }
}
