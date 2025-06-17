package com.java_revision.multithreading;

// Task 1 - Thread subclass
class FileDownloader extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            ThreadHelper.threadSleep(150);
            System.out.println("Downloading file... " + Thread.currentThread().getName());
        }
    }
}

// Task 2 - Runnable implementation
class DocumentIndexer implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            ThreadHelper.threadSleep(150);
            System.out.println("Indexing document... " + Thread.currentThread().getName());
        }
    }
}

public class ParallelTaskRunner {
    public static void main(String[] args) {
        // Task 3 - Lambda
        Runnable emailNotifier = () -> {
           for (int i = 0; i < 5; i++) {
            ThreadHelper.threadSleep(150);
            System.out.println("Sending email... " + Thread.currentThread().getName());
        }
        };

        Thread t1 = new FileDownloader();
        t1.setName("Downloader");
        Thread t2 = new Thread(new DocumentIndexer(), "Indexer");
        Thread t3 = new Thread(emailNotifier, "Notifier");

        t1.start();
        t2.start();
        t3.start();

    }
}
