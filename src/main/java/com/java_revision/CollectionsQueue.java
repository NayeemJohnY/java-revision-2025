package com.java_revision;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


class MyQueue<T> implements Queue<T> {

    private Stack<T> stack1;
    private Stack<T> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return stack1.contains(o) || stack2.contains(o);
    }

    private void transfer() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
    }

    @Override
    public boolean add(T e) {
        stack1.push(e);
        return true;
    }

    @Override
    public boolean offer(T e) {
        return add(e);
    }

    @Override
    public T remove() {
        transfer();
        if (stack2.isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return stack2.pop();
    }

    @Override
    public T poll() {
        transfer();
        return stack2.isEmpty() ? null : stack2.pop();
    }

    @Override
    public T element() {
        transfer();
        if (stack2.isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return stack2.peek();
    }

    @Override
    public T peek() {
        transfer();
        return stack2.isEmpty() ? null : stack2.peek();
    }

    @Override
    public boolean remove(Object o) {
        return stack1.remove(o) || stack2.remove(o);
    }

    @Override
    public void clear() {
        stack1.clear();
        stack2.clear();
    }

    // Not implementing these (optional) operations for brevity
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <U> U[] toArray(U[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Stack<T> temp = new Stack<>();
        temp.addAll(stack2); // Already reversed
        Stack<T> reverseStack1 = new Stack<>();
        for (T item : stack1)
            reverseStack1.push(item); // Reverse stack1 to match queue order
        while (!reverseStack1.isEmpty()) {
            temp.push(reverseStack1.pop());
        }
        return temp.toString();
    }
}



class Task implements Comparable<Task>{

    private String name;
    private int priority;
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    @Override
    public String toString() {
       return "Task: {name: " + this.name + "; priority: " + priority + "}";
    }
    
}



class TaskQueueProcessor {
    Task[] taskArray;
    private int front, rear, capacity ;

    public TaskQueueProcessor(int size) {
        taskArray = new Task[size + 1];
        this.capacity  = size + 1;
        rear = 0;
        front = 0;
    }

    public int size(){
        return ((front + 1) % capacity) + 1;
    }

    public void insertTask(Task task){
        if( (rear + 1) % capacity  == front){
            System.out.println("TaskProcessor Queue is already full");
        }
        else {
            taskArray[rear] = task;
            rear = (rear + 1) % capacity ;
            System.out.println("Added Task: " + task);
        }

    }

    public void removeTask(){
        if (front == rear){
            System.out.println("TaskProcessor Queue is Empty");
        }
        else {
            System.out.println("Removed Task: " +  taskArray[front]);
            front = (front + 1) % capacity ;
        }

    }

    public void tasks(){
        int i = front;
        while (i != rear){
            System.out.println(taskArray[i]);
            i = (i + 1) % capacity ;
        }
    }

}

class SupportTicket {
    private static int ticketCounter = 1001;
    private int id;
    private String description;
    public int getId() {
        return id;
    }
    private String status;
    private String timeStamp;

    public SupportTicket(String description) {
        id = ticketCounter++;
        this.description = description;
        this.status = "Open";
        this.timeStamp = Instant.now().toString();
        System.out.println("Created Ticket with Id: " + id + " desc: " + description);
    }
    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "SupportTicket [id=" + id + ", description=" + description + ", status=" + status + ", timeStamp="
                + timeStamp + "]";
    }

}

class ManageSupportTicket {
    private Queue<SupportTicket> supportQueue;
    public ManageSupportTicket() {
        supportQueue = new LinkedList<>();
    }

    public void createTicket(String ticketDescription){
        supportQueue.add(new SupportTicket(ticketDescription));
    }

    public void processTicket(){
        SupportTicket ticket = supportQueue.peek();
        ticket.setStatus("In Progress");
        System.out.println("Proccessing Ticket " + ticket.getId());
    }

    public void resolveTicket(){
        SupportTicket ticket = supportQueue.poll();
        ticket.setStatus("Resolved");
        System.out.println("Ticket Resolved " + ticket.getId());
    }

    @Override
    public String toString() {
        return supportQueue.toString();
    }

}

public class CollectionsQueue {

    public void findFirstNonRepeatingCharacter(String stream) {
        HashMap<Character, Integer> freqMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        for (Character ch : stream.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            queue.add(ch);
            while (!queue.isEmpty() && freqMap.get(queue.peek()) > 1) {
                queue.poll();
            }
        }
        if (!queue.isEmpty()) {
            System.out.println("First non-repeating character: " + queue.peek());
        } else {
            System.out.println("No non-repeating character found.");
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.add(10);
        myQueue.add(20);
        myQueue.add(30);
        System.out.println("Queue " + myQueue.toString());
        System.out.println(myQueue.peek());
        System.out.println("Queue " + myQueue.toString());
        myQueue.add(50);
        System.out.println(myQueue.poll());
        System.out.println("Queue " + myQueue.toString());
        System.out.println(myQueue.poll());
        System.out.println("Queue " + myQueue.toString());
        myQueue.add(590);
        System.out.println(myQueue.poll());
        System.out.println("Queue " + myQueue.toString());
        System.out.println(myQueue.poll());
        System.out.println("Queue " + myQueue.toString());

        // Problem 2: Find the first non-repeating character in a stream.
        CollectionsQueue collectionsQueue = new CollectionsQueue();
        collectionsQueue.findFirstNonRepeatingCharacter("HelloHaaeoppy");


        // Problem 3: Implement a priority-based task scheduler using PriorityQueue.
        PriorityQueue<Task> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Task("name", 10));
        priorityQueue.add(new Task("John", 2));
        priorityQueue.add(new Task("ACE", 25));
        priorityQueue.add(new Task("Age", 12));

        System.out.println(priorityQueue.toString());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        priorityQueue.add(new Task("ACE", 1));
        System.out.println(priorityQueue.toString());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

        // Problem 4: Simulate a circular queue for task processing.
        TaskQueueProcessor taskQueueProcessor = new TaskQueueProcessor(5);
        for (int i = 1; i <= 6; i++) {
            taskQueueProcessor.insertTask(new Task("Task" + i, i));
        }
        taskQueueProcessor.tasks();
        for (int i = 1; i <= 6; i++) {
            taskQueueProcessor.removeTask();
        }
        taskQueueProcessor.insertTask(new Task("Task7", 7));
        taskQueueProcessor.insertTask(new Task("Task8", 8));
        taskQueueProcessor.tasks();
        taskQueueProcessor.removeTask();
        taskQueueProcessor.insertTask(new Task("Task9", 9));
        taskQueueProcessor.tasks();
        taskQueueProcessor.removeTask();
        taskQueueProcessor.tasks();


        // Problem 5: Design a customer support ticketing system using a queue.
        ManageSupportTicket manageSupportTicket = new ManageSupportTicket();
        manageSupportTicket.createTicket("Login is Not working");
        manageSupportTicket.createTicket("Unable to launch payment portal");
        manageSupportTicket.createTicket("Cart always shows empty even items added");
        manageSupportTicket.processTicket();
        System.out.println(manageSupportTicket.toString());
        manageSupportTicket.resolveTicket();
        manageSupportTicket.processTicket();
        manageSupportTicket.createTicket("Cart always shows empty even items added");
        System.out.println(manageSupportTicket.toString());
        manageSupportTicket.resolveTicket();
        System.out.println(manageSupportTicket.toString());
    }

}
