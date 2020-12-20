package backtracking;

import java.util.Arrays;

public class MinimumOperationstoReduceXtoZero {
    int minOperations = Integer.MAX_VALUE;
    public int minOperations(int[] nums, int x) {
        minOperationsBacktracking(nums, 0, nums.length - 1, x, 1);
        
        return minOperations== Integer.MAX_VALUE? -1: minOperations;
    }
    
    public void minOperationsBacktracking(int[] nums, int left, int right, int x, int operations) {
        if (x < 0 || left > right) return;
        if (x == nums[left] || x == nums[right]) minOperations = Math.min(minOperations, operations);
        
        minOperationsBacktracking(nums, left+1, right, x - nums[left], operations+1);
        minOperationsBacktracking(nums, left, right-1, x - nums[right], operations+1);
    }
    
    public int minOperations2(int[] nums, int x) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int leftSum = 0;
        for (int i = 0 ; i <= n ; i++) {
            int rightSum = 0;
            if (i != 0) leftSum += nums[i-1];
            
            for (int j = 0; j <= n-i; j++) {
                if (j != 0) rightSum += nums[n-j];
                
                
                if (leftSum+ rightSum == x) min = Math.min(min, i+j);
                if (i + j >= min) break;
            }
            
            if (i >= min) break;
        }
        
        return min == Integer.MAX_VALUE? -1 : min;
    }
    
    public int minOperations3(int[] nums, int x) {
        // sum - x
        int target = Arrays.stream(nums).sum() - x;
        int maxLen = -1;
        for (int lo = -1, hi = 0, winSum = 0; hi < nums.length; ++hi) {
            winSum += nums[hi];
            
            while(lo + 1 < nums.length && winSum > target) {
                winSum -= nums[++lo];
            }
            
            if (winSum == target) maxLen = Math.max(maxLen, hi-lo);
        }
        
        return maxLen < 0? -1: nums.length - maxLen;
    }
}
