package tests.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

public class TestStreamPerfOptimization {

    // 1. Convert a list of integers to strings using parallel stream
    @Test
    public void convertToString() {
        List<Integer> numbers = IntStream.range(0, 2000).boxed().toList();
        List<String> strNumbers = numbers.parallelStream().map(String::valueOf).toList();
        System.out.println(strNumbers);
    }

    // 2. Filter even numbers from a large list efficiently
    @Test
    public void filterEvenNumbers() {
        List<Integer> numbers = IntStream.range(0, 2000).boxed().toList();
        System.out.println(numbers.parallelStream().filter(n -> n % 2 == 0).toList());
    }

    // 3. Use unordered() to boost performance in parallel stream
    @Test
    public void countUsingUnordered() {
        List<String> names = Arrays.asList("one", "two", "three", "four", "five", "six", "one", "three", "four",
                "nine");
        System.out.println(names.parallelStream().unordered().distinct().count());
    }

    // 4. Sum all elements using reduce in parallel stream
    @Test
    public void sumElements() {
        List<Integer> numbers = IntStream.range(0, 2000).boxed().toList();
        System.out.println(numbers.parallelStream().reduce(0, Integer::sum));
    }

    // 5. Find max from a list of numbers
    @Test
    public void findMax() {
        List<Integer> numbers = IntStream.range(0, 2000).boxed().toList();
        System.out.println(numbers.parallelStream().max(Integer::compare).orElse(-1));
    }

    // 6. Collect elements into a set using parallel stream
    @Test
    public void collectToSet() {
        List<String> input = Arrays.asList("one", "two", "three", "four", "five", "six", "one", "three", "four",
                "nine");
        Set<String> output = input.parallelStream().collect(Collectors.toSet());
        System.out.println(output);
    }

    // 7. Check if all elements match a predicate in parallel
    @Test
    public void allMatchPositive() {
        List<Integer> numbers = IntStream.range(1, 2000).boxed().toList();
        System.out.println(numbers.parallelStream().allMatch(n -> n > 0));
        numbers = IntStream.range(-200, 0).boxed().toList();
        System.out.println(numbers.parallelStream().allMatch(n -> n > 0));
        numbers = Arrays.asList(1, -1, 2, -1, 2, 3, 4, 5);
        System.out.println(numbers.parallelStream().allMatch(n -> n > 0));
    }
    // 🟡 Intermediate (8–14)

    // 8. Partition numbers based on even/odd
    @Test
    public void partitionEvenOdd() {
        List<Integer> numbers = IntStream.range(1, 2000).boxed().toList();
        Map<Boolean, List<Integer>> output = numbers.parallelStream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println(output);
    }

    // 9. Measure performance of sequential vs parallel processing
    @Test
    public void measurePerformance() {
        // Compare and return time taken by parallel stream/
        List<Integer> largeList = IntStream.range(1, 30000).boxed().toList();
        long startTime = System.nanoTime();
        largeList.stream()
                .forEach(number -> System.out
                        .println(number + " Thread used (sequential):  " + Thread.currentThread().getName()));
        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();
        largeList.parallelStream()
                .forEach(number -> System.out
                        .println(number + " Thread used (Parallel): " + Thread.currentThread().getName()));
        endTime = System.nanoTime();

        long duration2 = endTime - startTime;
        System.out.println("Sequential stream execution time: " + duration1 + " nanoseconds");
        System.out.println("Parallel stream execution time: " + duration2 + " nanoseconds");
    }

    // 10. Group strings by length
    @Test
    public void groupByLength() {
        List<String> input = Arrays.asList("one", "two", "three", "four", "five", "six", "one", "three", "four");
        Map<Integer, List<String>> outpt = input.parallelStream().collect(Collectors.groupingBy(String::length));
        System.out.println(outpt);
    }

    // 11. Avoid shared mutable state in parallel streams
    @Test
    public void calculateSumSafe() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        AtomicInteger sum = new AtomicInteger(0);
        numbers.parallelStream().forEach(sum::addAndGet);
        System.out.println(sum);
    }

    // 12. Use flatMap to flatten a list of lists
    @Test
    public void flattenLists() {
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5),
                Arrays.asList(11, 12, 13, 14, 15), Arrays.asList(11, 22, 33, 44, 55));
        List<Integer> output = nestedLists.parallelStream().flatMap(List::stream).toList();
        System.out.println(output);
    }

    // 13. Map elements to uppercase using map
    @Test
    public void mapToUpper() {
        List<String> words = Arrays.asList("one", "two", "three", "four", "five", "six", "one", "three", "four");
        List<String> output = words.parallelStream().map(String::toUpperCase).toList();
        System.out.println(output);
    }

    // 14. Sort a large list in parallel
    @Test
    public void sortNumbers() {
        List<Integer> numbers = Arrays.asList(111, 12, 131, 114, 15);
        numbers = numbers.parallelStream().sorted().toList();
        System.out.println(numbers);
    }

    // 🔴 Advanced (15–20)
    // 15. Custom thread pool for parallel streams
    @Test
    public void useCustomPool() {
        // Use ForkJoinPool to run parallel stream
        List<Integer> list = IntStream.range(0, 200).boxed().toList();
        try (ForkJoinPool customPool = new ForkJoinPool(4)) {
            try {
                customPool.submit(() -> list.parallelStream().filter(n -> n % 2 == 0)
                        .forEach(n -> System.out.println(n + " Thread " + Thread.currentThread().getName()))).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            customPool.shutdown();
        }
    }

    // 16. Compare result of stream().parallel() vs parallelStream()
    @Test
    public void areResultsSame() {
        List<Integer> largeList = IntStream.range(1, 3000).boxed().toList();
        long startTime = System.nanoTime();
        largeList.stream().parallel()
                .forEach(number -> System.out
                        .println(number + " Thread used (stream.parallel):  " + Thread.currentThread().getName()));
        long endTime = System.nanoTime();

        long duration1 = endTime - startTime;

        startTime = System.nanoTime();
        largeList.parallelStream()
                .forEach(number -> System.out
                        .println(number + " Thread used (Parallel): " + Thread.currentThread().getName()));
        endTime = System.nanoTime();

        long duration2 = endTime - startTime;
        System.out.println("stream.parallel execution time: " + duration1 + " nanoseconds");
        System.out.println("Parallel stream execution time: " + duration2 + " nanoseconds");
    }

    // 17. Combine map + filter + collect for performance
    @Test
    public void optimizedPipeline() {
        List<String> names = Arrays.asList("o", "two", "three", "four", "five", "s", "one", "three", "four");
        List<String> output = names.parallelStream().filter(s -> s.length() > 1).map(String::toUpperCase).toList();
        System.out.println(output);
    }

    // 18. Avoid intermediate state storage in pipeline
    @Test
    public void countFiltered() {
        List<String> words= Arrays.asList("o", "two", "three", "four", "five", "s", "one", "three", "four");
        System.out.println(words.parallelStream().filter(s -> s.length()>3).count());
    }

    // 19. Efficient distinct filtering in parallel
    @Test
    public void filterDistinct() {
       List<Integer> input = Arrays.asList(11, 22, 33, 44, 55);
        System.out.println(input.parallelStream().distinct().toList());
    }

    // 20. Handle large data with reduce and combiner
    @Test
     public void parallelSum() {
        // Use reduce with identity, accumulator, and combiner
        List<Integer> numbers = IntStream.range(0, 500).boxed().toList();
        System.out.println(numbers.parallelStream().reduce(0, Integer::sum, Integer::sum));
    }

}
