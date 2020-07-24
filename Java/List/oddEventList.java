package List;

public class oddEventList {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode currentOdd = head; 
        ListNode currentEven = head.next;
        ListNode startEven = currentEven;
        
        while (currentEven != null && currentEven.next != null) {
            currentOdd.next = currentEven.next;
            currentOdd = currentOdd.next;
            
            currentEven.next = currentOdd.next;
            currentEven = currentEven.next;
        }
        
        currentOdd.next = startEven;
        
        return head;
    }
    
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val+"->");
            head = head.next;
        }
    }
    
    public static void main(String[] args) {
        oddEventList oe = new oddEventList();
        ListNode head=new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        
        ListNode newHead = oe.oddEvenList(head);
        oe.printList(newHead);
    }
}
