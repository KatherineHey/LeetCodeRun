package array;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumInitialEnergytoFinishTasks {
    public int minimumEffort(int[][] tasks) {
//        Arrays.sort(tasks, new Comparator<int[]>(){ 
//               public int compare(int[] a, int[] b)
//               { 
//                   return a[1] - a[0] == b[1] - b[0]? b[1] - a[1] : b[1]-b[0] - (a[1] - a[0]); 
//               }
//        });
        
        Arrays.sort(tasks, (int[] a, int[] b)-> a[1] - a[0] == b[1] - b[0]? b[1] - a[1] : b[1]-b[0] - (a[1] - a[0]));
        
        int len = tasks.length;
        int[] dp = new int[len]; // minimum needed energy to complete tasks till this index
        int leftEnergy = tasks[0][1];
        
        for (int i = 0 ; i < len; i++) {
            if (i == 0) {
                dp[i] = leftEnergy;
                leftEnergy -= tasks[i][0];
            }
            else {
                if (leftEnergy >= tasks[i][1]) {
                    dp[i] = dp[i-1];
                    leftEnergy -= tasks[i][0];
                }
                else {
                    dp[i] = dp[i-1] + tasks[i][1] - leftEnergy;
                    leftEnergy = dp[i] - (dp[i-1] - leftEnergy) - tasks[i][0];
                }
            }
        }
        
        return dp[len-1];
    }
    
    public static void main(String[] args) {
        MinimumInitialEnergytoFinishTasks mi = new MinimumInitialEnergytoFinishTasks();
        int[][] tasks = {{1,3},{2,4},{10,11},{10,12},{8,9}};
        System.out.println(mi.minimumEffort(tasks));
        
    }
}
