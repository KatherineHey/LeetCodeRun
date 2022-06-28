
# Representing graphs

# DFS, BFS Explanation by csgator(BEST)

# Topological Sorting
```java
class Solution {
    public String alienOrder(String[] words) {
        // Compare every two words' lexicographical order to populate graph
        // From, Destinations
        HashMap<Character, HashSet<Character>> g = new HashMap<Character, HashSet<Character>>();
        int[] pre = new int[26];
        int cnt = 0;
        
        for (int i = 0; i < 26; i++) {
            pre[i] = -1; // mark letter not exists
        }
        
        // Show these chars exist 
        for (String s : words) {
            for (char c: s.toCharArray()) {
                if (pre[c-'a'] == -1) {
                    pre[c-'a'] = 0;
                    cnt++;
                }
            }
        }
        
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i+1];
            
            // Special case: w2 contains w1 and longer, continue
            //if (w2.startsWith(w1)) continue;
            
            // Find the first character that differs
            int len1 = w1.length();
            int len2 = w2.length();
            
            for (int j = 0; j < len1; j++) {
                // Reach w2's end and no difference
                if (j >= len2) return "";
                
                if (w1.charAt(j) != w2.charAt(j)) {
                    char from = w1.charAt(j);
                    char to = w2.charAt(j);
                    
                    if (!g.containsKey(from)) {
                        g.put(from, new HashSet<Character>());
                    }

                    if (!g.get(from).contains(to)) {
                        g.get(from).add(to);
                        pre[to-'a']++;
                    }
                    
                    break; // later characters do not matter anymore
                }
            }
        }
        
        // Topological sort the graph
        Queue<Character> q = new LinkedList<Character>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 26; i++) {
            if (pre[i] == 0) {
                
                char c = (char) (i+'a');
                q.add(c);
                sb.append(c);
            }
        }
        
        while (!q.isEmpty()) {
            char c = q.poll();
            if (g.containsKey(c)) {
                for (char to : g.get(c)) {
                    pre[to-'a']--;
                    if (pre[to-'a'] == 0) {
                        sb.append(to);
                        q.add(to);
                    }
                }
            }
        }
        
        return sb.length() == cnt? sb.toString():"";
    }
}
```

# Prims and Kruskal
# Dijikstra
# Dijkstra on sparse graphs - Competitive Programming Algorithms
# Number of Islands
# Friend Circles
# Decode String
