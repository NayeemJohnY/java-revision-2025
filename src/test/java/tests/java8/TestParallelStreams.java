package tests.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.java_revision.OrderService;

public class TestParallelStreams {

    @Test
    public void testParallelStream() {
        // 1. Convert a list of numbers to squares in parallel.
        long starttime = System.currentTimeMillis();
        IntStream.range(0, 100).parallel().map(num -> num * num).forEach(System.out::println);
        long endTime = System.currentTimeMillis();
        long parallelSeconds = endTime - starttime;

        // 2. Measure performance of stream vs parallelStream.
        starttime = System.currentTimeMillis();
        IntStream.range(0, 100).map(num -> num * num).forEach(System.out::println);
        endTime = System.currentTimeMillis();
        long seqSeconds = endTime - starttime;
        System.out.println("Parallel " + parallelSeconds + " Sequential " + seqSeconds + " Diff "
                + (seqSeconds - parallelSeconds));

        // 3. Collect results from parallel stream safely.
        List<String> list = Arrays.asList("banana", "apple", "mango", "papaya", "peach");
        System.out.println(list.parallelStream().map(String::toUpperCase).toList());

        List<Integer> squaredNumbers = IntStream.range(0, 100).parallel().boxed().map(num -> num * num)
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        System.out.println(squaredNumbers);

        // 4. Handle race conditions in parallel streams.
        List<Integer> collectorList = IntStream.range(0, 1000).parallel().boxed().toList();
        System.out.println("via Collectors TO List Size: " + collectorList.size());
        List<Integer> customList = new ArrayList<>();
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        IntStream.range(0, 1000).parallel().forEach(i -> {
            customList.add(i);
            copyOnWriteArrayList.add(i);
        });
        System.out.println("Via List Add List Size: " + customList.size());
        System.out.println("Via CopyonWrite List Size: " + copyOnWriteArrayList.size());

        final int[] counter = { 0 };
        AtomicInteger atomicCounter = new AtomicInteger(0);
        IntStream.range(0, 1000).parallel().forEach(i -> {
            counter[0]++;
            atomicCounter.incrementAndGet();
        });
        System.out.println("Counter value: " + counter[0]);
        System.out.println("Atomic value: " + atomicCounter.get());

        // 5. Apply filter + map + reduce using parallel stream.
        List<OrderService> orders = new ArrayList<>();
        orders.add(new OrderService(1001, "IPhone", 2, 29.9));
        orders.add(new OrderService(1005, "Samsung", 6, 19.9));
        orders.add(new OrderService(1001, "Windows Laptop Dell", 5, 200.5));
        orders.add(new OrderService(1011, "Water Bottle", 6, 5));
        orders.add(new OrderService(1011, "HeadPhones", 5, 100.15));

        double totalRevenue = orders.parallelStream().filter(o -> o.getQuantity() > 4)
                .mapToDouble(o -> o.getPricePerItem() * o.getQuantity()).reduce(0, Double::sum);
        System.out.println("Total Revenue: " + totalRevenue);

        // 6. Find max element in large list using parallel stream.
        List<Integer> largeList = new Random().ints(10_000, 1, 10_000).boxed().toList();
        largeList.parallelStream().max(Integer::compareTo).ifPresent(System.out::println);

        // 7. Use `parallelStream` on a map's values.
        Map<String, List<Double>> productsPrices = new HashMap<>();
        productsPrices.put("Electronics", Arrays.asList(12.7, 11.0, 114.5, 210.0, 100.0));
        productsPrices.put("Clothing", Arrays.asList(112.0, 111.0, 141.5, 120.6, 1001.0));
        Map<String, Double> averagePrices = productsPrices.entrySet().parallelStream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0.0)));
        System.out.println("Average prices: " + averagePrices);

        // 8. Write test case to verify stream parallelism.
        // Correctness - Peformance - behaviour - thread safe
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int sequentialSum = numbers.stream().mapToInt(Integer::intValue).sum();
        int parallelSum = numbers.parallelStream().mapToInt(Integer::intValue).sum();
        Assert.assertEquals(sequentialSum, parallelSum);

        // 9. Compare thread usage in sequential vs parallel.
        // Thread Utilization - Execution Time - Thread Pool
        long startTime = System.nanoTime();
        largeList.stream()
                .forEach(number -> System.out.println("Thread used (sequential): " + Thread.currentThread().getName()));
        endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Sequential stream execution time: " + duration + " nanoseconds");

        startTime = System.nanoTime();
        largeList.parallelStream()
                .forEach(number -> System.out.println("Thread used (parallel): " + Thread.currentThread().getName()));
        endTime = System.nanoTime();

        duration = endTime - startTime;
        System.out.println("Parallel stream execution time: " + duration + " nanoseconds");

        
        // 10. Debug execution order of parallel stream operations.
        numbers.parallelStream()
        .map(n -> {
                System.out.println("Mapping: " + n + " on thread: " + Thread.currentThread().getName());
                return n*2;
        })
        .filter(n -> {
                System.out.println("Filter " + n + "on thread: " + Thread.currentThread().getName());
                return n > 10;
        })
        .forEachOrdered(n -> System.out.println("For Each: " + n + " on thread: " + Thread.currentThread().getName()));
    }


}
