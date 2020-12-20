package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArithmeticSubarrays {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> result = new ArrayList<Boolean>();
        for (int i = 0; i < l.length; i++) {
            int[] arr = new int[r[i] - l[i]+1];
            int arrIndex= 0;
            for (int j = l[i]; j <= r[i]; j++) {
                arr[arrIndex] = nums[j];
                arrIndex++;
            }
            
            Arrays.sort(arr);
            
            result.add(isArithmetic(arr));
        }
        
        return result;
    }
    
    public boolean isArithmetic(int[] arr) {
        if(arr.length< 2) return false;
        
        int gap = arr[1]-arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i-1] != gap) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
       int[] nums = {4,6,5,9,3,7};
       int[] l = {0,0,2};
       int[] r =         {2,3,5};
       
       ArithmeticSubarrays a = new ArithmeticSubarrays();
       a.checkArithmeticSubarrays(nums, l, r);
    }
 }
