package queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonicQueue {
    // 单调递减
    private Deque<Integer> monotonicQueue = new ArrayDeque<Integer>();
    
    public void push(int n) {
        while (!monotonicQueue.isEmpty() && monotonicQueue.getLast() < n)
            monotonicQueue.removeLast();
        
        monotonicQueue.addLast(n);
    }
    
    public void pop(int n) {
        if (!monotonicQueue.isEmpty() && monotonicQueue.getFirst() == n) {
            monotonicQueue.poll();
        }
    }
    
    public int max() {
        return monotonicQueue.getFirst();
    }
    
    public static void main(String[] args) {
        MonotonicQueue mq = new MonotonicQueue();
        mq.push(3);
        mq.push(-1);
        mq.push(1);
        System.out.println(mq.max());
        mq.pop(3);
        System.out.println(mq.max());
    }
}
