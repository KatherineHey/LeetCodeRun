package string;

/**
 * https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/?ref=lbp
 * @author Katherine
 *
 */
public class Algorithm_KMP {
    int M = 0;
    int[] lps;
    String pattern;

    public int KMPSearch(String pat, String txt) 
    {
        M = pat.length();
        lps = new int[M];

        pattern = pat;
        computeLPSArray();
        
        int j = 0 ;
        int i = 0;
        while (i < txt.length()) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } 
            
            if (j == M) 
                return i-j;//if only one match, for more than one mapping: j = lps[j - 1]; 
            else if (i < txt.length() && pat.charAt(j) != txt.charAt(i)){
                if (j != 0)
                    j = lps[j-1];
                else
                    i++;
            }
        }
        
        return -1;
    }

    void computeLPSArray() 
    {
        int i = 1;
        lps[0] = 0;
        int j = 0;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j+1;
                j++;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j-1];
                }
                else {
                    lps[i] = j;
                    i++;
                }
            }
        }
    }

    // Driver program to test above function 
    public static void main(String args[]) 
    { 
        String txt = "ABCABABCABAB"; //Algorithm_KMP
        String pat = "ABABCABAB"; 
        Algorithm_KMP kmp = new Algorithm_KMP();
        
        System.out.println(kmp.KMPSearch(pat, txt)); 
    } 
}
