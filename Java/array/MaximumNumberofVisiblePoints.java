package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877845/JAVA-Sliding-Window
 * @author Katherine
 *
 */
public class MaximumNumberofVisiblePoints {
    
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles = new ArrayList<>();
        int count = 0;
        for (List<Integer> p : points) {
            int dx = p.get(0) - location.get(0);
            int dy = p.get(1) - location.get(1);
            if (dx == 0 && dy == 0) { // edge case of same point
                count++;
                continue;
            } 
            
            double curangle = Math.atan2(dy, dx) * (180 / Math.PI);
            System.out.println("angle: "+ curangle);
            angles.add(curangle);
        }
        
        Collections.sort(angles);
        
        List<Double> tmp = new ArrayList<>(angles);
        for (int i = 0 ; i < angles.size(); i++) {
            tmp.add(angles.get(i) + 360); // wrap around
        }
        
        int res = count;
        for (int i = 0, j = 0 ; i < tmp.size(); i++) {
            while (tmp.get(i) - tmp.get(j) > angle) {
                j++;
            }
            
            res = Math.max(res, count + i - j + 1);
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        int[][] points =  new int[][] {{2,1},{2,2},{3,3}};
        List pointslist = new ArrayList();
        for (int[] array : points) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int i : array) {
                row.add(i);
            }
            //This will add int[] object into the list, and not the int values.
            pointslist.add(row);
        }
        
        int angle = 90;
        ArrayList<Integer> location = new ArrayList<Integer>();
        location.add(1); location.add(1);
        
        MaximumNumberofVisiblePoints m = new MaximumNumberofVisiblePoints();
        System.out.println(m.visiblePoints(pointslist, angle, location));
    }
}
