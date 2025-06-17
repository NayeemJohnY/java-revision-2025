package com.java_revision.multithreading;

public class BankAccount {

    private int balance = 1000;

    public void withdraw(String threadName, int amount) {
        synchronized (this) {
            if (balance >= amount) {
                System.out.println(threadName + " trying to withdraw $" + amount);
                ThreadHelper.threadSleep(1000);
                balance -= amount;
                System.out.println(threadName + " completed withdrawal. Remaining balance: $" + balance);
            } else {
                System.out.println(threadName + " insufficient funds. Current balance: $" + balance);
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount();

        Runnable task1 = () -> account.withdraw("User-A", 700);
        Runnable task2 = () -> account.withdraw("User-B", 500);
        Runnable task3 = () -> account.withdraw("User-C", 300);

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        Thread t3 = new Thread(task3);

        t1.start();
        t2.start();
        t3.start();
    }
}
