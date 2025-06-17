package com.java_revision.multithreading;

public class SimulatedSequentialStep {

    public static void main(String[] args) throws InterruptedException {
        Runnable startBrowser = () -> {
            System.out.println("Start the browser");
            ThreadHelper.threadSleep(300);
        };

        Runnable login = () -> {
            System.out.println("Login to the application");
            ThreadHelper.threadSleep(200);
        };

        Runnable runTest = () -> {
            System.out.println("Running test cases");
            ThreadHelper.threadSleep(2000);
        };

        Thread t1 = new Thread(startBrowser);
        Thread t2 = new Thread(login);
        Thread t3 = new Thread(runTest);

        t1.start();
        t1.join();
        t2.start();
        t2.join();
        t3.start();
        t3.join();
    }
}
