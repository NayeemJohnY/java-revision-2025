package com.java_revision.multithreading;

public class PrintQueue {

    private String job = null;

    public synchronized void submitJob(String job) {
        while (this.job != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.job = job;
        System.out.println(" Job Submitted : " + this.job);
        notify();
    }

    public synchronized void printJob() {
        while (this.job == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" Job Printed : " + this.job);
        this.job = null;
        notify();
    }

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printQueue.submitJob("Document" + i);
                ThreadHelper.threadSleep(500);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                printQueue.printJob();
                ThreadHelper.threadSleep(800);
            }
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}
