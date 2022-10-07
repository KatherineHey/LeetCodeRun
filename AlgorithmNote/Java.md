```java
TreeMap<Integer, ArrayList<int[]>> distances = new TreeMap<Integer, ArrayList<int[]>>();
distances.computeIfAbsent(manhattanDistance, pair-> new ArrayList<int[]>()).add(new int[]{i, j});
```

```java
Treemap

// Tree map is order, therefore going asc through manhattan distances
for(Map.Entry<Integer, ArrayList<int[]>> entry : distances.entrySet()) {
    Integer distance = entry.getKey();
    ArrayList<int[]> pairs = entry.getValue();
    if (pairs.size() > 1) {
        // Asc sort, first choose smallest worker [0], then smallest bike[1]
        Collections.sort(pairs, (a,b) -> (a[0] == b[0]? a[1]-b[1]: a[0]-b[0]));
    }
}


TreeMap<Integer, Integer> dp = new TreeMap<>();
dp.put(0, 0);
for (int[] job : jobs) {
    // using current job profit
    int withCurJobProfit = dp.floorEntry(job[0]).getValue() + job[2];
    if (withCurJobProfit > dp.lastEntry().getValue()) {
        dp.put(job[1], withCurJobProfit);
    }
}
```

```java
int[][] maxLength = new int[m][n];

// Fill each row with -1
for (int[] row: maxLength)
    Arrays.fill(row, -1);

```

```java
// asc | min heap
PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a,b) -> Integer.compare(a[0], b[0]));
```

```java
// 1 [], expected: [0]
if (n == 1) return Collections.singletonList(0);
        
```
