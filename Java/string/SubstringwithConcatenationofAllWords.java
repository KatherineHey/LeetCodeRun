package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Nice template: https://leetcode.com/problems/
 * find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
 * 
 * 30. Substring with Concatenation of All Words
 * @author Katherine
 *
 */

public class SubstringwithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> results = new ArrayList<Integer>();
        
        HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();

        int wordsCount = words.length;
        int wl = 0;
        
        if (wordsCount > 0) wl = words[0].length();
        
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0)+1);
        }
        
        // travel all sub string combinations
        for (int i = 0; i < wl; ++i) {
            int left = i, count = 0;
            HashMap<String, Integer> currentMap = new HashMap<String, Integer>();
            for (int j = i; j <= s.length() - wl; j += wl) {
                String str = s.substring(j, j+wl);
                if (wordsMap.containsKey(str)) {
                    currentMap.put(str, currentMap.getOrDefault(str, 0)+1);
                    if (currentMap.getOrDefault(str, 0) <= wordsMap.getOrDefault(str, 0)) {
                        count++;
                    }
                    else {
                        while (currentMap.get(str) > wordsMap.get(str)) {
                            String leftStr = s.substring(left, left + wl);
                            left += wl;
                            currentMap.put(leftStr, currentMap.get(leftStr)-1);
                            if (currentMap.get(str) > wordsMap.get(str)) count--;
                        }
                    }
                    
                    if (count == wordsCount) {
                        results.add(left);
                    }
                }
                else {
                    currentMap = new HashMap<String, Integer>();
                    count = 0;
                    left = j+wl;
                }
            }
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        SubstringwithConcatenationofAllWords sc = new SubstringwithConcatenationofAllWords();
        String[] words = {"foo","bar", "the"};
        System.out.println(sc.findSubstring("barfoofoobarthefoobarman", words));
    }
}
