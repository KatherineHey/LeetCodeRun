package List;
/**
 * 206. Reverse Linked List
 * @author Katherine
 *
 */
public class Reverse {
    ListNode successor = null;
    
    public ListNode reverse(ListNode head) {
        if (head.next == null) return head;
        
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        
        ListNode last = reverseN(head.next, n-1);
        
        head.next.next = head;
        head.next = successor;
        return last;
    }
    
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }
}
