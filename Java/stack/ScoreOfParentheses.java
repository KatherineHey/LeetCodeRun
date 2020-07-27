package stack;

import java.util.Stack;

/**
 * 856. Score of Parentheses
Medium

Given a balanced parentheses string S, compute the score of the string based on the following rule:

    () has score 1
    AB has score A + B, where A and B are balanced parentheses strings.
    (A) has score 2 * A, where A is a balanced parentheses string.

 * @author Katherine
 *
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String S) {
        Stack<Integer> s = new Stack<>();
        int score = 0;
        
        for (char c: S.toCharArray()) {
            if (c == '(') {
                s.push(score);
                score = 0;
            } else {
                score = Math.max(1, score* 2) + s.pop();
            }
        }
        
        return score;
    }
    
    public int scoreOfParentheses0(String S) {
        Stack<String> st = new Stack<>();
        
        for (char c: S.toCharArray()) {
            if (c == '(') {
                st.push(c+"");
            } else { // since it's balanced, can only be ')'
                int innerScore = 0;
                while (!st.peek().equals("(")) {
                    innerScore += Integer.parseInt(st.pop());
                }
                
                st.pop(); // pop out the matching "("
                
                innerScore *= 2;
                if (innerScore == 0) innerScore = 1; //the most inside ()
                
                st.push(innerScore+"");
            }
        }
        
        int total = 0; // handles A+B
        while (!st.isEmpty()) {
            total +=Integer.parseInt(st.pop()); 
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        ScoreOfParentheses ss = new ScoreOfParentheses();
        System.out.println(ss.scoreOfParentheses("(()(()))"));
    }
}
