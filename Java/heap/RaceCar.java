package heap;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/race-car/
 * @author Katherine
 *
 */
public class RaceCar {
    public class StateNode {
        int position;
        int speed;
        public StateNode(int p, int s) {
            position = p;
            speed = s;
        }
    }
    
    public int racecar2(int target) {
        Queue<StateNode> q = new LinkedList<StateNode>();
        StateNode start = new StateNode(0, 1);
        q.add(start);
        int steps = 0;
        
        Set<String> visited = new HashSet<String>();
        visited.add(start.speed+","+start.position);
        // BFS
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0 ; i < len; i++) {
                StateNode cur = q.poll();
                if (cur.position == target)
                    return steps;
                
                // A
                int nextPosition = cur.position + cur.speed;
                int nextSpeed = cur.speed * 2;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    q.offer(new StateNode(nextPosition, nextSpeed));
                }    
                
                // R
                nextPosition = cur.position;
                nextSpeed = cur.speed > 0 ? -1 : 1;
                if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                    visited.add(nextSpeed + "," + nextPosition);
                    q.offer(new StateNode(nextPosition, nextSpeed));
                }
            }
            
            steps++;
        }
        
        return -1;
    }
    
    
    // Time limit exceeded :(
    public int racecar(int target) {
        // int array of [leftover distance to the target, current speed, total length]
        PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((p1, p2) -> p1[0] == p2[0] ? p2[2] - p1[2] : (p1[0] > 0 ? p1[0] - p2[0]: p2[0]-p1[0]));
        int[] start = {target, 1, 0};
        minHeap.add(start);
        Set<String> visited = new HashSet<>();
        String key = start[0] + " " + start[1];
        visited.add(key);
        int res = Integer.MAX_VALUE;
        while (!minHeap.isEmpty()) {
            int[] cur = minHeap.poll();
            if (cur[0] == 0) {// reach the target
                res = Math.min(res, cur[2]);
                continue;
            }
            
            while (!minHeap.isEmpty() && minHeap.peek()[2] > res) {
                minHeap.poll();
            }
            
            if (cur[2] + 1 < res) {
                // A
                int[] next = {cur[0] - cur[1], cur[1]*2, cur[2]+1};
                key = (next[0] + " " + next[1]);
                if (!visited.contains(key) && Math.abs(next[0]) < target)
                    minHeap.add(next);

                // R
                int[] next2 = {cur[0], cur[1]>0?-1:1, cur[2]+1};
                key = (next2[0] + " " + next2[1]);
                if (!visited.contains(key) && Math.abs(next2[0]) < target)
                    minHeap.add(next2);
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        RaceCar rc = new RaceCar();
        //System.out.println(rc.racecar(6));
        System.out.println(rc.racecar2(6));
    }
}
