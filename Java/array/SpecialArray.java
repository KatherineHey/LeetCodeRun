package array;

/**
 * 5531. Special Array With X Elements Greater Than or Equal X
 * @author Katherine
 *
 */
public class SpecialArray {
    public int specialArray(int[] nums) {
        int len = nums.length;
        int[] count = new int[len+1];
        
        for (int num: nums) {
            for (int i=1; i <= num && i < len+1; i++ ) {
                count[i]++;
            }
        }
        
        for (int i = 1 ; i < len+1; i++) {
            if (i == count[i]) return i;
        }
        
        return -1;
    }
}
