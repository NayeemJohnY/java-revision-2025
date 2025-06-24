package com.java_revision.multithreading;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebCrawling {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> websites = Arrays.asList(
                "https://testsummary.com", "https://mail.google.com/calender",
                "http//localhost.com/api/users?q=John", "https://chatgpt.com/chat-id12344567889",
                "https://google.com");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        CompletionService<Map<String, Integer>> service = new ExecutorCompletionService<>(executor);
        for (String website : websites) {
            service.submit(() -> {
                ThreadHelper.threadSleep((int) (1000 + Math.random() * 2000));
                System.out.println("Processed by " + Thread.currentThread().getName() + ": " + website);
                return Map.of(website, website.length());
            });
        }

        for (int i = 0; i < websites.size(); i++) {
            Future<Map<String, Integer>> result = service.take();
            System.out.println("content Length " + result.get());
        }

        executor.shutdown();

    }
}
