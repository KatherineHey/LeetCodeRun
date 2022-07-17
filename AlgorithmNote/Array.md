### Sliding window


##### Fixed size sliding window
- [x] 567. Permutation in String
```java
    public boolean checkInclusion(String s1, String s2) {
        // Fixed window size len1
        int len1 = s1.length();
        int len2 = s2.length();
        
        int[] cnt = new int[26];
        char[] chars1 = s1.toCharArray();
        
        for (int i = 0; i < chars1.length; i++) {
            cnt[chars1[i] - 'a']++;
        }
        
        char[] chars2 = s2.toCharArray();
        
        for (int i = 0; i < chars2.length; i++) {
            cnt[chars2[i] - 'a']--;
            
            // Moving out of the fixed sliding window, restore
            if (i >= len1) cnt[chars2[i-len1] - 'a']++;
            
            if (AllZeroes(cnt)) return true;
        }
        
        return false;
    }
    
    public boolean AllZeroes (int[] arr) {
        for (int element: arr) {
            if (element != 0) return false;
        }
        
        return true;
    }
```

##### Min/ fixed size sliding window with criteria


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
        
        for (char c : t.toCharArray()) charCnt[c]++;
        
        // Sliding window to go through all chars in s
        // Memorize the min start, and min len of the sliding window
        int l = 0, r = 0, minLen = Integer.MAX_VALUE, minStart = Integer.MAX_VALUE;
        
        while (r < s.length()) {
            char endChar = s.charAt(r);
            
            // Find a match in t
            if (charCnt[endChar] > 0) cnt--;
            
            charCnt[endChar]--; r++;
            
            // !! While match exists
            // Moving left pointer to find the min sliding window
            while (cnt == 0) { 
                if (r-l< minLen) {
                    minStart = l;
                    minLen = r - l; // not including r's char
                }
                
                char startChar = s.charAt(l);
                charCnt[startChar]++;
                
                // !!! All the non existing chars in t would only bring count to 0
                // Therefore, if cnt > 0 means it's a char exists in t
                if (charCnt[startChar] > 0) cnt++;
                
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
