package com.java_revision.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ParkingLotSystemSemaphore {

    static Semaphore parkingSpots = new Semaphore(5);
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is waiting to parking spot to available...");
            try {
                parkingSpots.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is occupied pardking spot...");
            ThreadHelper.threadSleep(3000);
            parkingSpots.release();
            System.out.println(Thread.currentThread().getName() + " released parking Spot...");
        };

        // for (int i = 1; i <= 10; i++) {
        //     new Thread(task, "Car-" + i).start();
        // }

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
           executorService.submit(task);
        }
        
        executorService.shutdown();
    }
}
