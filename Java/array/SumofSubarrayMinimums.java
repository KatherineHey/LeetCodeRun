package array;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 907. Sum of Subarray Minimums
Medium
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.
Since the answer may be large, return the answer modulo 10^9 + 7.
Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.
 * @author Katherine
 *
 */
public class SumofSubarrayMinimums {
    public int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) return 0;
        int len = A.length;
        int sum  = IntStream.of(A).sum();
        

        
        return sum;
    }
}
