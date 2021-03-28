package dynamicprogramming;

import java.util.Arrays;

public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int len = matrix.length;
        int[] pre = new int[len];
        int[] cur = new int[len];
        
        for (int h = 0; h < len; h++) { //height
            System.out.println("h:"+h);
            
            for (int j = 0; j < len; j++) {
                if (h == 0) {
                    cur[j] = matrix[h][j];
                    System.out.println("j:"+j+" "+ cur[j]);
                }
                else {
                    int minAbove = Integer.MAX_VALUE;
                    
                    if (j == 0) {
                        minAbove = Math.min(pre[j], pre[j+1]);
                    } else if (j == len-1) {
                        minAbove = Math.min(pre[j-1], pre[j]);
                    } else {
                        minAbove = Math.min(Math.min(pre[j], pre[j+1]), pre[j-1]);
                    }
                    
                    System.out.println("j:"+j+" "+minAbove);
                    cur[j] = matrix[h][j] + minAbove;
                }
            }
            
            
            pre = Arrays.copyOf(cur, len);
            System.out.println("pre[0]:"+pre[0]+" pre[1]:"+pre[1]);
        }
        
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            res = Math.min(res, pre[i]);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{-19,57},{-40,-5}};
        
        MinFallingPathSum mfps = new MinFallingPathSum();
        System.out.println(mfps.minFallingPathSum(matrix));
    }
}
