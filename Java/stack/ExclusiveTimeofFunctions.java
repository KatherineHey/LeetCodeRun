package stack;

import java.util.List;
import java.util.Stack;

import utility.IO;

/**
 * 636. Exclusive Time of Functions
 * https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
 * @author Katherine
 *
 */
public class ExclusiveTimeofFunctions {
    
    public int[] exclusiveTime(int n, List<String> logs) {
        // Stack of the function id that started
        Stack<Integer> s = new Stack<Integer>();
        
        int[] timeForFunctions = new int[n];
        
     // pre means the start of the interval
        int pre = 0;
        for (String l : logs) {
            String[] arr = l.split(":");
            int curTime = Integer.parseInt(arr[2]);
            
            if (arr[1].equals("start")) {
                if (!s.isEmpty()) timeForFunctions[s.peek()] += curTime - pre;
                
                s.push(Integer.parseInt(arr[0]));
                pre = curTime;
            } else {
                int curFunctionId = s.pop();
                timeForFunctions[curFunctionId] += curTime - pre + 1;
                
                // arr[2] is end of current interval, belong to current interval. That's why we have +1 here
                pre = curTime + 1;
                // pre means the start of next interval, so we need to +1
            }
        }
        
        return timeForFunctions;
    }
    
    public static void main(String[] args) {
        int n = 2;
        String[] logs = {"0:start:0","0:start:2","0:end:5","1:start:7","1:end:7","0:end:8"};
        
        ExclusiveTimeofFunctions et = new ExclusiveTimeofFunctions();
        IO.printArray(et.exclusiveTime(n, IO.convertArrayToArrayList1D(logs)));
    }
}
