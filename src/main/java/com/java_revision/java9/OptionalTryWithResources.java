package com.java_revision.java9;

import java.util.Optional;
import java.util.Scanner;

public class OptionalTryWithResources {
    public static void main(String[] args) {
        System.out.println("Enter Input: ");
        Scanner scanner = new Scanner(System.in);
        try (scanner) {
             Optional<Integer> value = Optional.empty();
             try {
                value = Optional.of(scanner.nextInt());
             } catch (Exception e) {
             }
            value.ifPresentOrElse(System.out::println,  () -> System.out.println("No Number is given"));
            System.out.println("value after  OR " + value.or( ()-> Optional.of(-1)).get());
        }
    }
}
