package com.java_revision.java9;

/*
 * Private Methods in Interfaces:

Prior to Java 9, interfaces could only have public abstract methods, default methods, and static methods.
 Java 9 allows interfaces to contain private and private static methods.
  These private methods can be used by default methods within the same interface to share common implementation logic, promoting code reuse.
 */
interface  MyLogger {
    default void logMessage(String message){
        logInternal(message, "INFO");
    }
     
    default void logWarning(String message) {
        // Hint 2: Call the private helper method with a different log level.
        logInternal(message, "WARN");
    }

    private void logInternal(String message, String level) {
        System.out.println("[" + level + "] " + message);
    }

    private static String formatTimestamp(){
        return java.time.LocalDateTime.now().toString();
    }

    default void logWithTimestamp(String message) {
        System.out.println(formatTimestamp() + " - " + message);
    }
}

class ConsoleLogger implements MyLogger {
    // No need to implement default methods, they are inherited.
    // We can override them if needed, but not for this demo.
}

public class InterfacePrivateMethodsDemo {
    public static void main(String[] args) {
        ConsoleLogger logger = new ConsoleLogger();
        logger.logMessage("This is a test message.");
        logger.logWarning("This is a warning!");
        logger.logWithTimestamp("Event occurred.");
    }
}
