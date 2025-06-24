package com.java_revision.multithreading;

import java.util.concurrent.locks.ReentrantLock;

public class TicketBookingSystem {

    private int ticketsWithSync = 10;
    private int ticketsWithLock = 10;

    public synchronized void synchronizedBookTicket(String userName) {
        while (ticketsWithSync == 0) {
            try {
                System.out.println("User " + userName + " Not Tickets Left");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ticketsWithSync--;
        System.out.println("User " + userName + " Booked ticket with Thread  Tickets left : " + ticketsWithSync);
        ThreadHelper.threadSleep(200);
        notifyAll();
    }

    ReentrantLock reentrantLock = new ReentrantLock();

    public void reetrantLockBookTicket(String userName) {
        if (ticketsWithLock == 0) {
            System.out.println("User " + userName + " Not Tickets Left");
            return;
        } else {
            reentrantLock.lock();
            try {
                ticketsWithLock--;
                System.out
                        .println("User " + userName + " Booked ticket with Thread  Tickets left : " + ticketsWithLock);
                ThreadHelper.threadSleep(200);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TicketBookingSystem ticketBookingSystem = new TicketBookingSystem();
        Runnable synTask = () -> {
            for (int i = 65; i < 76; i++) {
                ticketBookingSystem.synchronizedBookTicket(String.valueOf((char) i));
            }
        };

        Runnable reetrantTask = () -> {
            for (int i = 65; i < 76; i++) {
                ticketBookingSystem.reetrantLockBookTicket(String.valueOf((char) i));
            }
        };

        Thread t1 = new Thread(synTask, "SynThread");
        Thread t2 = new Thread(reetrantTask, "ReetrantThread");

        t1.start();
        t2.start();
    }
}
