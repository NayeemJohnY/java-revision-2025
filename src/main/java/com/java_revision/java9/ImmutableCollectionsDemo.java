
package com.java_revision.java9;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 *  Factory Methods for Immutable Collections:
New static of() methods were added to the List, Set, and Map interfaces
 (e.g., List.of(), Set.of(), Map.of()) to easily create immutable collections.
  This reduces boilerplate code and makes it safer to work with collections that should not be modified
 */

public class ImmutableCollectionsDemo {

    public static void main(String[] args) {
        // Hint 1: Create an immutable List of strings.
        List<String> names = List.of("Alice", "Bob", "Charlie");

        try {
            names.add("David");
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: Cannot add to immutable list! " + e.toString());
        }

        Set<Integer> numbers = Set.of(1, 2, 3, 4, 5);
        try {
            numbers.add(22);
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: Cannot add to immutable set ! " + e.toString());
        }

        // Hint 3: Create an immutable Map with key-value pairs.
        Map<String, Integer> ages = Map.of("Alice", 30, "Bob", 28, "Charlie", 35);
        try {
            ages.put("David", 33);
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: Cannot add to immutable map ! " + e.toString());
        }

    }
}
