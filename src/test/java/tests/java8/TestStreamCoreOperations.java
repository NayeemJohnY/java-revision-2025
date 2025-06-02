package tests.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.annotations.Test;

import com.java_revision.Employee;

public class TestStreamCoreOperations {

    /*
     * Problem 1: Remove duplicates from a list using distinct.
     */
    @Test
    public void removeDuplicatesUsingDistinct() {
        List<String> list = Arrays.asList("Java", "Python", "Java", "Go", "Python", "Go", "Go");
        System.out.println(list.stream().distinct().toList());
    }

    /*
     * Problem 2: Count elements greater than 50 in a list.
     */
    @Test
    public void countElementsGreaterThan50List() {
        List<Integer> list = Arrays.asList(1, 2, 3, 50, 10, 51, 60, 99, 12, 123, 345, 43, 23, 156, 67);
        System.out.println(list.stream().filter(e -> e > 50).count());
    }

    /*
     * Problem 3: Get top 5 highest numbers from a list.
     */
    @Test
    public void getTop5HighestNumbers() {
        List<Integer> list = Arrays.asList(1, 2, 3, 50, 10, 51, 60, 99, 12, 123, 345, 43, 23, 156, 67);
        System.out.println(list.stream().sorted(Collections.reverseOrder()).limit(5).toList());
    }

    /*
     * Problem 4: Convert list of integers to comma-separated string.
     */
    @Test
    public void convertListOfIntegerToString() {
        List<Integer> list = Arrays.asList(1, 2, 3, 50, 10, 51, 60, 99);
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(", ")));
    }

    /*
     * Problem 5: Calculate average of a list of doubles using mapToDouble.
     */
    @Test
    public void calculateAverageOfList() {
        List<Double> doubles = Arrays.asList(123.34, 234.56, 6788.89, 321.00, 345.89);
        doubles.stream().mapToDouble(Double::doubleValue).average().ifPresent(System.out::println);
    }

    /*
     * Problem 6: Filter a list of strings starting with vowel.
     */
    @Test
    public void filterListOfStringsWithVowel() {
        List<String> list = Arrays.asList("Java", "Python", "Umberlla", "abacus", "node", "orange");
        Predicate<String> isVowel = e -> "aeiou".indexOf(e.toLowerCase().charAt(0)) != -1;
        System.out.println(list.stream().filter(isVowel).toList());
    }

    /*
     * Problem 7: Flatten a list of lists using flatMap.
     */
    @Test
    public void flattenListOfList() {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("Apple", "Orange", "banana"), Arrays.asList("Red", "orange", "Yellow"));
        System.out.println(list.stream().flatMap(Collection::stream).toList());
    }

    /*
     * Problem 8: Find the first even number in a list.
     */
    @Test
    public void findFirstEvenNumberInList() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99);
        list.stream().filter(num -> num % 2 == 0).findFirst().ifPresent(System.out::println);
    }

    /*
     * Problem 9: Sort a list in reverse order using streams.
     */
    @Test
    public void sortListReverseOrder() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99);
        System.out.println(list.stream().sorted(Collections.reverseOrder()).toList());
    }

    /*
     * Problem 10: Collect even numbers into a set.
     */
    @Test
    public void collectEvenNumberToSet() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 10, 15, 20, 60);
        System.out.println(list.stream().filter(n -> n % 2 == 0).collect(Collectors.toSet()));
    }

    /*
     * Problem 11: Find the maximum and minimum numbers in a list.
     */
    @Test
    public void findMaxMin() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 10, 15, 20, 60);
        list.stream().min(Integer::compareTo).ifPresent(System.out::println);
        list.stream().max(Integer::compareTo).ifPresent(System.out::println);
    }

    /*
     * Problem 12: Find all numbers divisible by both 3 and 5.
     */
    @Test
    public void findNumberDivisible() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 10, 15, 20, 60);
        System.out.println(list.stream().filter(e -> e % 3 == 0 && e % 5 == 0).toList());
    }

    /*
     * Problem 13: Check if all numbers are positive.
     */
    @Test
    public void checkAllNumbersArePositive() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 110);
        System.out.println(list.stream().allMatch(e -> e >= 0));

        list = Arrays.asList(1, -1, 0, 10, 20, -9);
        System.out.println(list.stream().allMatch(e -> e >= 0));
    }

    /*
     * Problem 14: Check if any number is negative.
     */
    @Test
    public void checkAnyNumberIsNegative() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 110);
        System.out.println(list.stream().anyMatch(e -> e < 0));

        list = Arrays.asList(1, -1, 0, 10, 20, -9);
        System.out.println(list.stream().anyMatch(e -> e < 0));
    }

    /*
     * Problem 15: Check if no number is zero.
     */
    @Test
    public void checkNoNumberisZero() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 110);
        System.out.println(list.stream().noneMatch(e -> e < 0));

        list = Arrays.asList(1, -1, 0, 10, 20, -9);
        System.out.println(list.stream().noneMatch(e -> e < 0));
    }

    /*
     * Problem 16: Remove duplicate characters from a string.
     */
    @Test
    public void removeDuplicateCharsFromString() {
        String str = "Hello Java Prorgamming";
        System.out.println(
                str.chars().distinct().mapToObj(ch -> String.valueOf((char) ch)).collect(Collectors.joining()));
    }

    /*
     * Problem 17: Group a list of strings by their first character.
     */
    @Test
    public void groupListOfStrings() {
        List<String> list = Arrays.asList("Java", "python", "Javascript", "Playwright", "Selenium", "RestAssuured");
        System.out.println(list.stream().collect(Collectors.groupingBy(s -> s.toLowerCase().charAt(0))));
    }

    /*
     * Problem 18: Partition numbers into even and odd.
     */
    @Test
    public void partitionNumbers() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 13, 15, 19, 60);
        System.out.println(list.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)));
    }

    /*
     * Problem 19: Find the second highest number in a list.
     */
    @Test
    public void findSecondLargest() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 13, 15, 19, 69);
        list.stream().sorted(Collections.reverseOrder()).skip(1).findFirst().ifPresent(System.out::println);
    }

    /*
     * Problem 20: Create a frequency map of elements in a list.
     */
    @Test
    public void createFreqMap() {
        List<String> list = Arrays.asList("Java", "Python", "Java", "JS", "Python", " ", "", "");
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    /*
     * Problem 21: Get the summary statistics (count, min, max, average, sum).
     */
    @Test
    public void summaryStatics() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 13, 15, 19, 69);
        System.out.println(list.stream().mapToInt(Integer::valueOf).summaryStatistics());
    }

    /*
     * Problem 22: Convert a list of strings to a list of lengths.
     */
    @Test
    public void convertListOfStringsToLength() {
        List<String> list = Arrays.asList("Java", "Python", "Java", "JS", "Python", " ", "", "");
        System.out.println(list.stream().map(String::length).toList());
    }

    /*
     * Problem 23: Get distinct word lengths from a sentence.
     */
    @Test
    public void distinctWordLengthFromSentence() {
        String str = "Welcome to Java World, The World want  to  Explore more on Java";
        System.out.println(Arrays.stream(str.split("\\s+")).distinct().map(String::length).toList());
    }

    /*
     * Problem 24: Limit and skip results in a stream.
     */
    @Test
    public void limitSkipResultsInStream() {
        List<Integer> list = Arrays.asList(1, 21, 3, 50, 10, 51, 60, 99, 2, 13, 15, 19, 69);
        System.out.println(list.stream().skip(3).limit(5).toList());
    }

    /*
     * Problem 25: Filter null or empty strings from a list.
     */
    @Test
    public void filterNullEmptyExclude() {
        List<String> list = Arrays.asList("Java", "Python", null, "JS", "Python", " ", "", "", null);
        System.out.println(
                list.stream().filter(((Predicate<String>) e -> e == null || e.trim().isEmpty()).negate()).toList());
    }

    /*
     * Problem 26: Sort a list of strings by length.
     */
    @Test
    public void sortListOfStringsbyLength() {
        List<String> list = Arrays.asList("Java", "Python", null, "JS", "Python", " ", "", "", null);
        System.out
                .println(list.stream().sorted(Comparator.nullsLast(Comparator.comparingInt(String::length))).toList());
    }

    /*
     * Problem 27: Get the longest word in a list.
     */
    @Test
    public void getLongestWordInList() {
        String str = "Welcome to Java World, The World want  to  Explore more on Java";
        Arrays.stream(str.split("\\s+")).max(Comparator.comparingInt(String::length)).ifPresent(System.out::println);
    }

    /*
     * Problem 28: Merge two lists and remove duplicates.
     */
    @Test
    public void mergeTwoListsAndRemoveDuplicates() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 10);
        List<Integer> list2 = Arrays.asList(11, 2, 13, 4, 16, 17, 18, 9, 10);
        System.out.println(Stream.concat(list1.stream(), list2.stream()).distinct().toList());
    }

    /*
     * Problem 29: Convert list of objects to list of one field (e.g., names).
     */
    @Test
    public void converListOfObjectstoListOfNames() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 123, 30000));
        employeeList.add(new Employee("Carlos", 123, 10000));
        employeeList.add(new Employee("Daniel", 123, 5000));

        System.out.println(employeeList.stream().toList());
        System.out.println(employeeList.stream().map(Employee::getName).toList());
    }

    /*
     * Problem 30: Sum the squares of even numbers.
     */
    @Test
    public void sumOfSquaresOfEvenNumbers(){
         List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 6, 7, 8, 9, 10);
         System.out.println(list1.stream().filter(n -> n%2==0).map(num -> num*num).reduce(0, Integer::sum));
    }

}
