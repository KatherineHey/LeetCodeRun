# ❤️ 42. Trapping Rain Water

每次从栈里弹出的lowHeight相当于新的底部，每次只需要考虑两边bar已经新的底部形成的区域。因为新的底部所能围成的区域之前已经计算过了。

例如 3,1,2,3 （index: 0,1,2,3）

初始：栈中存在0,1 (idx)。 

2 出现，弹出1，此时计算3,1,2中间的小坑：1  (num)

栈中存在0,2 (idx)。

3出现，弹出2,2作为新的底部，计算3,2,3 这段漂浮的水条 = (3-0-1)* (3-2)

```java
    public int trap(int[] height) {
        // Mono decreasing
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < height.length; i++) {
            int rightHeight = height[i];
            while (!stack.isEmpty() && height[stack.peek()] < rightHeight) {
                int lowHeight = height[stack.pop()];

                if (!stack.isEmpty()) {
                    res += (Math.min(height[stack.peek()], rightHeight) - lowHeight) * (i - stack.peek() - 1);
                }
            }

            stack.push(i);
        }

        return res;
    }
```

# 84. Largest Rectangle in Histogram
```java
public int largestRectangleArea(int[] heights) {
        // Mono increasing stack, pop everything higher than current.
        // Since after a lower bar (cur), all previous higher bar are limited by lower bar
        // Aka, find the previous smaller than popped bar A, the one after A is the beginning of rec
        // Stack keeps index
        Stack<Integer> s = new Stack<Integer>();
        int largestRecArea = heights[0];
        
        for (int i = 0 ; i < heights.length; i++) {
            int cur = heights[i];
            while (!s.isEmpty() && heights[s.peek()] > cur) {
                int idx = s.pop();
                largestRecArea = Math.max(largestRecArea, (i - (s.isEmpty()? 0: (s.peek()+1))) * heights[idx]);
            }
            
            s.push(i);
        }
        
        while (!s.isEmpty()) {
            int idx = s.pop();    
            largestRecArea = Math.max(largestRecArea, (heights.length - (s.isEmpty()? 0: (s.peek()+1))) * heights[idx]);
        }
        
        return largestRecArea;
    }
```

# Decode string

Decode String 将int和stringbuilder分别放在两个stack中以避免核查是否为int。stringbuilder对应的stack中peek维护当前string，提前插入empty stringbuilder可以保证后续每次直接在peek append
```java
class Solution {
    public String decodeString(String s) {
        Stack<StringBuilder> st = new Stack<StringBuilder>();
        Stack<Integer> times= new Stack<Integer>();
        int num = 0;
        st.push(new StringBuilder());
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c<= '9') {
                num = num * 10 + c - '0';
            }
            else if (c == '[') {
                times.push(num);
                st.push(new StringBuilder());
                num = 0;
            } else if (c == ']') {
                StringBuilder toAppend = st.pop();
                StringBuilder nsb = new StringBuilder();
                int time = times.pop();
                while (time > 0) {
                    nsb.append(toAppend);
                    time--;
                }
                
                st.peek().append(nsb));
            } else {
                st.peek().append(c);
            }
        }
        
        // combine all the strings in the stack st
        return st.pop().toString();
    }
}
```

# 32. Longest Valid Parentheses
```java
  public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        int curMax = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(curMax);
                curMax = 0;
            }
            else {
                if (!stack.isEmpty()) { //indicate left exists
                    curMax += stack.pop();
                    curMax += 2;
                    res = Math.max(res, curMax);
                } else {
                    // invalid, reset curMax
                    curMax = 0;
                }
            }
        }

        return res;
    }
```

# ❤️ 636. Exclusive Time of Functions 
```java
   public int[] exclusiveTime(int n, List<String> logs) {
        Deque<int[]> stack = new ArrayDeque<>();
        int[] res = new int[n];

        for (String log : logs) {
            String[] arr = log.split(":");
            int functionId = Integer.parseInt(arr[0]);
            int curTime = Integer.parseInt(arr[2]);
            
            if (arr[1].equals("start")) {
                stack.push(new int[]{functionId, curTime});
            } else {
                int endTime = curTime + 1;
                int[] startPair = stack.pop();
                int duration = endTime - startPair[1];

                res[startPair[0]] += duration;

                // Compensate
                if (!stack.isEmpty()) {
                    res[stack.peek()[0]] -= duration; //!!!!
                }
            }
        }

        return res;
    }
```
