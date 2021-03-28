package dynamicprogramming;

import java.util.ArrayList;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int len = nums.length; //num of integers
        
        // dp[i][j] stores all the possible numbers between [nums[i], nums[j]]
        ArrayList<Integer>[][] dp=(ArrayList<Integer>[][]) new ArrayList[len][len];
        
        // distance range from 0 to len
        for (int d = 0; d < len; d++) {
            for (int i = 0 ; i + d < len; i++) {
                dp[i][i+d] = new ArrayList<Integer>();
                if (d == 0) {
                    dp[i][i+d].add(nums[i]);
                    dp[i][i+d].add(-nums[i]);
                } else {
                    for (int k = i+1; k <= i+d; k++) {
                        ArrayList<Integer> left = dp[i][k-1];
                        ArrayList<Integer> right = dp[k][i+d];
                        
                        for (int l: left) {
                            for (int r: right) {
                                dp[i][i+d].add(l+r);
                                dp[i][i+d].add(l-r);
                            }
                        }
                    }
                }
            }
        }
        
        int cnt = 0;
        
        for (int i : dp[0][len-1]) {
            if (i == S) cnt++;
        }
        
        return cnt;
    }
    
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        int S = 1;
        TargetSum ts = new TargetSum();
        System.out.println(ts.findTargetSumWays(nums, S));
    }
}
