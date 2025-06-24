package com.java_revision.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureCompletionService {

    /*
     * | Concept | Description |
     * | ------------------- |
     * -----------------------------------------------------------------------------
     * -------- |
     * | `Callable<T>` | Like `Runnable` but returns a value and can throw a checked
     * exception. |
     * | `Future<T>` | Represents the result of an asynchronous computation. |
     * | `CompletionService` | Helps manage multiple `Callable` tasks and retrieve
     * completed results as they finish. |
     * 
     * 
     * ✅ Why Use Them?
     * Callable is ideal when a thread needs to return a result (e.g., test status,
     * scraped data).
     * 
     * Future allows:
     * Waiting for result with .get()
     * Checking if the task is done with .isDone()
     * Cancelling the task
     * 
     * CompletionService improves management when multiple results are expected
     * asynchronously — you can retrieve completed ones in order of completion, not
     * submission.
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        Callable<String> callApi1 = () -> {
            Thread.sleep(2000); // Simulates delay
            return "API-1 Response";
        };

        Callable<String> callApi2 = () -> {
            Thread.sleep(1000); // Faster
            return "API-2 Response";
        };

        Callable<String> callApi3 = () -> {
            Thread.sleep(1500);
            return "API-3 Response";
        };

        service.submit(callApi1);
        service.submit(callApi2);
        service.submit(callApi3);

        for (int i = 0; i < 3; i++) {
            Future<String> result = service.take(); // Returns as they complete
            System.out.println("Received: " + result.get());
        }

        executor.shutdown();
    }
}
