package string;

/**
 * A valid number can be split up into these components (in order):

    A decimal number or an integer.
    (Optional) An 'e' or 'E', followed by an integer.

A decimal number can be split up into these components (in order):

    (Optional) A sign character (either '+' or '-').
    One of the following formats:
        At least one digit, followed by a dot '.'.
        At least one digit, followed by a dot '.', followed by at least one digit.
        A dot '.', followed by at least one digit.

An integer can be split up into these components (in order):

    (Optional) A sign character (either '+' or '-').
    At least one digit.

 * @author Katherine
 *
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean numberSeen = false;
        boolean eSeen = false;
        
        for (int i = 0 ; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9')
                numberSeen = true;
            else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen)
                    return false;
                
                pointSeen = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
                if (eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if ((i != 0 && s.charAt(i-1)!= 'e') && (i != 0 && s.charAt(i-1)!= 'E'))
                    return false;
            } else
                return false;
        }
        
        return numberSeen;
    }
    
    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        System.out.println(vn.isNumber("005047e+6"));
    }
}
