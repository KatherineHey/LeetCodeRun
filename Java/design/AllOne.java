package design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class AllOne {
    private Bucket head;
    private Bucket tail;
 // for accessing a specific Bucket among the Bucket list in O(1) time
    private Map<Integer, Bucket> countBucketMap;
    // keep track of count of keys
    private Map<String, Integer> keyCountMap;
    
    private class Bucket {
        int count;
        Set<String> keys;
        Bucket next;
        Bucket pre;
        
        public Bucket(int cnt) {
            count = cnt;
            keys = new HashSet<String>();
        }
    }
    
    /** Initialize your data structure here. */
    public AllOne() {
        head = new Bucket(Integer.MIN_VALUE);
        tail = new Bucket(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        
        countBucketMap = new HashMap<Integer, Bucket>();
        keyCountMap = new HashMap<String, Integer>();
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (keyCountMap.containsKey(key)) {
            changeKey(key, 1);
        } else {
            keyCountMap.put(key, 1);
            
            // update the smallest in the list
            if (head.next.count != 1) {
                addBucketAfter(new Bucket(1), head);
            }
            
            head.next.keys.add(key);
            countBucketMap.put(1, head.next);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (keyCountMap.containsKey(key)) {
            int count = keyCountMap.get(key);
            if (count == 1) {
                keyCountMap.remove(key);
                removeKeyFromBucket(countBucketMap.get(count), key);
            } else {
                changeKey(key, -1);
            }
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        return tail.pre == head? "": (String) tail.pre.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.keys.iterator().next();  
    }
    
    public void changeKey(String key, int offset) {
        int count = keyCountMap.get(key);
        keyCountMap.put(key, count+offset);
        
        Bucket curBucket = countBucketMap.get(count);
        curBucket.keys.remove(key);
        Bucket newBucket;
        
        if (countBucketMap.containsKey(count+offset)) {
            newBucket = countBucketMap.get(count+offset);
        } else {
            newBucket = new Bucket(count+offset);
            countBucketMap.put(count+offset, newBucket);
            addBucketAfter(newBucket, offset == 1? curBucket: curBucket.pre);
        }
        
        newBucket.keys.add(key);
        removeKeyFromBucket(curBucket, key);
    }
    
    public void addBucketAfter(Bucket newBucket, Bucket preBucket) {
        newBucket.pre = preBucket;
        newBucket.next = preBucket.next;
        preBucket.next.pre = newBucket;
        preBucket.next = newBucket;
    }
    
    public void removeKeyFromBucket(Bucket b, String key) {
        b.keys.remove(key);
        if (b.keys.size() == 0) {
            // rmeove the bucket
            removeBucketFromList(b);
            countBucketMap.remove(b.count);
        }
    }
    
    public void removeBucketFromList(Bucket b) {
        b.pre.next = b.next;
        b.next.pre = b.pre;
        b.next = null; //!!!
        b.pre = null; //!!
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
