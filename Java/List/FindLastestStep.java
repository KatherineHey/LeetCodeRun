package List;

import java.util.HashMap;

/**
 * 1562. Find Latest Group of Size M
 * @author Katherine
 *
 */
public class FindLastestStep {
    // Time limit exceeded
    public int findLatestStep(int[] arr, int m) {
        int n = arr.length;
        char[] str = new char[n];
        int lateststep = -1;
        
        for (int i = 0 ; i < n; i++) {
            str[arr[i]-1] = '1';
            
            // check whether there's m
            int consequative = 0;
            
            for (int j = 0 ; j < n; j++) {
                if (str[j] == '1') {
                    consequative++;
                    if (consequative == m && ((j < n-1 && str[j+1]!='1')||j==n-1)) {
                        //System.out.println("i:"+i);
                        lateststep = i+1;
                    } else if (consequative == m && m == n ) {
                        System.out.println("i2:"+i);
                        lateststep = i+1;
                    } else if (str[j]!='1') {
                        consequative = 0;
                    }
                } else {
                    consequative = 0;
                }
            }
        }
        
        return lateststep;
    }
    
    /**
     * Runtime: 161 ms, faster than 20.00% of Java online submissions for Find Latest Group of Size M.
        Memory Usage: 175.3 MB, less than 20.00% of Java online submissions for Find Latest Group of Size M.
     * @param arr
     * @param m
     * @return
     */
    public int findLatestStep2(int[] arr, int m) {
        // HashMap for starting and ending indexes with their length, <starting index, length> <ending index,length>
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = arr.length;
        int res = -1;
        
        int[] counts= new int[n+1];
        
        for (int i = 0 ; i < n; i++) {
            int pos = arr[i];
            int leftLen = map.getOrDefault(pos-1, 0);
            int rightLen = map.getOrDefault(pos+1, 0);
            
            int newlen = leftLen + rightLen + 1;
            
            if (leftLen > 0) counts[leftLen]--;
            if (rightLen > 0) counts[rightLen]--;
            
            map.put(pos-leftLen, newlen);
            map.put(pos+rightLen, newlen);
            counts[newlen]++;
            
            if (counts[m] > 0) res = i + 1;
        }
        
        return res;
    }
    
    /**
     * Runtime: 14 ms, faster than 100.00% of Java online submissions for Find Latest Group of Size M.
Memory Usage: 101.1 MB, less than 20.00% of Java online submissions for Find Latest Group of Size M.
     * @param arr
     * @param m
     * @return
     */
    public int findLatestStep3(int[] arr, int m) {
        int n = arr.length;
        int res = -1;
        
        // HashMap for starting and ending indexes with their length, <starting index, length> <ending index,length>
        int[] map = new int[n+2];
        int[] counts= new int[n+1];
        
        for (int i = 0 ; i < n; i++) {
            int pos = arr[i];
            int leftLen = map[pos-1];
            int rightLen = map[pos+1];
            
            int newlen = leftLen + rightLen + 1;
            
            if (leftLen > 0) counts[leftLen]--;
            if (rightLen > 0) counts[rightLen]--;
            
            map[pos-leftLen] = newlen;
            map[pos+rightLen] = newlen;
            counts[newlen]++;
            
            if (counts[m] > 0) res = i + 1;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        FindLastestStep fl = new FindLastestStep();
        
        
        int[] arr = {3,1,5,4,2};
        int m = 2;
        
       
        System.out.println(fl.findLatestStep3(arr, m));
        System.out.println("2:"+fl.findLatestStep2(arr, m));
//        
        int[] arr2 = {1};
        int m2= 1;
        System.out.println(fl.findLatestStep3(arr2, m2));
        System.out.println("2:"+fl.findLatestStep2(arr2, m2));
        
        int[] arr3 = {3,5,1,2,4};
        int m3 = 1;
        System.out.println(fl.findLatestStep3(arr3, m3));
        System.out.println("2:"+fl.findLatestStep2(arr3, m3));
        
        int[] arr4 = {10,6,9,4,7,8,5,2,1,3};
        int m4= 1;
        System.out.println(fl.findLatestStep3(arr4, m4));
        System.out.println("2:"+fl.findLatestStep2(arr4, m4));
        
        int[] arr5 = {1,2,3};
        int m5= 2;
        System.out.println(fl.findLatestStep3(arr5, m5));
        System.out.println("2:"+fl.findLatestStep2(arr5, m5));
        
    }
}
