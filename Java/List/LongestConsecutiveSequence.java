package List;

import java.util.HashMap;

/**
 * 128. Longest Consecutive Sequence
 * @author Katherine
 *
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int maxLen = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int num : nums) {
            if (!map.containsKey(num)) {
                int left = map.getOrDefault(num-1, 0);
                int right = map.getOrDefault(num+1, 0);
                
                int newLen = left+right+1;
                maxLen = Math.max(maxLen, newLen);
                map.put(num, newLen);
                map.put(num-left, newLen);
                map.put(num+right, newLen);
            } else {
                continue;
            }
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(lcs.longestConsecutive(nums));
    }
}
