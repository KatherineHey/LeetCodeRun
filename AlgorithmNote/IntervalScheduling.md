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
            if (end+1 < s.length())
                arr[end+1] -= dir;
        }
        
        // Prefix sum
        for(int i = 1; i < n+1; i++){
            arr[i]=arr[i]+arr[i-1];  
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
