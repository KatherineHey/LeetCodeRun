package array;

import java.util.HashSet;

public class CountNumber {
    public int numDifferentIntegers(String word) {
        HashSet<String> set = new HashSet<String>();
        
        
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i);
            if (c >= '1' && c <= '9') {
                int j = i+1;
                while (j < word.length() && word.charAt(j) >= '0' && word.charAt(j) <= '9') {
                    j++;
                }
                
                set.add(word.substring(i, j));
                i = j;
            } else if (c == '0' && i+1<word.length() && (word.charAt(i+1) < '0' || word.charAt(i+1)>'9')){
                set.add(word.substring(i, i+1));
            }
        }
        
        
        return set.isEmpty()?0:set.size();
    }
    
    public static void main(String[] args) {
        CountNumber c= new CountNumber();
        System.out.println("res:"+c.numDifferentIntegers("0000008288283828828818881881"));
    }
}
