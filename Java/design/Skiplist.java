package design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Very elegant DS!!!
 * Things worth reading: https://leetcode.com/problems/design-skiplist/discuss/393499/Java-Solution-beats-100
 * https://www.youtube.com/watch?v=7pWkspmYUVo&feature=youtu.be
 * @author Katherine
 *
 */
public class Skiplist {

    private static final double DEFAULT_PROB = 0.5;
    private final Random rand = new Random();
    
    // Head sentinels of each level
    // Consider them as a dummy head
    private final List<Node> sentinels = new ArrayList<>();
    {
       sentinels.add(new Node(Integer.MIN_VALUE));
    }

    private class Node {
        private int value;
        private Node right;
        private Node left;
        private Node down;
        private Node up;
        
        public Node(int val) {
            this.value = val;
        }
    }
    
    private boolean flipCoin() {
        return rand.nextDouble() < DEFAULT_PROB;
    }
    
    private Node getSentinel() {
        return sentinels.get(sentinels.size() - 1);
    }
    
    // find the node in front of the current target
    private Node getSmallerOrEquals(int target) {
        Node cur = getSentinel();
        while (cur != null) {
            if (cur.right == null || cur.right.value > target)
                if(cur.down == null)
                    break; // found
                else
                    cur = cur.down;
            else
                cur = cur.right;
        }

        return cur;
    }
    
    private void append(Node prev, Node cur) {
        Node next = prev.right;
        prev.right = cur;
        cur.left = prev;

        if (next != null) {
            next.left = cur;
            cur.right = next;
        }
    }
    
    /** Populate Level up is the to find the left most recent node that goes up
    * Then insert after that
    * Do the same, until flipCoin says stop.
    * Note: special case with no Sentinel for the level yet
    */
    private void populateLevelUp(final Node toInsert) {
        Node curPrev = toInsert.left, cur = toInsert;

        while (flipCoin()) {
            while (curPrev.left != null && curPrev.up == null) {
                curPrev = curPrev.left;
            }
            
            // Needs new Sentinel
            if (curPrev.up == null) {
                Node newSentinel = new Node(Integer.MIN_VALUE);
                curPrev.up = newSentinel;
                newSentinel.down = curPrev;
                
                sentinels.add(newSentinel);
            }
            
            curPrev = curPrev.up;
            Node newToInsert = new Node(cur.value);
            cur.up = newToInsert;
            newToInsert.down = cur;
            cur = cur.up;
            
            cur.left = curPrev;
            curPrev.right = cur;
        }
    }
    
    public Skiplist() {
        
    }
    
    public boolean search(int target) {
        Node prev = getSmallerOrEquals(target);
        return prev.value == target;
    }
    
    public void add(int num) {
        Node prev = getSmallerOrEquals(num);
        Node cur = new Node(num);
        append(prev, cur);
        populateLevelUp(cur);
    }
    
    public boolean erase(int num) {
        // Check if exists
        Node smallerOrEqual = getSmallerOrEquals(num);
        if (smallerOrEqual.value != num) return false;
        
        Node toRemove = smallerOrEqual;
        
        // Remove all the way levels up
        while (toRemove != null) {
            Node prev = toRemove.left; Node next = toRemove.right;
            prev.right = next;
            if (next != null) {
                next.left  = prev;
            }
            
            toRemove = toRemove.up;
        }
        return true;
    }
}
