package com.java_revision.multithreading;

public class LogProcessor {
    volatile boolean running = true;
    int i = 0;
    public void log() {
        System.out.println("LogProcessor started.");
        while (running) {
            i++;
        }
        System.out.println("LogProcessor stopped.");
    }

    public static void main(String[] args) throws InterruptedException {
        LogProcessor processor = new LogProcessor();
        Thread t1 = new Thread(processor::log);
        t1.start();
        ThreadHelper.threadSleep(3000);
        processor.running = false;
        t1.join();
    }
}
