package stack;

import java.util.Stack;


public class DecodeString {
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
                
                System.out.println(toAppend.toString());
                System.out.println(nsb.toString());
                st.push(st.pop().append(nsb));
            } else {
                st.peek().append(c);
            }
        }
        
        // combine all the strings in the stack st
        return st.pop().toString();
    }
    
    public static void main(String[] args) {
        DecodeString ds = new DecodeString();
        System.out.println(ds.decodeString("2[b4[F]c]"));
        System.out.println("bFFFFcbFFFFc");
    }
}
