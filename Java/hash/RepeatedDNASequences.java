package hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> occur = new HashSet<String>();
        HashSet<String> repeatedDna = new HashSet<String>();
        
        for (int i = 0 ; i < s.length() - 9; i++) {
            String dna = s.substring(i, i + 10);
            
            if (occur.contains(dna)) {
                repeatedDna.add(dna);
            } else {
                occur.add(dna);
            }
        }
        
        return new ArrayList(repeatedDna);
    }
}
