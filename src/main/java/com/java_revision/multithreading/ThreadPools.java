package com.java_revision.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPools {

    /*
     * Why Thread Pools?
     * Creating a new thread every time is expensive.
     * Thread pools reuse threads, reducing the cost of thread creation.
     * Helps in scalable, efficient parallel execution — perfect for test
     * automation, logging, parallel web scraping, etc.
     * 
     * | Concept | Description |
     * | ----------------- | --------------------------------------------- |
     * | `ExecutorService` | Main interface to manage thread execution. |
     * | `Executors` | Utility class to create various thread pools. |
     * | `submit()` | Submits a task (Runnable/Callable). |
     * | `shutdown()` | Gracefully shuts down the executor. |
     * | `shutdownNow()` | Forces immediate shutdown. |
     * 
     * 🔹 Common Thread Pool Types:
     * Executors.newFixedThreadPool(n); // Fixed number of threads
     * Executors.newCachedThreadPool(); // Grows/shrinks dynamically
     * Executors.newSingleThreadExecutor(); // Single thread only
     * 
     */
    public static void main(String[] args) {
        Runnable testCase = () -> {
            System.out.println("Running API Test - " + Thread.currentThread().getName());
            ThreadHelper.threadSleep(500); // Simulate test time
        };

        ExecutorService executor = Executors.newFixedThreadPool(3); // Simulate 3 parallel test runners

        for (int i = 1; i <= 10; i++) {
            executor.submit(testCase);
        }

        // executor.shutdown(); // No new tasks accepted

    }
}


class ParallelTestRunner {

    public static void main(String[] args) {
        // Step 1: Create ExecutorService with 2 threads
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Step 2: Define loginTest Runnable
        Runnable loginTest = () -> {
            System.out.println("Starting LoginTest on Thread "+ Thread.currentThread().getName());
            ThreadHelper.threadSleep(2000);
            System.out.println("Finished LoginTest on Thread "+ Thread.currentThread().getName());
        };

        // Step 3: Define searchTest Runnable
        Runnable searchTest = () -> {
            System.out.println("Starting searchTest on Thread "+ Thread.currentThread().getName());
            ThreadHelper.threadSleep(1000);
            System.out.println("Finished searchTest on Thread "+ Thread.currentThread().getName());
        };
        // Step 4: Define checkoutTest Runnable
        Runnable checkoutTest = () -> {
            System.out.println("Starting checkoutTest on Thread "+ Thread.currentThread().getName());
            ThreadHelper.threadSleep(3000);
            System.out.println("Finished checkoutTest on Thread "+ Thread.currentThread().getName());
        };
        // Step 5: Submit all tasks to ExecutorService
        executor.submit(loginTest);
        executor.submit(searchTest);
        executor.submit(checkoutTest);
        // Step 6: Shutdown the pool
        executor.shutdown();
    }
}
