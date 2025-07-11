package com.java_revision.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlocingQueueLogger {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> logs = new LinkedBlockingDeque<>();

        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    logs.put("Log Message " + i + " by Thread " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable consumer = () -> {
            ThreadHelper.threadSleep(5000);
            while (true) {
                try {
                    String log = logs.take();
                    if (log.equals("STOP"))
                        break;
                    System.out.println(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(producer, "P1");
        Thread t2 = new Thread(producer, "P2");
        Thread t3 = new Thread(producer, "P3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        logs.put("STOP");
        Thread t4 = new Thread(consumer, "C1");
        t4.start();
        t4.join();
    }
}
