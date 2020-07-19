package string;

/*
 * 76. Minimum Window Substring
 * Credit:
 * https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] tchars = new int[256];
        
        for (char c : t.toCharArray()) {
            tchars[c]++;
        }
        
        int tcount = t.length();
        int start = 0;
        int end = 0;
        int minWindow = Integer.MAX_VALUE;
        int head = 0;
        
        while (end < s.length()) {
            char c = s.charAt(end++);
            if (tchars[c]-- > 0) {
                tcount--;
            }
            
            while (tcount == 0) {
                if (end - start < minWindow) {
                    minWindow = end - start;
                    head = start;
                }
                if (tchars[s.charAt(start++)]++ == 0) { //meaning run into one of the characers in t
                    tcount++;
                }
            }
        }
        
        return minWindow==Integer.MAX_VALUE? "":s.substring(head, head+minWindow);
    }
    
    public static void main(String[] args) {
        MinimumWindowSubstring mws = new MinimumWindowSubstring();
        System.out.println(mws.minWindow("ADOBECODEBANC", "ABC"));
    }
}
