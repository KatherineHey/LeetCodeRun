package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import sort.QuickSort;
import utility.IO;

public class BestTeamWithNoConflicts {
    public int bestTeamScore(int[] scores, int[] ages) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < ages.length; i++) {
            if (!map.containsKey(ages[i])) {
                map.put(ages[i], new ArrayList<Integer>());
            }

            map.get(ages[i]).add(scores[i]);
        }

        // put value into the hashmap
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, ArrayList<Integer>>> list = new LinkedList<Map.Entry<Integer, ArrayList<Integer>>>(
                map.entrySet());
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, ArrayList<Integer>>>() {
            public int compare(Map.Entry<Integer, ArrayList<Integer>> o1, Map.Entry<Integer, ArrayList<Integer>> o2) {
                return (o1.getKey()) - (o2.getKey());
            }
        });
        
        ArrayList<Integer> sortedScores = new ArrayList<Integer>();
        // put data from sorted list to hashmap 
        HashMap<Integer, ArrayList<Integer>> temp = new LinkedHashMap<Integer, ArrayList<Integer>>();
        for (Map.Entry<Integer, ArrayList<Integer>> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
            Collections.sort(temp.get(aa.getKey()));
            
            for (int i : temp.get(aa.getKey())) {
                sortedScores.add(i);
            }
            
        }
        
        
        
        return getLargestIncreasingSum(sortedScores);
    }
    
    public int getLargestIncreasingSum(ArrayList<Integer> sortedScores) {
        HashMap<Integer, Integer> s = new HashMap<Integer, Integer>();
        int result = 0;
        for (int i = 0 ; i < sortedScores.size(); i++) {
            s.put(i, sortedScores.get(i));
            
            for (int j =  0 ; j < i; j ++) {
                if (sortedScores.get(j) <= sortedScores.get(i)) {
                    
                    s.put(i, Math.max(s.get(i), s.get(j) + sortedScores.get(i)));
                }
            }
            result = Math.max(result, s.get(i));
        }
        
        return result;
    }

    public static void main(String[] args) {
        BestTeamWithNoConflicts qs = new BestTeamWithNoConflicts();
        //int[] scores = {1,3,5,10,15};
        //int[] ages = {1,2,3,4,5};
        int[] scores = {4,5,6,5};
        int[] ages = {2,1,2,1};
        BestTeamWithNoConflicts b = new BestTeamWithNoConflicts();
        System.out.println(b.bestTeamScore(scores, ages));
    }
}
