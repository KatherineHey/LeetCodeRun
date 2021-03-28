package math;

public class MaxScore {
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length;
        
        // max amount from the k cards means
        // min amount from the len-k cards from middle
        //len - k; 
        
        // find out the min amount with window len-k size
        int sum = 0;
        for (int i = 0; i < len - k; i++) {
            sum += cardPoints[i];
        }
        
        int windowMin = sum;
        int total = sum;
        
        // end: len-1 
        // end - start = len - k => start ends at k
        for (int i = 0; i < k; i++) {
            sum -= cardPoints[i];
            sum += cardPoints[i + len - k];
           
            windowMin = Math.min(windowMin, sum);
        }
        
        for (int i = len- k; i<len; i++) {
            total += cardPoints[i];
        }
        
        return total - windowMin;
    }
}
