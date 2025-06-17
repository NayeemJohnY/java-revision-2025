package com.java_revision.multithreading;

public class LogWriterMonitor {

    public synchronized void write(String message) {
        System.out.println("🖊️  " + Thread.currentThread().getName() + " is writing: " + message);
        ThreadHelper.threadSleep(2000); // Simulate delay
        System.out.println("✅ " + Thread.currentThread().getName() + " done writing.");
    }

    public static void main(String[] args) {
        LogWriterMonitor logWriterMonitor = new LogWriterMonitor();
        Thread threadA = new Thread(() -> logWriterMonitor.write("I am threadA Message"), "ThreadA");
        Thread threadB = new Thread(() -> logWriterMonitor.write("I am threadB Message"), "ThreadB");
        Thread threadC = new Thread(() -> logWriterMonitor.write("I am threadC Message"), "ThreadC");

        Thread mointerThread = new Thread(() -> {
            while (true) {
                System.out.println("States -> A: " + threadA.getState() +
                        " | B: " + threadB.getState() +
                        " | C: " + threadC.getState());

                if (threadA.getState() == Thread.State.TERMINATED && threadB.getState() == Thread.State.TERMINATED
                        && threadC.getState() == Thread.State.TERMINATED) {
                    System.out.println("All Threads Completed");
                    break;
                }
                ThreadHelper.threadSleep(500);
            }
        }, "MonitorThread");

        threadA.start();
        threadB.start();
        threadC.start();
        mointerThread.start();

    }
}
