package dynamicprogramming;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import sort.sortedArray;

public class ClosestSubsequence {
    private int sln1(int[] nums, int g){
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int n = nums.length;
        int m = n/2;
        treeMap.put(0,0);
        for(int i = (1<<m)-1; i>0; i--){
            int sum = 0;
            for(int j = 0;j< m;j++){
                int a = i >> j;
                int b = a & 1;
                sum+= nums[j]*b;
            }
            treeMap.put(sum, 0);
        }
        
        int min = Math.abs(g);
        for(int i = (1<<(n-m))-1; i>0;i--){
            int sum = 0;
            for(int j = 0;j< n-m;j++){
                sum+= nums[j+m]*((i>>j)&1);
            }
            Integer floorKey = treeMap.floorKey(g-sum);
            Integer ceilingKey = treeMap.ceilingKey(g-sum);
            if(floorKey != null) min = Math.min(min, Math.abs(sum+floorKey-g));
            if(ceilingKey != null) min = Math.min(min, Math.abs(sum+ceilingKey-g));
        }

        Integer floorKey = treeMap.floorKey(g);
        Integer ceilingKey = treeMap.ceilingKey(g);
        if(floorKey != null) min = Math.min(min, Math.abs(floorKey-g));
        if(ceilingKey != null) min = Math.min(min, Math.abs(ceilingKey-g));
        return min;
    }
    
    public int minAbsDifference(int[] nums, int goal) {
        Set<Integer> dp = new HashSet();
        int diff = goal;
        dp.add(0);
        for (int num : nums) {
            //dp.add(num);
            Set<Integer> dp2 = new HashSet();
            for (int key : dp) {
                int key1 = key+num;
                dp2.add(key1);
                //System.out.println(key1);
                
                if (Math.min(diff, Math.abs(goal-key1)) >= 0) {
                    diff = Math.min(diff, Math.abs(goal - key1));
                } else {
                    diff = -Math.min(diff, Math.abs(goal - key1));
                }
            }
            
            if (!dp2.contains(num)) {
                dp2.add(num);
                //System.out.println(num);
                if (Math.min(diff, Math.abs(goal - num)) >= 0) {
                    diff = Math.min(diff, Math.abs(goal - num));
                } else {
                    diff = -Math.min(diff, Math.abs(goal - num));
                }
            }
            
            for (int key : dp) {
                int key1 = key;
                dp2.add(key1);
                //System.out.println(key1);
                
                if (Math.min(diff, Math.abs(goal-key1)) >= 0) {
                    diff = Math.min(diff, Math.abs(goal - key1));
                } else {
                    diff = -Math.min(diff, Math.abs(goal - key1));
                }
            }
            
            //System.out.println("~~~~~~~~~~~");
            dp = dp2;
        }
        
        return diff;
    }
    
    public static void main(String[] args) {
        ClosestSubsequence sa = new ClosestSubsequence();
        int[] nums = {7,-9,15,-2};
        System.out.println(sa.sln1(nums,-5));
    }
}
