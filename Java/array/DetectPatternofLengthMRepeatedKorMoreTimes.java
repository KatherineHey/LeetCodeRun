package array;
/**
 * 1566. Detect Pattern of Length M Repeated K or More Times
 * @author Katherine
 *
 */
public class DetectPatternofLengthMRepeatedKorMoreTimes {
    public boolean containsPattern(int[] arr, int m, int k) {
        int start = 0;
        int cur_start = 0;
        int n = arr.length;
        int times = 0;
        
        if (k == 1) {
            if (arr.length < m) return false;
            return true;
        }
        
        while (times < k && cur_start + 2*m <= n) {
            boolean match = true;
            // compare from start...start + m - 1 to all the things in start + m to start + 2m - 1
            for (int i = 0 ; i < m; i++) {
                if (arr[cur_start + i] != arr[cur_start+m+i]) {
                    start++;
                    times = 0;
                    cur_start = start;
                    match = false;
                    break;
                } 
            }
            
            if (!match) continue;
            else {
                cur_start  = cur_start+m;
                if (times==0) {
                    times = 2;
                } else {
                    times++;
                }
            }
        }
        
        if (times == k) return true;
        else return false;
    }
    
    public static void main(String[] args) {
        int[] arr = {2,2,2,2};
        int m = 2;
        int k = 3;
        DetectPatternofLengthMRepeatedKorMoreTimes d = new DetectPatternofLengthMRepeatedKorMoreTimes();
        System.out.println(d.containsPattern(arr, m, k));
    }
}
