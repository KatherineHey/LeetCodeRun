# Steps
When developing a dynamic-programming algorithm, we follow a sequence of four steps:
1. Characterize the structure of an optimal solution.
2. Recursively define the value of an optimal solution.
3. Compute the value of an optimal solution, typically in a bottom-up fashion.
4. Construct an optimal solution from computed information.

# When to use DP/ Categories?
When recursion alone O(2^n) won’t work
•	Counting 用通过n种资源一共有多少种方法
•	Optimization 达到这个目的最少需要多少步

# Difference between DP and recursion with memorization
Top-down: recursion with memorization
Bottom-up: DP

# Requirements for DP
## 1.	Optimal substructure 最优化原理（最优子结构性质）
“can be solved optimally by breaking it into sub-problems and then recursively finding the optimal solutions to the sub-problems”
## 2.	Overlapping sub-problems 子问题的重叠性
Sub-problems are overlapped such that we can compute only once and store the solution for future use
Reduce time time complexity (Exponential to polynomial)
If sub-problems do not overlap -> divide and conquer
## 3.	No-after effect 无后效性
The optimal solution of a subproblem will not change when it was used to solve a bigger problem optimally

# Algorithms that use DP
## Fibonacci sequence 
## Longest common subsequence
## Knapsack
## Floyd-Warshall
## Bellman-Ford

Tip: 
Paddings required to handle out of board cases. Actual indies start from 1 instead of 0.

说人话就是，如果总长度为m，则初始化dp[m+1] 以避免特殊边界处理

Templates
dp = … # create dp array
             # add padding if needed
dp[0][0] = … #init dp array base cases
                       # base cases

For i …
	For j …
 		…
		dp[i][j] = … # transition

return dp[n][m]

# Question Patterns
## 1. Minimum (maximum) path to reach a target
routes[i] = min(routes[i-1],routes[i-2],routes[i-3],...routes[i-k])+cost[i]

e.g. Leetcode 322. Coin Change
```
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        int[] dp = new int[amount+1];
        
        for (int i = 1; i <= amount; i++) {
            int fewest = Integer.MAX_VALUE;
            for (int coin: coins) {
                if (coin <= i && dp[i-coin] != Integer.MAX_VALUE) {
                    fewest = Math.min(dp[i-coin]+1, fewest);
                }
            }
            
            dp[i] = fewest;
        }
        
        return dp[amount] == Integer.MAX_VALUE? -1: dp[amount];
    }
```

## 2. Distinct Ways
routes[i] = routes[i-1]+routes[i-2]+routes[i-3]+...routes[i-k]

e.g. Leetcode 70 Climb stairs
```
   public int climbStairs(int n) {
        int[] dp = new int[2];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int cur = dp[1]+dp[0];
            dp[0] = dp[1];
            dp[1] = cur;
        }
        
        return dp[1];
    }
```
## 3. Merging Intevals

// from i to j
dp[i][j] = dp[i][k] + result[k]+dp{k+1][j]

e.g 96. Unique Binary Search Trees
```
    public int numTrees(int n) {
        int[] count = new int[n+1];
        count[0] =1;  
        count[1] =1;  
        for(int i =2; i<=n; i++){  
             for(int j =0; j<i; j++){  
                  count[i] += count[j]*count[i-j-1];   
             }  
        }  
        return count[n];  
    }
```

## 4. DP on Strings

e.g. 1143. Longest Common Subsequence
```
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // longest common subsequence 0~(i-1), o~(j-1) for text1 and text2
        int[][] dp = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        
        return dp[m][n];
    }
```

## 5. Decision Making
If you decide to choose the current value use the previous result where the value was ignored; vive-versa, if you decide to ignore the current value use previous result where value was used.

e.g. 198. House Robber

```
    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        
        // max amount from previous previous house, max amount from previous house
        int[] dp = {nums[0], nums[1]};
        
        for (int i = 2; i < nums.length; i++) {
            int cur = Math.max(dp[1], dp[0] + nums[i]);
            dp[0] = Math.max(dp[0], dp[1]); // Note!!
            dp[1] = cur;
        }
        
        return Math.max(dp[0], dp[1]);
    }
```

Quote from: 
https://patterns.eecs.berkeley.edu/?page_id=416
https://leetcode.com/discuss/study-guide/458695/dynamic-programming-patterns/990665


以下内容相对较老，更喜欢上面的分类方式
# Question Categories
DP的分类：

## 1.	1D Array DP
E.g.
- [x] Algorithms 3rd edition 15.1 Cut Rod
LC
- [x] 70. Climbing Stairs
- [x] 91. Decode Ways
- [x] 55. Jump Game
- [x] 45. Jump Game II
- [x] 139. Word Break
1D Array DP’
- [x] 801. Minimum Swaps To Make Sequences Increasing
## 2.	Two Sequences DP
- [x] 583. Delete Operation for Two Strings Nice question that can relate to many different problems
- [x] 115. Distinct Subsequences
- [x] 72. Edit Distance
## 3.	Matrix DP
- [x] 64. Minimum Path Sum
- [x] Algorithm 3rd edition 15.2 matrix chain multiplication
