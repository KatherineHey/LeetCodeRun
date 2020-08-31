package array;

import java.util.ArrayList;

public class IntervalIntersection {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        ArrayList<int[]> arr = new ArrayList<>();
        int a = 0, b = 0;
        
        while (a < A.length && b < B.length) {
            int start = Math.max(A[a][0], B[b][0]);
            int end = Math.min(A[a][1], B[b][1]);
            if (start <= end)
                arr.add(new int[] {start, end});
            
            if (A[a][1] > B[b][1]) b++;
            else a++;
        }
        
        return arr.toArray(new int[arr.size()][2]);
    }
    
    public static void main(String[] args) {
        //int[][] A = {{0,2},{5,10},{13,23},{24,25}};
        //int[][] B = {{1,5},{8,12},{15,24},{25,26}};
        int[][] A = {{3,10}};
        int[][] B = {{5,10}};
        
        IntervalIntersection ii = new IntervalIntersection();
        
        int[][] result = ii.intervalIntersection(A, B);
        
        for (int i = 0 ; i < result.length; i++) {
            System.out.print(result[i][0]+" ");
            System.out.println(result[i][1]);
        }
    }
}
