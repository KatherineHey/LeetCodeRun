package queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

import utility.Pair;


public class LongestSubarray {
    public int longestSubarray(int[] nums, int limit) {
        int l = 0; // consider i as right border
        int result = 0;
        
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<Pair<Integer, Integer>>((p1, p2) -> p1.getKey() - p2.getKey()); //minHeap
        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<Pair<Integer, Integer>>((p1, p2) -> p2.getKey() - p1.getKey());
        Pair<Integer, Integer> p = new Pair <Integer,Integer> (nums[0], 0);
        minHeap.add(p);
        maxHeap.add(p);
        
        for (int i = 0; i < nums.length; i++) {
            minHeap.add(new Pair<Integer, Integer>(nums[i], i));
            maxHeap.add(new Pair<Integer, Integer>(nums[i], i));
            
            while (maxHeap.peek().getKey() - minHeap.peek().getKey() > limit) {
                l = Math.min(minHeap.peek().getValue(), maxHeap.peek().getValue())+1;
                while (maxHeap.peek().getValue() < l) maxHeap.poll();
                while (minHeap.peek().getValue() < l) minHeap.poll();
            }
            
            result = Math.max(result, i - l + 1);
        }
        
        return result;
    }
    
    public int longestSubarray2(int[] nums, int limit) {
        Deque<Integer> maxd = new ArrayDeque<Integer>();
        Deque<Integer> mind = new ArrayDeque<Integer>();

        int i = 0, j;
        int result = 0;
        for (j=0 ; j < nums.length; ++j) {
            while (!maxd.isEmpty() && nums[j] > maxd.peekLast()) maxd.pollLast();
            while (!mind.isEmpty() && nums[j] < mind.peekLast()) mind.pollLast();
            
            maxd.add(nums[j]);
            mind.add(nums[j]);
            
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == nums[i]) maxd.poll();
                if (mind.peek() == nums[i]) mind.poll();
                
                ++i;
            } else {
                result = Math.max(result, j - i + 1);
                System.out.println("i:"+i+" j:"+j +" result:"+result);
            }
        }
        
        return result;
    }
    
    public int longestSubarray3(int[] A, int limit) {
        Deque<Integer> maxd = new ArrayDeque<>();
        Deque<Integer> mind = new ArrayDeque<>();
        int i = 0, j;
        for (j = 0; j < A.length; ++j) {
            while (!maxd.isEmpty() && A[j] > maxd.peekLast()) maxd.pollLast();
            while (!mind.isEmpty() && A[j] < mind.peekLast()) mind.pollLast();
            maxd.add(A[j]);
            mind.add(A[j]);
            if (maxd.peek() - mind.peek() > limit) {
                if (maxd.peek() == A[i]) maxd.poll();
                if (mind.peek() == A[i]) mind.poll();
                ++i;
            }
        }

        System.out.println("i:"+i+" j:"+j);
        return j - i;
    }
    
    public static void main(String[] args) {
        LongestSubarray ls = new LongestSubarray();
        int[] nums = {4,2,2,2, 4,4,2};
        int limit = 1;
        System.out.println(ls.longestSubarray2(nums, limit));
        System.out.println(ls.longestSubarray3(nums, limit));
    }
}
