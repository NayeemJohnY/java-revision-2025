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
