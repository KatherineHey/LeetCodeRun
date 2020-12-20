package recursive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 5600. Kth Smallest Instructions
 * @author Katherine
 *
 */

public class kthsmallestpath {
    LinkedHashSet<String> paths = new LinkedHashSet<String>(); 
    int K = 0;
    public  void permutation(String str) { 
        permutation("", str); 
    }

    private  void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            paths.add(prefix); //System.out.println(prefix); 
            if (paths.size() == K) System.out.println(prefix+"(((");
        }
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }
    
    public String kthSmallestPath(int[] destination, int k) {
        K = k;
        int vnum = destination[0];
        int hnm = destination[1];
        String input = "";
        for (int j = 0; j < hnm; j++) input+= "H";
        for (int i = 0; i< vnum; i++) input += "V";

        permutation(input);
        
        // sort paths
        // Sorting HashSet using List 
        List<String> arr = new ArrayList<String>(paths); 
        Collections.sort(arr); 
        return arr.get(k-1);
  
    }
    
    public static void main(String[] args) {
        kthsmallestpath k = new kthsmallestpath();
        int[] inpu = {2,3};
        k.kthSmallestPath(inpu, 1);
    }
}
