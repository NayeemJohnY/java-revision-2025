package com.java_revision.multithreading;

import java.util.concurrent.CopyOnWriteArrayList;

public class MultithreadedNotificationQueue {

    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> notificationList = new CopyOnWriteArrayList<>();
        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                String str = "Adding Notification " + i + " by " + Thread.currentThread().getName();
                System.out.println(str);
                notificationList.add(str);
            }
        };

        Runnable consumer = () -> {
            ThreadHelper.threadSleep(1000);
            for (String notification : notificationList) {
                System.out.println(Thread.currentThread().getName() + " Read Notification " + notification);
            }
        };

        Thread t1 = new Thread(producer, "Producer 1");
        Thread t2 = new Thread(consumer, "Consumer 1");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
