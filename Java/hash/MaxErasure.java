package hash;

import java.util.HashMap;

public class MaxErasure {
    int[] sumLeft;
    public int maximumUniqueSubarray(int[] nums) {
        int len = nums.length;
        sumLeft = new int[len+1];
        
        for (int i = 0 ; i < len; i++) {
            sumLeft[i+1] = sumLeft[i] + nums[i];
        }
        
        int res = nums[0];
        int l = 0; int r = 1;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(nums[l], l);
        while (r < len) {
            if (map.containsKey(nums[r])) {
                res = Math.max(res, getSum(l, r));
                int newl = map.get(nums[r])+1;
                while (l < newl) {
                    map.remove(nums[l]);
                    l++;
                }
                
                map.put(nums[r], r);
                r++;
            } else {
                map.put(nums[r], r);
                r++;
            }
        }
        
        res = Math.max(res, getSum(l, len));
        
        return res;
    }
    
    // l to r, inclusive l, not r
    public int getSum(int l, int r) {
        return sumLeft[r] - sumLeft[l];
    }
    
    public static void main(String[] args) {
        MaxErasure me = new MaxErasure();
        int[] nums = {5,2,1,2,5,2,1,2,5};
        System.out.println(me.maximumUniqueSubarray(nums));
        
    }
}
