package stack;

import java.util.Stack;

public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        // first find the highest bar in the height (only need to find one, if there are multiple same height bars)
        // and calculate the total size of all the bars
        int maxHeightIndex = 0;
        int totalBarSize = 0;
        
        for (int i = 0 ; i < height.length; i++) {
            totalBarSize += height[i];
            maxHeightIndex = (height[maxHeightIndex] > height[i]? maxHeightIndex: i);
        }
        
        // currentMaxIndex keeps the current max bar's index
        // stack keeps all the indexes of the bars that are smaller than currentMaxIndex
        int currentMaxIndex = -1;
        Stack<Integer> s = new Stack<>();
        int allLevelSize = 0;
        // If we encounter a bar's height >= height[currentMaxIndex], pop out all the bars until currentMaxIndex is out
        // And calculate the size of the popped bars' max level size
        for (int i = 0 ; i <= maxHeightIndex; i++) {
            if (i == 0 && height[i] == 0) continue;
            
            if (s.isEmpty() || height[i] < height[currentMaxIndex]) {
                s.push(i);
                if (currentMaxIndex == -1) currentMaxIndex = i;
            } else {
                while (s.pop() != currentMaxIndex) {
                }
                
                allLevelSize += height[currentMaxIndex] * (i - currentMaxIndex);
                currentMaxIndex = i;
                s.push(i);
            }
        }
        
        Stack<Integer> t = new Stack<>();
        currentMaxIndex = - 1;
        for (int j = height.length-1; j >= maxHeightIndex; j--) {
            if (j == height.length - 1 && height[j] == 0) continue;
            
            if (t.isEmpty() || height[j] < height[currentMaxIndex]) {
                t.push(j);
                if (currentMaxIndex == -1) currentMaxIndex = j;
            } else {
                while (t.pop() != currentMaxIndex) {
                }
                
                allLevelSize += height[currentMaxIndex] * (currentMaxIndex - j);
                currentMaxIndex = j;
                t.push(j);
            }
        }
        
        return allLevelSize + height[maxHeightIndex] - totalBarSize;
    }
    
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        
        TrappingRainWater tr = new TrappingRainWater();
        System.out.println(tr.trap(height));
    }
}
