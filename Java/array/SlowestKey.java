package array;

import java.util.ArrayList;
import java.util.HashMap;

public class SlowestKey {
    public char slowestKey(int[] releaseTimes, String keysPressed) {
        HashMap<Character, Integer> keysreleaseTime = new HashMap<Character, Integer>();
        
        int maxTime= 0 ;
        
        for (int i = 0; i < releaseTimes.length; i++) {
            char key = keysPressed.charAt(i);
            if (i == 0) {
                keysreleaseTime.put(key, releaseTimes[i]);
            } else {
                // update the key releasetime with max
                keysreleaseTime.put(key, Math.max(keysreleaseTime.getOrDefault(key, 0), releaseTimes[i] - releaseTimes[i-1]));
            }
            maxTime = Math.max(maxTime, keysreleaseTime.get(key));
        }
        
        // look for the highest key
        char result = 'a';
        
        for (char c : keysreleaseTime.keySet()) {
            //
            if(keysreleaseTime.get(c) == maxTime && c > result)
                result = c;
        }
        
        return result;
    }
}
