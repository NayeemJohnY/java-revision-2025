package com.java_revision.multithreading;

public class ThreadStateMonitor {

    public static void main(String[] args) {
        Thread dataFetchThread = new Thread(() -> {
            System.out.println("🛰️ Fetching data from API...");
            ThreadHelper.threadSleep(2000);
            System.out.println("✅ Data fetch complete.");
        }, "DataFetchThread");

        Thread reportGenThread = new Thread(() -> {
            try {
                dataFetchThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("📝 Generating report...");
            ThreadHelper.threadSleep(1500);
            System.out.println("✅ Report generation complete.");
        }, "ReportGenThread ");

        Thread monitorThread = new Thread(() -> {
            Thread.State prevFetchState = null;
            Thread.State prevReportState = null;

            while (true) {
                Thread.State fetchState = dataFetchThread.getState();
                Thread.State reportState = reportGenThread.getState();

                if (fetchState != prevFetchState) {
                    System.out.println("🔍 " + dataFetchThread.getName() + " is now: " + fetchState);
                    prevFetchState = fetchState;
                }

                if (reportState != prevReportState) {
                    System.out.println("🔍 " + reportGenThread.getName() + " is now: " + reportState);
                    prevReportState = reportState;
                }

                if (fetchState == Thread.State.TERMINATED && reportState == Thread.State.TERMINATED) {
                    System.out.println("✅ All threads completed. Monitoring stopped.");
                    break;
                }

                ThreadHelper.threadSleep(500);
            }

        }, "MonitorThread");

        dataFetchThread.start();
        reportGenThread.start();
        monitorThread.start();

        try {
            monitorThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
