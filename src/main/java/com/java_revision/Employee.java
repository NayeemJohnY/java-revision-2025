package com.java_revision;

public class Employee {
    private String name;
    private int id;
    private int salary;
 
    public Employee(String name, int id, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int calculateTotalSalary() {
        return this.getSalary();
    }

}
