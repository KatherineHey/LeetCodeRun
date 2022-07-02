```java
TreeMap<Integer, ArrayList<int[]>> distances = new TreeMap<Integer, ArrayList<int[]>>();
distances.computeIfAbsent(manhattanDistance, pair-> new ArrayList<int[]>()).add(new int[]{i, j});
```

```java
// Tree map is order, therefore going asc through manhattan distances
        for(Map.Entry<Integer, ArrayList<int[]>> entry : distances.entrySet()) {
            Integer distance = entry.getKey();
            ArrayList<int[]> pairs = entry.getValue();
            if (pairs.size() > 1) {
                // Asc sort, first choose smallest worker [0], then smallest bike[1]
                Collections.sort(pairs, (a,b) -> (a[0] == b[0]? a[1]-b[1]: a[0]-b[0]));
            }
        }
```
