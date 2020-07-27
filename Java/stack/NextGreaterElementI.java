package stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 496. Next Greater Element I
    Easy
    
    You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
    Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
    The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
    If it does not exist, output -1 for this number. 
    
    Credit for the solution
    https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
    
    Worth reading
    https://labuladong.gitbook.io/algo/shu-ju-jie-gou-xi-lie/dan-tiao-zhan
 * @author K
 *
 */
public class NextGreaterElementI {
    public void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        
        System.out.println();
    }
    
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // dec keeps record of the largest number till a point from left to right in nums2
        Stack<Integer> dec = new Stack<>();
        HashMap<Integer, Integer> mapRightGreat = new HashMap<>();
        
        for (int num: nums2) {
            while (!dec.isEmpty() && dec.peek() < num)
                mapRightGreat.put(dec.pop(), num);
            
            dec.push(num);
        }
        
        for (int i = 0 ; i < nums1.length; i++) {
            nums1[i] = mapRightGreat.getOrDefault(nums1[i], -1);
        }
        
        return nums1;
    }
    
    public static void main(String[] args) {
        NextGreaterElementI ng = new NextGreaterElementI();
        
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        
        ng.nextGreaterElement(nums1, nums2);
    }
}
