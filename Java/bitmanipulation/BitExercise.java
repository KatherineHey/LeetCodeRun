package bitmanipulation;

public class BitExercise {

    public int countOnes (int n) {
        int cnt = 0;
        while (n > 0) {
            n = n & (n-1); //Remove last bit that's 1: A&(A-1)
            cnt++;
        }
        
        return cnt;
    }
    
    public boolean isPowerOfFour(int n) {
        // n is power of 2       && 0x5 == (0101)B, so 0x55555555 means that every odd bit is 1.
        return ((n & (n-1)) == 0) && ((n & 0x55555555) > 0);
    }
    
    public int getSum (int a, int b) {
        int carry = 0;
        while (b != 0) {
            carry = (a & b);
            a = (a^b);
            b = carry <<1;
        }
        
        return a;
    }
    
    /**
     * An efficient solution for a fixed size integer (say 32 bits) is to one by one set bits,
     * then add 1 so that only the bit after MSB is set. Finally right shift by 1 and return the answer.
     * This solution does not require any condition checking.
     * @param a
     * @return
     */
    public int getSMB (int n) {
        // https://www.geeksforgeeks.org/find-significant-set-bit-number/
        // Below steps set bits after
        // MSB (including MSB)
 
        // Suppose n is 273 (binary
        // is 100010001). It does following
        // 100010001 | 010001000 = 110011001
        n |= n >> 1;
 
        // This makes sure 4 bits
        // (From MSB and including MSB)
        // are set. It does following
        // 110011001 | 001100110 = 111111111
        n |= n >> 2;
 
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
 
        // Increment n by 1 so that
        // there is only one set bit
        // which is just before original
        // MSB. n now becomes 1000000000
        n = n + 1;
 
        // Return original MSB after shifting.
        // n now becomes 100000000
        return (n >> 1);
    }
    
    public static void main(String[] args) {
        BitExercise be = new BitExercise();
        //be.countOnes(5);
        be.isPowerOfFour(4);
        be.isPowerOfFour(8);
        be.isPowerOfFour(12);
        
        System.out.println(be.getSum(1, 4));
    }
}
