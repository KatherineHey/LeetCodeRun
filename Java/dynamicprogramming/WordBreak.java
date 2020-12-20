package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 139. Word Break
 * @author Katherine
 *
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<String>();
        
        for (String word : wordDict) {
            words.add(word);
        }
        
        boolean[] exists = new boolean[s.length() + 1];
        exists[0] = true;
        for (int i = 1; i < s.length()+1; i++) {
            for (int j = 0 ; j < i; j++) {
                // sub word starts at j, ends at index: i-1
                if (words.contains(s.substring(j, i)) && exists[j]) {
                    exists[i] = true;
                }
            }
        }
        
        return exists[s.length()];
    }
    
    
    
    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<String>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        
        WordBreak wb = new WordBreak();
        
        System.out.println(wb.wordBreak(s, wordDict));
        
    }
}
