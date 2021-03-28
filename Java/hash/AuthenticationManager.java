package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AuthenticationManager {
    int TimeToLive = 0;
    int curTime = 0;
    
    // Time expires, all the keys
    HashMap<Integer, HashSet<String>> m = new HashMap<Integer, HashSet<String>>();
    public AuthenticationManager(int timeToLive) {
        curTime++;
        TimeToLive = timeToLive;
    }
    
    public void generate(String tokenId, int currentTime) {
        curTime = currentTime;
        int expTime = curTime+TimeToLive;
        m.computeIfAbsent(expTime, k-> new HashSet<String>());
        m.get(expTime).add(tokenId);
    }
    
    public void renew(String tokenId, int currentTime) {
        curTime = currentTime;
        if (!m.isEmpty()) {
            // Check for expire time
            List<Integer> allExpT = new ArrayList<Integer>(m.keySet());
            for (int exp : allExpT) {
                if (exp > curTime) {
                    // not expired, renew key tokenId if exists
                    HashSet<String> allKey = m.get(exp);
                    if (allKey.contains(tokenId)) {
                        m.computeIfAbsent(currentTime + TimeToLive, k-> new HashSet<String>());
                        m.get(currentTime + TimeToLive).add(tokenId);
                        allKey.remove(tokenId);
                        break;
                    }
                }
            }
        }
    }
    
    public int countUnexpiredTokens(int currentTime) {
        curTime = currentTime;
        int total = 0;
        for (int t : m.keySet()) {
            if (t > curTime) {
                total+= m.get(t).size();
            }
        }
        
        return total;
    }
    
    public static void main(String[] args) {
        //["AuthenticationManager","renew","generate","countUnexpiredTokens","generate","renew","renew","countUnexpiredTokens"]
        //        [[5],["aaa",1],["aaa",2],[6],["bbb",7],["aaa",8],["bbb",10],[15]]
        
//        AuthenticationManager a = new AuthenticationManager(5);
//        a.renew("aaa",1);
//        a.generate("aaa", 2);
//        a.countUnexpiredTokens(6);
//        a.generate("bbb", 7);
//        a.renew("aaa", 8);
//        a.renew("bbb", 10);
//        a.countUnexpiredTokens(15);
        AuthenticationManager a = new AuthenticationManager(13);
        a.countUnexpiredTokens(5);
        a.countUnexpiredTokens(6);
        a.countUnexpiredTokens(7);
        a.generate("kxlq", 9);
        a.generate("avem", 10);
        a.renew("bbb", 10);
        a.countUnexpiredTokens(15);
    }
}
