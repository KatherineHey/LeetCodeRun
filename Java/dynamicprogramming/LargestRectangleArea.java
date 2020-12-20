package dynamicprogramming;

public class LargestRectangleArea {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;
        
        int[] leftHigher = new int[len]; // index of the first bar to the left that is lower than the current
        int[] rightHigher = new int[len]; // index of the first bar to the right that is lower than the current
        
        leftHigher[0] = -1; rightHigher[len-1] = len;
        
        for (int i = 1; i < len; i++) {
            int j = i-1;
            while (j >= 0 && heights[j] >= heights[i]) {
                j = leftHigher[j];
            }
            
            leftHigher[i] = j;
        }
        
        for (int i = len-2; i >= 0; i--) {
            int j = i+1;
            while (j <= len-1 && heights[j] >= heights[i]) {
                j = rightHigher[j];
            }
            
            rightHigher[i] = j;
        }
        
        int maxSize = heights[0];
        
        for (int i = 0 ; i < len; i++) {
            maxSize = Math.max(maxSize, (rightHigher[i] - leftHigher[i]-1)*heights[i]);
        }
        
        return maxSize;
    }
    
    public static void main(String[] args) {
        LargestRectangleArea lr = new LargestRectangleArea();
//        int[] heights1 = {2,1,5,6,2,3};
//        System.out.println(lr.largestRectangleArea(heights1));
//        
//        int[] heights2 = {2,0,2};
//        System.out.println(lr.largestRectangleArea(heights2));
//        
//        int[] heights3 = {0,1,0,2,1,0,1,3,2,1,2,1};
//        System.out.println(lr.largestRectangleArea(heights3));
//        
//        int[] heights4 = {};
//        System.out.println(lr.largestRectangleArea(heights4));
//        
        int[] heights5 = {2,3};
        System.out.println(lr.largestRectangleArea(heights5));
    }
}
