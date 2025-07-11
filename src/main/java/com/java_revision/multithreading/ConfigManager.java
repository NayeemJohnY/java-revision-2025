package com.java_revision.multithreading;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ConfigManager {

    private static ConcurrentHashMap<String, String> configMap = new ConcurrentHashMap<>();
    private static CopyOnWriteArrayList<String> auditLog = new CopyOnWriteArrayList<>();

    static {
        configMap.put("env", "Staging");
        configMap.put("browser", "chrome");
        configMap.put("url", "https://staging-url.com");
    }

    public static void readConfig(String key) {
        auditLog.add(Thread.currentThread().getName() + " READ " + key + "=" + configMap.get(key));

    }

    public static void updateConfig(String key, String value) {
        configMap.put(key, value);
        auditLog.add(Thread.currentThread().getName() + " WROTE " + key + "=" + configMap.get(key));
    }

    public static void printAuditLog() {
        System.out.println("\n--- Audit Log ---");
        auditLog.forEach(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        String[] keys = { "env", "browser", "url" };
        String[] values = { "QA", "Firefox", "https://qa-url.com", "Prod", "Edge", "https://prod-url.com" };
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                int action = ThreadLocalRandom.current().nextInt(2); // 0 = read, 1 = write
                String key = keys[ThreadLocalRandom.current().nextInt(keys.length)];
                if (action == 0) {
                    readConfig(key);
                } else {
                    String value = values[ThreadLocalRandom.current().nextInt(values.length)];
                    updateConfig(key, value);
                }
            }
            ThreadHelper.threadSleep(200);
        };
        Thread t1 = new Thread(task, "T1-");
        Thread t2 = new Thread(task, "T2-");
        Thread t3 = new Thread(task, "T3-");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        printAuditLog();
    }
}
