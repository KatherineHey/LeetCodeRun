## Interval Scheduling
### EDF: Earliest Deadline First
Earliest deadline first (EDF) or least time to go is a dynamic priority scheduling algorithm used in real-time operating systems to place processes in a priority queue. Whenever a scheduling event occurs (task finishes, new task released, etc.) the queue will be searched for the process closest to its deadline. This process is the next to be scheduled for execution.

EDF is an optimal scheduling algorithm on preemptive uniprocessors, in the following sense: if a collection of independent jobs, each characterized by an arrival time, an execution requirement and a deadline, can be scheduled (by any algorithm) in a way that ensures all the jobs complete by their deadline, the EDF will schedule this collection of jobs so they all complete by their deadline. 

e.g. [ [1,4], [2,3], [3,4] ], the interval with early start might be very long and incompatible with many intervals. But if we choose the interval that ends early, we'll have more space left to accommodate more intervals.


435. Non-overlapping Intervals
```java
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        
        int[] pre = intervals[0];
        int end = pre[1];
        int maxNonOverlapCnt = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                maxNonOverlapCnt++;
                end = intervals[i][1];
            }
        }
        
        return intervals.length - maxNonOverlapCnt;
    }
```

253. Meeting Rooms II
```java
    public int minMeetingRooms(int[][] intervals) {
        int meetingsCnt = intervals.length;
        
        // Start
        int[] start = new int[meetingsCnt];
        int[] end = new int[meetingsCnt];
        
        for (int i = 0; i <meetingsCnt; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);
        
        int p1 = 0; int p2 = 0;
        int rooms = 0;
        int maxRooms = 1;
        
        // Only need to care about meetings start not ending scenarios
        // After all meetings start, rooms cnt will not increase
        while (p1 < meetingsCnt && p2 < meetingsCnt) {
            if (start[p1] < end[p2]) {
                rooms++; p1++;
                maxRooms = Integer.max(maxRooms, rooms);
            } else {
                rooms--;
                p2++;
            }
        }

        return maxRooms;
    }
```

## Merge Intervals

#### ❤️ 2158. Amount of New Area Painted Each Day
![image](https://github.com/KatherineHey/LeetCodeRun/assets/62370578/fe9c82c0-701f-4f12-ab27-13351aa71a6c)

![image](https://github.com/KatherineHey/LeetCodeRun/assets/62370578/dbcbf332-2aa3-4f1f-bc21-d5a2f268d25a)

![image](https://github.com/KatherineHey/LeetCodeRun/assets/62370578/7c53e9fa-1c55-4556-95ac-b06ee53a2186)

![image](https://github.com/KatherineHey/LeetCodeRun/assets/62370578/a229045b-0919-49bb-918b-85c93ae2bb5e)


```java
public int[] amountPainted(int[][] paint) {
    int n = paint.length;
    int[] result = new int[n];
    TreeMap<Integer, Integer> intervals = new TreeMap<>();

    for (int i = 0; i < n; i++) {
        int[] current = paint[i];
        // start and end to record the merged interval
        int start = current[0];
        int end = current[1];
        int toPaint = end - start;

        Map.Entry<Integer, Integer> floor = intervals.floorEntry(current[0]);
        if (floor != null) {
            if (floor.getValue() >= end) {
                // the entire current interval has been covered by floor, so the result[i] = 0 and simply just continue.
                continue;
            }
            if (floor.getValue() >= start) {
                // the current interval has been partially covered by floor, so deduct the overlapping length.
                toPaint -= floor.getValue() - start;
                intervals.remove(floor.getKey());
                start = floor.getKey();
            }
        }

        Map.Entry<Integer, Integer> ceiling = intervals.ceilingEntry(current[0]);
        // there could be multiple ceilings overlap with the current interval.
        // e.g. current [5, 20], ceilings: [6, 8], [10, 15], [18, 22]
        // We need to deduct the overlapping length properly
        while (ceiling != null && ceiling.getKey() <= end) {
            toPaint -= Math.min(end, ceiling.getValue()) - ceiling.getKey();
            intervals.remove(ceiling.getKey());
            end = Math.max(end, ceiling.getValue());
            ceiling = intervals.ceilingEntry(current[0]);
        }

        result[i] = toPaint;
        // add the merged interval to treemap
        intervals.put(start, end);
    }

    return result;
}
```

#### Use prefix sum to mark boarder of intervals, 2381. Shifting Letters II
```java
    public String shiftingLetters(String s, int[][] shifts) {
        int n=s.length();
        int arr[]=new int[n+1];
        
        // Range update for the startIdx and endIdx+1
        // This is marking boarder for later prefix sum consumption
        for (int[] shift : shifts) {
            int start = shift[0], end = shift[1], dir = (shift[2] == 1)?1:-1;
            arr[start] += dir;
            arr[end+1] -= dir;
        }
        
        // Prefix sum
        for(int i = 1; i < n+1; i++){
            arr[i]+=arr[i-1];  
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            int increaseBy=(s.charAt(i)-'a'+arr[i])%26;
            increaseBy=(increaseBy+26)%26;
            
            //converting into string
            sb.append((char)('a'+increaseBy));
        }
        return sb.toString();
    }
```
