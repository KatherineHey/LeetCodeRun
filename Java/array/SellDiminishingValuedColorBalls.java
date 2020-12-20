package array;

import java.util.Arrays;

/**
 * 1648. Sell Diminishing-Valued Colored Balls
 * @author Katherine
 *
 */
public class SellDiminishingValuedColorBalls {
    public int maxProfit(int[] inventory, int orders) {
        long profit = 0;
        
        // first order the inventory
        Arrays.sort(inventory); // asc
        
        // check from the largest number, to complete as many order as possible
        // use pointer p to keep track of the second largest number
        // it's possible p gets to -1 if all the numbers are the same.
        int i = inventory.length - 1;
        long peak = inventory[i];
        int p = i-1;
        while (p >= 0 && inventory[p] == peak) {
            p--;
        }
        
        while (orders > 0) {
            if (orders > i - p) {
                // order all the peaks
                orders -= i - p;
                profit += (i - p) * peak;
                peak--;
                while (p != -1 && inventory[p] == peak) 
                    p--; // move pointer p to the next non-peak value
            } else {
                // can already complete all the leftover orders in this round
                profit += orders * peak;
                orders = 0;
            }
        } 
        
        return (int)(profit % 1000000007L);
    }
    
    public static void main(String[] args) {
        int[] result = new int[3];
        if (result.length == 3) System.out.println("u poo");
        
        
        SellDiminishingValuedColorBalls sd = new SellDiminishingValuedColorBalls();
        int[] inventory = {701695700,915736894,35093938,364836059,452183894,951826038,861556610,
                441929847,842650446,858413011,457896886,35119509,776620034,396643588,83585103,681609037};
        int orders = 598226067;

        System.out.println(sd.maxProfit(inventory, orders));
    }
}
