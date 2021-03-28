package greedy;

import java.util.Arrays;

public class EqualSumArraysWithMinimumNumberofOperations {
    public int minOperations(int[] nums1, int[] nums2) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i: nums1) {
            sum1+= i;
        }
        for (int i : nums2) {
            sum2+= i;
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int diff = Math.abs(sum1 -sum2);
        
        if(diff == 0)
            return 0;
        
        if (sum2<sum1) {
            //System.out.println(nums1[5]);
            return count(nums2, nums1, diff);
        } else {
            return count(nums1, nums2, diff);
        }
    }
    
    // Nums1 smaller
    // Try to minimize diff as much as possible.
    public int count(int[] nums1, int[] nums2, int diff){
        int i = 0;
        int j = nums2.length-1;
        
        int res = 0;
        while (i < nums1.length && j >= 0) {
            int a = 6-nums1[i];
            int b = nums2[j]-1;
            //System.out.println(a+"--"+b+"--"+diff);
            
            if (a > b) {
                diff -= a;
                i++;
            } else {
                diff -= b;
                j--;
            }
            res++;
            if (diff <= 0) return res;
        }
        
        while (i < nums1.length) {
            int a = 6-nums1[i++];
            diff -= a;
            res++;
            if (diff <= 0)
                return res;
        }
            
        while (j >= 0) {
            int b = nums2[j--]-1;
            diff-= b;
            res++;
            if (diff <= 0)
                return res;
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {6,6};
        int[] nums2 = {1};
        
        EqualSumArraysWithMinimumNumberofOperations esa = new EqualSumArraysWithMinimumNumberofOperations();
        System.out.println(esa.minOperations(nums1, nums2));
    }
}
