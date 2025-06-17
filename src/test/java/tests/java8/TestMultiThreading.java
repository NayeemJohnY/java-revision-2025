package tests.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.testng.annotations.Test;

class NumberPrinter {
    int num = 1;
    private final int MAX = 10;

    public synchronized void printEven() {
        while (num < MAX) {
            if (num % 2 == 0) {
                System.out.println("Thread " + Thread.currentThread().getName() + " - num: " + num);
                num++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printOdd() {
        while (num < MAX) {
            if (num % 2 != 0) {
                System.out.println("Thread " + Thread.currentThread().getName() + " - num: " + num);
                num++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class TestMultiThreading {

    // 🟢 Beginner (1–10)
    // 1. Print Numbers with Two Threads
    // Hint: Use two threads to print even and odd numbers alternately. Use
    // wait()/notify().
    @Test
    public void testEvenAndOddPrinting() {
        NumberPrinter numberPrinter = new NumberPrinter();
        Thread t2 = new Thread(numberPrinter::printOdd, "Odd");
        Thread t1 = new Thread(numberPrinter::printEven, "Even");

        t1.start();
        t2.start();
    }

    // 2. Thread Creation in Three Ways
    // Hint: Implement using Thread, Runnable, and ExecutorService.
    @Test
    public void testUsingThreadClass() {
        Thread t1 = new Thread(() -> System.out.println("Using ThreadClass"));
        t1.start();
    }

    @Test
    public void testUsingRunnableInterface() {
        Runnable runnable = () -> System.out.println("Using Runnable Interface");
        runnable.run();
    }

    @Test
    public void testUsingExecutorService() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("Using Executor Service"));
    }

    public void runWithDelay(int delayInMills) {
        try {
            Thread.sleep(delayInMills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "Thread - i: " + i);
        }
    }

    // 3. Thread Sleep Example
    // 4. Thread Join Example
    // Hint: Demonstrate how Thread.sleep() pauses a thread.
    // Hint: Wait for one thread to finish using join().
    @Test
    public void testRunWithDelay() {
        Thread t1 = new Thread(() -> runWithDelay(0), "Without Delay");
        Thread t2 = new Thread(() -> runWithDelay(3000), "With Delay");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 5. Thread Priorities
    // Hint: Set thread priorities and observe results.
    @Test
    public void testRunPriorityThreads() {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " is executing iteration " + i);
                try {
                    Thread.sleep(1000 * i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread priority1Thread = new Thread(task, "Priority9");
        Thread priority2Thread = new Thread(task, "Priority10");

        priority1Thread.setPriority(5);
        priority2Thread.setPriority(10);

        priority1Thread.start();
        priority2Thread.start();

        try {
            priority1Thread.join();
            priority2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void printMessage() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - printing message " + i);
            try {
                Thread.sleep(100); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 6. Synchronized Method Access
    // Hint: Ensure one thread accesses the method at a time.
    @Test
    public void testPrintMessageConcurrently() {
        Thread t1 = new Thread(this::printMessage, "Thread1");
        Thread t2 = new Thread(this::printMessage, "Thread2");
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void printMessageBlockSynchroized(List<Integer> list, boolean isDivisbleBy3) {
        for (int i = 0; i < 10; i++) {

            synchronized (this) {
                if ((i % 3 == 0) == isDivisbleBy3) {
                    list.add(33333);
                    System.out.println(Thread.currentThread().getName() + " :  is adding: " + 33333);
                } else {
                    list.add(i);
                    System.out.println(Thread.currentThread().getName() + " :  is adding " + i);
                }
            }

        }
    }

    // 7. Synchronized Block Access
    // Hint: Use synchronized(this) instead of synchronizing the whole method.
    @Test
    public void testPrintMessageWithBlockSync() {
        List<Integer> list = new ArrayList<>();
        Thread t1 = new Thread(() -> printMessageBlockSynchroized(list, false), "OddThread");
        Thread t2 = new Thread(() -> printMessageBlockSynchroized(list, true), "EvenThread");
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        System.out.println(list);
    }

    class RaceCondition {
        int count = 0;

        public void incrementCounter() {
            while (count < 10) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                System.out.println(Thread.currentThread().getName() + " incremented to " + count);
            }
        }
    }

    // 8. Race Condition Simulation
    // Hint: Simulate counter update with and without synchronization.
    @Test
    public void testIncrementCounter() {
        RaceCondition raceCondition = new RaceCondition();
        Thread t1 = new Thread(raceCondition::incrementCounter, "Thread1");
        Thread t2 = new Thread(raceCondition::incrementCounter, "Thread2");
        Thread t3 = new Thread(raceCondition::incrementCounter, "Thread3");
        Thread t4 = new Thread(raceCondition::incrementCounter, "Thread4");
        t2.start();
        t1.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void print(String message) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + message + " " + i);
            try {
                Thread.sleep(100); // simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 9. Multiple Threads Printing Shared Resource
    // Hint: Create shared resource accessed by multiple threads.
    @Test
    public void testMultipleThreadsPrint() {
        Runnable task1 = () -> print("Message from Thread A");
        Runnable task2 = () -> print("Message from Thread B");
        Runnable task3 = () -> print("Message from Thread C");
        Thread t1 = new Thread(task1, "Thread-A");
        Thread t2 = new Thread(task2, "Thread-B");
        Thread t3 = new Thread(task3, "Thread-C");
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads completed.");
    }

    Object resourceA = new Object();
    Object resourceB = new Object();

    public void task1() {
        synchronized (resourceA) {
            System.out.println(Thread.currentThread().getName() + " locked Resource A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            synchronized (resourceB) {
                System.out.println(Thread.currentThread().getName() + " locked Resource B");
            }
        }
    }

    public void task2() {
        synchronized (resourceB) {
            System.out.println(Thread.currentThread().getName() + " locked Resource B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            synchronized (resourceA) {
                System.out.println(Thread.currentThread().getName() + " locked Resource A");
            }
        }
    }

    // 10. Deadlock Simulation
    // Hint: Simulate two threads waiting for each other’s lock.
    @Test
    public void testDeadlockSimulation() {
        Thread t1 = new Thread(this::task1, "Thread1");
        Thread t2 = new Thread(this::task2, "Thread2");
        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 🟡 Intermediate (11–20)
    // 11. Producer Consumer (Using wait/notify)
    // Hint: Implement bounded buffer problem.
    class ProducerConsumer {
        private static final int CAPACITY = 10;
        LinkedList<Integer> buffer = new LinkedList<>();

        public synchronized void produce(int iteration) {
            System.out.println(Thread.currentThread().getName() + " : iteration :" + iteration);
            while (buffer.size() == CAPACITY) {
                try {
                    System.out.println("Buffer is Full");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            buffer.add(iteration);
            System.out.println("Produced value in Buffer: " + buffer);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll();
        }

        public synchronized void consume(int iteration) {
            System.out.println(Thread.currentThread().getName() + " : iteration :" + iteration);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (buffer.isEmpty()) {
                try {
                    System.out.println("Buffer is Empty");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consumed value from Buffer: " + buffer.pop());
            System.out.println("Buffer After Consume : " + buffer);
            notifyAll();
        }
    }

    @Test
    public void testProduceAndConsume() {
        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                producerConsumer.produce(i);
            }
        }, "ProducerThread");

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                producerConsumer.consume(i);
            }
        }, "ConsumerThread");

        consumerThread.start();
        producerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 12. Thread Pool Example
    // Hint: Use ExecutorService and fixed thread pool.
    @Test
    public void testExecuteTasksWithPool() {
        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " iterator: " + i);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executor.submit(task);
        }
        executor.shutdown();
    }

    // 13. Print in Order (3 Threads)
    // Hint: Use wait()/notify() to ensure threads print in order.
    private int state = 0; // 0 = A, 1 = B, 2 = C
    private final int cycles = 10;

    public synchronized void print(int threadId) {
        for (int i = 0; i < cycles; i++) {
            while (state % 3 != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName() + " iterator: " + i);
            state++;
            notifyAll();
        }
    }

    @Test
    public void testExecutionOrder() {
        Thread t1 = new Thread(() -> this.print(0), "Thread-A");
        Thread t2 = new Thread(() -> this.print(1), "Thread-B");
        Thread t3 = new Thread(() -> this.print(2), "Thread-C");
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runWithLock(){
        try{

        }
        finally{

        }
    }

    // 14. Reentrant Lock Usage
    // Hint: Use ReentrantLock to guard critical section.
    @Test
    public void testRunWithLock() {

        ReentrantLock reentrantLock = new ReentrantLock();
    }

}
