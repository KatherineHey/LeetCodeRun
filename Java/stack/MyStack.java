package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. Implement Stack using Queues
 * @author Katherine
 *
 */

public class MyStack {
    Queue<Integer> q = new LinkedList<Integer>();
    int top = 0;
    
    /** Initialize your data structure here. */
    public MyStack() {
        
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q.add(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = q.size();
        while (size > 2) {
            q.add(q.poll());
            size--;
        }
        
        top = q.peek();
        q.add(q.poll());
        
        return (int) q.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
    
    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);  
        System.out.println(s.top());   // returns 2
        System.out.println(s.pop());   // returns 2
        System.out.println(s.empty()); // returns false
    }
}
