package dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class FrogPositon {
    double prob = 1;
    double result = 0;
    Map<Integer, List<Integer>> edgeMap;

    public double frogPosition(int n, int[][] edges, int t, int target) {
        edgeMap = new HashMap<>();
        edgeMap.put(1, new ArrayList<Integer>());

        for (int[] ed : edges) {
            edgeMap.computeIfAbsent(ed[0], (k -> new ArrayList<Integer>())).add(ed[1]);
            edgeMap.computeIfAbsent(ed[1], (k -> new ArrayList<Integer>())).add(ed[0]);
        }

        dfs(1, 1, 0, edgeMap.get(1).size(), t, target);
        return result;
    }

    public void dfs(int cur, int parent, int curTime, int curSiblingCount, int t, int target) {
        if (cur == target) {
            if (t == curTime || (t > curTime && curSiblingCount <= 0)) {
                result = prob;
            }

            return;
        }

        if (curSiblingCount == 0)
            return;

        prob /= curSiblingCount * 1.0;
        for (int neighbor : edgeMap.get(cur)) {
            if (neighbor != parent) {
                dfs(neighbor, cur, curTime + 1, edgeMap.get(neighbor).size() - 1, t, target);
            }
        }
        prob *= curSiblingCount * 1.0;
    }
    
    public double frogPosition2(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        edgeMap.put(1, new ArrayList<Integer>());

        for (int[] ed : edges) {
            edgeMap.computeIfAbsent(ed[0], (k -> new ArrayList<Integer>())).add(ed[1]);
            edgeMap.computeIfAbsent(ed[1], (k -> new ArrayList<Integer>())).add(ed[0]);
        }
        
        return dfs2(edgeMap, 1, t, target, 1);
    }
    
    public double dfs2(Map<Integer, List<Integer>> edgeMap, int current, int timeLeft, int target, double prob) {
        var neighbors = edgeMap.get(current);
        if (current == target) {
            if (timeLeft == 0 || neighbors.size() == 0) {
                return prob;
            } else {
                return 0;
            }
        } else if (timeLeft == 0) {
            return 0;
        }
        
        prob /= neighbors.size();
        for (int neighbor : neighbors) {
            edgeMap.get(neighbor).remove((Integer) current);
            var result = dfs2(edgeMap, neighbor, timeLeft - 1, target, prob);
            if (result != 0) {
                return result;
            }
        }
        
        return 0;
    }
}