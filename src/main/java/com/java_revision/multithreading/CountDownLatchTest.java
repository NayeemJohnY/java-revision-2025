package com.java_revision.multithreading;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        Runnable task = () -> {
            ThreadHelper.threadSleep(1000);
            System.out.println("Set up ready from " + Thread.currentThread().getName());
            countDownLatch.countDown();
        };

        Thread t1 = new Thread(task, "DB Setup");
        Thread t2 = new Thread(task, "Config Loader");
        Thread t3 = new Thread(task, "WebDriver Setup");

        t1.start();
        t2.start();
        t3.start();
        countDownLatch.await();
    }

}
