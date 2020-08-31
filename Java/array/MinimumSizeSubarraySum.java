package array;

/**
 * 209. Minimum Size Subarray Sum
Medium

Given an array of n positive integers and a positive integer s,
find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.

 * @author Katherine
 *
 */

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        int i = 0, j = 0;
        int total = 0;
        int len = Integer.MAX_VALUE;
        
        while (j < nums.length) {
            total += nums[j];
            j++;
            while (total >= s) {
                len = Math.min(len, j - i);
                
                total -= nums[i];
                i++;
            }
        }
        
        return len == Integer.MAX_VALUE? 0 : len;
    }
    
    public static void main(String[] args) {
        MinimumSizeSubarraySum ms = new MinimumSizeSubarraySum();
        int[] nums = {2,3,1,2,4,3};
        System.out.println(ms.minSubArrayLen(7, nums));
    }
}
