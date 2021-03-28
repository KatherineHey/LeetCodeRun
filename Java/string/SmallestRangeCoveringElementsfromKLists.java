package string;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;

import utility.IO;
import utility.Pair;

public class SmallestRangeCoveringElementsfromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        // <index of the num in the listlist, number that would have been in a target
        // merge result>
        Map<Integer, Integer> numsIdxMap = new LinkedHashMap<Integer, Integer>(16, (float) 0.75, true);
        int listlistlen = nums.size();

        // keep record of the current idx that to be merged into the merge result
        int[] arraysCurIdx = new int[listlistlen];

        long[] result = { Integer.MIN_VALUE, Integer.MAX_VALUE }; // initialize with a big combination
        
        // Consider nums as an array of rows
        // Pair: <row's index (in the array array nums), value of the actual number in the row>
        // Min heap based on the Pair's value
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>((a,b) -> a.getValue() - b.getValue());

        // Populate the initial min heap with one number from each array (first one from each row)
        for (int k = 0; k < listlistlen; k++) {
            minHeap.offer(new Pair<> (k, nums.get(k).get(0)));
        }
        
        while (!minHeap.isEmpty()) {
            // Find the smallest in the listlistlen nums
            // Pop from minheap to get the current smallest value from all the arrays
            Pair<Integer, Integer> cur = minHeap.poll();
            int smallestArrIdx = cur.getKey();
            int curMergedNum = cur.getValue();
            
            numsIdxMap.put(smallestArrIdx, curMergedNum);

            if (numsIdxMap.size() == listlistlen) { // not having one from all the arrays yet, keep inserting
                // find the oldest updated idx
                Iterator<Integer> itr = numsIdxMap.values().iterator();
                int oldestInsertNum = itr.next();
    
                if (isSmallerArr(oldestInsertNum, curMergedNum , result))
                    result = new long[]{ oldestInsertNum, curMergedNum };
            }

            // Add next to be merged from the array into the min heap
            arraysCurIdx[smallestArrIdx]++;
            if (arraysCurIdx[smallestArrIdx] < nums.get(smallestArrIdx).size())
                minHeap.offer(new Pair<>(smallestArrIdx, nums.get(smallestArrIdx).get(arraysCurIdx[smallestArrIdx])));

        }

        int[] res = { (int) result[0], (int) result[1] };
        return res;
    }

    // Return true if a <= b, false otherwise
    public boolean isSmallerArr(long astart, long aend, long[] b) {
        return aend - astart < b[1] - b[0] || (astart < b[0] && aend - astart == b[1] - b[0]);
    }

    public static void main(String[] args) {
        int[][] nums = { { 4, 10, 15, 24, 26 }, { 0, 9, 12, 20 }, { 5, 18, 22, 30 } };
        SmallestRangeCoveringElementsfromKLists sr = new SmallestRangeCoveringElementsfromKLists();
        IO.printArray(sr.smallestRange(IO.convertArrayToArrayList(nums)));
    }
}
