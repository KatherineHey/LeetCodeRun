package math;

public class ConcatenationofConsecutiveBinaryNumbers {
    public int concatenatedBinary(int n) {
        long res = 0, mul = 1;
        int mod = 1_000_000_007;
        for(int i = n; i > 0; --i){
            int no = i;
            while(no > 0){
                if(no % 2 == 1) res = (res + mul) % mod;
                no >>= 1;
                mul = (mul<<1) % mod;
            }
        }
        
        return (int)res;
    }
    
    public static void main(String[] args) {
        ConcatenationofConsecutiveBinaryNumbers cc = new ConcatenationofConsecutiveBinaryNumbers();
        cc.concatenatedBinary(3);
    }
}
