package com.java_revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class TaskManager {

    class Task {
        private String name;
        private boolean isCompleted;

        public Task(String name) {
            this.name = name;
            isCompleted = false;
        }

        @Override
        public String toString() {
            return name + " - " + (isCompleted ? "Complete" : "Pending");
        }

    }

    private List<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    public void addTask(String taskName) {
        tasksList.add(new Task(taskName));
    }

    public void markComplete(String taskName) {
        for (Task task : tasksList) {
            if (task.name.equals(taskName)) {
                task.isCompleted = true;
                break;
            }
        }
    }

    public void printTasks() {
        System.out.println(tasksList.toString());
    }
}

class UserRegistry {

    Set<String> registry;

    public UserRegistry() {
        registry = new TreeSet<>();
    }

    public void register(String email) {
        registry.add(email.toLowerCase());
    }

    public Set<String> getAllEmails() {
        return registry;
    }
}

class Library {

    HashMap<String, Integer> books;

    public Library() {
        books = new HashMap<>();
    }

    public void addBook(String title) {
        books.put(title, books.getOrDefault(title, 0) + 1);
    }

    public void borrowBook(String title) {
        if (books.getOrDefault(title, 0) > 0) {
            books.put(title, books.get(title) - 1);
        }
    }

    public void getAvailability(String title) {
        System.out.println("Available copies of " + title + ": " + books.getOrDefault(title, 0));
    }

}

class BrowserHistory {
    Stack<String> history;
    String previousPage = "[Start Page]";

    public BrowserHistory() {
        history = new Stack<>();
    }

    public void visit(String page) {
        history.push(previousPage);
        previousPage = page;
    }

    public void goBack() {
        if (history.isEmpty()) {
            System.out.println("No history to go back to.");
        } else {
            String page = history.pop();
            System.out.println("Went back to: " + page);
        }
    }
}

class SupportTicketSystem {

    class Ticket {
        String name;
        String issue;

        public Ticket(String name, String issue) {
            this.name = name;
            this.issue = issue;
        }

        @Override
        public String toString() {
            return name + " - " + issue;
        }
    }

    Queue<Ticket> ticketQueue;

    public SupportTicketSystem() {
        ticketQueue = new LinkedList<>();
    }

    public void raiseTicket(String name, String issue) {
        ticketQueue.add(new Ticket(name, issue));
    }

    public void handleNextTicket() {
        if (ticketQueue.isEmpty())
            System.out.println("No tickets in queue.");
        else
            System.out.println(ticketQueue.poll().toString());
    }
}

public class CollectionsInterviewStyle {
    public static void main(String[] args) {

        // 1. Task Manager
        TaskManager manager = new TaskManager();
        manager.addTask("Grocery shopping");
        manager.addTask("Study Java");
        manager.markComplete("Grocery shopping");
        manager.printTasks();

        // 2. Unique Email Registry
        UserRegistry registry = new UserRegistry();
        registry.register("alice@example.com");
        registry.register("bob@example.com");
        registry.register("alice@example.com");
        registry.register("charlie@example.com");
        System.out.println(registry.getAllEmails());

        // 3. Library Inventory System
        Library lib = new Library();
        lib.addBook("Java");
        lib.addBook("Java");
        lib.addBook("Python");
        lib.borrowBook("Java");
        lib.borrowBook("Python");
        lib.getAvailability("Java");
        lib.getAvailability("Python");
        lib.getAvailability("C++");

        // 4. Browser History Navigation
        BrowserHistory history = new BrowserHistory();
        history.visit("google.com");
        history.visit("github.com");
        history.visit("stackoverflow.com");
        history.goBack(); // Pops github.com
        history.goBack(); // Pops google.com
        history.goBack(); // [Start Page]
        history.goBack(); // Stack is empty

        // 5. Customer Support Ticketing System
        SupportTicketSystem system = new SupportTicketSystem();
        system.raiseTicket("Alice", "Can't login");
        system.raiseTicket("Bob", "App crash");
        system.raiseTicket("Charlie", "Payment failed");

        system.handleNextTicket(); // Alice
        system.handleNextTicket(); // Bob
        system.handleNextTicket(); // Charlie
        system.handleNextTicket(); // Queue empty

    }

}
