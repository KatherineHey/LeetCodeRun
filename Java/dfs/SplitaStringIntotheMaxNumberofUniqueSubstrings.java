package dfs;

import java.util.HashSet;
import java.util.Set;

public class SplitaStringIntotheMaxNumberofUniqueSubstrings {
    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        int[] max = new int[1];
        dfs(set, 0, s, max);
        
        return max[0];
    }
    
    public void dfs(Set<String> set, int startIndex, String str, int[] max) {
        if (startIndex == str.length()) {
            max[0] = Math.max(max[0], set.size());
            return;
        }
        
        for (int i = startIndex ; i < str.length(); i++) {
            String tmp = str.substring(startIndex, i+1);
            if (!set.contains(tmp)) {
                set.add(tmp);
                dfs(set, i+1, str, max);
                set.remove(tmp);
            }
        }
    }
    
    public static void main(String[] args) {
        SplitaStringIntotheMaxNumberofUniqueSubstrings s = new SplitaStringIntotheMaxNumberofUniqueSubstrings();
        System.out.println(s.maxUniqueSplit("ababccc"));
    }
}
