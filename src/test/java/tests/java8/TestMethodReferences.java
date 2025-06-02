package tests.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import org.testng.annotations.Test;

import com.java_revision.Event;
import com.java_revision.SimplePerson;

public class TestMethodReferences {
    // 1. Replace lambda with method reference for `System.out::println`.
    @Test
    public void replaceLamdawithMethodRefrence() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.forEach(num -> System.out.println(num));
        System.out.println("Using Method Ref");
        list.forEach(System.out::println);
    }

    // 2. Use constructor reference to create list of objects.
    // 17. Constructor Reference for Object Mapping
    // 10. Constructor Reference with Custom Class
    @Test
    public void usingConstructorReference() {
        List<String> events = Arrays.asList("Day0 Event", "Day1 Intro", "Day 3 Assessment", "Day 4 Closure");
        System.out.println(events.stream().map(Event::new).toList());
    }

    // 3. Use static method reference for a math utility.
    @Test
    public void useStaticMethodReference() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list.stream().map(Math::sqrt).toList());
    }

    // 4. Use instance method reference of existing object.
    // 15. Method Reference inside Custom Functional Interface
    @Test
    public void useInstanceMethodReference() {
        Event event = new Event("Day 01");
        Runnable eventdate = event::displayEvent;
        eventdate.run();
    }

    // 5. Sort a list using method reference to compareTo.
    @Test
    public void sortListMethodReference() {
        List<Integer> list = Arrays.asList(11, 21, 3, 14, 5);
        System.out.println(list.stream().sorted(Integer::compareTo).toList());
    }

    // 6. Use `ClassName::instanceMethod` in stream map.
    // 16. Chained Method References in Stream API
    // 9. Reference to an Instance Method of Arbitrary Object
    @Test
    public void classNameInstanceMethod() {
        List<String> events = Arrays.asList("Day0 Event", "Day1 Intro", "Day 3 Assessment", "Day 4 Closure");
        System.out.println(events.stream().map(Event::new).map(Event::getName).toList());
    }

    // 7. Replace comparator lambda with method reference.
    // 12. Sorting Strings Using Method Reference
    @Test
    public void replaceComparator() {
        List<String> events = Arrays.asList("Day0 Event", "Day1 Intro", "Day 3 Assessment", "Day 4 Closure");
        events.sort(Comparator.comparingInt(String::length));
        System.out.println(events);
    }

    // 8. Collect stream of strings to uppercase using method reference.
    // 14. Mapping Values with Method Reference
    @Test
    public void toUpperCaseMethoReference() {
        List<String> events = Arrays.asList("Day0 Event", "Day1 Intro", "Day 3 Assessment", "Day 4 Closure");
        System.out.println(events.stream().map(String::toUpperCase).toList());
        Event event = new Event("Trigger Event");
    }

    // 9. Use method reference with `forEach`.
    @Test
    public void useMethodReferenceforForEach() {
        List<String> events = Arrays.asList("Day0 Event", "Day1 Intro", "Day 3 Assessment", "Day 4 Closure");
        events.forEach(System.out::println);
    }

    // 10. Convert string list to integer list using constructor reference.
    @Test
    public void convertStringtoInteger() {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println(list.stream().map(Integer::parseInt).toList());
    }

    // 20. Sorting Custom Objects Using Method Reference
    @Test
    public void sortCustObjectsMethdodReferences() {
        List<SimplePerson> persons = new ArrayList<>();
        persons.add(new SimplePerson("John", 30));
        persons.add(new SimplePerson("DSA", 29));
        persons.add(new SimplePerson("QAW", 30));
        persons.add(new SimplePerson("Dave", 29));
        persons.add(new SimplePerson("Grook", 29));
        persons.add(new SimplePerson("Mike", 32));
        System.out.println(persons.stream()
                .sorted(Comparator.comparingInt(SimplePerson::getAge)).map(SimplePerson::getName).toList());
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    // 13. Method Reference with Stream and filter
    @Test
    public void testMethodReferenceinFilter() {
        List<Integer> list = Arrays.asList(11, 21, 3, 14, 5);
        System.out.println(list.stream().filter(TestMethodReferences::isEven).toList());
    }

    public static int add(int a, int b) {
        return a + b;
    }

    // 11. Static Method Reference with BiFunction
    @Test
    public void useStaticMethodReferenceBiFunction() {
        BiFunction<Integer, Integer, Integer> sum = TestMethodReferences::add;
        System.out.println(sum.apply(10, 20));
    }

    public class Greeter {
        public void sayHello(String name) {
            System.out.println("Hello, " + name + "!");
        }
    }

    @Test
    public void testspecificObjectRef() {
        Greeter greeter = new Greeter();
        List<String> names = List.of("Alice", "Bob", "Charlie");
        names.forEach(greeter::sayHello);
    }

}
