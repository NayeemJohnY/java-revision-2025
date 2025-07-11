package com.java_revision.java9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 *  Stream API Improvements:

takeWhile() and dropWhile(): These new methods allow you to efficiently process elements in a stream based on a predicate,
 either taking elements until a condition is false or dropping elements until a condition is true.

iterate() with Predicate: An overloaded iterate() method was added to allow a hasNext predicate, enabling more controlled iteration over streams.

ofNullable(): This method creates a stream containing a single element if the provided value is non-null, or an empty stream if it's null,
 helping to avoid NullPointerExceptions when working with streams.
 */
public class StreamEnhancements {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);

        System.out.println("Original List: " + numbers);

        // Hint 1: Use takeWhile() to take elements as long as a condition is true.
        // It stops as soon as the condition is false.
        List<Integer> taken = numbers.stream().takeWhile(n -> n < 4).toList();
        System.out.println("takeWhile n < 4: " + taken);

        // Hint 2: Use dropWhile() to drop elements as long as a condition is true.
        // It starts processing elements once the condition is false.
        List<Integer> dropped = numbers.stream().dropWhile(n -> n < 4).toList();
        System.out.println("Dropwhile n < 4: " + dropped);

        // Hint 3: Use the new iterate() method with a hasNext predicate.
        // Generate numbers starting from 0, incrementing by 2, as long as it's less than 10.
        Stream.iterate(0, n-> n < 10, n -> n+ 2).forEach(System.out::println);

         // Hint 4: Create a stream from a non-null value.
        System.out.println("--- Stream.ofNullable() ---");
        String name1 = "Alice";
        String name2 = null;
        Stream.ofNullable(name1).forEach(s -> System.out.println("Non-null stream: " + s));
        Stream.ofNullable(name2).forEach(s -> System.out.println("null stream: " + s)); // won't print
        List<String> userNames = Arrays.asList("Bob", null, "Charlie");
        List<String> validNames = userNames.stream().flatMap(Stream::ofNullable).toList();
        System.out.println("Valid names after flatMap(ofNullable): " + validNames); 
    }

}
