Steps
When developing a dynamic-programming algorithm, we follow a sequence of four steps:
1. Characterize the structure of an optimal solution.
2. Recursively define the value of an optimal solution.
3. Compute the value of an optimal solution, typically in a bottom-up fashion.
4. Construct an optimal solution from computed information.

When to use DP/ Categories?
When recursion alone O(2^n) won’t work
•	Counting 用通过n种资源一共有多少种方法
•	Optimization 达到这个目的最少需要多少步
Difference between DP and recursion with memorization
Requirements for DP
1.	Optimal substructure 最优化原理（最优子结构性质）
“can be solved optimally by breaking it into sub-problems and then recursively finding the optimal solutions to the sub-problems”
2.	Overlapping sub-problems 子问题的重叠性
Sub-problems are overlapped such that we can compute only once and store the solution for future use
Reduce time time complexity (Exponential to polynomial)
If sub-problems do not overlap -> divide and conquer
3.	No-after effect 无后效性
The optimal solution of a subproblem will not change when it was used to solve a bigger problem optimally

Top-down: recursion with memorization
Bottom-up: DP

Algorithms that use DP
Fibonacci sequence 
Longest common subsequence
Knapsack
Floyd-Warshall
Bellman-Ford

Tip: 
Paddings required to handle out of board cases. Actual indies start from 1 instead of 0.

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

Question Categories
DP的分类：

1)	1D Array DP
E.g.
Algorithms 3rd edition 15.1 Cut Rod
LC
70. Climbing Stairs
91. Decode Ways
55. Jump Game
45. Jump Game II
139. Word Break
1D Array DP’
801. Minimum Swaps To Make Sequences Increasing
2)	Two Sequences DP
583. Delete Operation for Two Strings Nice question that can relate to many different problems
115. Distinct Subsequences
72. Edit Distance
3)	Matrix DP

64. Minimum Path Sum
Algorithm 3rd edition 15.2 matrix chain multiplication
