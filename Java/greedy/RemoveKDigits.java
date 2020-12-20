package greedy;

import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Character> digits = new Stack<>();
        
        for (char c: num.toCharArray()) {
            while (k > 0 && !digits.isEmpty() && digits.peek() > c) {
                digits.pop();
                k--;
            }
            
            digits.push(c);
        }
        
        while (k > 0 && !digits.isEmpty()) {
            digits.pop();
            k--;
        }
        
        String number = digits.stream().map(Object::toString).collect(Collectors.joining());
        number = number.replaceFirst("^0*", "");

        return number.isEmpty()? "0": number;
    }
    
    
    public String removeKdigits2(String num, int k) {
        String cur = num;
        int kLeft = k;
        while (kLeft > 0) {

            int smallest = Integer.MAX_VALUE;
            
            for (int i = 0; i < cur.length(); i++) {
                StringBuilder sb = new StringBuilder(cur);
                // get the smallest number after removing one digit
                if (sb.length() == 1) return "0";
                smallest = Math.min(smallest, Integer.parseInt(sb.deleteCharAt(i).toString()));
            }
            
            cur = String.valueOf(smallest);
            kLeft--;
        }
        
        return cur;
    }
    
    public static void main(String[] args) {
        RemoveKDigits rk = new RemoveKDigits();
        System.out.println(rk.removeKdigits("1432219", 3));
    }
}
