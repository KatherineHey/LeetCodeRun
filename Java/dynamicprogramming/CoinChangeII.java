package dynamicprogramming;

import java.util.Arrays;

/**
 * 518. Coin Change 2
 * @author Katherine
 *
 */
public class CoinChangeII {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        if (amount == 0 ) return 1;
        if (len == 0) return 0;
        
        Arrays.sort(coins);
        
        // use [0, n), in other words, 0-n-1 to get amount 
        int[][] dp = new int[len+1][amount+1];
        
        for (int i = 0; i < len; i++) {
            if (coins[i] <= amount)
                dp[i+1][coins[i]] = 1;
            //System.out.println("current coin:"+coins[i]+"sum:"+coins[i]+" count:"+dp[i+1][coins[i]]);
        }

        for (int s = 1; s <= amount; s++) {
            for (int i = 0; i < len; i++) {
             // amount s only be achieved by using just one coin
                if (s>= coins[i] && s % coins[i] == 0)
                    dp[i+1][s] = 1;
            }
        }
        
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {

                dp[i][j] += dp[i-1][j]; // not use the last coin to make the sum j
                
                // use the last/ current coin to make the sum j. The current coin is coins[i-1]
                if (j - coins[i-1] >= 0) {
                    // use as little coin (1) to use as many last coin as possible
                    for (int number = 1; number <= j/ coins[i-1]; number++) {
                        dp[i][j] += dp[i-1][j-number * coins[i-1]];
                    }
                    
                    // System.out.println("current coin:"+coins[i-1]+" j:"+j+" count:"+dp[i][j]);
                }
            }
        }
        
        return dp[len][amount];
    }
    
    public static void main(String[] args) {
        CoinChangeII cc = new CoinChangeII();
        int[] coins = {1,2,5};
        System.out.println(cc.change(5, coins));
    }
}
