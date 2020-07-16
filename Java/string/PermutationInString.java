package string;

import java.util.Arrays;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] window = new int[26];
        
        int len1 = s1.length();
        int len2 = s2.length();
        
        if (len1 > len2) return false;
        
        for (int i = 0; i < len1; i++) {
            window[s1.charAt(i)-'a']++;
            window[s2.charAt(i)-'a']--;
        }
        
        if (allZero(window)) return true;
        
        for (int i = len1 ; i < len2; i++) {
            window[s2.charAt(i - len1) - 'a']++;
            window[s2.charAt(i) - 'a']--;
            
            if (allZero(window)) return true;
        }
        
        return false;
    }
    
    public boolean allZero(int[] window) {
        for (int i : window) {
            if (i != 0) return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        PermutationInString ps = new PermutationInString();
        
        System.out.println(ps.checkInclusion("ab", "eidbaooo"));
    }
}
