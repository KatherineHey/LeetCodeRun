package array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class StringsAreClose {
    public boolean closeStrings(String word1, String word2) {
        HashMap<Character, Integer> map1 = new HashMap<Character, Integer>();
        HashMap<Character, Integer> map2 = new HashMap<Character, Integer>();
        
        for (char c: word1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        
        for (char c: word2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        
        List<Integer> l1 = new ArrayList<>(map1.values());
        List<Integer> l2 = new ArrayList<>(map2.values());
        Set<Character> s1 = map1.keySet();
        Set<Character> s2 = map2.keySet();
        
        Collections.sort(l1);
        Collections.sort(l2);
        return l1.equals(l2) && s1.equals(s2);
    }
}
