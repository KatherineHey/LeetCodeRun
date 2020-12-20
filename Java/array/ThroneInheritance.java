package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

/**
 * Throne Inheritance
 * @author Katherine
 *
 */
public class ThroneInheritance {

    HashMap<String, ArrayList<String>> family = new HashMap<String, ArrayList<String>>();
    String king;
    HashSet<String> dead = new HashSet<String>();
    
    public ThroneInheritance(String kingName) {
        king = kingName;
        family.put(king, new ArrayList<String>());
    }
    
    public void birth(String parentName, String childName) {
        family.get(parentName).add(childName);
        family.put(childName, new ArrayList<String>());
    }
    
    public void death(String name) {
        dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        /**
         * Successor(x, curOrder):
            if x has no children or all of x's children are in curOrder:
                if x is the king return null
                else return Successor(x's parent, curOrder)
            else return x's oldest child who's not in curOrder
         */
        ArrayList<String> results= new ArrayList<String>();
        
        if (family.size() == 0) return results;
        
        Stack<String> s = new Stack<String>();
        
        s.push(king);
        
        while (!s.isEmpty()) {
            String cur = s.pop();
            if (!dead.contains(cur)) results.add(cur);
            
            int currentStackSize = s.size();
            for (String p : family.get(cur)) {
                s.add(currentStackSize, p);
            }
        }
        
        return results;
    }
    
    public static void main(String[] args) {
        ThroneInheritance t= new ThroneInheritance("king"); // order: king
        t.birth("king", "andy"); // order: king > andy
        t.birth("king", "bob"); // order: king > andy > bob
        t.birth("king", "catherine"); // order: king > andy > bob > catherine
        t.birth("andy", "matthew"); // order: king > andy > matthew > bob > catherine
        t.birth("bob", "alex"); // order: king > andy > matthew > bob > alex > catherine
        t.birth("bob", "asha"); // order: king > andy > matthew > bob > alex > asha > catherine
        t.getInheritanceOrder(); // return ["king", "andy", "matthew", "bob", "alex", "asha", "catherine"]
        t.death("bob"); // order: king > andy > matthew > bob > alex > asha > catherine
        System.out.println(t.getInheritanceOrder()); // return ["king", "andy", "matthew", "alex", "asha", "catherine"]
    }
}
