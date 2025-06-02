package tests.java8;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.testng.annotations.Test;

public class TestFunctionalInterfaces {

    // Problem 1: Custom Functional Interface – Multiply
    @FunctionalInterface
    public interface Multiplication<T> {
        abstract T multiply(T a, T b);

    }

    @Test
    public void multiplyFunctionalInterface() {
        Multiplication<Integer> multiplication = (a, b) -> a * b;
        System.out.println(multiplication.multiply(4, 5));
        System.out.println(((Multiplication<Double>) (a, b) -> a * b).multiply(20.5, 56.9));
    }

    // Problem 2: Use Predicate to Check Even Number
    @Test
    public void predicateEvenNumber() {
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println(isEven.test(6));
    }

    // Problem 3: Use Function to Get String Length
    @Test
    public void functionStringLength() {
        Function<String, Integer> strLength = str -> str.length();
        System.out.println(strLength.apply("Java"));
    }

    // Problem 4: Use Consumer to Print Uppercase
    @Test
    public void consumerUppercase() {
        Consumer<String> consumer = s -> System.out.println(s.toUpperCase());
        consumer.accept("lambda");
    }

    // Problem 5: Use Supplier to Generate Random Number
    @Test
    public void supplierGenerateRandomNumber() {
        Supplier<Double> supplier = Math::random;
        System.out.printf("%.3f", supplier.get());
    }

    // Problem 6: Predicate with negate()
    @Test
    public void predicateWithNegate() {
        List<String> strings = Arrays.asList("", "Hi", " ");
        System.out.println(strings.stream().filter(((Predicate<String>) String::isEmpty).negate()).toList());
    }

    // Problem 7: BiFunction for Division
    @Test
    public void biFunctionDivision() {
        BiFunction<Integer, Integer, Double> division = (a, b) -> a / (double) b;
        System.out.println(division.apply(10, 2));
    }

    // Problem 8: Use UnaryOperator to Reverse a String
    @Test
    public void unaryOpeatorReverseString() {
        UnaryOperator<String> reverse = s -> new StringBuilder(s).reverse().toString();
        System.out.println(reverse.apply("Java"));
    }

    // Problem 9: Use BinaryOperator to Concatenate Strings
    @Test
    public void binaryOpeatorConcatenateString() {
        BinaryOperator<String> concatenate = (s1, s2) -> s1 + s2;
        System.out.println(concatenate.apply("Hello", "World"));
    }

    // Problem 10: Chaining Functions
    @Test
    public void chaningFunction() {
        Function<String, String> trim = String::trim;
        Function<String, String> uppercase = String::toUpperCase;
        System.out.println(trim.andThen(uppercase).apply(" hello "));
        System.out.println(trim.compose(uppercase).apply(" hello "));
    }

    // Problem 11: Check for Palindrome using Predicate
    @Test
    public void predicateCheckPalindrome() {
        Predicate<String> isPalindrome = s -> new StringBuilder(s).reverse().equals(s);
        System.out.println(isPalindrome.test("madam"));
    }

    // Problem 12: Sum of List using BinaryOperator
    @Test
    public void binaryOpearatorSum() {
        BinaryOperator<Integer> sum = (num1, num2) -> num1 + num2;
        System.out.println(Arrays.asList(2, 4, 6).stream().reduce(0, sum));
    }

    // Problem 13: Use Function to Convert String to Integer
    @Test
    public void functionConvertStringToInteger() {
        Function<String, Integer> parse = Integer::parseInt;
        System.out.println(parse.apply("123"));
    }

    // Problem 14: Custom Functional Interface for Greeting
    @FunctionalInterface
    public interface Greet {
        String greet(String name);
    }

    @Test
    public void customFunctionalInterface() {
        Greet greeting = name -> "Hello, " + name + "!";
        System.out.println(greeting.greet("John"));
        System.out.println(((Greet) name -> "Hello, " + name + "!").greet("ACE"));
    }

    // Problem 15: Use BiPredicate to Compare Two Strings
    @Test
    public void biPredicateCompareStrings() {
        BiPredicate<String, String> compare = (s1, s2) -> s1.equalsIgnoreCase(s2);
        System.out.println(compare.test("java", "JAVA"));
        compare = String::equalsIgnoreCase;
        System.out.println(compare.test("java", "JAVA"));
    }

    // Problem 16: Use Supplier to Provide Default Username
    @Test
    public void supplierDefaultUsername() {
        Supplier<String> username = () -> "guest";
        System.out.println(username.get());
    }

    // Problem 17: Compose Two Functions for Chained Logic
    @Test
    public void chainingFunctions() {
        Function<Integer, Integer> doubleit = num -> num * 2;
        Function<Integer, String> integer = String::valueOf;
        System.out.println(integer.compose(doubleit).apply(5));
    }

    // Problem 18: Predicate for Age Validation
    @Test
    public void validateAge() {
        System.out.println(((Predicate<Integer>) age -> age > 18).test(17));
        System.out.println(((Predicate<Integer>) age -> age >= 18).test(18));
    }

    // Problem 19: Consumer for Logging Messages
    @Test
    public void consumeForLoggingMessages() {
        Consumer<String> consumer = s -> System.out.println(Instant.now() + " - " + s);
        consumer.accept("Service started");
    }

    // Problem 20: Method Reference with Consumer
    @Test
    public void methodReference() {
        Arrays.asList("a", "b", "c").forEach(System.out::println);
    }

    // Problem 21: Compose two Functions: add 5 and then double.
    @Test
    public void composeFunctions() {
        Function<Integer, Integer> addFive = num -> num + 5;
        Function<Integer, Integer> doubleit = num -> num * num;
        System.out.println(addFive.compose(doubleit).apply(5)); // 30 => 5 * 5 => 25 +5 = 30
        System.out.println(doubleit.compose(addFive).apply(5)); // 100 => 5 + 5 => 10 *10 => 100
    }

    // Problem 22: Use Consumer to print each item with prefix.
    @Test
    public void printItemWithPrefix() {
        List<String> list = Arrays.asList("Java", "Python", "Java", null, "Python", null, null);
        list.forEach(e -> {
            if (e != null)
                System.out.println("Lang " + e);
        });

    }

    // Problem 23: Create Supplier to generate a random UUID.
    @Test
    public void randomUUIDGenerator() {
        Supplier<UUID> uuid = UUID::randomUUID;
        System.err.println(uuid.get());
    }

    // Problem 24: Chain multiple Consumers for logging.
    @Test
    public void chainConsumerForLogging() throws SecurityException, IOException {
        Logger logger = Logger.getLogger("ConsumeChains");
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);

        BiConsumer<Level, String> allLogger = (loglevel, message) -> {
            System.out.println(loglevel.getName() + " : " + message);
            logger.log(loglevel, message);
        };
        BiConsumer<Level, String> errorLogger = (loglevel, message) -> {
            if (loglevel == Level.SEVERE) {
                System.out.println("Severe Failure In System");
            }
        };

        BiConsumer<Level, String> chainedLogger = allLogger.andThen(errorLogger);
        chainedLogger.accept(Level.SEVERE, "Exception Occurred");
        chainedLogger.accept(Level.INFO, "JUST An Info Mesaage");
        chainedLogger.accept(Level.WARNING, "JUST An Warning Mesaage");

    }

    // Problem 25: Filter list of strings using combined Predicates.
    @Test
    public void combinePredicates() {
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "kiwi");
        Predicate<String> isUpperCase = e -> e.toUpperCase().equals(e);
        Predicate<String> isFruit = e -> fruits.indexOf(e.toLowerCase()) != -1;
        Predicate<String> isValidString = e -> e != null && !e.trim().isEmpty();

        List<String> strings = Arrays.asList("Java", "PYTHON", "Go", "APPLE", "mango", "Jasccript", null, " ", "",
                "RUBY");
        System.out.println(strings.stream().filter(isValidString.and(isUpperCase).and(isFruit)).toList());
        System.out.println(strings.stream().filter(isValidString.and(isUpperCase.or(isFruit))).toList());
        System.out.println(strings.stream().filter(isValidString.and(isUpperCase.or(isFruit)).negate()).toList());
        System.out.println(strings.stream().filter(isValidString.negate()).toList());
    }

    // Problem 26: Use UnaryOperator to modify elements in a list.
    @Test
    public void modifyElementsInList() {
        UnaryOperator<String> toUpperCase = String::toUpperCase;
        List<String> strings = Arrays.asList("Java", "PYTHON", "Go", "APPLE", "mango", "Jasccript");
        System.out.println(strings.stream().map(toUpperCase).toList());
    }

    // Problem 27: Create BiFunction to concatenate two strings.
    @Test
    public void biFunctionConcatenate() {
        BiFunction<String, String, String> concatenate = String::concat;
        System.out.println(concatenate.apply("Java", "Script"));
    }

    public String retryMechasim(Supplier<String> supplier, int maxattempts) {
        int attempts = 0;
        String value;
        do {
            value = supplier.get();
            if (value.length() < 5){
                return value;
            }
            String newString = value.substring(0, value.length()-1);
            supplier = () -> newString;
            System.out.println("Retry Attempt : " + attempts);
            attempts++;
        } while (attempts < maxattempts);
        return null;
    }

    // Problem 28: Implement a retry mechanism using Supplier.
    @Test
    public void testRetryMechanism(){
        System.out.println(retryMechasim(()-> "JavaScript", 7));
    }

}
