package com.java_revision.multithreading;

public interface ThreadHelper {
    public static void threadSleep(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
