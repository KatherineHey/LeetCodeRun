package array;

public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int total = 0;
        int currentMax = 0;
        int currentMin = 0;
        for (int a : A) {
            total += a;
            currentMax = Math.max(currentMax + a, a);
            maxSum = Math.max(currentMax, maxSum);
            currentMin = Math.min(currentMin +a, a);
            minSum = Math.min(currentMin, minSum);
        }
        
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
    
    public static void main(String[] args) {
        MaximumSumCircularSubarray ms = new MaximumSumCircularSubarray();
        int[] A = {1,-2,3,-2};
        System.out.println(ms.maxSubarraySumCircular(A) +" should be 3");
        
        int[] A1 = {5,-3,5};
        System.out.println(ms.maxSubarraySumCircular(A1) +" should be 10");
        
        int[] A2 = {3,-1,2,-1};
        System.out.println(ms.maxSubarraySumCircular(A2) +" should be 4");
        
        int[] A3 = {3,-2,2,-3};
        System.out.println(ms.maxSubarraySumCircular(A3) +" should be 3");
        
        int[] A4 = {-2,-3,-1};
        System.out.println(ms.maxSubarraySumCircular(A4) +" should be -1");
    }
}
