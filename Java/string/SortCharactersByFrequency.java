package string;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Tip: ASCII has 256 characters, need 8 bits, or 1 Byte to store all the
 * English letters are covered within 128.
 * 
 * @param s
 * @return
 */
public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        HashMap<Character, Integer> counts = new HashMap<Character, Integer>();

        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        // Sort the hashmap by value
        // Create a list from elements of HashMap
        List<Map.Entry<Character, Integer>> list = new LinkedList<Map.Entry<Character, Integer>>(counts.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to string
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, Integer> aa : list) {
            int i = aa.getValue();
            while (i > 0) {
                sb.append(aa.getKey());
                i--;
            }

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        SortCharactersByFrequency sc = new SortCharactersByFrequency();
        System.out.println(sc.frequencySort("Aabb"));
        int aint = 'a';
        int Aint = 'A';
        System.out.println(aint);
        System.out.println(Aint);
    }
}
