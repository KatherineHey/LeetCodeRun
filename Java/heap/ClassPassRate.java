package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class ClassPassRate {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<double[]> positions = new PriorityQueue<double[]>((a,b) -> ((b[0]+1.0)/(b[1]+1.0)-b[0]/b[1]) - ((a[0]+1.0)/(a[1]+1.0)-a[0]/a[1]) > 0?1:-1);
        
        for (int[] c: classes) {
            positions.add(new double[] {c[0], c[1]});
        }
        
        while (extraStudents > 0) {
            double[] tmp = positions.poll();
            tmp[0]++;
            tmp[1]++;
            positions.add(tmp);
            extraStudents--;
        }
        
        double totalDivide = 0.0;
        while (!positions.isEmpty()) {
            double[] tmp = positions.poll();
            totalDivide += tmp[0]/tmp[1];
        }
        
        totalDivide = totalDivide/classes.length;
        
        return totalDivide;
    }
}
