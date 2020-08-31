package array;

/**
 * 5500. Maximum Length of Subarray With Positive Product
 * @author Katherine
 *
 */
public class MaximumLengthofSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        int maxLen = 0, pos = 0, neg = 0;
        
        for (int num : nums) {
            if (num == 0) {
                pos = 0;
                neg = 0;
                continue;
            }
            
            if (num > 0) {
                pos++;
                neg = neg == 0? 0 : neg+1;
            } else {
                int original_pos = pos;
                pos = neg == 0? 0 : neg+1;
                neg = original_pos+1;
            }
            
            maxLen = Math.max(maxLen, pos);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        int[] nums = {70,-18,75,-72,-69,-84,64,-65,0,-82,62,54,-63,-85,53,-60,-59,29,32,59,-54,-29,-45,0,-10,22,42,-37,-16,0,-7,-76,-34,37,-10,2,-59,-24,85,45,-81,56,86};
        //int[] nums = {-1000000000,-1000000000, -1,-1};
        MaximumLengthofSubarrayWithPositiveProduct m = new MaximumLengthofSubarrayWithPositiveProduct();
        System.out.println(m.getMaxLen(nums));
    }
}
