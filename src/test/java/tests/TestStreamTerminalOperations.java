package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.testng.annotations.Test;

import com.java_revision.Book;
import com.java_revision.Employee;
import com.java_revision.Student;
import com.java_revision.Product;

public class TestStreamTerminalOperations {

    /*
     * Problem 1: Sum all elements in a list using reduce.
     */
    @Test
    public void sumofAllElementsInList() {
        List<Integer> list = Arrays.asList(1, 4, 5, 9, 7, 10);
        System.out.println(list.stream().reduce(0, (a, b) -> a + b));
        System.out.println(list.stream().reduce(0, Integer::sum));
    }

    /*
     * Problem 2: Concatenate all strings in a list using reduce.
     */
    @Test
    public void concatenateStrings() {
        List<String> list = Arrays.asList("Java", "Python", "Java", "Go");
        System.out.println(list.stream().reduce("", String::concat));
    }

    /*
     * Problem 3: Count words with more than 5 characters using count.
     */
    @Test
    public void countWordsMorethan5Chars() {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "Go");
        System.out.println(list.stream().filter(s -> s.length() > 5).count());
    }

    /*
     * Problem 4: Find the longest string in a list using reduce.
     */
    @Test
    public void findLongestStringInList() {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "Go");
        System.out.println(list.stream().reduce("", (s1, s2) -> s1.length() > s2.length() ? s1 : s2));
    }

    /*
     * Problem 5: Check if all strings in a list are lowercase using allMatch.
     */
    @Test
    public void checkAllStringInListAreLowercase() {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "Go");
        System.out.println(list.stream().allMatch(s -> s.toLowerCase().equals(s)));
        list = Arrays.asList("java", "python", "javascript", "go");
        System.out.println(list.stream().allMatch(s -> s.toLowerCase().equals(s)));
    }

    /*
     * Problem 6: Check if any string contains a digit using anyMatch.
     */
    @Test
    public void checkANystringContainsDigit() {
        List<List<String>> list1 = Arrays.asList(Arrays.asList("Java", "Python 3.x", "JavaScript", "Go"),
                Arrays.asList("Java", "Python", "JavaScript", "Go"));
        for (List<String> list : list1) {
            System.out.println(list.stream().anyMatch(
                    s -> s.chars().mapToObj(ch -> (char) ch).filter(Character::isDigit).findAny().isPresent()));
        }
    }

    /*
     * Problem 7: Check if no string is empty using noneMatch.
     */
    @Test
    public void checkNoStringIsEmpty() {
        List<List<String>> list1 = Arrays.asList(Arrays.asList("Java", "Python 3.x", "JavaScript", "Go"),
                Arrays.asList("Java", "", "JavaScript", "Go"));
        for (List<String> list : list1) {
            System.out.println(list.stream().noneMatch(String::isEmpty));
        }
    }

    /*
     * Problem 8: Find the average salary from a list of Employee objects using
     * collect.
     */
    @Test
    public void findAverageSalaryOfEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 2222, 30000));
        employeeList.add(new Employee("Carlos", 432, 10000));
        employeeList.add(new Employee("Daniel", 2112, 5000));

        System.out.println(employeeList.stream().collect(Collectors.averagingInt(Employee::getSalary)));

    }

    /*
     * Problem 9: Join all strings from a list with a delimiter using
     * Collectors.joining.
     */
    @Test
    public void joinAllStringsWithDelimiter() {
        List<String> list = Arrays.asList("Java", "Python", "JavaScript", "Go");
        System.out.println(list.stream().collect(Collectors.joining(";")));
    }

    /*
     * Problem 10: Group strings by their length using Collectors.groupingBy.
     */
    @Test
    public void groupStringsByLength() {
        List<String> list = Arrays.asList("apple", "banana", "orange", "kiwi", "grape");
        System.out.println(list.stream().collect(Collectors.groupingBy(String::length)));
    }

    /*
     * Problem 11: Partition a list of numbers into multiples of 3 and non-multiples
     * using Collectors.partitioningBy.
     */
    @Test
    public void partitionListMultiplesOf3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 99, 33, 11, 12, 45, 67, 16, 100, 19);
        System.out.println(list.stream().collect(Collectors.partitioningBy(num -> num % 3 == 0)));
    }

    /*
     * Problem 12: Find the frequency of each character in a string using streams.
     */
    @Test
    public void findFrequencyOfeachCharacter() {
        String str = "Learn Java Programming By Practise ";
        System.out.println(str.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    /*
     * Problem 13: Collect unique word lengths from a sentence using map and
     * collect.
     */
    @Test
    public void uniqueWordLengthFromSentence() {
        String str = "Programming problem java javascript java problem world learn practise larn world";
        System.out.println(Arrays.stream(str.split("\\s+")).distinct().map(String::length).collect(Collectors.toSet()));
    }

    /*
     * Problem 14: Get the employee name with the highest salary using max.
     */
    @Test
    public void getEmployeeNameWithHighestSalary() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 322, 30000));
        employeeList.add(new Employee("Carlos", 1211, 10000));
        employeeList.add(new Employee("Daniel", 7654, 5000));
        employeeList.stream().max(Comparator.comparingInt(Employee::getSalary)).ifPresent(System.out::println);
        ;
    }

    /*
     * Problem 15: Find the first string longer than 10 characters using findFirst.
     */
    @Test
    public void findLongerStingThan10Chars() {
        List<String> list = Arrays.asList(
                "apples", "custard apples", "pineapples", "banana", "orange", "kiwi", "grape", "watermelons");
        list.stream().filter(s -> s.length() > 10).findFirst().ifPresent(System.out::println);
    }

    /*
     * Problem 16: Convert a stream of objects to a map using Collectors.toMap.
     */
    @Test
    public void convertStreamOfObjectsToMap() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 3332, 30000));
        employeeList.add(new Employee("Carlos", 6412, 10000));
        employeeList.add(new Employee("Daniel", 312, 5000));
        employeeList.add(new Employee("Daniel", 5432, 51000));
        System.out.println(employeeList.stream().collect(
                Collectors.toMap(Employee::getName, Employee::getSalary, Math::max)));
    }

    /*
     * Problem 17: Get a summary of integer list using summaryStatistics.
     */
    @Test
    public void summaryOfIntegerList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 99, 33, 11, 12, 45, 67, 16, 100, 19);
        System.out.println(list.stream().mapToInt(Integer::valueOf).summaryStatistics());

    }

    /*
     * Problem 18: Filter and collect names starting with 'A' to a sorted list.
     */
    @Test
    public void filterAndCOllectNamesStartsWithA() {
        List<String> list = Arrays.asList("Ariel", "Daniel", "Arun", "Babu");
        System.out.println(list.stream().filter(s -> s.charAt(0) == 'A').toList());
    }

    /*
     * Problem 19: Calculate the total word count from a list of sentences.
     */
    @Test
    public void calculateTotalWordCounts() {
        List<String> list = Arrays.asList("Hello World! Programming is easy to learn",
                "Welcome back! to Java Programming, We are at Java8 Stream Terminal Operators");
        System.out.println(list.stream().map(s -> s.split("\\s+")).flatMap(Arrays::stream).count());
    }

    /*
     * Problem 20: Find duplicate elements in a list and collect them into a set.
     */
    @Test
    public void findDuplicateElementsInList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 99, 33, 2, 4, 5, 99, 33);
        HashSet<Integer> hashSet = new HashSet<>();
        System.out.println(list.stream().filter(n -> !hashSet.add(n)).collect(Collectors.toSet()));
    }

    /*
     * Problem 21: Find the employee with the lowest salrary using min and
     * Comparator.
     */
    @Test
    public void findEmployeeWithLowestSalary() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 231, 30000));
        employeeList.add(new Employee("Carlos", 654, 10000));
        employeeList.add(new Employee("Daniel", 567, 5000));
        employeeList.stream().min(Comparator.comparingInt(Employee::getSalary)).ifPresent(System.out::println);
    }

    /*
     * Problem 22: Calculate total cost of products grouped by category using
     * Collectors.groupingBy and summingDouble.
     */

    @Test
    public void calculateTotalCostOfproductsByCategory() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("IPhone", "smartphones", 200));
        products.add(new Product("Calvin Klein CK One", "fragrances", 120));
        products.add(new Product("Samsung", "smartphones", 49.9));
        products.add(new Product("Dog Food", "groceries", 23.5));
        products.add(new Product("Gucci Bloom Eau de", "fragrances", 100));
        products.add(new Product("Realme", "smartphones", 19.9));

        System.out.println(products.stream().collect(
                Collectors.groupingBy(Product::getCategory, Collectors.summarizingDouble(Product::getCost))));
    }

    /*
     * Problem 23: Convert a list of comma-separated strings into a flat list of
     * integers and compute the sum.
     */
    @Test
    public void convertListofStringToIntegerAndComputeSum() {
        List<String> list = Arrays.asList("1,2,3,4,5,6,7,8,9", "9,8,7,6,5,4,3,2,1", "1,3,5,7,9", "2,4,6,8");
        System.out.println(
                list.stream()
                        .map(s -> s.split(",")).flatMap(Arrays::stream)
                        .map(Integer::parseInt).reduce(0, Integer::sum));
    }

    /*
     * Problem 24: Group a list of books by author and collect titles per author
     * using groupingBy and mapping.
     */
    @Test
    public void groupListOfBooksByAuthorAndCollectTitlesPerAuthor() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Prograaming", "Arun Devgan", 2010));
        books.add(new Book("Java8 Advances", "Arun Devgan", 2011));
        books.add(new Book("AWS Developer Associate", "Stephen Maark", 2021));
        books.add(new Book("Azure Administrator", "John Savil", 2022));
        books.add(new Book("AWS Developer Associate", "Neal Davis", 2024));
        books.add(new Book("Azure Fundamentals", "John Savil", 2025));
        System.out.println(books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor,
                        Collectors.mapping(Book::getTitle, Collectors.toList()))));
    }

    /*
     * Problem 25: Check if all numbers in a list are prime using allMatch.
     */
    private boolean isPrime(int n) {
        if (n <= 1)
            return false;
        return IntStream.rangeClosed(2, (int) Math.sqrt(n)).allMatch(i -> n % i != 0);
    }

    @Test
    public void checkAllNumbersArePrime() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 99, 33, 2, 4, 5, 99, 33);
        // list = Arrays.asList(3,5, 7, 11, 13, 17, 19, 23);
        // System.out.println(list.stream().allMatch(num -> IntStream.range(2, num / 2 +
        // 1).allMatch(i -> num % i != 0)));
        System.out.println(list.stream().allMatch(this::isPrime));
    }

    /*
     * Problem 26: Partition words into palindromes and non-palindromes.
     */
    @Test
    public void partitionWordsPalindromic() {
        List<String> list = Arrays.asList(
                "apples", "custard apples", "madam", "deed", "noon", "rotator");
        System.out.println(list.stream()
                .collect(Collectors.partitioningBy(s -> new StringBuilder(s).reverse().toString().equals(s))));
    }

    /*
     * Problem 27: Find the second-highest number in a list using streams.
     */
    @Test
    public void findSecondHighestNumber() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 6, 99, 33, 2, 4, 5, 12, 66);
        list.stream().sorted().skip(list.size() - 2).findFirst().ifPresent(System.out::println);
        list.stream().sorted(Collections.reverseOrder()).skip(1).findFirst().ifPresent(System.out::println);
    }

    /*
     * Problem 28: Get the name of the Highest Salray EMployee in a list using
     * collect(Collectors.maxBy(...)).
     */
    @Test
    public void highestSalaryEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Arun", 123, 30000));
        employeeList.add(new Employee("Babu", 2222, 35000));
        employeeList.add(new Employee("Carlos", 121243, 10000));
        employeeList.add(new Employee("Daniel", 54311, 5000));
        employeeList.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))).ifPresent(System.out::println);
    }

    /*
     * Problem 29: Count the frequency of each word in a sentence using
     * Collectors.groupingBy and counting.
     */
    @Test
    public void countFrequencyOfEachWord() {
        String str = "Programming problem java javascript java problem world learn practise learn world";
        System.out.println(
                Arrays.stream(str.split("\\s+"))
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    /*
     * Problem 30: Generate statistics (min, max, average) on student marks using
     * IntSummaryStatistics.
     */
    @Test
    public void generateStatistics() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 123, 89, 90, 95, 99));
        students.add(new Student("Arun", 234, 80, 90, 70, 60));
        students.add(new Student("Paul", 211, 40, 80, 70, 50));

        System.out.println(students.stream().map(Student::getMarks).flatMapToInt(Arrays::stream).summaryStatistics());
    }
}
