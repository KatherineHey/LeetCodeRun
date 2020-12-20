package multithread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.IntConsumer;

/**
 * 1195. Fizz Buzz Multithreaded
 * https://leetcode.com/problems/fizz-buzz-multithreaded/discuss/385395/Java-using-synchronized-with-short-explanation.
 * @author 
 *
 */

public class FizzBuzz {
    private int n;
    private int currentNumber = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 != 0 || currentNumber % 5 == 0) {
                wait();
                continue;
            }
            
            printFizz.run();
            currentNumber++;
            notifyAll();
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 || currentNumber % 5 != 0) {
                wait();
                continue;
            }
            printBuzz.run();
            currentNumber++;
            notifyAll();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 15 != 0) {
                wait();
                continue;
            }
            printFizzBuzz.run();
            currentNumber++;
            notifyAll();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            if (currentNumber % 3 == 0 || currentNumber %5 == 0) {
                wait();
                continue;
            }
            printNumber.accept(currentNumber);
            currentNumber++;
            notifyAll();
        }
    }
    
    public static void main(String[] args) {
        Queue<String> strings = new LinkedBlockingQueue<>();

        FizzBuzz fizzBuzz = new FizzBuzz(16);

        Thread t1, t2, t3, t4;

        (t1 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> strings.add("fizz"));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
                Thread.currentThread().interrupt();
            }
        })).start();

        (t2 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> strings.add("buzz"));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
                Thread.currentThread().interrupt();
            }
        })).start();

        (t3 = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> strings.add("fizzbuzz"));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
                Thread.currentThread().interrupt();
            }
        })).start();

        (t4 = new Thread(() -> {
            try {
                fizzBuzz.number(number -> strings.add("" + number));
            } catch (InterruptedException e) {
                System.err.println(e.toString());
                Thread.currentThread().interrupt();
            }
        })).start();

        try {
            t4.join();
            t3.join();
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
