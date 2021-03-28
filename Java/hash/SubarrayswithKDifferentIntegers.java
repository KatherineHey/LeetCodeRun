package hash;

import java.util.HashMap;

public class SubarrayswithKDifferentIntegers {
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostK(A, K) - atMostK(A, K-1);
    }
    
    public int atMostK(int[] A, int K) {
        HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
        int i = 0; int res = 0;
        
        for (int j = 0; j < A.length; j++) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0)+1);
            
            while (K < 0) {
                count.put(A[i], count.getOrDefault(A[i], 0) - 1);
                if (count.getOrDefault(A[i], 0) == 0) K++;
                
                i++;
            }
            
            res += j-i+1;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        SubarrayswithKDifferentIntegers ss = new SubarrayswithKDifferentIntegers();
        int[] A  = {1,2,1,3};
        int k = 2;
        System.out.println(ss.subarraysWithKDistinct(A, k));
    }
}
