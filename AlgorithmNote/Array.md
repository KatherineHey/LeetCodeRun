### Sliding window

- [x] 3 Longest subarray without repeating character

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
