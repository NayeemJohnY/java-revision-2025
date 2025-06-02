package tests.java8;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.testng.annotations.Test;

interface VehicleInterace {

    // Method with implementation - Ony private and Default
    default void display() {
        System.out.println("Vechile is running at km/h");
    }
    // private void privateMethod(){};
    // public void publicMethod(){} // Public methods only can be abstact
    // protected void protectedMethod(){} // Protected cannot be used
    // abstract void abstactMethod(){}; // abstract method should not have body

    // default void defaultMethod(); // default should have body
    // private void privateMethod2(); // private should have body
    // public void publicMethod1();
    // protected void protectedMethod1(); // can't use protected with abstract
    // methods
    // abstract void abstactMethod1();

    // Static, default, private - methods need impleementation
    // public, abstract - can be abstraced
    // protected - can't use

}

class Car implements VehicleInterace {

    @Override
    public void display() {
        System.out.println("Car is running at 60 km/h");
    }
}

class Bus implements VehicleInterace {

}

interface Automotive {

    default void display() {
        System.out.println("Automtive Runing at miles/h");
    }

    static void autmotiveDescritpion() {
        System.out.println("Things that move with Enigne");
    }

}

class Truck implements VehicleInterace, Automotive {

    @Override
    public void display() {
        System.out.println("Truck runs at miles or km per hour");
    }
}

interface Logger {
    default void log(String msg) {
        System.out.println(msg);
    }
}

interface FileLogger extends Logger {
    default void fileLogger(String msg) {
        log(msg);
    }

    abstract void fileappender();
}

class FileLoggerClass implements FileLogger {

    @Override
    public void fileappender() {
        System.out.println("Impelemented File Appender abstract Method");
    }

}

class TestLogger implements Logger {
    void display() {
        log("In a Test Logger");
    }
}

@FunctionalInterface
interface Multiplication {

    abstract int multiply(int a, int b);

    default void DisplayMultipcationResult(int a, int b) {
        System.out.println(a * b);
    }
}

interface Utility {
    static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("d MMM, YYYY"));
    }
}

interface Sorting {
    default void sort(List<Integer> list) {
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a + 2, b * 2);
        list.sort(comparator);
    }
}

class SortableClass implements Sorting {

}

public class TestInterfaceDefaultStaticMethods {

    @Test
    public void testInterfaceDefaultStaticMethods() {
        // 1. Define an interface with a default method and override it.
        VehicleInterace car = new Car();
        car.display();

        VehicleInterace bus = new Bus();
        bus.display();

        // 2. Use multiple interfaces with default methods – resolve conflict.
        Truck truck = new Truck();
        truck.display();

        // 3. Create a static method in an interface and call it.
        Automotive.autmotiveDescritpion();
        // truck.autmotiveDescritpion() - Not allowed only with interface name and
        // cannot override it. it will considered as new method

        // 4. Use default method in interface for logging.
        TestLogger logger = new TestLogger();
        logger.display();

        // 5. Extend interface and use super to call default method.
        FileLoggerClass fileLoggerClass = new FileLoggerClass();
        fileLoggerClass.fileLogger("FIle Logger Message");

        // 6. Interface with both abstract and default methods.
        fileLoggerClass.fileappender();

        // 7. Define default method in functional interface – test lambda behavior.
        Multiplication multiplication = (a, b) -> a * b;
        multiplication.DisplayMultipcationResult(20, 30);
        System.out.println(multiplication.multiply(20, 30));

        // 8. Use static method in utility interface for date formatting.
        System.out.println(Utility.formatDate(LocalDate.now()));

        // 9. Implement default sorting behavior in interface.
        SortableClass sortableClass = new SortableClass();
        List<Integer> list = Arrays.asList(1, 3, 5, 9, 7, 5, 8);
        sortableClass.sort(list);
        System.out.println(list);

        // 10. Call default method via interface reference.
        List<String> logs = Arrays.asList("Log1", "Log20", "Log100");
        logs.forEach(logger::log);
    }

    interface Greeting {
        default void sayHello() {
            // Your logic here
            System.out.println("Hello from interface!");
        }

        default void sayHelloWithName(String name) {
            System.out.println("Hello from interface! " + name);
        }

        static void printInfo() {
            System.out.println("Static method from interface");
        }

        static String getGreetMsg(String name) {
            return "Hello From Get Greeting " + name;
        }

    }

    class Greet implements Greeting {
    }

    class BGreet implements Greeting {

        @Override
        public void sayHello() {
            System.out.println("Hello from BGreet class!");
        }
    }

    class CGreet implements Greeting {

        @Override
        public void sayHello() {
            System.out.println("Hello from CGreet class!");
        }
    }

    interface Info {
        static void displayInfo() {
            System.out.println("Display Info");
        }
    }

    interface Helper {
        default void help() {
            System.out.println("Helping for log");
            log();
        }

        private void log() {
            System.out.println("From Logging");
        }
    }

    class HelperClass implements Helper {

    }

    interface A {
        default void print() {
            System.out.println("Print A");
        }

        default void display() {
            System.out.println("Display in A");
        }
    }

    interface B {
        default void print() {
            System.out.println("Print B");
        }

        default void show() {
            System.out.println("Show in B");
        }
    }

    public class Problem9 implements A, B {

        @Override
        public void print() {
            System.out.println("Print From Class");
        }

    }

    interface Printer {
        default void print() {
            System.out.println("Printer Interface print");
        }

        default int returnValue(int i) {
            return i * i;
        }
    }

    public class Problem11 implements Printer {
        @Override
        public void print() {
            Printer.super.print();
            System.out.println("Problem11 class print");
        }
    }

    interface First {
        default void execute() {
            System.out.println("Execute in First");
        }
    }

    interface Second extends First {
        @Override
        default void execute() {
            First.super.execute();
            System.out.println("Execute in Second");
        }
    }

    class Problem15 implements Second {
    }

    interface Base {
        static void status() {
            System.out.println("Status in Static");
        }

        default void log() {
            System.out.println("Log in Base");
        }
    }

    interface Derived extends Base {
    }

    class Problem16 implements Derived {
    }

    @FunctionalInterface
    interface Operation {
        int compute(int x, int y);

        default void printHeader() {
            System.out.println("Header print");
        }
    }

    interface Security {
        static void verify() {
            System.out.println("In Verify Static");
            validate();
        }

        private static void validate() {
            System.out.println("In Validate");
        }
    }

    class Container {
        interface Inner {
            default void run() {
                System.out.println("Inside class Interface");
            }
        }
    }

    class Problem19 implements Container.Inner {
    }

    interface Config {
    String DEFAULT = "config";

    static String load() {
        return "Loaded " + DEFAULT;
    }
}

    @Test
    public void testInterface() {
        // ✅ Beginner (1–7)
        // 1. Default Method Invocation from Interface
        new Greet().sayHello();

        // 2. Static Method in Interface
        Greeting.printInfo();

        // 3. Multiple Classes Implementing Default Method
        // 4. Override Default Method
        new BGreet().sayHello();
        new CGreet().sayHello();

        // 5. Default Method in Interface with Parameter
        new Greet().sayHelloWithName("John");

        // 6. Static Method Returning Value
        // 10. Static Method with Parameters
        // 12. Accessing Static Method in Interface from Another Class
        System.out.println(Greeting.getGreetMsg("Tom"));

        // 7. Interface with Only Static Method
        Info.displayInfo();

        // 🟡 Intermediate (8–14)
        // 8. Default Method Calling Private Method
        new HelperClass().help();

        // 9. Class Implementing Two Interfaces with Same Default Method
        new Problem9().print();

        // 11. Invoke Interface Default Method via Super
        new Problem11().print();

        // 13. Default Method Returning Value
        System.out.println(new Problem11().returnValue(10));

        // 14. Multiple Interfaces with Unique Default Methods
        new Problem9().display();
        new Problem9().show();

        // 🔴 Advanced (15–20)
        // 15. Chain Default Method Logic
        new Problem15().execute();

        // 16. Interface Inheritance with Static and Default
        new Problem16().log();
        Base.status();

        // 17. Default Method Inside Functional Interface
        Operation ops = (x, y) -> x * y;
        System.out.println(ops.compute(10, 4));

        //18. Private Static Method in Interface
        Security.validate();
        Security.verify();

        //19. Nested Interface with Default Method
        new Problem19().run();

        //20. Static Method with Return and Interface Field
        System.out.println(Config.load());

    }
}
