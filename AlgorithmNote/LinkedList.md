##### Insertion
Insert after the i th element

i = 0
```
newNode.next = first
first = newNode
```

i = 3
```
before = first.next.next;
newNode.next = before.next;
before.next = newNode;
```

##### Deletion of Nodes
- [x] 237. Delete Node in a Linked List
```java
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next ;
    }
```

##### Reverse Linked List
Iterative
```java
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        
        // Reverse the list (pre will be head of reversed list)
        ListNode cur = head.next;
        ListNode pre = head;
        pre.next = null;
        while (cur != null) {
            ListNode nextTmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTmp;
        }
        
        return pre;
    }
```

Recursion
```java
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
```

##### Circular Linked List
Josephus

- [x] 1823. Find the Winner of the Circular Game
```java
    public int findTheWinner(int n, int k) {
        // initialize a circular linked list
        ListNode n1 = new ListNode(1);
        ListNode pre = n1;
        for (int i = 2; i <= n; i++) {
            ListNode tmp = new ListNode(i);
            pre.next = tmp;        
            pre = tmp;
        }
        
        pre.next = n1;
        
        ListNode cur = n1;
        int interval = k-1;
        while (cur.next != cur) {
            while (interval > 0) {
                cur = cur.next;
                pre = pre.next;
                interval--;
            }
            
            // remove cur
            pre.next = cur.next;
            cur = cur.next;
            interval = k-1;
        }
        
        return cur.val;
    }   
```

##### Doubly Linked List

##### Floyd's Cycle Detection Algorithm
```
Pseudocode:

    Initialize two-pointers and start traversing the linked list.
    Move the slow pointer by one position.
    Move the fast pointer by two positions.
    If both pointers meet at some point then a loop exists and if the fast pointer meets 
    the end position then no loop exists.

once the tortoise and hare pointers meet for the first time, the start of the cycle can be determined 
by moving tortoise pointer 
back to start and then moving both tortoise and hare one step at a time. 
The point where they first meet is the start of the cycle.
```

![image](https://user-images.githubusercontent.com/62370578/180663967-8c26d835-1600-45b1-b4e5-c0a3c6c7d40d.png)

- [x] 142. Linked List Cycle II
```java
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) 
            return null;
        
        ListNode slow = head, fast = head, start = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                while (start != fast) {
                    start = start.next;
                    fast = fast.next;
                }
                
                return start;
            }
        }
        
        return null;
    }
```

##### LRU Cache
```java
class LRUCache {
    int Capacity;
    DoubleLinkedListNode Prehead; // the dummy node before head
    DoubleLinkedListNode Tail; // the dummy tail node
    HashMap<Integer, DoubleLinkedListNode> Map;
    
    class DoubleLinkedListNode {
        int key;
        int value;
        DoubleLinkedListNode pre;
        DoubleLinkedListNode next;
        
        public DoubleLinkedListNode(int k, int v) {
            key = k;
            value = v;
        }
        
        public DoubleLinkedListNode() {
        }
    }
    
        
    /**
     * Add a node to the current double linked list
     * Always add the given node to the position after the dummy head
     * @param cur
     */
    public void addNode(DoubleLinkedListNode node) {
        DoubleLinkedListNode curHead = Prehead.next;
        node.next = curHead;
        node.pre = Prehead;
        curHead.pre = node; //****
        Prehead.next = node;
    }
    
    public void putToHead(DoubleLinkedListNode cur) {
        removeNode(cur);
        addNode(cur);
    }
    
    /**
     * Remove a node from middle of the double linked list
     * @param node
     */
    public void removeNode(DoubleLinkedListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    
    public void removeTail(){
        // Remove the real tail, as in the one ahead of dummy tail
        // Remove tail from double linked list
        Tail.pre.next = null;
        Tail = Tail.pre;  
        
        // Remove real tail from hashmap
        Map.remove(Tail.key); 
    }
    
    public LRUCache(int capacity) {
        Capacity = capacity;
        Map = new HashMap<>();
        Prehead = new DoubleLinkedListNode();
        Tail = new DoubleLinkedListNode();
        Prehead.pre = null; Prehead.next = Tail;
        Tail.pre = Prehead; Tail.next = null;
    }
    
    public int get(int key) {
        if (!Map.containsKey(key)) return -1;
        
        DoubleLinkedListNode cur = Map.get(key);
        putToHead(cur);
        
        return cur.value;
    }
    
    public void put(int key, int value) {
        DoubleLinkedListNode cur;
        if (!Map.containsKey(key)) {
            cur = new DoubleLinkedListNode(key, value);
            
            if (Map.size() >= Capacity) {
                removeTail();
            }
            
            addNode(cur);
            
            Map.put(key, cur);
        } else {
            cur = Map.get(key);
            cur.value = value;
            
            putToHead(cur);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

##### Copy list with random pointer
```java
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        
        Node current = head;
        HashMap<Node,Node> oldNewMap = new HashMap<Node, Node>();
        Node newCurrent = new Node(-1);
        
        // loop through 1st round to get all the nodes
        while (current != null) {
            newCurrent.next = new Node(current.val);
            newCurrent = newCurrent.next;
            oldNewMap.put(current, newCurrent);
            current = current.next;
        }
        
        // loop through second round to get all the random set up
        current = head;
        while (current != null) {
            oldNewMap.get(current).random = oldNewMap.getOrDefault(current.random, null);
            current = current.next;
        }
        
        return oldNewMap.get(head);
    }
```

##### Sort list
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // Step1: Get the head of the list and head of the middle of the list
        ListNode slow = head; // slow will be the head of the middle of the list
        ListNode fast = head;
        ListNode pre = null;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }
        
        pre.next = null;
        
        // Step 2: sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        return mergeTwoLists(l1, l2);
    }
    
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode tail = dummyHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            
            tail = tail.next;
            
        }
        
        tail.next = (list1 != null) ? list1 : list2;
        return dummyHead.next;
    }
}
```
