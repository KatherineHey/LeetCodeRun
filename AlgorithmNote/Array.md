##### Fixed size sliding window
- [x] 567.Permutation in String
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

##### Sliding window 


- [x] 3.Longest subarray without repeating character

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

- [x] 76.Minimum Window Substring
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

##### Array tricks
- [x] 152.Maximum Product Subarray 
```java
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int currMax = 1;
        int currMin = 1;
        
        for (int num : nums) {
            int tmpMax = Math.max(num, Math.max(currMax * num, currMin * num));
            int tmpMin = Math.min(num, Math.min(currMin * num, currMax * num));
            
            currMax = tmpMax;
            currMin = tmpMin;
            
            max = Math.max(max, currMax);
            min = Math.min(min, currMin);
        }
        
        return max;
    }
```

##### Two pointers
- [x] 11. Container With Most Water
```java
    public int maxArea(int[] height) {
        int p1 = 0, p2 = height.length-1;
        int maxArea = Integer.MIN_VALUE;
        
        while (p1 < p2) {
            maxArea = Math.max(maxArea, Math.min(height[p1], height[p2]) * (p2-p1));
            if (height[p1] < height[p2]) {
                p1++;
            } else {
                p2--;
            }
        }
        
        return maxArea;
    }
```

Note, based on Stackoverflow
```
the toCharArray() is not inside the loop body; it's only called once, and then the loop iterates over that resulting array. 
(The for-each loop is different than the general for-loop, which evaluates the stop-condition each iteration.) – 
not-just-yeti
```


##### Range caching
- [ ] 370. Range Addition

You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.

```java
 public int[] getModifiedArray(int length, int[][] updates) {

    int[] res = new int[length];
     for(int[] update : updates) {
        int value = update[2];
        int start = update[0];
        int end = update[1];
        
        res[start] += value;
        
        if(end < length - 1)
            res[end + 1] -= value;
        
    }
    
    int sum = 0;
    for(int i = 0; i < length; i++) {
        sum += res[i];
        res[i] = sum;
    }
    
    return res;
}
```
