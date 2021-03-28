package dynamicprogramming;

import java.util.Arrays;

public class GetMaximumConsecutive {
    public int getMaximumConsecutive(int[] coins) {
        Arrays.sort(coins);
        int preMax = 0;
        
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= preMax + 1) {
                preMax += coins[i];
            } 
        }
        
        return preMax + 1;
    }
}
