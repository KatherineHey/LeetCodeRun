package string;

import java.util.HashMap;

/**
 * 3. Longest Substring Without Repeating Characters
Medium

Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

 * @author Katherine
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(); // character, index pair
        
        for (int i=0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i))+1 /*move j to the right of the last occurrence*/);
            }
            
            map.put(s.charAt(i), i);
            longest = Math.max(longest, i - j + 1);
        }
        
        return longest;
    }
    
    public int lengthOfLongestSubstring0(String s) {
        HashMap<Character, Integer> map = new HashMap<>(); //character & index pair
        char[] sc = s.toCharArray();
        int longest = 0;
        
        for (int i = 0; i < sc.length; i++) {
            if (map.isEmpty() || !map.containsKey(sc[i])) {
                map.put(sc[i], i);
                longest = Math.max(longest, map.size());
            } else {
                char c = sc[i];
                longest = Math.max(longest, i - map.get(c));
                map.values().removeIf(val -> val < map.get(c));
                
                map.put(sc[i], i);
            }
        }
        
        return longest;
    }
    
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters ls = new LongestSubstringWithoutRepeatingCharacters();
        System.out.println(ls.lengthOfLongestSubstring("abooo"));
        
    }
}
