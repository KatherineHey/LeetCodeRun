package array;

import java.util.Arrays;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips.length == 0) return true;
        Arrays.sort(trips, (trip1, trip2) -> trip1[1] - trip2[1]);
        
        int currentPassenger = trips[0][0];
        if (currentPassenger > capacity) return false;
        
        int l = 0; int r = 1;
        
        while (r < trips.length) {
            currentPassenger += trips[r][0];
            
            while (l < r && trips[r][1] > trips[l][2]) {// remove the non-overlap
                currentPassenger -= trips[l][0];
                l++;
            }
            
            if (currentPassenger > capacity) return false;
            r++;
        }
        
        return true;
    }
}
