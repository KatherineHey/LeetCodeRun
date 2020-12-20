package List;
class AddTwoNumbersII {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL1 = reverseList(l1);
        ListNode newL2 = reverseList(l2);
        ListNode newHead = mergeTwoLists(newL1, newL2);
        return reverseList(newHead);
    }
    
    // reverse the list, return the new head of the list
    public ListNode reverseList(ListNode l) {
        ListNode prev = null;
        ListNode cur = l;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
            
        return prev;
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode cur= new ListNode(-1);
        ListNode newDummyHead = cur;
        int carry = 0;
        
        while (l1 != null && l2 != null) {
            int total = l1.val + l2.val + carry;
            carry = total / 10;
            cur.next = new ListNode(total - 10 * carry);
            
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        while (l1 != null) {
            int total = l1.val + carry;
            carry = total / 10;
            cur.next = new ListNode(total - 10 * carry, null);
            
            cur = cur.next;
            l1 = l1.next;
        }
        
        while (l2 != null) {
            int total = l2.val + carry;
            carry = total / 10;
            cur.next = new ListNode(total - 10 * carry, null);
            
            cur = cur.next;
            l2 = l2.next;
        }
        
        return newDummyHead.next;
    }
    
    public static void main(String[] args) {
        AddTwoNumbersII at = new AddTwoNumbersII();
        ListNode l3 = at.new ListNode(3); l3.next = null;
        ListNode l1 = at.new ListNode(4, l3);
        ListNode l2 = at.new ListNode(4);
        
        at.addTwoNumbers(l1, l2);
    }
}
