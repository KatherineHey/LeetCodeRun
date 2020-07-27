package stack;

import java.util.ArrayList;
import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        /**
         * example: 4,5,5, 0
         * stack:
         * i = 0, stack: 0
         * i = 1, stack: 0, 1
         * i = 2, cur = 1, res = 5 * (2-0-1) = 5, stack: 0
         * i = 2, stack: 0, 2
         * i = 3, cur = 2, res = 5 * (3-0-1) = 10, stack: 0
         * i = 3, cur = 0, res = 4 * 4 = 16
         */
        Stack<Integer> s = new Stack<>();
        int max = 0;

        /**
         * heights[s.peek() + 1] >= heights[tp] because the index on top of the stack right now
         * s.peek() is the first index left of tp with height smaller than tp's height
         * (if s.peek() was greater then it should have already been poped out of the stack).
         * 
         * heights[i - 1] >= heights[tp] because index i is
         * the first index right of tp with height smaller than tp's height
         * (if i was greater then tp would have remained on the stack)
         */
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length? 0: heights[i];
            while (!s.isEmpty() && heights[s.peek()]>=h) {
                int topIndex = s.pop();
                max = Math.max(max, heights[topIndex] * (s.isEmpty()? i : (i - s.peek() - 1)));
            }
            
            s.push(i);
        }
        
        return max;
    }
    public int largestRectangleArea0(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int largestRectangle = Integer.MIN_VALUE;
        ArrayList<Integer> arr = new ArrayList<>();
        
        for (int i = 0; i < heights.length; i++) {
            if (i + 1 < heights.length && heights[i] <= heights[i+1])
                continue;
            
            // check all the possible size till reaching i
            int minH = heights[i];
            for (int j = i; j >= 0; j--) {
                minH = Math.min(minH, heights[j]);
                largestRectangle = Math.max(largestRectangle, minH * (i - j + 1));
            }
        }
        
        return largestRectangle;
    }
    
    public static void main(String[] args) {
        int[] height = {2,0,2};
        
        LargestRectangleInHistogram lr = new LargestRectangleInHistogram();
        System.out.println(lr.largestRectangleArea(height));
    }
}
