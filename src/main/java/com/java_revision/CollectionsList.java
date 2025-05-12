package com.java_revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



class MyArrayList<T> {

    private T[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        if (size == arr.length)
            resize();
        arr[size++] = element;
    }

    public T remove(int index) {
        T removed = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[--size] = null;
        return removed;
    }

    public T get(int index) {
        return arr[index];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        arr = newArr;
    }
}


class MyEmployee {
    private String name;
    private int age;

    public MyEmployee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void sortList(List<MyEmployee> employeeList, String sortByField){

        // Comparator<Employee> comparator;
        // if (field.equals("name")) {
        //     comparator = (e1, e2) -> e1.name.compareTo(e2.name);
        // } else if (field.equals("age")) {
        //     comparator = (e1, e2) -> e1.age - e2.age;
        // } else {
        //     throw new IllegalArgumentException("Unsupported field: " + field);
        // }

        // employeeList.sort(comparator);

        employeeList.sort(new Comparator<MyEmployee>() {

            @Override
            public int compare(MyEmployee e1, MyEmployee e2) {
                if (sortByField.equals("name"))
                    return e1.name.compareTo(e2.name);
                else if (sortByField.equals("age"))
                    return e1.age - e2.age;
                else 
                    throw new IllegalArgumentException("Unsupported field: " + sortByField);
            }
            
        });
        
    }

    @Override
    public String toString() {
        return "Student{name='" + name +  "' age=" + age + "}";
    }
}

public class CollectionsList {

    public static void main(String[] args) {
        // Problem 1
        MyArrayList<Integer> intArrayList = new MyArrayList<>();
        intArrayList.add(100);
        intArrayList.add(200);
        intArrayList.remove(1);
        intArrayList.add(300);
        System.out.println(intArrayList.get(1));

        // Problem 2
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 1, 2, 2, 3, 4));
        ArrayList<Integer> uniqueList = new ArrayList<>(new HashSet<>(list));
        System.out.println(uniqueList);

        // Problem 3
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("Hello", "World", "Hello", "Great"));
        Map<String, Integer> map = new HashMap<>();
        for (String s : stringList)
            map.put(s, map.getOrDefault(s, 0) + 1);
       System.out.println(map);

        // Problem 4
        List<MyEmployee> employees = new ArrayList<>();
        employees.add(new MyEmployee("John", 100));
        employees.add(new MyEmployee("ACE", 30));
        employees.add(new MyEmployee("Naan", 50));

        System.out.println("Before Sort" + employees);
        MyEmployee.sortList(employees, "name");
        System.out.println("After Name Sort" + employees);
        MyEmployee.sortList(employees, "age");
        System.out.println("After Age Sort" + employees);

        //Problem 5 Reverse a LinkedList without using inbuilt methods
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(101, 210, 1, 2, 5, 4, 9, 7, 10));
        System.out.println("Before Reverse " + linkedList);
        LinkedList<Integer> reverseList = new LinkedList<>();
        for (Integer integer : linkedList) {
            reverseList.addFirst(integer);
        }
        // System.out.println("After Reverse " + linkedList.reversed());
        System.out.println("After Reverse " + reverseList);


    }
}
