package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length == 0) return 0;
        
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<int[]> set = new ArrayList<int[]>();
        
        int maxro = 0;

        for (int[] road: roads) {
            if (!map.containsKey(road[0]))
                map.put(road[0], new ArrayList<Integer>());
            map.get(road[0]).add(road[1]);
            if (!map.containsKey(road[1]))
                map.put(road[1], new ArrayList<Integer>());
            map.get(road[1]).add(road[0]);
            set.add(road);
        }

        // Create a list from elements of HashMap 
           List<Map.Entry<Integer, ArrayList<Integer>> > list = 
                  new LinkedList<Map.Entry<Integer, ArrayList<Integer>> >(map.entrySet()); 
        // Sort the list 
           Collections.sort(list, new Comparator<Map.Entry<Integer, ArrayList<Integer>> >() { 
               public int compare(Map.Entry<Integer, ArrayList<Integer>> o1,  
                                  Map.Entry<Integer, ArrayList<Integer>> o2) 
               { 
                   return (o2.getValue().size())- (o1.getValue().size()); 
               } 
           }); 
           
        // put data from sorted list to hashmap  
           HashMap<Integer,  ArrayList<Integer>> temp = new LinkedHashMap<Integer,  ArrayList<Integer>>(); 
           for (Map.Entry<Integer,  ArrayList<Integer>> aa : list) { 
               temp.put(aa.getKey(), aa.getValue()); 
           } 
           
           
           ArrayList<Integer> cityCandidates = new ArrayList<>();
           // print the sorted hashmap 
           for (Map.Entry<Integer, ArrayList<Integer>> en : temp.entrySet()) { 
              if (cityCandidates.size() < 2) {
                  cityCandidates.add(en.getKey());
              } else {
                  int minSize = temp.get(cityCandidates.get(1)).size();
                  if (en.getValue().size() >= minSize)
                      cityCandidates.add(en.getKey());
              }
           } 
           
           for (int i = 0; i < cityCandidates.size(); i++) {
               for (int j = i+1 ; j < cityCandidates.size(); j++) {
                   int networksize = temp.get(cityCandidates.get(i)).size() + temp.get(cityCandidates.get(j)).size();
                   if (temp.get(cityCandidates.get(i)).contains(cityCandidates.get(j))) {
                       networksize--;
                   }
                   
                   maxro = Math.max(maxro, networksize);
               }
           }
           
           
           return maxro;
    }
    
   
    
    public static void main(String[] args) {
        //4
        //[[0,1],[0,3],[1,2],[1,3]]
        MaximalNetworkRank m = new MaximalNetworkRank();
        int n = 8;
        int[][] roads = new int[][] {{0,1},{1,2},{2,3},{2,4},{5,6},{5,7}};
        //int[][] roads = new int[][] {{0,1},{0,3},{1,2},{1,3}};
        //int[][] roads = new int[][] {{0,1},{0,3},{1,2},{1,3},{2,3},{2,4}};
        System.out.println(m.maximalNetworkRank(n, roads));
    }
}
