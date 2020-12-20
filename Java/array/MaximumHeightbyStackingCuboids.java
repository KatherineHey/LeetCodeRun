package array;

import java.util.Arrays;

import utility.IO;

public class MaximumHeightbyStackingCuboids {
    public int maxHeight(int[][] cuboids) {
        // sort all the inner height to be asc
        for (int[] row: cuboids) {
            Arrays.sort(row);
        }
        
        Arrays.sort(cuboids, (a, b) -> a[0] == b[0]? a[1] == b[1] ? a[2]-b[2]:a[1]-b[1]:a[0]-b[0]);
        int len = cuboids.length;
        
        // find the longest increasing height
        int cur = cuboids[0][2];
        int max = cur;
        int[] h = new int[len]; // so far highest increasing height
        
        for (int i = 0; i < len; i++) {
            int subse = 0;
            for (int j = 0; j < i; j++) {
                if (isInc(cuboids[j], cuboids[i])) {
                    subse = Math.max(subse, h[j]);
                }
            }
            
            h[i] = subse + cuboids[i][2];
            max = Math.max(max, h[i]);
        }
        
        return max;
    }
    
    public boolean isInc(int[] l, int[] r) {
        return l[0]<= r[0] && l[1] <= r[1] && l[2] <= r[2];
    }
    
    public static void main(String[] args) {
        MaximumHeightbyStackingCuboids m = new MaximumHeightbyStackingCuboids();
        //int[][] cuboids = {{43,43,20},{95,37,53},{45,23,12}};
        int[][] cuboids = {{92,47,83},{75,20,87},{68,12,83},{12,85,15},{16,24,47},{69,65,35},{96,56,93},{89,93,11},{86,20,41},{69,77,12},{83,80,97},{90,22,36}};
        System.out.println(m.maxHeight(cuboids));
    }
}
