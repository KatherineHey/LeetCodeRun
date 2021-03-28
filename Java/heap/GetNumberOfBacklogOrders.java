package heap;

import java.util.PriorityQueue;

public class GetNumberOfBacklogOrders {
    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> sell = new PriorityQueue<int[]>((s1, s2) -> s1[0]-s2[0]); //smallest
        PriorityQueue<int[]> buy = new PriorityQueue<int[]>((s1, s2) -> s2[0]-s1[0]); //biggest
        
        for (int[] order: orders) {
            if (order[2] == 0) {
                // current is a buy order
                while (!sell.isEmpty() && sell.peek()[0] <= order[0] && order[1] > 0) {
                    int[] s = sell.poll();
                    
                    if (s[1] == order[1]) {
                        order[1] = 0;
                    } else if (s[1] > order[1]) {
                        s[1] -= order[1];
                        
                        order[1] = 0;
                        sell.offer(s);
                        break;
                    } else {
                        order[1] -= s[1];
                        // check further if others should be fulfilled.
                        
                       
                    }
                } 
                
                if (order[1] != 0)
                    buy.offer(order);
                
            } else if (order[2] == 1) {
                // current is a sell order
                while (!buy.isEmpty() && buy.peek()[0] >= order[0]&& order[1] > 0) {
                    int[] b = buy.poll();
                    
                    if (b[1] == order[1]) {
                        order[1] = 0;
                    } else if (b[1] > order[1]) {
                        b[1] -= order[1];
                        
                        order[1] = 0;
                        buy.add(b);
                        break;
                    } else {
                        order[1] -= b[1];
                    }
                }
                
                if (order[1] != 0)
                    sell.offer(order);
            }
        }
        
        long cnt = 0;
        while (!buy.isEmpty()) {
            int[] b = buy.poll();
            cnt += b[1];
        }
        
        while (!sell.isEmpty()) {
            int[] s = sell.poll();
            cnt+= s[1];
        }
        
        return (int)(cnt % 1000000007);
    }
    
    public static void main(String[] args) {
        int[][] or = {{23,8,0},{28,29,1},{11,30,1},{30,25,0},{26,9,0},{3,21,0},{28,19,1},{19,30,0},{20,9,1},{17,6,0}};
        GetNumberOfBacklogOrders gn = new GetNumberOfBacklogOrders();
        System.out.println(gn.getNumberOfBacklogOrders(or));
    }
}
