package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 503. Next Greater Element II
 * @author Katherine
 *
 */
public class NextGreaterElementII {
    public int[] nextGreaterElements1(int[] nums) {
        Stack<int[]> dec = new Stack<>();//value, index
        int[] numsNextGreater = new int[nums.length];
        HashMap<Integer, Integer> mapRightGreat = new HashMap<>(); 
        
        for (int i = 0; i < nums.length * 2 -1; i++) {
            int j = i % nums.length;
            while (!dec.isEmpty() && dec.peek()[0] < nums[j]) {
                int[] decTop = dec.pop();
                if (mapRightGreat.getOrDefault(decTop[1], -1) == -1)
                    mapRightGreat.put(decTop[1], nums[j]);
            }
            
            dec.push(new int[] {nums[j], j});
        }
        
        for (int i = 0 ; i < nums.length; i++) {
            numsNextGreater[i] = mapRightGreat.getOrDefault(i, -1);
        }
        
        return numsNextGreater;
    }
    
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> dec = new Stack<>();//value, index
        int[] numsNextGreater = new int[nums.length];
        Arrays.fill(numsNextGreater, -1);
        
        for (int i = 0; i < nums.length * 2; i++) {
            int j = i % nums.length;
            while (!dec.isEmpty() && nums[dec.peek()] < nums[j]) {
                numsNextGreater[dec.pop()] = nums[j];
            }
            
            dec.push(j);
        }
        
        return numsNextGreater;
    }
    
    public static void main(String[] args) {
        NextGreaterElementII ng = new NextGreaterElementII();
        NextGreaterElementI ngi = new NextGreaterElementI();
        int[] nums1 = {1,2,1};
        int[] nums2 = {1,3,4,2};
        
        ngi.printArray(ng.nextGreaterElements(nums1));
    }
}
