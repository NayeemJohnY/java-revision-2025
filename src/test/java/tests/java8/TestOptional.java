package tests.java8;

import java.lang.foreign.Linker.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

import org.testng.annotations.Test;

import com.java_revision.Employee;

public class TestOptional {

    @Test
    public void testOptionalBeginner() {
        // 1: Create an Optional<String> with a non-null value and print it.
        Optional<String> nonNullOptionString = Optional.of("Java Programming");
        System.out.println("Non Null: " + nonNullOptionString.get());

        // 2: Create an empty Optional and check if it's present.
        Optional<String> emptyOptional = Optional.empty();
        System.out.println("Empty: " + emptyOptional.isPresent());

        // 3: Use Optional.ofNullable() to wrap a possibly null string and print a
        // default using orElse()
        String oddEven = null;
        int num = new Random().nextInt(3);
        if (num != 0)
            oddEven = num % 2 == 0 ? "Even Number" : "Odd Number";
        Optional<String> nullableOptional = Optional.ofNullable(oddEven);
        System.out.println("Nullable: " + num + " is " + nullableOptional.orElse("Neither Odd/Even Number"));

        // 4: Convert a String to uppercase using map() on an Optional.
        // 6: Use ifPresent() to print the value only if it's present.
        Optional.ofNullable(oddEven).map(String::toUpperCase).ifPresent(System.out::println);

        // 5: Safely retrieve the length of a nullable string using Optional.
        System.out.println(Optional.ofNullable(oddEven).map(String::length).orElse(0));

        // 7: Check if an Optional<Integer> contains an even number
        Optional<Integer> optionalNumber = Optional.empty();
        if (new Random().nextBoolean())
            optionalNumber = Optional.of(num);
        optionalNumber.ifPresent(n -> System.out.println(n + "s Even: " + (n % 2 == 0)));

    }

    public Optional<Double> optionalDivisor(double a, double b) {
        if (b != 0) {
            return Optional.of(a / b);
        }
        return Optional.empty();
    }

    public Optional<Integer> optionalIntegerSum(Optional<Integer> a, Optional<Integer> b) {
        if (a.isPresent() && b.isPresent()) {
            return Optional.of(a.get() + b.get());
        }
        return Optional.empty();
    }

    @Test
    public void testOptionalIntermediate() {
        // 8: Use flatMap() to unwrap an Optional<Optional<String>>.
        Optional<Optional<String>> nestedOptional = Optional.of(Optional.empty());
        if (new Random().nextBoolean())
            nestedOptional = Optional.of(Optional.of("StringValue"));
        System.out.println(nestedOptional.flatMap(Function.identity()).orElse("Empty"));

        // 9: Write a method that returns an Optional<Double> if input is non-zero
        // division.
        System.out.println(optionalDivisor(10, 2).orElse(Double.MAX_VALUE));
        System.out.println(optionalDivisor(10, 0).orElse(Double.MAX_VALUE));

        // 10: Combine two Optionals of Integers if both are present, return their sum.
        optionalIntegerSum(Optional.of(10), Optional.of(20)).ifPresent(System.out::println);
        System.out.println(optionalIntegerSum(Optional.of(10), Optional.empty()).orElse(0));

        // 11: Chain multiple map() calls to transform a string safely.
        Optional<String> multileMapsOptionalStrig = Optional.of("   String   ");
        System.out.println(multileMapsOptionalStrig.map(String::trim)
                .map(s -> s.replaceAll("St", "")).map(String::toUpperCase).orElse("String is Empty"));

        // 12: Use filter() on an Optional to keep only values > 100.
        Optional<Integer> optionalList = Optional.of(101);
        optionalList.filter(e -> e > 100).ifPresent(System.out::println);

        // 13: Read an environment variable using Optional and provide a fallback.
        System.out.println(System.getenv());
        Optional<String> envOptionalURL = Optional.ofNullable(System.getenv("SESSIONNAME"));
        System.out.println("URL: " + envOptionalURL.orElse("https://dummy.com"));

        envOptionalURL = Optional.ofNullable(System.getenv("DUMMY"));
        System.out.println("URL: " + envOptionalURL.orElse("https://dummy.com"));

        // 14: Parse a nullable String to Integer using Optional and handle
        // NumberFormatException.
        Optional<String> optioalNullableString = Optional.ofNullable("3sw2");
        Optional<Integer> res = optioalNullableString.flatMap(str -> {
            try {
                return Optional.ofNullable(Integer.parseInt(str));
            } catch (Exception e) {
                return Optional.empty();
            }
        });
        System.out.println(res.orElse(-1));
    }

    @Test
    public void testOptionalAdvanced() {
        // 15: From a list of user objects (some with null emails), extract non-null
        // emails using Optional.
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("A", 123, 30000));
        employeeList.add(new Employee("B", 124, 30000, "bemployee@emplooyee.com"));
        employeeList.add(new Employee("C", 332, 10000, "cemployee@emplooyee.com"));
        employeeList.add(new Employee("D", 126, 5000));
        employeeList.add(new Employee("F", 4334, 5000, "f4324employee@emplooyee.com"));

        System.out.println(employeeList.stream().map(e -> Optional.ofNullable(e.getEmailId()))
                .filter(Optional::isPresent).map(Optional::get).toList());

        // 16: Implement a method that returns Optional<User> by ID; if not found,
        // return empty.
        int searchId = 332;
        System.out.println(employeeList.stream().filter(e -> e.getId() == searchId).findFirst());
        System.out.println(employeeList.stream().filter(e -> e.getId() == 233322).findFirst());

        // 17: Replace nested null checks in a method using Optional.
        // public String getUserCity(User user) {
        // if (user != null) {
        // Address address = user.getAddress();
        // if (address != null) {
        // City city = address.getCity();
        // if (city != null) {
        // return city.getName();
        // }
        // }
        // }
        // return "Unknown";
        // }
        // Optional.ofNullable(user).map(User::getAddress).map(Address:getCity).map(City::getName).orElse("unknown")

        // 18: Convert a legacy API that returns null into one that returns Optional.
        Employee employee = new Employee("John", 123, 1000);
        System.out.println(employee.getEmailId());
        System.out.println(employee.getEmailIDOptional());

        // 19: Use Optional in a stream pipeline to extract the first even number from a
        // list or return -1.
        List<Integer> numbers = Arrays.asList(21, 3, 41, 5, 77, 88, 12, 15, 32, 12);
        System.out.println(numbers.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));
        numbers = Arrays.asList(21, 3, 41, 5, 77, 87, 121, 15, 321, 121);
        System.out.println(numbers.stream().filter(n -> n % 2 == 0).findFirst().orElse(-1));

        //20: Write a generic utility method: Optional<T> safeGet(Supplier<T>) to catch and handle exceptions safely.
        System.out.println(getEmployeeNameAndEmailInString(()-> employee.getName() + employee.getEmailId().toLowerCase()));
        employee.setEmailId("empemep@employee.com");
        System.out.println(getEmployeeNameAndEmailInString(()-> employee.getName() + employee.getEmailId().toLowerCase()));
    }

    public <T> Optional<T> getEmployeeNameAndEmailInString(Supplier<T> supplier){
        try {
            T res = supplier.get();
            return Optional.of(res);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return Optional.empty();
        }
    }
}
