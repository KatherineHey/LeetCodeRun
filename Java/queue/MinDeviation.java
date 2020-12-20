package queue;

import java.util.PriorityQueue;

import utility.Pair;

public class MinDeviation {
    public int minimumDeviation(int[] nums) {
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>((o1, o2) -> o1.getKey() - o2.getKey());
        int maxOddFromEven = 0;
        int result = Integer.MAX_VALUE;
    
        for (int num : nums) {
            int oddFromEven = num;
            while (oddFromEven % 2 == 0)
                oddFromEven /= 2;
    
            maxOddFromEven = Math.max(maxOddFromEven, oddFromEven);
            minHeap.add(new Pair<Integer, Integer>(oddFromEven, num));
        }
    
        while (minHeap.size() == nums.length) {
            var pair = minHeap.poll();
            result = Math.min(result, maxOddFromEven - pair.getKey());
    
            if (pair.getKey() % 2 == 1 || pair.getKey() < pair.getValue()) {
                minHeap.add(new Pair<Integer, Integer>(pair.getKey() * 2, pair.getValue()));
                maxOddFromEven = Math.max(maxOddFromEven, pair.getKey() * 2);
            }
        }
    
        return result;
    }

    public static void main(String[] args) {
        MinDeviation md = new MinDeviation();
        int[] nums = { 399, 908, 648, 357, 693, 502, 331, 649, 596, 698 };// 227
        System.out.println(md.minimumDeviation(nums));
    }
}
