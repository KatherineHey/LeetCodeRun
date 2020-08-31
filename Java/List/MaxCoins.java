package List;

import java.util.Arrays;

public class MaxCoins {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int total = 0;
        int last = piles.length-1;
        for (int i = 0 ; i < piles.length/3; i++) {
            total += piles[last-1];
            last -= 2;
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        int[] piles = {9,8,7,6,5,1,2,3,4};
        
        MaxCoins mc = new MaxCoins();
        System.out.println(mc.maxCoins(piles));
    }
}
