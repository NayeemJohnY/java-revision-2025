package tests;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.java_revision.Book;
import com.java_revision.Event;
import com.java_revision.Product;
import com.java_revision.SimplePerson;
import com.java_revision.Student;

public class TestSteamCollectorsAPIPractice {

    // Beginner (1–10)

    // Problem 1: Convert a List<String> of names to a Set<String> using Collectors.
    @Test
    public void testListToSetConversion() {
        List<String> list = Arrays.asList("Welcome", "to", "java", "programming", "java", "Welcome", "to", "to");
        Assert.assertEquals(list.stream().collect(Collectors.toSet()),
                new HashSet<>(Arrays.asList("Welcome", "to", "java", "programming")));
    }

    // Problem 2: Convert a list of strings to a map of string to its length
    @Test
    public void testListToMapConversion() {
        List<String> list = Arrays.asList("Welcome", "to", "java", "programming");
        Assert.assertEquals(
                list.stream().collect(Collectors.toMap(Function.identity(), String::length)),
                Map.of("Welcome", 7, "to", 2, "java", 4, "programming", 11));
    }

    // Problem 3: Count how many Strings have Length > 5
    @Test
    public void testCountStringsLongerThanFive() {
        List<String> list = Arrays.asList("Welcome", "to", "java", "programming");
        Assert.assertEquals(list.stream().filter(str -> str.length() > 5).collect(Collectors.counting()), 2);
    }

    // Problem 4: Join a list of strings into a single comma-separated string
    @Test
    public void testJoinStringsWithComma() {
        List<String> list = Arrays.asList("Welcome", "to", "java", "programming");
        Assert.assertEquals(
                list.stream().collect(Collectors.joining(",")), "Welcome,to,java,programming");
    }

    // Problem 5: Given a list of Person(name, age), find the average age using
    // Collectors.averagingInt.
    @Test
    public void testAverageAgeOfPeople() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 25));
        persons.add(new SimplePerson("DSA", 29));
        persons.add(new SimplePerson("QAW", 30));
        Assert.assertEquals(persons.stream().collect(Collectors.averagingInt(SimplePerson::getAge)).doubleValue(),
                (25 + 29 + 30) / 3);
    }

    // Problem 6: Find the sum of integers in a list using Collectors.summingInt.
    @Test
    public void testSumOfIntegers() {
        List<Integer> list = Arrays.asList(23, 21, 25, 26, 27, 28, 29, 30, 22);
        Assert.assertEquals(list.stream().collect(Collectors.summingInt(Integer::intValue)), 231);
    }

    // Problem 7: Group a list of strings by their length
    @Test
    public void testGroupStringsByLength() {
        List<String> list = Arrays.asList("js", "go", "java", "ruby", "Javascript", "perl", "Python");
        System.out.println(list.stream().collect(Collectors.groupingBy(String::length)));
    }

    // Problem 8: Partition a list of integers into even and odd numbers using
    // Collectors.partitioningBy.
    @Test
    public void testPartitionEvenOddNumbers() {
        List<Integer> list = Arrays.asList(23, 21, 25, 26, 27, 28, 29, 30, 22);
        System.out.println(list.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)));
    }

    // Problem 9: Find the longest string in a list using collectingAndThen /
    // Collectors.maxBy
    @Test
    public void testFindLongestString() {
        List<String> list = Arrays.asList("js", "go", "java", "ruby", "Javascript", "perl", "Python");
        list.stream().collect(Collectors.maxBy(Comparator.comparingInt(String::length))).ifPresent(System.out::println);
    }

    // Problem 10: Convert stream of strings to TreeSet in natural order
    @Test
    public void testCollectToTreeSet() {
        List<Integer> list = Arrays.asList(23, 21, 25, 22, 21, 25, 30, 31, 26, 27, 28, 29, 30, 22);
        System.out.println(list.stream().collect(Collectors.toCollection(() -> new TreeSet<>())));
        System.out.println(list.stream().collect(Collectors.toCollection(TreeSet::new)));
    }

    // Intermediate (11–20)
    // Problem 11: Given a list of Person(name, age), group people by age using
    // Collectors.groupingBy.
    @Test
    public void testGroupPeopleByAge() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30));
        persons.add(new SimplePerson("DSA", 29));
        persons.add(new SimplePerson("QAW", 30));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32));
        System.out.println(persons.stream().collect(Collectors.groupingBy(SimplePerson::getAge)));
    }

    // Problem 12: Group words by their first character.
    @Test
    public void testGroupWordsByFirstCharacter() {
        List<String> list = Arrays.asList("js", "go", "java", "ruby", "Javascript", "perl", "Python");
        System.out.println(list.stream().collect(Collectors.groupingBy(str -> str.toLowerCase().charAt(0))));
    }

    // Problem 13: Count how many times each age appears in a list of people
    @Test
    public void testCountOccurrencesByAge() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30));
        persons.add(new SimplePerson("DSA", 29));
        persons.add(new SimplePerson("QAW", 30));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32));
        System.out
                .println(persons.stream().collect(Collectors.groupingBy(SimplePerson::getAge, Collectors.counting())));
    }

    // Problem 14: Group people by age, and join their names as a comma-separated
    // string per age group.
    @Test
    public void testGroupAndJoinNamesByAge() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30));
        persons.add(new SimplePerson("DSA", 29));
        persons.add(new SimplePerson("QAW", 30));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32));
        System.out.println(persons.stream().collect(
                Collectors.groupingBy(SimplePerson::getAge,
                        Collectors.mapping(SimplePerson::getName, Collectors.joining(",")))));
    }

    // Problem 15: Group people by gender and find the oldest person in each group.
    @Test
    public void testFindOldestPersonByGender() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30, "Male"));
        persons.add(new SimplePerson("DSA", 29, "Female"));
        persons.add(new SimplePerson("QAW", 30, "Female"));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32, "Male"));
        // persons.stream().collect(Collectors.groupingBy(
        // SimplePerson::getGender,
        // Collectors.maxBy(Comparator.comparingInt(SimplePerson::getAge))));
        System.out.println(persons.stream().collect(Collectors.groupingBy(
                p -> p.getGender() == null ? "Unknown" : p.getGender(),
                Collectors.maxBy(Comparator.comparingInt(SimplePerson::getAge)))));
    }

    // Problem 16: Partition a list of integers by even/odd and sum each partition.
    @Test
    public void testPartitionAndSumIntegers() {
        List<Integer> list = Arrays.asList(23, 21, 25, 22, 21, 25, 30, 31, 26, 27, 28, 29, 30, 22);
        System.out.println(list.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0, Collectors.summingInt(Integer::intValue))));
    }

    // Problem 17: Implement a custom collector to collect elements into a list in
    // reverse order.
    @Test
    public void testCustomCollectorReverseList() {
        List<Integer> list = Arrays.asList(23, 21, 25, 22, 21, 25, 30, 31, 26, 27, 28, 29, 30, 22);
        Collector<Integer, List<Integer>, List<Integer>> toReverseList = Collector.of(
                ArrayList::new,
                (acc, element) -> acc.add(0, element),
                (left, right) -> {
                    left.addAll(0, right);
                    return left;
                });
        System.out.println(list.stream().collect(toReverseList));
    }

    // Problem 18: Convert a list of strings to a map with first character as key
    // and merged names with same initial.
    // values
    @Test
    public void testListToMapWithMergeFunction() {
        List<String> list = Arrays.asList("js", "go", "java", "ruby", "Javascript", "perl", "python");
        System.out.println(list.stream().collect(
                Collectors.groupingBy(s -> s.toLowerCase().charAt(0),
                        Collectors.mapping(s -> s, Collectors.joining(",")))));
    }

    // Problem 19: Group a list of people by age, and map each group to a list of
    // names.
    @Test
    public void testGroupingAndMappingNamesByAge() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30, "Male"));
        persons.add(new SimplePerson("DSA", 29, "Female"));
        persons.add(new SimplePerson("QAW", 30, "Female"));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32, "Male"));
        System.out.println(persons.stream().collect(Collectors.groupingBy(SimplePerson::getAge,
                Collectors.mapping(SimplePerson::getName, Collectors.toList()))));
    }

    // Problem 20: Group Person by age, then by gender.
    @Test
    public void testMultiLevelGroupingEmployees() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30, "Male"));
        persons.add(new SimplePerson("DSA", 29, "Female"));
        persons.add(new SimplePerson("QAW", 30, "Female"));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32, "Male"));
        System.out.println(persons.stream().collect(
                Collectors.groupingBy(SimplePerson::getAge, Collectors.groupingBy(
                        p -> p.getGender() == null ? "Unknown" : p.getGender()))));
    }

    // Advanced (21–30)

    // Problem 21: Get count, min, max, average, and sum of a list of integers using
    // Collectors.summarizingInt.
    @Test
    public void testSummarizingStatistics() {
        List<Integer> list = Arrays.asList(23, 21, 25, 22, 21, 25, 30, 31, 26, 27, 28, 29, 30, 22);
        System.out.println(list.stream().collect(Collectors.summarizingInt(Integer::intValue)));
    }

    // Problem 22: Group students by class and compute average marks per class.
    @Test
    public void testNestedGroupingWithSummary() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", 123, "VI", 89, 90, 95, 99));
        students.add(new Student("Arun", 234, "V", 80, 90, 70, 60));
        students.add(new Student("Paul", 211, "VI", 40, 80, 70, 50));
        students.add(new Student("Sam", 321, "IV", 40, 80, 70, 50));
        students.add(new Student("Peter", 323, "III", 40, 80, 70, 50));
        students.add(new Student("Peter", 453, "III", 40, 80, 70, 50));

        System.out.println(students.stream()
                .collect(Collectors.groupingBy(Student::getGrade,
                        Collectors.averagingDouble(Student::calculateAverage))));
    }

    // Problem 23: Partition people by age > 18 and collect names in each group.
    @Test
    public void testPartitionPeopleByAgeAndMapNames() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30));
        persons.add(new SimplePerson("DSA", 19));
        persons.add(new SimplePerson("QAW", 30));
        persons.add(new SimplePerson("Dave", 17));
        persons.add(new SimplePerson("Grook", 18));
        persons.add(new SimplePerson("Mike", 15));
        System.out.println(persons.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() > 18,
                        Collectors.mapping(SimplePerson::getName, Collectors.toList()))));
    }

    // Problem 24: Collect a list of List<String> into a flat list using
    // flatMapping.
    @Test
    public void testFlatMappingListsOfStrings() {
        List<List<String>> listOfList = Arrays.asList(
                Arrays.asList("js", "go", "java", "ruby", "Javascript", "perl", "python"),
                Arrays.asList("Kiwi", "banana", "apple", "mango", "pineapple"),
                Arrays.asList("Red", "blue", "yellow", "green", "black"));

        System.out.println(listOfList.stream().collect(Collectors.flatMapping(List::stream, Collectors.toList())));
    }

    // Problem 25: Group products by category and find the top 3 most expensive in
    // each category
    @Test
    public void testTopNProductsByCategory() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("IPhone", "smartphones", 200));
        products.add(new Product("Calvin Klein CK One", "fragrances", 120));
        products.add(new Product("Samsung", "smartphones", 49.9));
        products.add(new Product("Dog Food", "groceries", 23.5));
        products.add(new Product("Gucci Bloom Eau de", "fragrances", 100));
        products.add(new Product("Axe Super Fresh", "fragrances", 150));
        products.add(new Product("Niviea Deo", "fragrances", 110));
        products.add(new Product("Realme", "smartphones", 19.9));
        products.add(new Product("Redmi", "smartphones", 20.9));
        products.add(new Product("Moto", "smartphones", 25.0));

        System.out.println(products.stream().collect(
                Collectors.groupingBy(Product::getCategory,
                        Collectors.collectingAndThen(Collectors.toList(),
                                list -> list.stream().sorted((p1, p2) -> Double.compare(p2.getCost(), p1.getCost()))
                                        .limit(3).toList()))));
    }

    // Problem 26: Group events by their LocalDate occurrence.
    @Test
    public void testGroupEventsByDate() {
        List<Event> events = new ArrayList<>();
        events.add(new Event("Warm up Session"));
        events.add(new Event("Day 1 Session - Intro", LocalDate.now().plusDays(1)));
        events.add(new Event("Day 1 Session - Resrouces", LocalDate.now().plusDays(1)));
        events.add(new Event("Day 5 Session - Guide", LocalDate.now().plusDays(5)));
        events.add(new Event("Day 5 Session - Learn Path", LocalDate.now().plusDays(5)));
        events.add(new Event("Day 0 Registration", LocalDate.now().minusDays(1)));
        events.add(new Event("Event Day"));
        events.add(new Event("3 Days to Go - Reminder", LocalDate.now().minusDays(3)));

        System.out.println(events.stream().collect(Collectors.groupingBy(Event::getDate)));
        System.out.println(events.stream().collect(
                Collectors.groupingBy(Event::getDate, Collectors.mapping(Event::getName, Collectors.toList()))));
    }

    // Problem 27: Group books by author and year (combine as a tuple or custom key
    @Test
    public void testGroupBooksByAuthorAndYear() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Java Prograaming", "Arun Devgan", 2010));
        books.add(new Book("Java8 Advances", "Arun Devgan", 2011));
        books.add(new Book("AWS Developer Associate", "Stephen Maark", 2021));
        books.add(new Book("Azure Administrator", "John Savil", 2022));
        books.add(new Book("AWS Developer Associate", "Neal Davis", 2024));
        books.add(new Book("Azure Fundamentals", "John Savil", 2022));
        System.out.println(books.stream()
                .collect(Collectors
                        .groupingBy(book -> new AbstractMap.SimpleEntry<>(book.getAuthor(), book.getYear()))));
    }

    // Problem 28: Collect to an unmodifiable list using collectingAndThen
    // @Test (expectedExceptions = UnsupportedOperationException.class)
    @Test
    public void testCollectToUnmodifiableList() {
        List<Integer> list = Arrays.asList(23, 21, 25, 22, 21, 25, 30, 31, 26, 27, 28, 29, 30, 22);
        List<Integer> unmodifiableList = list.stream().collect(
                Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        unmodifiableList.add(100);
    }

    // Problem 29: Merge multiple maps in a stream using collectors.
    @Test
    public void testMergeMultipleMaps() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Fruit", "Banana");
        hashMap.put("Programming", "Java");
        hashMap.put("Color", "Yellow");
        hashMap.put("HashMap", "key Value pair");

        HashMap<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("Fruit", "Mango");
        hashMap2.put("Programming", "Python");
        hashMap2.put("Yellow", "Color");
        hashMap2.put("HashSet", "Uniqueness");

        List<Map<String, String>> maps = Arrays.asList(hashMap, hashMap2);
        System.out.println(maps.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> new ArrayList<>(Collections.singletonList(entry.getValue())),
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;
                        })));

    }

    // Problem 30: Count frequency of each word in a sentence using
    // Collectors.groupingBy and Collectors.counting().
    @Test
    public void testBuildFrequencyMapOfWords() {
        String str = "banana apple kiwi apple banana mango peach";
        System.out.println(Arrays.stream(str.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    @Test
    // Problem 2: Partition a list of integers into even and odd
    public void testPartitionEvenOdd() {
        List<Integer> list = Arrays.asList(1, 4, 5, 6, 7, 8, 11, 13, 15, 19);
        System.out.println(list.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)));
    }

    @Test
    // Problem 3: Count the number of occurrences of each word in a list
    public void testCountWordOccurrences() {
        List<String> list = Arrays.asList("red", "black", "yellow", "red", "black", "black", "white");
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    @Test
    // Problem 5: Get the average length of strings in a list
    public void testAverageStringLength() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().collect(Collectors.averagingInt(String::length)));
    }

    @Test
    // Problem 8: Collect all characters from a list of strings into a set
    public void testCollectCharactersToSet() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().map(String::chars).flatMapToInt(c -> c).mapToObj(c -> (char) c)
                .collect(Collectors.toSet()));
    }

    @Test
    // Problem 9: Group strings by first character
    public void testGroupByFirstCharacter() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().collect(Collectors.groupingBy(s -> s.charAt(0))));
    }

    @Test
    // Problem 10: Partition list of numbers into multiples of 3 and not
    public void testPartitionByMultipleOfThree() {
        List<Integer> list = Arrays.asList(1, 3, 4, 5, 6, 12, 12, 13, 15, 19, 21, 24, 23);
        System.out.println(list.stream().collect(Collectors.partitioningBy(n -> n % 3 == 0)));
    }

    @Test
    // Problem 11: Create a map from a list of strings with string as key and
    // occurrence count as value
    public void testCreateFrequencyMap() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }

    @Test
    // Problem 12: Find the sum of lengths of all strings
    public void testSumOfStringLengths() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().collect(Collectors.summarizingInt(String::length)).getSum());
    }

    @Test
    // Problem 13: Convert list of strings to upper case and collect into a list
    public void testConvertToUpperAndCollect() {
        List<String> list = Arrays.asList("banana", "apple", "kiwi", "apple", "banana", "mango", "peach");
        System.out.println(list.stream().map(String::toUpperCase).toList());
    }
}
