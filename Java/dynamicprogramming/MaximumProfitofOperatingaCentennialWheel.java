package dynamicprogramming;

public class MaximumProfitofOperatingaCentennialWheel {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (runningCost > boardingCost * 4) return -1;
        int maxProfit = Integer.MIN_VALUE;
        
        int served = 0;
        int leftCustomers = 0;
        int shift = 0;
        int result=  -1;
        
        int customerRounds = customers.length;
        int i = 0;
        while (i < customerRounds || leftCustomers>0) {
            int count = i < customerRounds ? customers[i] : 0;
            i++;
            int thisRoundServe = 0;
            if (count >= 4) {
                thisRoundServe = 4;
            } else if (leftCustomers > 0) {
                thisRoundServe = count + leftCustomers >= 4 ? 4: count + leftCustomers;
            } else {
                thisRoundServe = count;
            }
            
            served += thisRoundServe;
            leftCustomers = leftCustomers + count - thisRoundServe;
            
            shift++;
            
            int currentProfit = served * boardingCost - runningCost * shift;
            
            if (currentProfit > maxProfit && currentProfit > 0) {
                result = shift;
                maxProfit = currentProfit;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        MaximumProfitofOperatingaCentennialWheel m = new MaximumProfitofOperatingaCentennialWheel();
        int[] customers = {0,43,37,9,23,35,18,7,45,3,8,24,1,6,37,2,38,15,1,14,39,27,4,25,
                27,33,43,8,44,30,38,40,20,5,17,27,43,11,6,2,30,49,30,25,32,3,18,23,45,43,
                30,14,41,17,42,42,44,38,18,26,32,48,37,5,37,21,2,9,48,48,40,45,25,30,49,
                41,4,48,40,29,23,17,7,5,44,23,43,9,35,26,44,3,26,16,31,11,9,4,28,49,43,39,
                9,39,37,7,6,7,16,1,30,2,4,43,23,16,39,5,30,23,39,29,31,26,35,15,5,11,45,44,
                45,43,4,24,40,7,36,10,10,18,6,20,13,11,20,3,32,49,34,41,13,11,3,13,0,13,44,
                48,43,23,12,23,2};
        int boardingCost = 43;
        int runningCost = 54;
        System.out.println(m.minOperationsMaxProfit(customers, boardingCost, runningCost));
    }
}
