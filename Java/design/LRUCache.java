package design;

import java.util.HashMap;

public class LRUCache {
    int Capacity;
    DoubleLinkedNode head; // dummy head to keep track of the real head easily
    DoubleLinkedNode tail; // dummy tail to keep track of the border easily
    HashMap<Integer, DoubleLinkedNode> map = new HashMap<Integer, DoubleLinkedNode>();
    
    class DoubleLinkedNode {
        int key;
        int value;
        DoubleLinkedNode pre;
        DoubleLinkedNode next;
    }
    
    /**
     * Add a node to the current double linked list
     * Always add the given node to the position after the dummy head
     * @param cur
     */
    public void addNode(DoubleLinkedNode node) {
        node.next = this.head.next;
        node.pre = this.head;
        this.head.next.pre = node; //****
        this.head.next = node;
    }
    
    /**
     * Remove a node from middle of the double linked list
     * @param node
     */
    public void removeNode(DoubleLinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void putToHead(DoubleLinkedNode node) {
        removeNode(node);
        addNode(node);
    }
    
    /**
     * Remove the current real tail, not dummy tail
     * And return the current real tail
     * @param node
     */
    public DoubleLinkedNode removeTail() {
        DoubleLinkedNode beforeTail = this.tail.pre;
        removeNode(beforeTail);
        return beforeTail;
    }
    
    public LRUCache(int capacity) {
        this.Capacity = capacity;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.pre = null;
        head.next = tail;
        tail.next = null;
        tail.pre = head;
    }

    public int get(int key) {
        int value = -1;
        if (map.containsKey(key)) {
            value = map.get(key).value;
            putToHead(map.get(key));
        }
        
        return value;
    }

    public void put(int key, int value) {
        DoubleLinkedNode node = map.getOrDefault(key, null);
        
        if (node == null) {
            DoubleLinkedNode newNode = new DoubleLinkedNode();
            
            newNode.key  = key;
            newNode.value = value;
            
            map.put(key, newNode);
            addNode(newNode);

            
            if (map.size() > this.Capacity) {
                DoubleLinkedNode tail = removeTail();
                map.remove(tail.key);
            }
        } else {
            // update the value if the key exists
            node.value = value;
            putToHead(node);
        }
    }
    
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(2, 2);
        System.out.println(cache.get(2));
        cache.put(1, 1);
        cache.put(4, 1);
        System.out.println(cache.get(2));
    }
}
