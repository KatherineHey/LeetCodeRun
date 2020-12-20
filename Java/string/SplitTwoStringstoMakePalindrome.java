package string;

public class SplitTwoStringstoMakePalindrome {
    public boolean checkPalindromeFormation(String a, String b) {
        // ul b aab fd
        // jizc b   lu
        return checkPalindromHelper(a, b) || checkPalindromHelper(b,a);
    }
    
    public boolean checkPalindromHelper(String a, String b) {
        int len = a.length();
        
        int i = 0; 
        while (i < len) {
            if (a.charAt(i) == b.charAt(len-i-1))
                i++;
            else
                break;
        }
        
        if (i == len/2) return true;
        if (i <= len-1) {
            // check if the remaining of a or b is a palindrom
            if (isPalindrome(a.substring(i,len-i)) || isPalindrome(b.substring(i, len-i))) return true;
        }
        
        return false;
    }
    
    public boolean isPalindrome(String s) {
        int i = 0; 
        int j = s.length() - 1;
        
        while (i < j) {
            if(s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        SplitTwoStringstoMakePalindrome s = new SplitTwoStringstoMakePalindrome();
        System.out.println(s.checkPalindromeFormation("abda","acmc"));
    }
}
