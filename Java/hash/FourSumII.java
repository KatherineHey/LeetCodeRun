package hash;

import java.util.HashMap;

/**
 * 454. 4Sum II
 * @author Katherine
 *
 */
public class FourSumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        
        for (int i = 0 ; i < A.length; i++) {
            for (int j = 0 ; j < B.length; j++) {
                map.put(A[i]+B[j], map.getOrDefault(A[i]+B[j], 0)+1);
            }
        }
        
        for (int i = 0 ; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                if (map.containsKey(-C[i]-D[j]))
                    count += map.get(-C[i]-D[j]);
            }
        }
        
        return count;
    }

    public static void main(String[] args) {
        int[] A = { -1,-1};
        int[] B = {-1,1};
        int[] C = {-1, 1};
        int[] D = { 1, -1};
        
        FourSumII fs = new FourSumII();
        System.out.println(fs.fourSumCount(A, B, C, D));
    }
}
