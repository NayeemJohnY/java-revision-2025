package com.java_revision.multithreading;

import java.util.concurrent.*;
import java.util.*;

public class CallableTestExecutor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> testCases = Arrays.asList(
                createTest("LoginTest"),
                createTest("SearchTest"),
                createTest("FilterTest"),
                createTest("CartTest"),
                createTest("CheckoutTest"));

        List<Future<String>> results = new ArrayList<>();

        // Submit all test cases
        for (Callable<String> testCase : testCases) {
            results.add(executor.submit(testCase));
        }

        // Collect and print results
        for (Future<String> future : results) {
            System.out.println("✅ " + future.get()); // blocks until result is ready
        }

        executor.shutdown();
    }

    // Factory method to create Callable test tasks
    private static Callable<String> createTest(String testName) {
        return () -> {
            System.out.println("🔄 Starting " + testName + " on Thread: " + Thread.currentThread().getName());
            ThreadHelper.threadSleep(2000); // simulate execution
            System.out.println("✅ Completed " + testName + " on Thread: " + Thread.currentThread().getName());
            return testName + " Passed"; // return result string
        };
    }
}
