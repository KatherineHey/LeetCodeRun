package recursive;

import java.util.ArrayList;
import java.util.List;

import utility.IO;

public class DifferentWaystoAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result=new ArrayList<>();
        if(input==null||input.length()==0)  return result;
        List<String> ops=new ArrayList<>();
        
        // Split the input string into numbers operators string

        int len = input.length();
        for(int i=0; i<len; i++){
            int j = i;
            while (j < len && Character.isDigit(input.charAt(j))) j++;
            
            ops.add(input.substring(i, j));
            if(j < len) ops.add(input.charAt(j)+"");
            
            i = j;
        }
        
        return compute(ops, 0, ops.size()-1);
    }
    
    public List<Integer> compute(List<String> ops, int lo, int hi) {
        List<Integer> res = new ArrayList<Integer>();
        if (lo == hi) {
            Integer num=Integer.valueOf(ops.get(lo));
            res.add(num);
            return res;
        }
        
        for (int i = lo + 1; i <= hi-1; i= i+2) {
            List<Integer> left = compute(ops, lo, i-1);
            List<Integer> right = compute(ops, i+1, hi);
            String operator = ops.get(i);
            //System.out.println(operator);
            for (int l: left) {
                for (int r: right) {
                    //System.out.println("l:"+l+" r:"+r);
                    if (operator.equals("+"))
                        res.add(l+r);
                    else if (operator.equals("-"))
                        res.add(l-r);
                    else
                        res.add(l * r);
                }
            }
        }
        
        return res;
    }

    public static void main(String[] args) {
        DifferentWaystoAddParentheses dw = new DifferentWaystoAddParentheses();
        IO.printArrayList(dw.diffWaysToCompute("2-1-1"));
    }
}
