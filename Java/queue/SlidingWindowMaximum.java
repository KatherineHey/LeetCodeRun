package queue;

import java.util.ArrayDeque;
import java.util.Deque;

import utility.IO;

public class SlidingWindowMaximum {
    public class MonotonicQueue {
        // decreasing
        private Deque<Integer> monotonicQueue = new ArrayDeque<Integer>();
        
        public void push(int n) {
            while (!monotonicQueue.isEmpty() && monotonicQueue.getLast() < n)
                monotonicQueue.removeLast();
            
            monotonicQueue.addLast(n);
        }
        
        public void pop(int n) {
            if (!monotonicQueue.isEmpty() && monotonicQueue.getFirst() == n) {
                monotonicQueue.poll();
            }
        }
        
        public int max() {
            return monotonicQueue.getFirst();
        }
    }
    
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue mq = new MonotonicQueue();
        int[] maxSliding = new int[nums.length - k + 1];
        for (int i = 0 ; i < nums.length; i++) {
            if (i < k-1) {
                mq.push(nums[i]);
            } else {
                mq.push(nums[i]);
                maxSliding[i-k+1] = mq.max();
                mq.pop(nums[i-k+1]);
            }
        }
        
        return maxSliding;
    }
    
    public static void main(String[] args) {
        SlidingWindowMaximum sw = new SlidingWindowMaximum();
        int[] nums = {4,1,3,-1,-3,5,3,6,7};
        int[] result = sw.maxSlidingWindow(nums, 3);
        
        IO.printArray(result);
    }
    
    public int[] maxSlidingWindowSolution2(int[] nums, int k) {
        // valid range: i-k+1, i
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        
        Deque<Integer> q = new ArrayDeque<Integer>();
        int j = 0;
        for (int i = 0; i < len; i++) {
            //remove out of valid range numbers
            while (!q.isEmpty() && q.peek() < i-k+1)
                q.poll();
            
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i])
                q.pollLast();
            
            q.add(i);
            
            if (i >= k-1)
                ans[j++] = nums[q.peekFirst()];
        }
        
        return ans;
    }
}
