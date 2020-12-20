package queue;

import java.util.PriorityQueue;

/**
 * 1642. Furthest Building You Can Reach
 * @author Katherine
 *
 */
public class FurthestBuildingYouCanReach {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 1; i < heights.length; i++) {
            int d = heights[i] - heights[i-1];
            if (d > 0)
                pq.add(d);
            if (pq.size() > ladders) {
                d = pq.poll();
                bricks -= d;
                if (bricks < 0)
                    return i;
            }
        }
        
        return heights.length-1;
    }
}
