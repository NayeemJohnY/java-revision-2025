package com.java_revision.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelTestExecutor {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable loginTest = () -> {
            runTest("loginTest");
        };
        Runnable searchTest = () -> {
            runTest("SearchTest");
        };

        Runnable filterTest = () -> {
           runTest("filterTest");
        };

        Runnable cartTest = () -> {
            runTest("cartTest");
        };

        Runnable checkoutTest = () -> {
            runTest("checkoutTest");
        };

        executorService.submit(loginTest);
        executorService.submit(searchTest);
        executorService.submit(filterTest);
        executorService.submit(cartTest);
        executorService.submit(checkoutTest);

        executorService.shutdown();
    }

    private static void runTest(String testName) {
        System.out.println("Starting " + testName + " on Thread: " + Thread.currentThread().getName());
        ThreadHelper.threadSleep(3000);
        System.out.println("Competed " + testName + " on Thread: " + Thread.currentThread().getName());
    }
}
