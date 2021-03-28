package array;

public class EatenApple {
    public int eatenApples(int[] apples, int[] days) {
        int lastTillDay = 0; // extend total lastTill day based on apples and days
        int totalApples = 0;
        int lastNoAppleDay = -1;
        
        for (int i = 0; i < apples.length; i++) {
            int appleNum = apples[i];
            if (appleNum == 0) {
                if (lastTillDay < i) 
                    lastNoAppleDay = i;
                    
                continue;
            }
            
            int day = Math.max(i+1, lastTillDay+1) ;
            int currentApplesLastTillDay =  Math.min(day+appleNum-1, day+days[i]-1);
            
            int tmpLastTillDay = Math.max(lastTillDay, currentApplesLastTillDay);
            
            
            totalApples += (tmpLastTillDay - Math.max(lastTillDay, lastNoAppleDay+1));
            lastTillDay = tmpLastTillDay;
        }
        
        return totalApples;
    }
    
    public static void main(String[] args) {
        EatenApple ea = new EatenApple();
        int[] apples = {1,2,3,5};
        int[] days =   {3,2,1,4};
        System.out.println(ea.eatenApples(apples, days));
    }
}
