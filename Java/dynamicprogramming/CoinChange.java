package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (int c: coins) {
            arr.add(c);
        }
        
        int[] dp = new int[amount+1];
        dp[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            if (arr.contains(i)) dp[i] = 1;
            else {
                int min = Integer.MAX_VALUE;
                int j = 0;
                while (j < arr.size() && arr.get(j) < i) {
                    min = Math.min(min, (dp[i - arr.get(j++)] + 1));
                }

                // bail early if the smallest coin is bigger than amount
                if (min == Integer.MAX_VALUE) return -1;
                
                dp[i] = min;
            }
        }
        
        return dp[amount];
    }
    
    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] coins = {1,2,5};
        int amount = 11;
        System.out.println(cc.coinChange(coins, amount));
    }
}
