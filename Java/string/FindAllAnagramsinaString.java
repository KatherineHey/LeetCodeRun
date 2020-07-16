package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsinaString {
    public List<Integer> findAnagrams(String s, String p) {
        int[] pCounts = new int[26];
        int plen = p.length();
        int[] sCounts = new int[26];
        List<Integer> indexes = new ArrayList<>();
        if (s.length() <plen) return indexes;
        
        for (char c: p.toCharArray()) {
            pCounts[c - 'a']++; // update each bucket for each character
        }
        
        for (int i = 0 ; i < s.toCharArray().length; i++) {
            if (i < plen-1) {
                sCounts[s.charAt(i) - 'a']++;
            } else {
                sCounts[s.charAt(i) - 'a']++;
                
                if (Arrays.equals(pCounts, sCounts))
                    indexes.add(i-(plen-1));
                
                sCounts[s.charAt(i-(plen-1)) - 'a']--;
            }
        }
        
        return indexes;
    }
    
    public static void main(String[] args) {
        FindAllAnagramsinaString fa = new FindAllAnagramsinaString();
        List<Integer> result = fa.findAnagrams("abab", "ab");
        
        for (int i = 0 ; i < result.size(); i++) {
            System.out.print(result.get(i) +",");
        }
    }
}
