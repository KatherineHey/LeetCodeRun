Campus bike ii

https://leetcode.com/problems/campus-bikes-ii/discuss/305218/DFS-%2B-Pruning-And-DP-Solution
The DFS solution is pretty straight forward, try assign each bike to each worker.
Time Complexy: O(n * m !), n is number of workers, m is number of bikes

Ususally, when input size <= 10, O(n!) can be accepeted. When input size <= 12, we probably need do some pruning. if the test case is not strong, or problem designer wants to allow this techonolgy (dfs + pruning) to pass. we can luckly get a AC.(For my experenice in LeetCode, when problem is tagged as Medium, this kind solution can be passed)

For this problem, we add a very simple but effective condition:
```java
	if (distance > min) return ;

	int min = Integer.MAX_VALUE;
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(new boolean[bikes.length], workers, 0, bikes, 0);
        return min;
    }
    public void dfs(boolean[] visit, int[][] workers, int i, int[][] bikes, int distance) {
        if (i >= workers.length) {
            min = Math.min(distance, min);
            return ;
        }
        if (distance > min) {
            return ;
        }
        for (int j = 0; j < bikes.length; j++) {
            if (visit[j]) {
                continue;
            }
            visit[j] = true;
            dfs(visit, workers, i + 1, bikes, distance + dis(bikes[j], workers[i]));
            visit[j] = false;
        }
        
    }
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
```
Actually the Brute Force Solution is a Permutation Problem's solution.
One possible optimization is transfering Permutation Problem to Combination Problem(subset problem )，from n! to 2^n. In Leetcode, I think when n < 15 , it can be accepted.

Assuming we have 3 bikes, 3 workers, and [ (b1,w1), (b2,w2),(b3,w3)] is our solution. In the Above DFS solution, we have caculated [(b2,w2),(b1,w1),(b3,w3)], [(b1,w1),(b3,w3),(b2,w2)] and so on. The distance of them are exactly same, However we only need one. In another word, we only need to know the distance of set, not list :{(b1,w1),(b2,w2),(b3,w3)}, now the problem change from permutation problem to combination.

As long as we assign b3 to w3, b2 to w2, b1 to w1, we are good. The assigning order is userless.
For ith worker, the min distance = ith worker uses jth bike + min distance all i - 1 workers to use i -1 others bike from m. so this is dp problem .

Here we use bit to represent jth bike is used or not
state : dp[i][s] = the min distance for first i workers to build the state s ,
transit: dp[i][s] = min(dp[i][s], dp[i - 1][prev] + dis(w[i -1], bike[j)) | 0 < j <m, prev = s ^ (1 << j)
init:dp[0][0] = 0;
result: dp[n][s] s should have n bit

the code should be easy to understand.
```java
  public int assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        int[][] dp = new int[n + 1][1 << m];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int s = 1; s < (1 << m); s++) {
                for (int j = 0; j < m; j++) {
                    if ((s & (1 << j)) == 0) {
                        continue;
                    }
                    int prev = s ^ (1 << j);
                    dp[i][s] = Math.min(dp[i - 1][prev] + dis(workers[i - 1], bikes[j]), dp[i][s]) ;
                    if (i == n) {
                        min = Math.min(min, dp[i][s]);
                    }
                }
            }
        }
        return min;
    }
  
    public int dis(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
``
