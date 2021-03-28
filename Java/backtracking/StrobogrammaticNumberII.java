package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 247 Strobogrammatic Number II
Problem:

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example, Given n = 2, return ["11","69","88","96"].
 * @author Katherine
 *
 */
public class StrobogrammaticNumberII {
    public List<String> findStrobogrammatic(int n) {
        if (n == 0) {
            ArrayList<String> res = new ArrayList<String>();
            return res;
        }

        return helper(n, n);
    }
    
    public List<String> helper(int cur, int target) {
        List<String> res = new ArrayList<String>();
        if (cur == 0) {
            res.add("");
            return res;
        }
        
        if (cur == 1) {
            res.add("1");
            res.add("0");
            res.add("8");
            return res;
        }
        
        List<String> tmp = helper(cur-2, target);
        for (String s: tmp) {
            res.add("6"+s+"9");
            res.add("9"+s+"6");
            res.add("1"+s+"1");
            res.add("8"+s+"8");
            
            if (cur != target)
                res.add("0"+s+"0");
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        StrobogrammaticNumberII sn = new StrobogrammaticNumberII();
        System.out.println(sn.findStrobogrammatic(2));
        System.out.println(sn.findStrobogrammatic(3));
    }
}
