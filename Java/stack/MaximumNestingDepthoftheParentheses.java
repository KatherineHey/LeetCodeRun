package stack;

import java.util.Stack;

public class MaximumNestingDepthoftheParentheses {
    public int maxDepth(String s) {
        int maxDep = 0;
        Stack<Character> st = new Stack<Character>();
        
        for (char c: s.toCharArray()) {
            if (c == '(') {
                st.push(c);
            } else if (c == ')') {
                maxDep = Math.max(maxDep, st.size());
                st.pop();
            }
        }
        
        return maxDep;
    }
}
