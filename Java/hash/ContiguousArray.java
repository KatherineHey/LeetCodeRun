package hash;

import java.util.HashMap;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000.
 * @author Katherine
 *
 */
public class ContiguousArray {
    /**
     * memory limit exceeded :(
     * @param nums
     * @return
     */
    public int findMaxLength0(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int continuousLen = 0;
        
        // zeroes store the number of 0 from index i to j
        int[][] zeroes = new int[len][len];

        // initialize zeroes and ones
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                zeroes[i][i]++;
        }
        
        for (int j = 1 ; j < nums.length; j++) {
            for (int i = 0 ; i < j; i++) {
                zeroes[i][j] = zeroes[i][j-1] + (nums[j] == 0? 1: 0);
                int ijlen = j-i+1;
                if (ijlen == zeroes[i][j] * 2)
                    continuousLen = Math.max(continuousLen, ijlen);
            }
        }
        
        return continuousLen;
    }
    
    /**
     * time limit exceeded :(
     * @param nums
     * @return
     */
    public int findMaxLength1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = nums.length;
        int continuousLen = 0;
        
        // zeroes store the number of 0 from index 0 to index j
        int[] zeroes = new int[len];
        zeroes[0] = (nums[0] == 0? 1: 0);
       
        for (int j = 1 ; j < nums.length; j++) {
            zeroes[j] = zeroes[j-1] + (nums[j] == 0? 1: 0);
            
            for (int i = 0 ; i < j; i++) {
                int ijlen = j-i+1;
                if (ijlen %2 != 0) continue;
                int ijtotalzeroes = 0;
                if (nums[i] == 0 && nums[j] == 0) {
                    ijtotalzeroes = zeroes[j] - zeroes[i] + 1;
                }
                else if (nums[i] == 0 || nums[j] == 0) {
                    if (nums[j] == 0) {
                        ijtotalzeroes = zeroes[j] - zeroes[i];
                    } else {
                        ijtotalzeroes = zeroes[j] - zeroes[i] + 1;
                    }
                } else {
                    ijtotalzeroes = zeroes[j] - zeroes[i];
                }

                if (ijlen == ijtotalzeroes * 2) {
                    continuousLen = Math.max(continuousLen, ijlen);
                    continue;
                }
            }
        }
        
        return continuousLen;
    }
    
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int sum = 0;
        for (int i = 0 ; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) { // there's a sum 0 again
                max = Math.max(max, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        
        return max;
    }
    public static void main(String[] args) {
        ContiguousArray ca = new ContiguousArray();
        //int[] nums = {0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,1};
        int[] nums = {0,0,1,0,0,0,1,1};
        //int[] nums = {0,1};
        //int[] nums = {0,1,1,0,1,1,1,0};
        System.out.println("answer:"+ca.findMaxLength1(nums));
        System.out.println(ca.findMaxLength(nums));
    }
}
