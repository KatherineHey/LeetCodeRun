package array;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Number of Ways Where Square of Number Is Equal to Product of Two Numbers
 * @author Katherine
 *
 */
public class NumTriplets {
    public int numTriplets(int[] nums1, int[] nums2) {
        int total = 0;
        HashMap<Long, Long> nums1square = new HashMap<Long, Long>();
        HashMap<Long, Long> nums1product = new HashMap<Long, Long>();
        
        for (int i = 0 ; i < nums1.length; i++) {
            long squa = (long)nums1[i] * (long)nums1[i];
            nums1square.put(squa, nums1square.getOrDefault(squa, (long) 0)+1);
            
            for (int j = i + 1; j < nums1.length; j++) {
                long prod = (long)nums1[i] * (long)nums1[j];
                nums1product.put(prod, nums1product.getOrDefault(prod, (long) 0)+1);
            }
        }
        
        for (int i = 0 ; i < nums2.length; i++) {
            long squa = (long)nums2[i] * (long)nums2[i];
            if (nums1product.containsKey(squa)) total+=nums1product.get(squa);
            
            for (int j = i+ 1; j < nums2.length; j++) {
                long prod = (long)nums2[i] * (long)nums2[j];
                if (nums1square.containsKey(prod)) total+= nums1square.get(prod);
            }
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {43024,99908};
        int[] nums2 = {1864};
        
        NumTriplets nt = new NumTriplets();
        System.out.println(nt.numTriplets(nums1, nums2));
        
    }
}
