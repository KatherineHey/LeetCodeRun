package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 4Sum
 * @author Katherine
 *
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if(nums.length <4)
            return results;

        Arrays.sort(nums);
        for (int i = 0 ; i < nums.length - 3; i++) {
            for (int j = i+1; j < nums.length - 2; j++) {
                int twoSum = nums[i] + nums[j];
                
                int k = j + 1;
                int l = nums.length - 1;
                
                while (k < l) {
                    int fourSum = twoSum + nums[k] + nums[l];
                    if ( fourSum < target) {
                        k++;
                    } else if (fourSum > target) {
                        l--;
                    } else {
                        ArrayList<Integer> fourSumTmpResult = new ArrayList<Integer>();
                        fourSumTmpResult.add(nums[i]);
                        fourSumTmpResult.add(nums[j]);
                        fourSumTmpResult.add(nums[k]);
                        fourSumTmpResult.add(nums[l]);
                        
                        if (!results.contains(fourSumTmpResult)) {
                            results.add(fourSumTmpResult);
                        }
                        
                        k++;
                        l--;
                    }
                }
            }
        }
        
        return results;
    }
    
    
}
