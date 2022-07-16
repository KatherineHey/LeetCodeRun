### Sliding window

- [x] 3. Longest subarray without repeating character

```java
  public int lengthOfLongestSubstring(String s) {
        int[] charCnt = new int[256];
        int start = 0;
        int end = 0;
        int maxLen = 0;
        char[] chars = s.toCharArray();
        
        while (end < chars.length) {
            // end is inclusive
            charCnt[chars[end]]++;
            
            while (charCnt[chars[end]] > 1) {
                charCnt[chars[start]]--;
                start++;
            }
            
            maxLen = Math.max(maxLen, end-start+1);
            end++;
        }
        
        return maxLen;
    }
```

- [x] 76. Minimum Window Substring
```java
    public String minWindow(String s, String t) {
        // Count of each char in t
        int[] charCnt = new int[256];
        
        // Total count of chars in t.
        int cnt = t.length();
        
        for (char c : t.toCharArray()) {
            charCnt[c]++;
        }
        
        // Sliding window to go through all chars in s
        // Memorize the min start, and min len of the sliding window
        int l = 0, r = 0, minLen = Integer.MAX_VALUE, minStart = Integer.MAX_VALUE;
        
        while (r < s.length()) {
            char rightChar = s.charAt(r);
            
            // Find a match in t
            if (charCnt[rightChar] > 0) cnt--;
            charCnt[rightChar]--;
            
            r++;
            
            // While match exists
            // Moving left pointer to find the min sliding window
            while (cnt == 0) { 
                if (r-l< minLen) {
                    minStart = l;
                    minLen = r - l; // not including r's char
                } 
                
                char leftChar = s.charAt(l);
                charCnt[leftChar]++;
                // !!! All the non existing chars in t would have been previously negative.
                // Therefore only bring count to 0, if cnt > 0 means it's a char exists in t
                if (charCnt[leftChar] > 0) {
                    cnt++;
                }
                
                l++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
```

Note, based on Stackoverflow
```
the toCharArray() is not inside the loop body; it's only called once, and then the loop iterates over that resulting array. 
(The for-each loop is different than the general for-loop, which evaluates the stop-condition each iteration.) – 
not-just-yeti
```
