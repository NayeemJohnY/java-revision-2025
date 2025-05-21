package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

import com.java_revision.Employee;

public class TestLambdaExpressions {

    // Problem 1: Sort List of Strings by Length
    @Test
    public void sortListStringByLength() {
        List<String> list = new ArrayList<>(Arrays.asList("apple", "kiwi", "banana"));
        // Solution 1
        list.sort((s1, s2) -> s1.length() - s2.length());
        System.out.println(list);
        // Solution 2
        list.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println(list);
    }

    // Problem 2: Filter Even Numbers
    @Test
    public void filterEvenNumbers() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 77, 88, 99, 11, 13, 66));
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(numbers.stream().filter(isEven).toList());
    }

    // Problem 3: Custom Functional Interface - Adder
    @FunctionalInterface
    interface Addition {
        abstract int add(int num1, int num2);
    }

    @Test
    public void addTwoIntegerUsingFunctionalInterface() {
        Addition addition = (num1, num2) -> num1 + num2;
        System.out.println(addition.add(10, 20));
    }

    // Problem 4: String Concatenation Lambda
    @Test
    public void concatenateStrings() {
        BiFunction<String, String, String> concatenate = (s1, s2) -> s1 + s2;
        System.out.println(concatenate.apply("Java", "World"));
    }

    // Problem 5: Find Maximum Using Lambda
    @Test
    public void findMaxofELementsUsingComparatorLambda() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 77, 88, 99, 11, 13, 66));
        Comparator<Integer> comparator = (num1, num2) -> Integer.compare(num1, num2);
        System.out.println(Collections.max(numbers, comparator));
        System.out.println(Collections.max(numbers, Integer::compare));
    }

    // Problem 6: Count Strings Starting With Letter
    @Test
    public void countStringStartingwithLetter() {
        List<String> list = new ArrayList<>(List.of("Apple", "Ant", "Banana", "Avocado"));
        String startLetter = "A";
        Predicate<String> startsWith = s -> s.startsWith(startLetter);
        System.out.println(list.stream().filter(startsWith).count());
    }

    // Problem 7: Convert List of Integers to Squares
    @Test
    public void convertIntegertoSquares() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 77, 88, 99, 11, 13, 66));
        Function<Integer, Integer> funSquare = num -> num * num;
        System.out.println(numbers.stream().map(funSquare).toList());
    }

    // Problem 8: Sort Employees by Salary
    @Test
    public void sortEmployeesbySalary() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000));
        employeeList.add(new Employee("B", 123, 30000));
        employeeList.add(new Employee("C", 123, 10000));
        employeeList.add(new Employee("D", 123, 5000));

        employeeList.sort((e1, e2) -> Integer.compare(e1.getSalary(), e2.getSalary()));
        System.out.println("Sorted List\n" + employeeList);
    }

    // Problem 9: Lambda for Runnable
    @Test
    public void implementRunnable() {
        ((Runnable) () -> System.out.println("Thread running")).run();
    }

    // Problem 10: Filter Non-Empty Strings
    @Test
    public void filterNonEmptyStrings() {
        List<String> list = new ArrayList<>(List.of("", "Java", "", "Lambda"));
        System.out.println(list.stream().filter(s -> !s.isEmpty()).toList());
        System.out.println(list.stream().filter(((Predicate<String>) String::isEmpty).negate()).toList());
    }

    // Problem 11: Remove Null and Empty Strings from a List
    @Test
    public void removeNullEmptyString() {
        List<String> list = new ArrayList<>(Arrays.asList("Java", null, "", "Lambda", null));
        System.out.println(list.stream().filter(((Predicate<String>) s -> s == null || s.isEmpty()).negate()).toList());
    }

    // Problem 12: Multiply All Numbers in a List
    @Test
    public void multipleAllNumbersInList() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 4, 6, 10, 25));
        System.out.println(numbers.stream().reduce(1, (a, b) -> a * b));
    }

    // Problem 13: Find the First Element Matching a Condition
    // Description: Use filter and findFirst to get the first string starting with
    // "J".
    @Test
    public void findFirstMatchingELement() {
        List<String> list = new ArrayList<>(List.of("C++", "Java", "Python"));
        list.stream().filter(s -> s.startsWith("J")).findFirst().ifPresent(System.out::println);
    }

    /*
     * Problem 14: Count Occurrences of a Word
     * Description: Count how many times a word (e.g., "Java") appears in a list.
     */
    @Test
    public void CountOccurrencesOfWord() {
        List<String> list = new ArrayList<>(List.of("Java", "C", "Java", "Python"));
        System.out.println(list.stream().filter(s -> s.equals("Java")).count());
    }

    /*
     * Problem 15: Group Strings by Length
     * Description: Use Collectors.groupingBy to group strings by their lengths.
     */
    @Test
    public void groupStringsByLength() {
        List<String> list = new ArrayList<>(List.of("a", "bb", "ccc", "dd"));
        System.out.println(list.stream().collect(Collectors.groupingBy(String::length)));
    }

    /*
     * Problem 16: Convert a List of Strings to Uppercase
     * Description: Use map and lambda to convert each string to uppercase.
     */
    @Test
    public void converListOfStringsToUppercase() {
        List<String> list = new ArrayList<>(List.of("java", "lambda"));
        System.out.println(list.stream().map(String::toUpperCase).toList());
    }

    /*
     * Problem 17: Sum of All Odd Numbers
     */
    @Test
    public void sumofAllOddNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(numbers.stream().filter(num -> num % 2 != 0).reduce(0, Integer::sum));
    }

    /*
     * Problem 18: Convert List of Strings to a Single Comma-Separated String
     */
    @Test
    public void ConvertListOfStringstoSingleString() {
        List<String> strings = Arrays.asList("Java", "Lambda", "Stream");
        System.out.println(strings.stream().collect(Collectors.joining(",")));
    }

    /*
     * Problem 19: Find the Longest String in a List
     */
    @Test
    public void findLongestStringInList() {
        List<String> strings = Arrays.asList("Java", "Python", "Go");
        strings.stream().max((s1, s2) -> Integer.compare(s1.length(), s2.length())).ifPresent(System.out::println);
    }

    /*
     * Problem 20: Sort List in Descending Order
     */
    @Test
    public void sortListInDescendingOrder() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 2);
        numbers.sort((num1, num2) -> num2 - num1);
        System.out.println(numbers);
    }

    /*
     * Problem 21: Find Duplicate Elements in a List
     */
    @Test
    public void findDuplicateELementsInList() {
        List<String> strings = Arrays.asList("Java", "Python", "Go", "Java");
        HashSet<String> hashSet = new HashSet<>();
        System.out.println(strings.stream().filter(s -> !hashSet.add(s)).toList());
    }

    /*
     * Problem 22: Check if All Elements Are Even
     */
    @Test
    public void checkAllElementsAreEven() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 2, 3, 6, 8);
        List<Integer> evens = Arrays.asList(4, 2, 6, 8);
        System.out.println(numbers.stream().allMatch(n -> n % 2 == 0));
        System.out.println(evens.stream().allMatch(n -> n % 2 == 0));
    }

    /*
     * Problem 23: Partition Numbers into Even and Odd
     */
    @Test
    public void partitionNumbersByEvenOdd() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 2, 3, 6, 8);
        System.out.println(numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)));
    }

    /*
     * Problem 24: Remove Duplicates and Sort List
     */
    @Test
    public void removeDuplicatesAndSortList() {
        List<String> strings = Arrays.asList("Java", "Python", "Go", "Java");
        System.out.println(strings.stream().distinct().sorted().toList());
    }

    /*
     * Problem 25: Filter Strings with Length > N
     */
    @Test
    public void filterStringsLengthGreaterthanN() {
        List<String> strings = Arrays.asList("Java", "Python", "Go", "Java");
        System.out.println(strings.stream().filter(s -> s.length() > 3).toList());
    }

    /*
     * Problem 26: Find Max Value in a List
     */
    @Test
    public void findMaxValueInList() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 2, 3, 6, 8);
        numbers.stream().max(Integer::compare).ifPresent(System.out::println);
    }

    /*
     * Problem 27: Convert Map to List of Keys/Values
     */
    @Test
    public void convertMapToListOfKeysAndValues() {
        Map<String, Integer> map = Map.of("Java", 8, "Python", 3, "Go", 1);
        System.out.println("Keys " + map.keySet().stream().toList());
        System.out.println("Values " + map.values().stream().toList());
    }

    /*
     * Problem 28: Flatten Nested List
     */
    @Test
    public void flattenNestedList() {
        List<List<String>> nested = Arrays.asList(
                Arrays.asList("Java", "Python"),
                Arrays.asList("C++", "Go"),
                List.of());

        List<String> flat = nested.stream().flatMap(Collection::stream).toList();
        System.out.println(flat);
    }

    /*
     * Problem 29: Find Frequency of Each Word
     */
    @Test
    public void wordFrequencyMap() {
        List<String> list = Arrays.asList("Java", "Python", "Java", "Go", "Python", "Go", "Go");
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    /* 
    * Problem 30: Reverse a List Using Stream
     */
    @Test
    public void reverseListUsingStream() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.sort(Collections.reverseOrder());
        System.out.println(list);

        list = list.stream().sorted(Collections.reverseOrder()).toList();
        System.out.println(list);
    }
}
