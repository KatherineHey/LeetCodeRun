package array;

public class MinCost {
    public int minCost(String s, int[] cost) {
        int N = s.length();
        char[] sch = s.toCharArray();
        int p1 = 0;
        int p2 = 1;
        
        int minCost  =0;
        
        while (p2 < N) {
            if (sch[p1] == sch[p2]) {
                minCost+= Math.min(cost[p1], cost[p2]);
                if (cost[p1] < cost[p2]) p1=p2;
            } else {
                p1 = p2;
            }
            
            p2++;
        }
        
        return minCost;
    }
    
    public static void main(String[] args) {
        MinCost mc = new MinCost();
        String s = "aaaaaaaaaaaaaa" ;
                
        int[] cost = {1,3,6,5,4,5,4,4,2,8,3,10,6,6};
        System.out.println(mc.minCost(s, cost));
    }
}
