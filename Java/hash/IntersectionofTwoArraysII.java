package hash;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 350. Intersection of Two Arrays II
 * @author Katherine
 *
 */

public class IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        for(int n: nums1) {
            m.put(n, m.getOrDefault(n, 0)+1);
        }
        
        for (int n2: nums2) {
            if (m.getOrDefault(n2, 0) > 0) {
                arr.add(n2);
                m.put(n2, m.get(n2)-1);
            }
        }
        
        int[] result = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            result[i] = arr.get(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        
        IntersectionofTwoArraysII iota = new IntersectionofTwoArraysII();
        System.out.println(iota.intersect(nums1, nums2));
    }
}
