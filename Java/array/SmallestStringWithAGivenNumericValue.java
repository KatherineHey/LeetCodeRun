package array;

public class SmallestStringWithAGivenNumericValue {
    public String getSmallestString(int n, int k) {
        int numOfZ = k / 26;
        while (k - numOfZ * 26 < n - numOfZ && numOfZ > 0)
            numOfZ--;
        
        // There are numOf z in the end of the string
        // The rest of the number starts with a
        
        int rest = k - numOfZ * 26;
        int restN = n-numOfZ;
        StringBuilder sb = new StringBuilder();
        
        while (restN > 1) {
            sb.append("a");  
            restN--;
        }
        
        char c = (char)((rest - (n-numOfZ) )+'a');
        sb.append(c);
        
        while(numOfZ > 0) {
            sb.append("z");
            numOfZ--;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        SmallestStringWithAGivenNumericValue ss = new SmallestStringWithAGivenNumericValue();
        System.out.println(ss.getSmallestString(24, 552));
    }
}
