package com.java_revision.multithreading;

public class Warehouse {
    private boolean hasBox = false;

    public synchronized void addBox() {
        // synchronized + while loop
        while (hasBox) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasBox = true;
        System.out.println(Thread.currentThread().getName() + " added a box.");
        notifyAll();
    }

    public synchronized void removeBox() {
        while (!hasBox) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hasBox = false;
        System.out.println(Thread.currentThread().getName() + " removed a box.");
        notifyAll();
    }

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                warehouse.addBox();
                ThreadHelper.threadSleep(500);
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                warehouse.removeBox();
                ThreadHelper.threadSleep(800);
            }
        };

        Thread prodcuerThread = new Thread(producer, "Producer");
        Thread consumerThread1 = new Thread(consumer, "Consumer1");
        Thread consumerThread2 = new Thread(consumer, "Consumer2");
        Thread consumerThread3 = new Thread(consumer, "Consumer3");
        Thread consumerThread4 = new Thread(consumer, "Consumer4");

        prodcuerThread.start();
        consumerThread1.start();
        consumerThread2.start();
        consumerThread3.start();
        consumerThread4.start();

        try {
            prodcuerThread.join();
            consumerThread1.join();
            consumerThread2.join();
            consumerThread3.join();
            consumerThread4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
