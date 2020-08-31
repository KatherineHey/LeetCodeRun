package List;

import java.util.ArrayList;
import java.util.List;

public class MostVisitedSector {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int[] sectorCounts = new int[n+1]; // total count n+1 , leave 0 blank
        int maxCount = 0;
        boolean firstRound = true;
        
        for (int i = 1; i < rounds.length; i++) {
            // to be visited sectors in this i
            int start = rounds[i-1]+1;
            int end = rounds[i];
            
            if (end < start)
                end = end + n;
            
            if (firstRound) {
                start--;
                firstRound = false;
            }
            
            while (start <= end) {
                int realSectorNumber = start > n ? start - n : start;
                sectorCounts[realSectorNumber]++;
                maxCount = Integer.max(maxCount, sectorCounts[realSectorNumber]);
                start++;
            }
        }
        
        List<Integer> results = new ArrayList<Integer>();
        
        for (int i = 1; i <= n; i++) {
            if (sectorCounts[i] == maxCount) {
                results.add(i);
            }
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        int n = 2;
        int[] rounds = {2,1,2,1,2,1,2,1,2};
        
        MostVisitedSector mvs = new MostVisitedSector();
        System.out.println(mvs.mostVisited(n, rounds));
    }
}
