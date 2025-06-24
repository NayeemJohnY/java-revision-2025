package com.java_revision.multithreading;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockSharedCounter {

    int counter = 0;
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void readCounter() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read: " + counter);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeCounter() {
        rwLock.writeLock().lock();
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " write: " + counter);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockSharedCounter sharedCounter = new ReentrantLockSharedCounter();
        Runnable readTask = () -> {
            for (int i = 0; i < 10; i++) {
                sharedCounter.readCounter();
            }
        };

        Runnable writeTask = () -> {
            for (int i = 0; i < 10; i++) {
                sharedCounter.writeCounter();
            }
        };
        Thread t1 = new Thread(readTask, "ReadThread1");
        Thread t2 = new Thread(readTask, "ReadThread2");
        Thread t3 = new Thread(readTask, "ReadThread3");
        Thread t4 = new Thread(writeTask, "WriteThread1");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
