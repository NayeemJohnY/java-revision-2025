package com.java_revision.multithreading;

public class LogPrioritySystem {

    public static void main(String[] args) {
        Runnable criticalLogger = () -> {
           for (int i = 0; i < 20; i++) {
             System.out.println("Critical Log form Thread " + Thread.currentThread().getName());
             ThreadHelper.threadSleep(100);
           }
        };

        Runnable warningLogger = () -> {
           for (int i = 0; i < 20; i++) {
             System.out.println("Warning Log form Thread " + Thread.currentThread().getName());
             ThreadHelper.threadSleep(100);
           }
        };

        Runnable infoLogger = () -> {
           for (int i = 0; i < 20; i++) {
             System.out.println("Info Log form Thread " + Thread.currentThread().getName());
             ThreadHelper.threadSleep(100);
           }
        };

        Thread criticalThread = new Thread(criticalLogger, "CriticalLogger");
        Thread warningThread = new Thread(warningLogger, "WarningLogger");
        Thread infoThread = new Thread(infoLogger, "InfoLogger");

        // Set appropriate priorities here
        // criticalThread.setPriority(Thread.MAX_PRIORITY);
        // warningThread.setPriority(Thread.NORM_PRIORITY);
        // infoThread.setPriority(Thread.MIN_PRIORITY);
        // Start all 3 threads
        warningThread.start();
        infoThread.start();
        criticalThread.start();
    }
}

