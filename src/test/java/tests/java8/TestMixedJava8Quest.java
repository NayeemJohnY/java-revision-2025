package tests.java8;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.annotations.Test;

import com.java_revision.Employee;
import com.java_revision.OrderService;

public class TestMixedJava8Quest {

    // 1. Filter Employees with Optional Email
    @Test
    public void testfilterEmployeesWithEmail() {
        // Use Stream + Optional to return non-null email IDs
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com"));
        employeeList.add(new Employee("D", 126, 5000));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com"));

        List<String> emails = employeeList.stream()
                .map(Employee::getEmailId)
                .map(Optional::ofNullable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        System.out.println(emails);

        emails = employeeList.stream()
                .map(Employee::getEmailIDOptional).filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        System.out.println(emails);

    }

    // 2. Find First Non-Repeated Character in String
    @Test
    public void findFirstUniqueChar() {
        // Use Stream to identify the first non-repeating character
        String input = "programming";
        input.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(t -> t.getValue() == 1).findFirst()
                .ifPresent(t -> System.out.println(t.getKey()));
    }

    // 3. Sort a List of Orders by Date Using Comparator.comparing
    @Test
    public void sortListOfOrdersByDate() {
        List<OrderService> orders = new ArrayList<>();
        orders.add(new OrderService(1001, "IPhone", 2, 29.9, LocalDate.now()));
        orders.add(new OrderService(1005, "Samsung", 6, 19.9, LocalDate.now().plusDays(3)));
        orders.add(new OrderService(1001, "Windows Laptop Dell", 5, 200.5, LocalDate.now().plusMonths(2)));
        orders.add(new OrderService(1011, "Water Bottle", 6, 5, LocalDate.now().minusDays(5)));
        orders.add(new OrderService(1011, "HeadPhones", 5, 100.15, LocalDate.now().plusDays(2)));

        orders.sort(Comparator.comparing(OrderService::getOrderDate));
        System.out.println(orders);
    }

    // 4. Convert a List of Strings to Uppercase Using Map
    @Test
    public void convertToUpper() {
        // Use Stream map() + method reference
        List<String> list = Arrays.asList("hello", "world");
        System.out.println(list.stream().map(String::toUpperCase).toList());
    }

    // 5. Group Employees by Department
    @Test
    public void groupByDepartment() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)));
    }

    // 6. Get Top 3 Highest Salaried Employees
    @Test
    public void getTop3Salaries() {
        // Sort and limit using Stream
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com"));
        employeeList.add(new Employee("D", 126, 5000));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com"));
        employeeList.add(new Employee("G", 1111, 50000, "fiftyemployee@emplooyee.com"));
        System.out.println(employeeList.stream().sorted((e1, e2) -> Integer.compare(e2.getSalary(), e1.getSalary()))
                .limit(3).toList());
    }

    // 7. Execute Two Tasks in Parallel and Combine Result
    @Test
    public void combineTasks() {
        // Use supplyAsync + thenCombine
        CompletableFuture<String> taskA = CompletableFuture.supplyAsync(() -> {
            return "Task A";
        });

        CompletableFuture<String> taskB = CompletableFuture.supplyAsync(() -> {
            return "Task B";
        });

        taskA.thenCombine(taskB, (a, b) -> {
            return a + ":Done " + b + ":Done";
        }).thenAccept(System.out::println).join();

    }

    // 8. Get Average Salary by Department
    @Test
    public void getAverageSalaryByDept() {
        // Use groupingBy + averagingDouble
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingInt(Employee::getSalary))));
    }

    // 9. Chain CompletableFuture to Fetch User & Address
    @Test
    public void getUserDetails() {
        CompletableFuture.supplyAsync(() -> {
            return "John";
        }).thenCompose(username -> CompletableFuture.supplyAsync(() -> {
            return username + ", Address: 123 Street";
        })).thenAccept(System.out::println).join();
    }

    // 10. Find Most Frequent Word in a List
    @Test
    public void mostFrequentWord() {
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        System.out.println(words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null));
    }

    // 11. Count Duplicate Elements in List
    @Test
    public void countDuplicates() {
        // Streams to count duplicates
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        Map<String, Long> countMap = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(countMap.entrySet().stream().filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    // 12. Get Employees Sorted by Department and Name
    @Test
    public void sortByDeptAndName() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 101, 40000, "a@x.com", "IT"),
                new Employee("Bob", 102, 50000, "b@x.com", "HR"),
                new Employee("Charlie", 103, 30000, "c@x.com", "IT"),
                new Employee("David", 104, 45000, "d@x.com", "HR"));
        employees = employees.stream().sorted(
                Comparator.comparing(Employee::getDepartment).thenComparing(Comparator.comparing(Employee::getName)))
                .toList();
        System.out.println(employees);
    }

    // 13. Convert List of Employees to Map<Id, Name>
    @Test
    public void listToMap() {
        List<Employee> employees = Arrays.asList(
                new Employee("X", 1, 5000),
                new Employee("Y", 2, 6000),
                new Employee("Z", 3, 7000));
        // Convert to Map of ID -> Name
        System.out.println(employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getName)));
    }

    // 14. Get Employee with Max Salary using Reduce
    @Test
    public void maxSalaryUsingReduce() {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 1, 25000),
                new Employee("B", 2, 40000),
                new Employee("C", 3, 20000));
        // Use reduce to find employee with max salary
        BinaryOperator<Employee> accumulator = (a, b) -> a.getSalary() > b.getSalary() ? a : b;
        employees.stream().reduce(accumulator).ifPresent(System.out::println);
    }

    // 15. Flatten a List of Lists
    @Test
    public void flattenListOfLists() {
        List<List<String>> list = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f"));
        // Flatten into single list
        List<String> flattenList = list.stream().flatMap(List::stream).toList();
        System.out.println(flattenList);
    }

    // 16. Find Employees without Email Using Optional
    @Test
    public void employeesWithoutEmail() {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 101, 30000),
                new Employee("B", 102, 35000, "b@x.com"),
                new Employee("C", 103, 40000));
        // ilter employees with no email (Optional isEmpty)
        System.out.println(employees.stream().filter(e -> e.getEmailIDOptional().isEmpty()).toList());
    }

    // 17. Execute Tasks in Parallel and Get Fastest Result
    @Test
    public void fastestTaskResult() {
        Random random = new Random();
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            TestCompletableFuture.threadSleep(random.nextInt(5) * 2000);
            return "Task1";
        });

        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            TestCompletableFuture.threadSleep(random.nextInt(5) * 2000);
            return "Task2";
        });

        // Use applyToEither to get first finished task
        CompletableFuture<String> result = task1.applyToEither(task2, str -> {
            return str + ":Done";
        });

        System.out.println(result.join());
    }

    // 18. Partition Employees by Salary > 20000
    @Test
    public void partitionBySalary() {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 1, 15000),
                new Employee("B", 2, 25000),
                new Employee("C", 3, 22000),
                new Employee("D", 4, 18000));
        // Partition into two groups: salary > 20000 and <= 20000
        System.out.println(employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() > 20000)));
    }

    // 19. Remove Duplicates and Sort Names Alphabetically
    @Test
    public void removeDupAndSort() {
        List<String> names = Arrays.asList("John", "Alice", "Bob", "Alice", "John", "David");
        // Remove duplicates and sort
        System.out.println(names.stream().distinct().sorted().toList());
    }

    // 20. Find Second Highest Salary
    @Test
    public void secondHighestSalary() {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 1, 50000),
                new Employee("B", 2, 30000),
                new Employee("C", 3, 45000),
                new Employee("D", 4, 60000));
        // Sort and skip to get second highest salary
        employees.stream().sorted((e1, e2) -> e2.getSalary() - e1.getSalary()).skip(1).findFirst()
                .ifPresent(System.out::println);
    }

    // 21. Find Employee(s) With Minimum Salary in Each Department
    @Test
    public void findEmployeesWithMinimumSalary() {
        // Use Collectors.groupingBy and Collectors.collectingAndThen to get the
        // lowest-paid employee(s) per department.
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        employeeList.add(new Employee("H", 127, 5000, null, "HR"));

        // Eomployee
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.minBy(Comparator.comparingInt((Employee::getSalary))),
                                Optional::get))));
        System.out.println("========================");

        // Eomployees
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.toList(), list -> {
                            int minSalary = list.stream().mapToInt(Employee::getSalary).min().orElse(0);
                            return list.stream().filter(e -> e.getSalary() == minSalary).toList();
                        }))));
    }

    // 22. Convert List of Employees to JSON String of Names
    @Test
    public void convertListOfEmployeesToJSONStringOfNames() {
        // Convert a list of Employee objects to a JSON-style comma-separated string of
        // their names using Collectors.joining.
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        employeeList.add(new Employee("H", 127, 5000, null, "HR"));

        System.out.println(employeeList.stream().map(Employee::getName).collect(Collectors.joining(",")));
    }

    // 23. Get Summary Statistics of Salaries
    @Test
    public void getSummaryStatisticsOfSalaries() {
        // Use IntSummaryStatistics from Collectors.summarizingInt to get min, max,
        // average, and count of salaries.
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        employeeList.add(new Employee("H", 127, 5000, null, "HR"));

        System.out.println(employeeList.stream().collect(Collectors.summarizingInt(Employee::getSalary)));
    }

    // /24. Check if All Employees Have Email
    @Test
    public void checkAllEmployeesHaveEmail() {
        // Use allMatch with Optional::isPresent to check if all employees have non-null
        // email addresses.
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("D", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        employeeList.add(new Employee("H", 127, 5000, null, "HR"));

        System.out.println(employeeList.stream().map(Employee::getEmailIDOptional).allMatch(Optional::isPresent));
    }

    // 25. Generate 10 Random Unique Numbers Between 1 and 100
    @Test
    public void generate10RandomUniqueNumbers() {
        // Use Stream.generate, distinct(), and limit() to generate 10 unique random
        // numbers.
        System.out.println(Stream.generate(() -> new Random().nextInt(100)).distinct().limit(10).toList());
    }

    // 26. Asynchronously Fetch and Merge Order Details and Shipping Status
    @Test
    public void asynchronousFetchMerge() {
        // Simulate fetching order and shipping status using CompletableFuture and merge
        // the result.
        CompletableFuture<List<Integer>> orders = CompletableFuture.supplyAsync(() -> {
            return Arrays.asList(1, 2, 3, 4, 7, 6, 8, 9, 10);
        });

        CompletableFuture<HashMap<Integer, Boolean>> shippingStatus = CompletableFuture.supplyAsync(() -> {
            HashMap<Integer, Boolean> shippingMap = new HashMap<>();
            for (int i = 1; i <= 10; i++) {
                shippingMap.put(i, i % 2 == 0);
            }
            return shippingMap;
        });

        CompletableFuture<List<String>> result = orders.thenCombine(shippingStatus, (orderslist, shippingmap) -> {
            return orderslist.stream()
                    .map(o -> "Order " + o + " shipping is " + (shippingmap.get(o) ? "Completed" : "Pending")).toList();
        });

        result.thenAccept(System.out::println).join();
    }

    // 27. Print Top N Frequent Elements in a List
    @Test
    public void printTopNFrequeentElements() {
        // From a list of strings, print top N most frequent items using a frequency map
        // and sorted entries.
        List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry", "avocado", "banana", "apple",
                "mango", "apple", "cherry");
        int n = 5;
        List<Entry<String, Long>> list = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(n).toList();
        System.out.println(list);
    }

    // 28. Find All Palindrome Strings in a List
    @Test
    public void filterPalindromStrings() {
        // Use Stream to filter strings that are palindromes (e.g., “madam”, “level”).
        List<String> words = Arrays.asList("apple", "banana", "apricot", "madam", "level", "rotator");
        System.out.println(words.stream().filter(s -> new StringBuilder(s).reverse().toString().equals(s)).toList());
    }

    // 29. Get Employees with Name Starting with a Specific Letter per Department
    @Test
    public void getEmployeesStartWithLetterPerDepartment() {
        // Group by department and filter names starting with a given letter, like "A".
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Ajay", 123, 30000, null, "HR"));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com", "IT"));
        employeeList.add(new Employee("Alice", 126, 5000, null, "HR"));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com", "Support"));
        employeeList.add(new Employee("H", 127, 5000, null, "HR"));

        // Eomployee
        System.out.println(employeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().filter(e -> e.getName().startsWith("A")).toList()))));
    }

    private CompletableFuture<String> retryTask(Supplier<String> task, int retriesLeft) {
        return CompletableFuture.supplyAsync(task)
                .handle((res, ex) -> {
                    if (ex == null)
                        return CompletableFuture.completedFuture(res);

                    if (retriesLeft > 0) {
                        System.out.println("Retrying... attempts left: " + retriesLeft);
                        return retryTask(task, retriesLeft - 1);
                    } else {
                        CompletableFuture<String> failed = new CompletableFuture<>();
                        failed.completeExceptionally(ex);
                        return failed;
                    }
                }).thenCompose(Function.identity());
    }

    // 30. Retry a Task 3 Times Using CompletableFuture
    @Test
    public void retryTaskCompletableFutue() {
        // Create a task using CompletableFuture that retries a failing operation up to
        // 3 times before giving up.
        int retriesLeft = 3;
        retryTask(() -> {
            if (Math.random() < 0.7) { // 70% chance to fail
                throw new RuntimeException("Operation failed");
            }
            return "Success!";
        }, retriesLeft).thenAccept(System.out::println).exceptionally(ex -> {
            System.out.println("Failed after retries: " + ex.getMessage());
            return null;
        }).join();

    }

    // 21. Count Words Starting With a Given Letter
    @Test
    public void countWordsStartingWithLetter() {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry", "avocado");
        char startLetter = 'a';

        // Count how many words start with 'a'
        long count = words.stream().filter(s -> s.charAt(0) == startLetter).count();

        System.out.println("Count = " + count);
    }

    // 22. Join Employee Names by Department
    @Test
    public void joinNamesByDept() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 101, 30000, "alice@abc.com", "IT"),
                new Employee("Bob", 102, 32000, "bob@abc.com", "HR"),
                new Employee("Carol", 103, 31000, "carol@abc.com", "IT"));

        // Map<String, String> where key is dept, value is comma-separated employee
        // names
        Map<String, String> deptNameMap = employees.stream()
                .collect(Collectors.toMap(Employee::getDepartment, Employee::getName,
                        (e1Name, e2Name) -> e1Name + ", " + e2Name));
        System.out.println(deptNameMap);
    }

    // 23. Find Common Elements Between Two Lists
    @Test
    public void findCommonElements() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);

        // Find common elements using Java 8 Stream
        List<Integer> common = list1.stream().filter(e -> list2.contains(e)).toList();
        System.out.println(common);
    }

    // 24. Check if All Employees Have Emails
    @Test
    public void checkAllEmployeesHaveEmails() {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 101, 30000, "a@x.com", "IT"),
                new Employee("Bob", 102, 30000, null, "HR"));

        // Check if all employees have email using allMatch()
        boolean allHaveEmails = employees.stream().allMatch(e -> e.getEmailId() != null);

        System.out.println("All have emails: " + allHaveEmails);
    }

    // 25. Sort Employees by Salary in Descending Order
    @Test
    public void sortEmployeesBySalaryDesc() {
        List<Employee> employees = Arrays.asList(
                new Employee("X", 1, 25000),
                new Employee("Y", 2, 45000),
                new Employee("Z", 3, 30000));

        // Sort employees by salary descending
        List<Employee> sortedList = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .toList();

        System.out.println(sortedList);
    }

    // 26. Merge Two Lists and Remove Duplicates
    @Test
    public void mergeAndDeduplicate() {
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        List<String> list2 = Arrays.asList("banana", "date", "apple");

        // Merge and deduplicate using Stream
        List<String> result = Stream.concat(list1.stream(), list2.stream()).distinct().toList();

        System.out.println(result);
    }

    // 27. Find Any Employee From Support Department
    @Test
    public void findAnyFromSupportDept() {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 1, 15000, "a@x.com", "HR"),
                new Employee("B", 2, 18000, "b@x.com", "Support"));

        // Find any employee from "Support" department using findAny()
        Optional<Employee> supportEmployee = employees.stream().filter(e -> e.getDepartment().equals("Support"))
                .findAny();

        supportEmployee.ifPresent(System.out::println);
    }

    // 28. Check if List Contains Duplicate Strings
    @Test
    public void hasDuplicates() {
        List<String> values = Arrays.asList("one", "two", "three", "one");

        // Use Stream to check if list has duplicates
        boolean hasDuplicate = values.stream().distinct().toList().size() != values.size();

        System.out.println("Contains duplicates: " + hasDuplicate);
    }

    // 29. Find Longest String in a List
    @Test
    public void findLongestString() {
        List<String> strings = Arrays.asList("hi", "hello", "welcome", "world");

        // Find longest string using max() and Comparator
        Optional<String> longest = strings.stream().max(Comparator.comparingInt(String::length));

        longest.ifPresent(System.out::println);
    }

    // 30. Generate List of Squares from List of Numbers
    @Test
    public void generateSquares() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Convert to list of squares
        List<Integer> squares = numbers.stream().map(n-> n*n).toList();

        System.out.println(squares);
    }

}
