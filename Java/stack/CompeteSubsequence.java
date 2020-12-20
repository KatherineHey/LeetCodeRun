package stack;

import java.util.Stack;

public class CompeteSubsequence {
    public int[] mostCompetitive(int[] nums, int k) {
        Stack<Integer> s = new Stack<Integer>();
        
        for (int i = 0 ; i < nums.length; i++) {
            if (s.isEmpty() || (nums[i] >= s.peek() && s.size() < k)) {
                s.push(nums[i]);
                continue;
            }
            
            while (!s.isEmpty() && nums[i] < s.peek() && nums.length - i > k - s.size()+1)
                s.pop();
            
            if (s.size() < k)
                s.push(nums[i]);
        }
        
        int[] result = new int[k];
        for (int i = k-1; i >= 0; i--) {
            result[i] = s.pop();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        CompeteSubsequence cs = new CompeteSubsequence();
        int[] nums = {2,4,3,3,5,4,9,6};
        cs.mostCompetitive(nums, 4);
    }
}
