package com.java_revision.multithreading;

class ThreadClass extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            ThreadHelper.threadSleep(100);
            System.out.println("[" + this.getClass().getSimpleName() + "] Hello from "
                    + Thread.currentThread().getName());
        }

    }
}

class RunnableClass implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            ThreadHelper.threadSleep(100);
            System.out.println("[RunnableClass] Hello from " + Thread.currentThread().getName());
        }
    }

}

public class DualThreadPrinter {
    public static void main(String[] args) {
        Thread t1 = new ThreadClass();
        Thread t2 = new Thread(new RunnableClass());
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ThreadHelper.threadSleep(100);
                System.out.println("[LambdaExpression] Hello from " + Thread.currentThread().getName());
            }
        });
        t1.start();
        t2.start();
        t3.start();

    }

}
