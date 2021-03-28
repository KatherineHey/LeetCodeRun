package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Twos {
    int M = 1000000007;
    public int countPairs(int[] deliciousness) {
        long res = 0;
        HashMap<Integer, Long> map = new HashMap<Integer, Long>();
        for (int del: deliciousness) {
            map.put(del, map.getOrDefault(del, 0L)+1L);
        }
        
        List<Integer> deliciousKeys = new ArrayList<>(map.keySet());
        Collections.sort(deliciousKeys);
        Arrays.sort(deliciousness);
        
        int keysSize = deliciousKeys.size();
        
        List<Integer> powerOfTwos = new ArrayList<Integer>();
        int cur = 1;
        powerOfTwos.add(cur);

        int maxTwo = deliciousness[deliciousness.length-1];
        if (deliciousness.length > 1) maxTwo += deliciousness[deliciousness.length-2];

        
        while (cur < maxTwo) {
            cur *= 2;
            powerOfTwos.add(cur % M);
        }
        
        for (int i=0; i < keysSize; i++) {
            int x = deliciousKeys.get(i);
            
            for (int j = 0; j < powerOfTwos.size(); j++) {
                int left = powerOfTwos.get(j) - x;
                
                if (left >= x && map.containsKey(left)) {
                    if (left == x ) {
                        res += (map.get(x) * (map.get(x)-1) / 2 ) %M;
                    } else if (left != x) {
                        res += map.get(x) * map.get(left)%M;
                    }
                }
                
            }
        }
        
        return (int)res%M;
    }
    
    public static void main(String[] args) {
        int[] arr = {64, 64};
        Twos cp = new Twos();
        System.out.println(cp.countPairs(arr));
    }
}
