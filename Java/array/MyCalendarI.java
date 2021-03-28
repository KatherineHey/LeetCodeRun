package array;

import java.util.Arrays;

public class MyCalendarI {
 // Array of events with (start,end) time
    int[][] events = new int[1000][2];
    
    public MyCalendarI() {
        for (int i = 0 ; i < events.length; i++) {
            events[i][0] = -1;
            events[i][1] = -1;
        }
    }
    
    public boolean book(int start, int end) {
        for (int i = 0; i < events.length; i++) {
            if (events[i][0] == -1) {
                // Add in current start and end
                events[i][0] = start;
                events[i][1] = end;

                break; // finished
            }
            
            if (start < events[i][0]) continue; //no overlap
            
            if ((start >= events[i][0] && start < events[i][1]) || 
                (end > events[i][1])) return false;
        }
        
        Arrays.sort(events, (a,b)->(b[0]-a[0]));
        
        return true;
    }
    
    public static void main(String[] args) {
        MyCalendarI mci = new MyCalendarI();
        mci.book(47, 50);
        mci.book(33, 41);
        System.out.println(mci.book(39,45));
    }
}
