package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 218. The Skyline Problem
 * @author Katherine
 *
 */
public class Skyline {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        // find the critical points that change the max height among the buildings on the left
        // https://leetcode.com/problems/the-skyline-problem/discuss/61193/Short-Java-solution
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        List<int[]> height = new ArrayList<int[]>();
        
        for (int[] building: buildings) {
            height.add(new int[] {building[0], -building[2]});
            height.add(new int[] {building[1], building[2]});
        }
        
        Collections.sort(height, (a, b) -> a[0] == b[0]? a[1]-b[1]: a[0]-b[0]);
        
        PriorityQueue<Integer> positions = new PriorityQueue<Integer>((a,b) -> b-a);
        int prevHeight = 0;
        positions.add(0);//for gaps between buildings
        
        for (int[] h: height) { // going through all the heights from left to right
            if (h[1] < 0) { // start of a building
                positions.add(-h[1]);
            } else { // end of a building
                positions.remove(h[1]);
            }
            
            int curHeight = positions.peek();
            if (curHeight != prevHeight) {
                res.add(new ArrayList<>(List.of(h[0], curHeight)));
                prevHeight= curHeight;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        Skyline s = new Skyline();
        s.getSkyline(buildings);
    }
}